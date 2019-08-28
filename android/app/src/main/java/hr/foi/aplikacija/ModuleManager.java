package hr.foi.aplikacija;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import  hr.foi.interfaces.interfaceModuli;
import hr.foi.grafoviprikaz.grafPrikaz;
import hr.foi.tektprikaz.tekstPrikaz;
public class ModuleManager {
    private AppCompatActivity activity;
    private List<interfaceModuli> listaModula;
    private static ModuleManager instance;

    private ModuleManager(){
        listaModula = new ArrayList<>();
        listaModula.add(new grafPrikaz());
        listaModula.add(new tekstPrikaz());

    }
    public static ModuleManager getInstance()
    {
        if (instance == null)
            instance = new ModuleManager();

        return instance;
    }
    public void setActivity(AppCompatActivity activity){
   this.activity=activity;

    }



  public void pokreniModul(ViewGroup parent, List<String> pod, List<String> podv) {
      interfaceModuli selectedItem = listaModula.get(0);


      selectedItem.setData(pod, podv);

      FragmentManager fm = activity.getSupportFragmentManager();

      fm.beginTransaction()
              .replace(R.id.fragmenttest, selectedItem.getFragment())
              .commit();

  }

}
