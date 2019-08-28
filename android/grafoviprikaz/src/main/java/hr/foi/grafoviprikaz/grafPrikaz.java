package hr.foi.grafoviprikaz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import hr.foi.interfaces.interfaceModuli;

public class grafPrikaz extends Fragment implements interfaceModuli{
    List<String> pod = new ArrayList<String>();
    List<String> podv = new ArrayList<String>();

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

        return getLayoutInflater().inflate(R.layout.prikaz_grafova, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.prikaz_grafova, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}
