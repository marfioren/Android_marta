package hr.foi.tektprikaz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
    public Fragment getFragment() {
        return this;
    }
    @Override
    public String getName(Context context) {
        return "bla";
    }

    @Override
    public View vratiPogled(ViewGroup container) {

        return getLayoutInflater().inflate(R.layout.tekst_prikaz, container, false);
    }
}
