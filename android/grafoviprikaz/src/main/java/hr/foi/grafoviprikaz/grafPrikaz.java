package hr.foi.grafoviprikaz;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import hr.foi.interfaces.interfaceModuli;

public class grafPrikaz extends Fragment implements interfaceModuli{
    List<String> pod = new ArrayList<String>();
    List<String> podv = new ArrayList<String>();
    private LineChart[] mCharts=new LineChart[2];
    private int[] mColors=new int[]{
            Color.rgb(137, 236, 81), Color.rgb(240, 230, 30), Color.rgb(89, 199, 250),
            Color.rgb(250, 104, 119)
    };
    public void setData(List<String> pod, List<String> podv){
        this.pod=pod;
        this.podv=podv;


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

        return inflater.inflate(R.layout.prikaz_grafova, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mCharts[0]=(LineChart) view.findViewById(R.id.chart1);
        mCharts[1]=(LineChart) view.findViewById(R.id.chart2);


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
