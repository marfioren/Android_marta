package hr.foi.tektprikaz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hr.foi.interfaces.interfaceModuli;


public class tekstPrikaz extends Fragment implements interfaceModuli{
    List<String> pod = new ArrayList<String>();
    List<String> podv = new ArrayList<String>();
    AppCompatActivity activity;
    public void setData(List<String> pod, List<String> podv){
        this.pod=pod;
        this.podv=podv;
    }
    @Override
    public View vratiPogled(ViewGroup container) {

        return getLayoutInflater().inflate(R.layout.tekst_prikaz, container, false);
    }
    @Override
    public Fragment getFragment() {
        return this;
    }
    @Override
    public String getName(Context context) {
        return "bla";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tekst_prikaz, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        String[] podaci = pod.toArray(new String[pod.size()]);
        String[] podaciv = podv.toArray(new String[podv.size()]);
        TextView ispisTeksta= view.findViewById(R.id.prikaz);
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
