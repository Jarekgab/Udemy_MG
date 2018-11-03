package pl.nauka.jarek.udemy_mg;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApiActivityExample extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.getHtmlDataButton)
    Button button;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_example);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ThreadClass().execute("URL", "URL2", "URL3");        //uruchomienie wielowątkowości
            }
        });
    }

    private class ThreadClass extends AsyncTask<String, Integer, Float> {

        @Override
        protected void onPreExecute() {     //metoda działa w wątku użytkowmika, np. pzygot. statusBar, ProgressBar
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            textView.setText("" + System.currentTimeMillis());      //pokazuje aktualny czas w ms
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {   //metoda działa w wątku użytkowmika
                                                             //informacja, która w parametach values jest przechowywana
                                                            //np aktualizacja ProgressBar
                                                            //zbiera infomacje z metody doInBackground
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);

        }

        @Override
        protected Float doInBackground(String... params) {      //metoda działa w innym wątku

            String url = params[0];                            //dostęp do parametrów przekazanych w new ThreadClass() (URL..)

            textView.setText(textView.getText() + "     " + "" + System.currentTimeMillis() + "!!");

            String a = url;
            for (int i = 0; i < 10000; i++) {
                a = a + url;

                if (i > 0 && i % 100 == 0)
                publishProgress(i / 100);
            }
            return 0.0F;        //parametr ostaniej zmiennej, czyli  new ThreadClass().execute("URL", "URL2", "URL3");
        }

        @Override
        protected void onPostExecute(Float f) {          //metoda, która działa po zakończeniu innyc wątków np. ukrycia ProgressBar
            progressBar.setVisibility(View.INVISIBLE);
            textView.setText(textView.getText() +"     "+ "" + System.currentTimeMillis());
            super.onPostExecute(f);
        }

        @Override
        protected void onCancelled(Float f) {           //działa na wywołanie cancel() w metodzie doInBackground
            super.onCancelled(f);
        }
    }



}
