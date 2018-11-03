package pl.nauka.jarek.udemy_mg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class DrawLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





//        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
//        requestWindowFeature(FEATURE_NO_TITLE);         //usunięcie z widoku paska z siecią, godziną itp.
//
        DrawView drawView = new DrawView(this);
        setContentView(drawView);
//        drawView.requestFocus();



    }
}
