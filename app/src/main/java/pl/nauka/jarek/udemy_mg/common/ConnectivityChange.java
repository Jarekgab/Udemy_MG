package pl.nauka.jarek.udemy_mg.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class ConnectivityChange extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {

            Intent local = new Intent();
            local.setAction("service.to.activity.transfer");
            local.putExtra("CONNECTIVITY", "test");
            context.sendBroadcast(local);
        }
    }
}
