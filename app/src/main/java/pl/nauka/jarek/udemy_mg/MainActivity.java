package pl.nauka.jarek.udemy_mg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.shoppingListButton)
    Button shoppingListButton;
    @BindView(R.id.drawButton)
    Button drawButton;
    @BindView(R.id.drawButtonLayout)
    Button drawButtonLayout;
    @BindView(R.id.apiButton)
    Button apiButton;
    @BindView(R.id.apiExampleButton)
    Button apiExampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick(R.id.shoppingListButton)
    void onClickShoppingList() {
        Intent intent = new Intent(this, ShoppingListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.drawButton)
    public void onClickDrawButton() {
        Intent intent = new Intent(this, DrawActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.drawButtonLayout)
    public void onClickDrawButtonLayout() {
        Intent intent = new Intent(this, DrawLayoutActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.apiButton)
    public void onClickApiButton() {
        Intent intent = new Intent(this, ApiActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.apiExampleButton)
    public void onClickApiExampleButton() {
        Intent intent = new Intent(this, ApiActivityExample.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
