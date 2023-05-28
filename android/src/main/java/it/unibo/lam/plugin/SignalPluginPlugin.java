package it.unibo.lam.plugin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.getcapacitor.PermissionState;


import java.util.ArrayList;
import java.util.List;

@CapacitorPlugin(name = "SignalPlugin", permissions = {
    @Permission(alias = "WIFI", strings = {Manifest.permission.ACCESS_WIFI_STATE}),
    @Permission(alias =  "LTE", strings = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}),
    @Permission(alias = "MICROPHONE", strings = {Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}),
})
public class SignalPluginPlugin extends Plugin {

    private static final int REQUEST_CODE_PERMISSIONS = 1;
    private static final String TAG = "SignalPlugin";

    private SignalPlugin implementation = new SignalPlugin();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void getSignalInfo(PluginCall call) {
        if(getPermissionState("WIFI") != PermissionState.GRANTED ||
                getPermissionState("LTE") != PermissionState.GRANTED ||
                getPermissionState("MICROPHONE") != PermissionState.GRANTED){
            requestAllPermissions(call, "signalPermissionCallback");
        }
        JSObject ret = new JSObject();
        ret.put("value", implementation.getSignalInfo(this.getContext()));
        call.resolve(ret);
    }

    @PermissionCallback
    private void signalPermissionCallback(PluginCall call) {
        if( getPermissionState("WIFI") == PermissionState.GRANTED &&
            getPermissionState("LTE") == PermissionState.GRANTED &&
            getPermissionState("MICROPHONE") == PermissionState.GRANTED){
            this.getSignalInfo(call);
        } else {
            call.reject("Permission not granted");
        }
    }

    /*public void initPermissions(){
        List<String> permissionToRequest = new ArrayList<>();
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(this.getContext(), permission) != PackageManager.PERMISSION_GRANTED){
                permissionToRequest.add(permission);
            }
        }

        if(permissionToRequest.isEmpty()){
            Toast.makeText(this.getContext(), "All permissions granted", Toast.LENGTH_SHORT).show();
            return;
        }

        ActivityCompat.requestPermissions(this, permissionToRequest.toArray(new String[0]), REQUEST_CODE_PERMISSIONS);
    }*/

    /*public void handle(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this.getContext(), permissions[i] + " permission not granted", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,permissions[i] + " permission not granted");
                }else{
                    Log.d(TAG, permissions[i] + " permission granted");
                }
            }
        }
    }*/
}
