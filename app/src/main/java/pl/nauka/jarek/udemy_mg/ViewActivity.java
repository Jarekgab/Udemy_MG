package pl.nauka.jarek.udemy_mg;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.digitalzombielab.dayview.DayView;

public class ViewActivity extends AppCompatActivity {


    @BindView(R.id.dayView)
    DayView dayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dayView.setBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dayView.setBorderColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dayView.setCardBackgroundColor(Color.WHITE);
        dayView.setTextColor(Color.BLACK);

        dayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayView.setDate(new Date(2017, 1, 1));
            }
        });

        dayView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                dayView.setDate(new Date(year, month, day));
                return true;
            }
        });


    }


}



