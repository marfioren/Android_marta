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
            this.formatIspisa="Tekst";

        }
        else{
            this.formatIspisa="Graf";
        }

    }
    String formatIspisa;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabir_stanice);


        Intent serviceIntent=new Intent(this, ExampleService.class);

        startService(serviceIntent);

    }

    public void Stanica1_podaci(View view) {
         provjerir();
        setContentView(R.layout.fragmenti);
        ModuleManager mm= ModuleManager.getInstance();

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);
mm.setActivity(this);
        DohvaćanjePodataka podaci= new DohvaćanjePodataka();
        podaci.ImeStanice("c76a33ba-8f0e-43b8-a8b8-508fa8ed49a0");
        podaci.dohvatiPodatke();

mm.pokreniModul(parent, podaci.pod, podaci.podv);

    }

    public void Stanica2_podaci(View view) {
        provjerir();

    }

    public void Stanica3_podaci(View view) {
        provjerir();


    }
}



