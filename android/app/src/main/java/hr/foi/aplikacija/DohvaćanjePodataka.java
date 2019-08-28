package hr.foi.aplikacija;

import android.os.AsyncTask;

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
import java.util.ArrayList;
import java.util.List;

public class DohvaćanjePodataka {

    JSONArray jsonarray;
    String con;

    String imeStanice;
    List<String> pod = new ArrayList<String>();
    List<String> podv = new ArrayList<String>();
    String BrojStanice;
    String bs;

    public void ImeStanice(String ime) {

        this.imeStanice = ime;

    }

    public void dohvatiPodatke(){
        String JSON_STRING;
        String json_url;
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




    }


    public List<String> vratiPodatkeOTemperaturi(){

        return this.pod;
    }
    public List<String> vratiPodatkeOVlaznosti(){

        return this.podv;
    }
}
