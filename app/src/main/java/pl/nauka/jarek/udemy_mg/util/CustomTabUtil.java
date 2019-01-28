package pl.nauka.jarek.udemy_mg.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import pl.nauka.jarek.udemy_mg.R;

public class CustomTabUtil {

    @SuppressLint("ResourceAsColor")
    public static void openUrl(Context context, String url){

        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        CustomTabsIntent customTabsIntent = intentBuilder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));

    }
}
