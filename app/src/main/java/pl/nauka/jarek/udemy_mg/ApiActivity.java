package pl.nauka.jarek.udemy_mg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.nauka.jarek.udemy_mg.common.Connectivity;
import pl.nauka.jarek.udemy_mg.model.User;

public class ApiActivity extends AppCompatActivity{

    @BindView(R.id.htmlDataButton)
    Button htmlDataButton;
    @BindView(R.id.resoultTextView)
    TextView resultTextView;
    @BindView(R.id.jsonDataButton)
    Button jsonDataButton;
    @BindView(R.id.isConnectedTextView)
    TextView isConnectedTextView;
    @BindView(R.id.isConnectedWifiTextView)
    TextView isConnectedWifiTextView;
    @BindView(R.id.isConnectedMobileTextView)
    TextView isConnectedMobileTextView;
    @BindView(R.id.imageView)
    ImageView imageView;

    private RequestQueue requestQueue;
    BroadcastReceiver updateUIReciver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        ButterKnife.bind(this);

        //Pobieranie danych z ConnectivityChange bez onCreate()
        IntentFilter filter = new IntentFilter();
        filter.addAction("service.to.activity.transfer");

        updateUIReciver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent != null){
                    String s = intent.getStringExtra("CONNECTIVITY");
                    connectivityChange();
                }
            }
        };
        registerReceiver(updateUIReciver, filter);

        connectivityChange();

        requestQueue = Volley.newRequestQueue(this);                                   //kolejka do obsługi sieci, zamiast wielowatkosci

        htmlDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText("");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://jsonplaceholder.typicode.com/users", new Response.Listener<String>() {  //https://www.google.pl/
                    @Override
                    public void onResponse(String response) {           //response wszystkie dane
//                        resultTextView.setText(response);                      //odpowiwedz, że udało się coś pobrać

                        //Liczenie "\"name\": " -> czyli ustalenie wartości skrajnych petli for

                        String in = response;
                        int i = 0;

                        Pattern p = Pattern.compile("\"name\": ");
                        Matcher m = p.matcher(in);
                        while (m.find()) {
                            i++;
                        }

                        //Zapisywanie name

                        String userCut = response;
                        int a = 0;
                        int lp = 1;

                        for (int k = 0; k < i; k++) {

                            if (a == 2) {
                                a = 0;
                            }

                            userCut = userCut.substring(userCut.indexOf("\"name\": ") + 8);
                            String name = userCut.substring(userCut.indexOf("\"") + 1, userCut.indexOf("\","));

                            if (a == 0) {
                                resultTextView.append(lp + ". " + name + "\n");
                                lp++;
                            }
                            a++;
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {            //w przypadku błędu
                        resultTextView.setText("!!!");

                    }
                });
                requestQueue.add(stringRequest);
            }

        });

        final String jsonUrl = "https://jsonplaceholder.typicode.com/users";
        final List<User> users = new ArrayList<>();

        jsonDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText("");
                users.clear();
                JsonArrayRequest jar = new JsonArrayRequest(jsonUrl, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = (JSONObject) response.get(i);
                                Gson gson = new Gson();
                                User u = gson.fromJson(user.toString(), User.class);
                                users.add(u);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i = 0; i < users.size(); i++) {
                            resultTextView.append(i + 1 + ". " + users.get(i).getName() + "\n");
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {            //w przypadku błędu
                                resultTextView.setText("!!!");
                            }
                        }
                );
                requestQueue.add(jar);
            }
        });
    }

    private void connectivityChange() {
        if (Connectivity.isConnected(this)) {
            htmlDataButton.setVisibility(View.VISIBLE);
            jsonDataButton.setVisibility(View.VISIBLE);
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setTextColor(Color.BLACK);
            resultTextView.setText("");
            resultTextView.setTextSize(15);
            imageView.setImageResource(R.drawable.ic_check_circle);
        } else {
            htmlDataButton.setVisibility(View.INVISIBLE);
            jsonDataButton.setVisibility(View.INVISIBLE);
            resultTextView.setTextColor(Color.RED);
            resultTextView.setText("  Sprawdź dostęp do internetu :-/");
            resultTextView.setTextSize(20);
            imageView.setImageResource(R.drawable.ic_remove_circle);
        }

        if (Connectivity.isConnectedWifi(this)) {
            isConnectedWifiTextView.setText("Wifi ON");
        } else {
            isConnectedWifiTextView.setText("Wifi OFF");
        }

        if (Connectivity.isConnectedMobile(this)) {
            isConnectedMobileTextView.setText("Mobile ON");
        } else {
            isConnectedMobileTextView.setText("Mobile OFF");
        }
    }
}
