package hr.foi.alarm;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Podaci extends Service {
    String con;
    String JSON_STRING;
    AlertDialog alertDialog;

    public void onCreate(){
        super.onCreate();

    }



    public String onStartCommand(String... params) {
        String type = params[0];
        String user = params[1];
        String pass = params[2];
        String login_url = "http://mjerenje.info/services/test_podaci.php";

        try {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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
            return con;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
