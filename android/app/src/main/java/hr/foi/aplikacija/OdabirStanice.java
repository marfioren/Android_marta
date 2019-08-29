package hr.foi.aplikacija;
import hr.foi.alarm.ExampleService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

public class OdabirStanice extends AppCompatActivity{

    protected  void provjerir(){
        RadioButton rT = findViewById(R.id.OdabirTeks);
        RadioButton rG = findViewById(R.id.OdabirGraf);
        if(rT.isChecked()){
            this.formatIspisa=1;

        }
        else{
            this.formatIspisa=0;
        }

    }
    int formatIspisa;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_stanice);


        Intent serviceIntent=new Intent(this, ExampleService.class);

        startService(serviceIntent);

    }
   private void pokreniManager(String imeStanice){

       ModuleManager mm= ModuleManager.getInstance();
       ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
       mm.setActivity(this);
       DohvaćanjePodataka podaci= new DohvaćanjePodataka();
       podaci.ImeStanice(imeStanice);
       podaci.dohvatiPodatke();
       mm.pokreniModul(parent, podaci.pod, podaci.podv, this.formatIspisa);

   }
    public void Stanica1_podaci(View view) {
        provjerir();
        setContentView(R.layout.fragmenti);
        pokreniManager("12station34-12station34-12testuuid");
    }

    public void Stanica2_podaci(View view) {
        provjerir();
        setContentView(R.layout.fragmenti);
        pokreniManager("ca4bb982-ceb0-4c07-b9f2-848351928903");

    }

    public void Stanica3_podaci(View view) {
        provjerir();
        setContentView(R.layout.fragmenti);
        pokreniManager("c76a33ba-8f0e-43b8-a8b8-508fa8ed49a0");


    }
}



