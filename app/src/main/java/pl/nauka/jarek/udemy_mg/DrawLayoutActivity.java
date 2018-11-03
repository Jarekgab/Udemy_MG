package pl.nauka.jarek.udemy_mg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawLayoutActivity extends AppCompatActivity {

    private DrawView drawview;

    @BindView(R.id.clearButton)
    Button clearButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_layout);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawview = (DrawView) findViewById(R.id.drawView);
    }

    @OnClick(R.id.clearButton)
    public void onClickClearButton() {
        drawview.clearCanvas();
    }
}
