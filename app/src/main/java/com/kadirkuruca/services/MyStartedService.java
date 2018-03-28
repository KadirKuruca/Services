package com.kadirkuruca.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
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

        Log.e(TAG,"OnCreate Çağrıldı. "+" "+Thread.currentThread().getName());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent ıntent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"OnStartCommand Çağrıldı. "+" "+Thread.currentThread().getName());
/*
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/ // 15 sn ye çalışacak bir işlem başlattık. Servis başladığı an telefon kitlenir ve başka bir işlem yapılamaz ve daha sonra da uygulama crash olur.
        player = MediaPlayer.create(MyStartedService.this,R.raw.music);
        player.setLooping(true);
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"OnDestroy Çağrıldı. "+" "+Thread.currentThread().getName());
        player.stop();
        super.onDestroy();
    }
}
