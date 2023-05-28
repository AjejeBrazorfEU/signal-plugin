package it.unibo.lam.plugin;


import android.Manifest;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import androidx.core.content.ContextCompat;

public class WiFiInfo {

    public static int getWiFiSignalStrength(Context context){
        if (!checkPermission(context)) {
            return -1;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getRssi();
    }

    private static boolean checkPermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_WIFI_STATE);
        return result == android.content.pm.PackageManager.PERMISSION_GRANTED;
    }
}
