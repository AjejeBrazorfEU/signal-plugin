package it.unibo.lam.plugin;

import android.content.Context;
import android.media.MicrophoneInfo;
import android.util.Log;
import com.getcapacitor.JSObject;

public class SignalPlugin {

    public String echo(String value) {
        Log.i("Echo", value);
        return value;
    }

    public Object getSignalInfo(Context context) {
        int LTE,WIFI,MICROPHONE;

        LTE = LTEInfo.getLTESignalStrength(context);
        WIFI = WiFiInfo.getWiFiSignalStrength(context);
        MICROPHONE = AcousticInfo.getDecibel(context);

        return new JSObject() {{
            put("LTE", LTE);
            put("WIFI", WIFI);
            put("MICROPHONE", MICROPHONE);
        }};
    }
}
