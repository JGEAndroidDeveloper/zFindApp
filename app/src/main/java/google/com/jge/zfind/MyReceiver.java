package google.com.jge.zfind;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gaming Dethklok on 9/3/2015.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo networkInfo;
        Toast.makeText(context, "Connectivity has changed, this can result in the app not retrieving data.", Toast.LENGTH_SHORT).show();
        /**if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {

                Toast.makeText(context, "Wifi has changed", Toast.LENGTH_SHORT).show();
           int  Info = ConnectivityManager.TYPE_MOBILE;

        } else if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
             networkInfo =
                    intent.getParcelableExtra(ConnectivityManager.CONNECTIVITY_ACTION);
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI &&
                    ! networkInfo.isConnected()) {
                // Wifi is disconnected
                Log.d("Inetify", "Wifi is disconnected: " + String.valueOf(networkInfo));
                Toast.makeText(context, "Disconnect.", Toast.LENGTH_SHORT).show();
            }
        }*/

    }


}
