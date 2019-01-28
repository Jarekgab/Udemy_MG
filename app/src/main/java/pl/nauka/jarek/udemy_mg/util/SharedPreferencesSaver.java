package pl.nauka.jarek.udemy_mg.util;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import pl.nauka.jarek.udemy_mg.model.ShoppingListElement;

public class SharedPreferencesSaver {

    private static final String LIST_ITEMS_KEY = "LIST_ITEMS_KEY";
    private static final String SPINNER_ITEMS_KEY = "SPINNER_ITEMS_KEY";

    public static void saveTo(List<List<ShoppingListElement>> spinnerList, List<String> spinnerItems, SharedPreferences sp){

        Gson gson = new Gson();
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(LIST_ITEMS_KEY, gson.toJson(spinnerList));           //Gson zamienia na String
        editor.putString(SPINNER_ITEMS_KEY, gson.toJson(spinnerItems));     //Gson zamienia na String
        editor.apply();                                                     //zapis równoległy bez blokady wątku
    }

    public static List<List<ShoppingListElement>> loadSpinnerL(SharedPreferences sp){

        String newSpinnerListString = sp.getString(LIST_ITEMS_KEY, null);     //String z SpinnerList
        Gson gson = new Gson();

        return gson.fromJson(newSpinnerListString, new TypeToken<List<List<ShoppingListElement>>>() {}.getType());
        //zamiana Stringa na List
    }

    public static List<String> loadSpinnerI(SharedPreferences sp){

        String newSpinnerItemsString = sp.getString(SPINNER_ITEMS_KEY, null);     //String z spinnerItems
        Gson gson = new Gson();

        return gson.fromJson(newSpinnerItemsString, new TypeToken<List<String>>() {}.getType());
        //zamiana Stringa na List
    }
}
