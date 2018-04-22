package com.kadirkuruca.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Kadir on 29.03.2018.
 */

public class MyStartedService extends Service {

    private static final String TAG = MyStartedService.class.getSimpleName();
    MediaPlayer player;

    myAsyncTask gorev;

    @Override
    public void onCreate() {

        Log.i(TAG,"OnCreate Çağrıldı. "+" "+Thread.currentThread().getName());
        gorev = new myAsyncTask();
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
        //new myAsyncTask().execute(sleepTime);
        gorev.execute(sleepTime); // Burada önceden oluşturulan nesneye parametre verdik. Üsttekiyle aynı şey.

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"OnDestroy Çağrıldı. "+" "+Thread.currentThread().getName());
        //player.stop();
        gorev.cancel(true);
        super.onDestroy();
    }

    class myAsyncTask extends AsyncTask<Integer,String,String>{

        private final String TAG = myAsyncTask.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            Log.e("PRE EXECUTE CAĞRILDI"," "+Thread.currentThread().getName());
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... voids) {
            Log.e("DOINBACKGROUND CAĞRILDI"," "+Thread.currentThread().getName());
            int sleepTime = voids[0];

            int sayac = 0;
            if(!gorev.isCancelled()){
                while(sayac < sleepTime){
                    sayac++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Thread direkt olarak kesilir.
                    }
                    publishProgress("Geçen Süre : " +sayac); // Süreyi fonksiyon sayesinde onPostExecute fonksiyonuna gönderdik.
                }
            }

            /*try {
                Thread.sleep(sleepTime*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            return "İŞLEM BAŞARILI";
        }

        @Override
        protected void onPostExecute(String aVoid) {
            Log.e(TAG,"ONPOST EXECUTE CAĞRILDI "+Thread.currentThread().getName());
            Toast.makeText(MyStartedService.this,aVoid,Toast.LENGTH_SHORT).show();
            stopSelf();
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.e(TAG,"PROGRESSUPDATE CAĞRILDI, Süre : " +values[0]+"  "+Thread.currentThread().getName()); // Burada values kullanarak süreye ulaştık.
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            Log.e(TAG,"ONCANCEL CAĞRILDI "+Thread.currentThread().getName());
            super.onCancelled();
        }
    }

}
