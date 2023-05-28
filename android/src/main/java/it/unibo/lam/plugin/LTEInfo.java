package it.unibo.lam.plugin;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;
import android.util.Log;

public class LTEInfo {

    public static int getLTESignalStrength(Context context) {
        if(!checkPermission(context)){
            return -1;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfos = telephonyManager.getAllCellInfo(); //This will give info of all sims present inside your mobile

        if(cellInfos!= null){
            for(CellInfo c : cellInfos){
                if(c.isRegistered()){
                    return c.getCellSignalStrength().getDbm();
                }
            }
        }
        if(cellInfos == null)
            Log.d("LTEInfo", "cellInfos is null");
        return 0;
    }

    private static boolean checkPermission(Context context) {
        int result1 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int resutl2 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        return result1 == android.content.pm.PackageManager.PERMISSION_GRANTED && resutl2 == android.content.pm.PackageManager.PERMISSION_GRANTED;
    }


}
