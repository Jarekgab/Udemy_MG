package pl.nauka.jarek.udemy_mg;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import pl.nauka.jarek.udemy_mg.adapter.ShoppingListAdapter;
import pl.nauka.jarek.udemy_mg.model.ShoppingListElement;

public class ShoppingListActivity extends AppCompatActivity {


    @BindView(R.id.itemList)
    ListView itemList;
    @BindView(R.id.itemSpinner)
    Spinner itemSpinner;
    @BindView(R.id.deleteButton)
    ImageButton deleteButton;
    @BindView(R.id.itemName_ET)
    BootstrapEditText itemName;
    @BindView(R.id.deleteItemButton)
    ImageButton deleteItemButton;


    //private List<ShoppingListElement> listItems;     //lista zwykła
    private List<String> spinnerItems;  //lista rozwijana

    private static final String LIST_ITEMS_KEY = "LIST_ITEMS_KEY";
    private static final String SPINNER_ITEMS_KEY = "SPINNER_ITEMS_KEY";
    private static final String shopping_list_key = "SHOPPING_LIST_KEY";
    private ShoppingListAdapter listAdapter; //łączy 2 różne interfejsy: List<String> listItems i ListView itemList
    private ArrayAdapter<String> spinnerAdapter; //tutaj wystarczy zwykły adapter

    private List<List<ShoppingListElement>> spinnerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TypefaceProvider.registerDefaultIconSets();
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //listItems = new ArrayList<>();

        spinnerItems = new ArrayList<>();

        if (spinnerItems.isEmpty()){
            spinnerItems.add("Lista główna");
            spinnerItems.add("Lista zakupów 2");
            spinnerList = new ArrayList<>();
            spinnerList.add(new ArrayList<ShoppingListElement>());
            spinnerList.add(new ArrayList<ShoppingListElement>());
        }


        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spinnerItems);
        itemSpinner.setAdapter(spinnerAdapter);

        itemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {       //przenoszenie pozycji ze spinnera do listy zwykłej

                listAdapter = new ShoppingListAdapter(ShoppingListActivity.this, R.layout.row_shopping_list, spinnerList.get(position));
                itemList.setAdapter(listAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemName.getText() != null && !itemName.getText().toString().trim().isEmpty() && !itemName.getText().toString().equals("Podaj nazwę produktu")) {

                    ShoppingListElement shoppingListElement = new ShoppingListElement(itemSpinner.getSelectedItem().toString(), itemName.getText().toString(), Color.BLACK, false);
                    itemName.setText("");

                    spinnerList.get(itemSpinner.getSelectedItemPosition()).add(shoppingListElement);

//                    if (itemSpinner.getSelectedItem().equals("Lista zakupów 1")){
//                        spinnerList.get(0).add(shoppingListElement);
//                    }
//
//                    if (itemSpinner.getSelectedItem().equals("Lista zakupów 2")){
//                        spinnerList.get(1).add(shoppingListElement);
//                    }
//
//                    if (itemSpinner.getSelectedItem().equals("Lista zakupów 3")){
//                        spinnerList.get(2).add(shoppingListElement);
//                    }

                    listAdapter.notifyDataSetChanged();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//
//        SharedPreferences sp = getPreferences(MODE_PRIVATE);
//        String newListItemsString = sp.getString(LIST_ITEMS_KEY, null);     //String z listItems
//        String newSpinnerItemsString = sp.getString(SPINNER_ITEMS_KEY, null);     //String z spinnerItems
//        Gson gson = new Gson();
//        ArrayList newListItems = gson.fromJson(newListItemsString, new TypeToken<ArrayList>() {
//        }.getType());              //zamiana Stringa na ArrayList
//        ArrayList newSpinnerItems = gson.fromJson(newSpinnerItemsString, new TypeToken<ArrayList>() {
//        }.getType());        //zamiana Stringa na ArrayList
//
//        if (newListItems != null) {
//            listItems = newListItems;
//        }
//
//        if (newSpinnerItems != null) {
//            spinnerItems = newSpinnerItems;
//        }


        listAdapter = new ShoppingListAdapter(this, R.layout.row_shopping_list, spinnerList.get(0));
        itemList.setAdapter(listAdapter);   //ustawienie adaptera na itemList //wrzucenie danych do zwyklej listy w aplikacji za pomocą ShoppingListAdapter
    }


    @Override
    protected void onPause() {
        super.onPause();

//        SharedPreferences sp = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//
//        Gson gson = new Gson();
//        editor.putString(LIST_ITEMS_KEY, gson.toJson(listItems));           //Gson zamienia na String
//        editor.putString(SPINNER_ITEMS_KEY, gson.toJson(spinnerItems));     //Gson zamienia na String
//        editor.apply();                                                     //zapis równoległy bez blokady wątku
    }

    @OnClick(R.id.deleteButton)
    public void onClickDelete() {

        List<ShoppingListElement> selectedView = spinnerList.get(itemSpinner.getSelectedItemPosition());
        for (int i = 0; i < selectedView.size(); i++) {
            if (selectedView.get(i).getClassChecked() == true) {
                selectedView.remove(i);
                i--;
            }
        }
        listAdapter.notifyDataSetChanged();
    }



    @OnFocusChange(R.id.itemName_ET)
    void onFocusChanged(boolean focused) {

        if (focused == true) {
            itemName.setText("");
        } else {
            itemName.setText("Podaj nazwę produktu");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_list_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_new_list) {

            createDialog();
            return true;
        }

        if (id == R.id.action_remove_list) {

            if (!spinnerItems.isEmpty() && itemSpinner.getSelectedItemPosition() > 0){
                spinnerItems.remove(itemSpinner.getSelectedItemPosition());
                spinnerList.remove(itemSpinner.getSelectedItemPosition());

                itemSpinner.setSelection(0);
                listAdapter = new ShoppingListAdapter(ShoppingListActivity.this, R.layout.row_shopping_list, spinnerList.get(0));
                itemList.setAdapter(listAdapter);

            }else if (!spinnerItems.isEmpty() && itemSpinner.getSelectedItemPosition() == 0){

                spinnerList.get(itemSpinner.getSelectedItemPosition()).clear();
                itemSpinner.setSelection(0);
                listAdapter = new ShoppingListAdapter(ShoppingListActivity.this, R.layout.row_shopping_list, spinnerList.get(0));
                itemList.setAdapter(listAdapter);

//                Toast.makeText(this,"Nie można usuwać głównej listy", Toast.LENGTH_LONG).show();

                Toast toast = Toast.makeText(this, "Nie można usuwać głównej listy", Toast.LENGTH_LONG);
                View view = toast.getView();

                view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                TextView text = view.findViewById(android.R.id.message);
                text.setTextColor(Color.WHITE);
                toast.show();
            }
            listAdapter.notifyDataSetChanged();
            spinnerAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.input_box);
        TextView dialogListNameTV = (TextView) dialog.findViewById(R.id.listNameTextView);
        final EditText dialogListNameET = (EditText) dialog.findViewById(R.id.listNameEditText);

        dialogListNameTV.setText("Nazwa listy");
        dialogListNameTV.setTextColor(Color.WHITE);

        Button cancelButton= (Button) dialog.findViewById(R.id.cancelButton);
        Button saveButton= (Button) dialog.findViewById(R.id.saveButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerItems.add(dialogListNameET.getText().toString());
                spinnerList.add(new ArrayList<ShoppingListElement>());
                
                int newField = spinnerItems.size()-1;

                itemSpinner.setSelection(newField);
                listAdapter = new ShoppingListAdapter(ShoppingListActivity.this, R.layout.row_shopping_list, spinnerList.get(newField));
                itemList.setAdapter(listAdapter);

                dialog.dismiss();
                Toast.makeText(ShoppingListActivity.this,"Dodano nową liste: " + dialogListNameET.getText().toString(), Toast.LENGTH_LONG).show();

            }
        });
        dialog.show();
    }
}

