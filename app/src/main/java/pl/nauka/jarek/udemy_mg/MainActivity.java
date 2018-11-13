package pl.nauka.jarek.udemy_mg;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    @BindView(R.id.viewButton)
    Button viewButton;
    @BindView(R.id.infoButton)
    Button infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

    @OnClick(R.id.viewButton)
    public void onClickViewButton() {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.infoButton)
    public void onClickInfoButton() {
//        String url = "https://www.udemy.com/praktyczny-podstawowy-kurs-programowania-android/learn/v4/content";
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse(url));
//        startActivity(intent);

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_shopping_list) {
            startActivity(new Intent(this, ShoppingListActivity.class));

        } else if (id == R.id.nav_draw) {
            startActivity(new Intent(this, DrawActivity.class));

        } else if (id == R.id.nav_draw_layout) {
            startActivity(new Intent(this, DrawLayoutActivity.class));

        } else if (id == R.id.nav_api) {
            startActivity(new Intent(this, ApiActivity.class));

        } else if (id == R.id.nav_api_example) {
            startActivity(new Intent(this, ApiActivityExample.class));

        } else if (id == R.id.nav_view) {
            startActivity(new Intent(this, ViewActivity.class));

        } else if (id == R.id.nav_info) {
            startActivity(new Intent(this, AboutActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
