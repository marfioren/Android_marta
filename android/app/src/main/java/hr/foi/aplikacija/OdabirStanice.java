package hr.foi.aplikacija;
import hr.foi.alarm.ExampleService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import hr.foi.interfaces.PokretanjeModula;
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

        Intent intent = new Intent(this, PokretanjeModula.class);
            Bundle extras = new Bundle();
            extras.putString("imeStanice","ca4bb982-ceb0-4c07-b9f2-848351928903");
            extras.putString("vrsta", this.formatIspisa);
            intent.putExtras(extras);
        startActivity(intent);

    }

    public void Stanica2_podaci(View view) {
        provjerir();
        Intent intent = new Intent(this, PokretanjeModula.class);
        Bundle extras = new Bundle();
        extras.putString("imeStanice","ca4bb982-ceb0-4c07-b9f2-848351928903");
        extras.putString("vrsta", this.formatIspisa);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void Stanica3_podaci(View view) {
        provjerir();
        Intent intent = new Intent(this, PokretanjeModula.class);
        Bundle extras = new Bundle();
        extras.putString("imeStanice","c76a33ba-8f0e-43b8-a8b8-508fa8ed49a0");
        extras.putString("vrsta", this.formatIspisa);
        intent.putExtras(extras);
        startActivity(intent);

    }
}



