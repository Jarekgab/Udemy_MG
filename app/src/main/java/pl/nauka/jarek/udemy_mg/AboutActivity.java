package pl.nauka.jarek.udemy_mg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.nauka.jarek.udemy_mg.util.CustomTabUtil;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.ll_website)
    LinearLayout llWebsite;
    @BindView(R.id.ll_logo)
    LinearLayout llLogo;
    @BindView(R.id.ll_licenses)
    LinearLayout llLicenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.ll_website)
    public void onClickWebsite() {
        CustomTabUtil.openUrl(this, "https://www.udemy.com/praktyczny-podstawowy-kurs-programowania-android/learn/v4/overview");
    }


    @OnClick(R.id.ll_logo)
    public void onClickLogo() {
        CustomTabUtil.openUrl(this, "https://www.udemy.com");
    }

    @OnClick(R.id.ll_licenses)
    public void onClickLicenses() {
        //TODO Zrobić liste licencji

    }
}
