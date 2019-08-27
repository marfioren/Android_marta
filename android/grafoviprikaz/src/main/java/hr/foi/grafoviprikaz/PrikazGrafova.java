package hr.foi.grafoviprikaz;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PrikazGrafova extends AppCompatActivity {

    JSONArray jsonarray;
    String con;

    String imeStanice;
    List<String> pod = new ArrayList<String>();
    List<String> podv = new ArrayList<String>();
    String BrojStanice;
    String bs;
    private LineChart[] mCharts=new LineChart[2];
    private int[] mColors=new int[]{
            Color.rgb(137, 236, 81), Color.rgb(240, 230, 30), Color.rgb(89, 199, 250),
            Color.rgb(250, 104, 119)
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prikaz_grafova);
        Intent intent = getIntent();
        imeStanice = intent.getStringExtra("imeStanice");
//        BrojStanice = extras.getString("idGumba");
        //      Pattern p = Pattern.compile("\\d+");
        //    Matcher m = p.matcher(BrojStanice);
        //  while(m.find()) {
        // bs=m.group();
        //}
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
            mCharts[0]=(LineChart) findViewById(R.id.chart1);
            mCharts[1]=(LineChart) findViewById(R.id.chart2);


            String[] podaci = pod.toArray(new String[pod.size()]);
            String[] podaciv = podv.toArray(new String[podv.size()]);
            for(int i=0; i<mCharts.length; i++){
                if(i==0){
                    LineData data= getData(podaci, i);
                    setupChart(mCharts[i], data, mColors[i]);
                }

                else{
                    LineData data2= getData(podaciv, i);
                    setupChart(mCharts[i], data2, mColors[i]);
                }


            }

        }

    }

    private LineData getData(String[] count, int brojGrafa) {
        ArrayList<Entry> yVals=new ArrayList<>();
        String[] polje = count;
        if(brojGrafa==0){
            for(int i=0; i<24; i++){

                float val=(float) (Float.parseFloat(polje[i]));
                yVals.add(new Entry(i, val));
            }
            LineDataSet set1=new LineDataSet(yVals, "temperatura");
            set1.setLineWidth(3f);
            set1.setCircleRadius(5f);
            set1.setCircleHoleRadius(2.5f);
            set1.setColor(Color.LTGRAY);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.WHITE);
            set1.setDrawValues(false);

            LineData data= new LineData(set1);
            return data;
        }

        else{
            for(int i=0; i<24; i++){

                float val=(float) (Float.parseFloat(polje[i]));
                yVals.add(new Entry(i, val));
            }
            LineDataSet set1=new LineDataSet(yVals, "vlažnost");
            set1.setLineWidth(3f);
            set1.setCircleRadius(5f);
            set1.setCircleHoleRadius(2.5f);
            set1.setColor(Color.LTGRAY);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.WHITE);
            set1.setDrawValues(false);

            LineData data= new LineData(set1);
            return data;
        }






    }

    private void setupChart(LineChart chart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(color);
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);

        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(false);
        chart.setBackgroundColor(color);
        chart.setViewPortOffsets(10,0,10,0);
        chart.setData(data);
    }


}
