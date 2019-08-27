package hr.foi.interfaces;
import hr.foi.grafoviprikaz.PrikazGrafova;
import hr.foi.tektprikaz.PrikazTeksta;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PokretanjeModula extends AppCompatActivity{
    String ime;
    String vrsta;
     protected void pokreniStvaranjeGrafova(){
         Intent intent = new Intent(this, PrikazGrafova.class);
         intent.putExtra("imeStanice",this.ime);
         startActivity(intent);

     }

    protected void pokreniIspisTeksta(){
        Intent intent = new Intent(this, PrikazTeksta.class);
        intent.putExtra("imeStanice",this.ime);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        ime = extras.getString("imeStanice");
        vrsta = extras.getString("vrsta");
        if(vrsta.contains("Tekst")) {
            pokreniIspisTeksta();
        }
        else{
            pokreniStvaranjeGrafova();
        }
    }
}
