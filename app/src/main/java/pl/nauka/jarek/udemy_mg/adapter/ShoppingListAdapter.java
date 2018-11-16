package pl.nauka.jarek.udemy_mg.adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import pl.nauka.jarek.udemy_mg.R;
import pl.nauka.jarek.udemy_mg.model.NameColor;


public class ShoppingListAdapter extends ArrayAdapter {
    private List<NameColor> listItems;
    private Context context;
    private int resource;
    ArrayAdapter<String> spinnerAdapter;
    List<String> spinnerItems;
    Spinner spinner;
    ListView itemList;


    public ShoppingListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<NameColor> listItems, ArrayAdapter<String> spinnerAdapter, List<String> spinnerItems, Spinner spinner, ListView iteamList) {
        super(context, resource, listItems);
        this.context = context;
        this.resource = resource;
        this.listItems = listItems;
        this.spinnerAdapter = spinnerAdapter;
        this.spinnerItems = spinnerItems;
        this.spinner = spinner;
        this.itemList = iteamList;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        /*
        * //Tworzenie pliku XML układu odpowiedniego obiektu//
        * Należy pobrać LayoutInflater, który jest już podłączony do bieżącego kontekstu
        * i poprawnie skonfigurowany dla urządzenia:
        *
        * LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        */

        View rowView = inflater.inflate(R.layout.row_shopping_list, parent, false);
        //ustawienie widoku naszego wiersza
        //w rowView mamy dostęp do elementów row_shopping_list

        final TextView name = rowView.findViewById(R.id.name_ET);

        name.setText(listItems.get(position).getName());
        name.setTextColor(listItems.get(position).getColor());

        final CheckBox selected = rowView.findViewById(R.id.selected_CB);
        selected.setChecked(listItems.get(position).getClassChecked());

        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected.isChecked() == true) {

                    listItems.get(position).setColor(Color.RED);
                    name.setText(listItems.get(position).getName());
                    name.setTextColor(listItems.get(position).getColor());

                    listItems.get(position).setClassChecked(true);
                    selected.setChecked(listItems.get(position).getClassChecked());
                } else {
                    listItems.get(position).setColor(Color.BLACK);
                    name.setText(listItems.get(position).getName());
                    name.setTextColor(listItems.get(position).getColor());

                    listItems.get(position).setClassChecked(false);
                    selected.setChecked(listItems.get(position).getClassChecked());
                }
                ShoppingListAdapter.super.notifyDataSetChanged();
            }
        });

        return rowView;
}


}
//TODO 1.Usunięcie pustego miejsca w spinner
