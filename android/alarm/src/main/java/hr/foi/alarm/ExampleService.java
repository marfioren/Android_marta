package hr.foi.alarm;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.AlarmClock;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

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
import java.util.Timer;
import java.util.TimerTask;

import static hr.foi.alarm.App.CHANNEL_ID;

public class ExampleService extends Service {
    String JSON_STRING;
    JSONArray jsonarray;
    String con;
    String tekst;
    List<String> provjera_podaci = new ArrayList<String>();
    MediaPlayer myPlayer;
    String json_url;
    @Override
    public void onCreate(){
        super.onCreate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        myPlayer = MediaPlayer.create(this,
                Settings.System.DEFAULT_RINGTONE_URI);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input =intent.getStringExtra("InputExtra");


        Intent notificationIntent= new Intent(this, MainActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, notificationIntent, 0);
        final NotificationCompat.Builder builder= new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(con)
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent);

        Timer timer = new Timer();
        //Set the schedule function

        timer.scheduleAtFixedRate(new TimerTask() {

                                      @Override
                                      public void run() {



                                          builder.setContentTitle(tekst);

                                          try {

                                              String urli = "http://mjerenje.info/services/zadnjiPodaci.php";
                                              URL url = new URL(urli);
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
                                              jsonarray = new JSONArray(con);
                                              for (int i = 0; i < jsonarray.length(); i++) {
                                                  JSONObject jsonobject = jsonarray.getJSONObject(i);
                                                  String humidity = jsonobject.getString("humidity");
                                                  String temperature = jsonobject.getString("temperature");
                                                 provjera_podaci.add(temperature);
                                                 provjera_podaci.add(humidity);
                                              }


                                          } catch (MalformedURLException e) {
                                              e.printStackTrace();
                                          } catch (IOException e) {
                                              e.printStackTrace();
                                          } catch (JSONException e) {
                                              e.printStackTrace();
                                          }

                                          try {
                                              builder.setStyle(new NotificationCompat.InboxStyle()

                                                      .addLine("Stanica 1:")
                                                      .addLine("  vlažnost: "+jsonarray.getJSONObject(0).getString("humidity")+"  temperatura: "+jsonarray.getJSONObject(0).getString("temperature"))

                                                      .addLine("Stanica 2:")
                                                      .addLine("  vlažnost: "+jsonarray.getJSONObject(1).getString("humidity")+"  temperatura: "+jsonarray.getJSONObject(1).getString("temperature"))

                                                      .addLine("Stanica 3:")

                                                      .addLine("  vlažnost: "+jsonarray.getJSONObject(2).getString("humidity")+"  temperatura: "+jsonarray.getJSONObject(2).getString("temperature")));
                                          } catch (JSONException e) {
                                              e.printStackTrace();
                                          }
                                          builder.setSound(null, 0);

                                          Notification notification=builder.build();


                                          startForeground(1, notification);


                                      }
                                  },
                0, 1000);

        return START_NOT_STICKY;




       }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
