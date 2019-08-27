package hr.foi.interfaces;
import hr.foi.grafoviprikaz.PrikazGrafova;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PokretanjeModula extends AppCompatActivity{
    String ime;
    public void SetImeStanice(String ime){

        this.ime=ime;
    }
    public void pokreni(){
        Intent intent = new Intent(this, PrikazGrafova.class);
        intent.putExtra("imeStanice",this.ime);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Intent intent = getIntent();
        ime = intent.getStringExtra("imeStanice");


    }
}
