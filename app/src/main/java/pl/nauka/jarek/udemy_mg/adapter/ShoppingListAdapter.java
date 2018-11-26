package pl.nauka.jarek.udemy_mg.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import pl.nauka.jarek.udemy_mg.R;
import pl.nauka.jarek.udemy_mg.model.ShoppingListElement;


public class ShoppingListAdapter extends ArrayAdapter {
    private List<ShoppingListElement> listItems;
    private Context context;
    private int resource;


    public ShoppingListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ShoppingListElement> listItems) {
        super(context, resource, listItems);
        this.context = context;
        this.resource = resource;
        this.listItems = listItems;

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
        final LinearLayout rowShoppingListLL = rowView.findViewById(R.id.row_shopping_list_ll);

        name.setText(listItems.get(position).getName());
        name.setTextColor(listItems.get(position).getColor());

        final CheckBox selected = rowView.findViewById(R.id.selected_CB);
        selected.setChecked(listItems.get(position).getClassChecked());

        rowShoppingListLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected.isChecked() == false){
                    selected.setChecked(true);
                }else {
                    selected.setChecked(false);
                }

                if (selected.isChecked() == true) {

                    listItems.get(position).setRed();
                    name.setText(listItems.get(position).getName());
                    name.setTextColor(listItems.get(position).getColor());

                    listItems.get(position).setClassChecked(true);
                    selected.setChecked(listItems.get(position).getClassChecked());
                } else {
                    listItems.get(position).setBlack();
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
