package it.unibo.lam.plugin;


import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

public class AcousticInfo {
    private static final String TAG = AcousticInfo.class.getSimpleName();

    public static int getDecibel(Context context) {
        if(checkPermission(context)) {
            Log.d(TAG, "getDecibel: operation permitted");
            //Log.d(TAG,"getDecibel: thread name " + Thread.currentThread().getName());

            MediaRecorder mediaRecorder=new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            File f = new File(context.getFilesDir(),"audiotest");
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Log.d(TAG, "MediaRecorderReady: " + f.getAbsolutePath());
            mediaRecorder.setOutputFile(f);

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Log.d(TAG, "getDecibel: recording started");
            //Log.d(TAG, "getDecibel: " + mediaRecorder.getMaxAmplitude());
            //handler.postDelayed(stopRecording, 500);
            try{
                // Questa sleep viene messa in modo tale da ascoltare almeno mezzo secondo
                // di audio in modo da avere un valore sensato del rumore
                Thread.sleep(500);
                Log.d(TAG, "getDecibel: stop recording");
                int res = (int)(mediaRecorder.getMaxAmplitude());
                //Log.d(TAG, "getDecibel: " + res);

                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                return res;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            Log.e(TAG, "getDecibel: operation not permitted");
        }
        return 0;
    }

    private static boolean checkPermission(Context context) {
        int result1 = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(context,RECORD_AUDIO);
        return result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }
}
