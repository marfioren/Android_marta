package hr.foi.tektprikaz;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.WindowManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrikazTeksta extends AppCompatActivity {




    JSONArray jsonarray;
    String con;

    String imeStanice;
    List<String> pod = new ArrayList<String>();
    List<String> podv = new ArrayList<String>();
    String BrojStanice;
    String bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tekst_prikaz);
        Intent intent = getIntent();
        imeStanice = intent.getStringExtra("imeStanice");

        Podaci_sec();
    }

    public void Podaci_sec(){

        new W_dovlacenje_pod().execute();
    }

    class W_dovlacenje_pod extends AsyncTask<String, Void, String> {

        String JSON_STRING;
        String json_url;


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String urli = "http://mjerenje.info/services/graf_temp.php?imeStanice="+imeStanice;

                URL url = new URL(urli);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {

                    stringBuilder.append(JSON_STRING + "\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                con=stringBuilder.toString().trim();
                jsonarray = new JSONArray(con);

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    pod.add(jsonobject.getString("avgTemp"));
                    podv.add(jsonobject.getString("avgMoist"));




                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }



        @Override

        protected void onPostExecute(String result) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


            String[] podaci = pod.toArray(new String[pod.size()]);
            String[] podaciv = podv.toArray(new String[podv.size()]);
            TextView ispisTeksta= findViewById(R.id.prikaz);
            ispisTeksta.setMovementMethod(new ScrollingMovementMethod());
            ispisTeksta.append("Prosjek temperature po satima u zadnjih 24h"+"\n");
            for(int i=0; i<podaci.length; i++){
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis() - 3600 * 1000*(i+1)));
                ispisTeksta.append(currentDateTimeString);
            ispisTeksta.append(":  "+podaci[i]+"\n");


            }

            ispisTeksta.append("Prosjek vlažnosti po satima u zadnjih 24h"+"\n");

            for(int i=0; i<podaciv.length; i++){
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis() - 3600 * 1000*(i+1)));
                ispisTeksta.append(currentDateTimeString);
                ispisTeksta.append(":  "+podaci[i]+"\n");


            }

        }

    }




}
