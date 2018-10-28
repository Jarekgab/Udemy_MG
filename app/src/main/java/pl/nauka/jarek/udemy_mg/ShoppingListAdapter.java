package pl.nauka.jarek.udemy_mg;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter {
    private List<String> objects;
    private Context context;
    private int resource;
    ArrayAdapter<String> spinnerAdapter;
    List<String> spinnerItems;
    Spinner spinner;


    public ShoppingListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public ShoppingListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects, ArrayAdapter<String> spinnerAdapter, List<String> spinnerItems, Spinner spinner) {
        this(context, resource, objects);
        this.spinnerAdapter = spinnerAdapter;
        this.spinnerItems = spinnerItems;
        this.spinner = spinner;

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

        TextView name = rowView.findViewById(R.id.name_ET);
        name.setText("" + objects.get(position));
        //setText bo ustawilismy @NonNull List objects czyli private List<String> objects;

        CheckBox selected = rowView.findViewById(R.id.selected_CB);
        selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerItems.add(objects.get(position));
                spinnerItems.remove("");             //?
                spinnerItems.add((String) "");          //?
                spinner.setSelection(spinnerItems.indexOf(""));     //?
                spinnerAdapter.notifyDataSetChanged();     //powiadamia adapter o zmienie danych
                objects.remove(position);       //usunięcie elementu z listy
                ShoppingListAdapter.super.notifyDataSetChanged();   //powidaomienie tego adaptera
            }
        });

        return rowView;
    }


}
