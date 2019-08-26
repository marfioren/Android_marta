package hr.foi.aplikacija;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

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

public class Worker extends AsyncTask<String, Void, String> {
    Context con;
    String JSON_STRING;

    AlertDialog alertDialog;
    Worker(Context ctx){
        con=ctx;

    }

    @Override
    protected String doInBackground(String... params) {
        String type= params[0];
        String user=params[1];
        String pass=params[2];
        String login_url="http://mjerenje.info/services/login.php?user="+user+"&pass="+pass;
        if(type.equals("login")){
            try {



                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                InputStream inputStream =httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder stringBuilder=new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null){

                    stringBuilder.append(JSON_STRING+"\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(con).create();

        alertDialog.setTitle("Status logiranja:");
    }

    @Override
    protected void onPostExecute(String result) {
        result = result.replaceAll("[^\\d.]", "");

        String bla=  result.toString();
        if(bla.contains("1213")) {
            Intent intent = new Intent(con, OdabirStanice.class);
            con.startActivity(intent);

        }
        else{

            alertDialog.setMessage("Neuspjeh");
            alertDialog.show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
