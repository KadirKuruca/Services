package com.kadirkuruca.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Kadir on 29.03.2018.
 */

public class MyStartedService extends Service {

    private static final String TAG = MyStartedService.class.getSimpleName();
    MediaPlayer player;

    @Override
    public void onCreate() {

        Log.i(TAG,"OnCreate Çağrıldı. "+" "+Thread.currentThread().getName());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent ıntent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"OnStartCommand Çağrıldı. "+" "+Thread.currentThread().getName());
/*
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // 15 sn ye çalışacak bir işlem başlattık. Servis başladığı an telefon kitlenir ve başka bir işlem yapılamaz ve daha sonra da uygulama crash olur.
        player = MediaPlayer.create(MyStartedService.this,R.raw.music);
        player.setLooping(true);
        player.start();*/

        int sleepTime = intent.getIntExtra("sleepTime",1);
        new myAsyncTask().execute(sleepTime);

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"OnDestroy Çağrıldı. "+" "+Thread.currentThread().getName());
        //player.stop();
        super.onDestroy();
    }

    class myAsyncTask extends AsyncTask<Integer,Void,Void>{

        private final String TAG = myAsyncTask.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            Log.e("PRE EXECUTE CAĞRILDI"," "+Thread.currentThread().getName());
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... voids) {
            Log.e("DOINBACKGROUND CAĞRILDI"," "+Thread.currentThread().getName());
            int sleepTime = voids[0];

            try {
                Thread.sleep(sleepTime*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.e("ONPOST EXECUTE CAĞRILDI"," "+Thread.currentThread().getName());
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.e("PROGRESSUPDATE CAĞRILDI"," "+Thread.currentThread().getName());
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            Log.e("ONCANCEL CAĞRILDI"," "+Thread.currentThread().getName());
            super.onCancelled();
        }
    }

}
