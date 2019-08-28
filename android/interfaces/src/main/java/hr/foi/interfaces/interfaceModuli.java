package hr.foi.interfaces;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public interface interfaceModuli {

    public Fragment getFragment();
    public String getName(Context context);
    public void setData(List<String> pod, List<String> podv);
    public View vratiPogled(ViewGroup container);
}
