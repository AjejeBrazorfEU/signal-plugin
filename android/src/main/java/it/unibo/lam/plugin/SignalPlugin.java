package it.unibo.lam.plugin;

import android.util.Log;

public class SignalPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }
}
