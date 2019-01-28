package pl.nauka.jarek.udemy_mg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class DrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
            Tworzenie widoku inaczej, czyli bez Layoutu.
        */

        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        requestWindowFeature(FEATURE_NO_TITLE);         //usunięcie z widoku paska z siecią, godziną itp.

        DrawView drawView = new DrawView(this);
        setContentView(drawView);
        drawView.requestFocus();
    }
}
