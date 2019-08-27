package hr.foi.interfaces;
import hr.foi.grafoviprikaz.PrikazGrafova;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class PokretanjeModula extends AppCompatActivity implements PrijenosPodataka{
    String d;
    public void data(String data){
        this.d=data;

    }

    public void pokreni() {
        Intent intent = new Intent(this, PrikazGrafova.class);

        startActivity(intent);
    }
}
