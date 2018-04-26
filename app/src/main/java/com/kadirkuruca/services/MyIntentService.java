package com.kadirkuruca.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Kadir on 26.04.2018.
 */

public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.e(TAG,"ONCREATE "+Thread.currentThread().getName()+" Thread Üzerinde");
        super.onCreate();
    }

    public MyIntentService() {
        super("My Worker Thread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //Bu method çağrıldıkça işlemleri kuyruğa alır ve biri bitince diğerini çalıştırır ve bitince servisi otomatik olarak kapatır.
        Log.e(TAG,"ONHANDLEINTENT "+Thread.currentThread().getName()+" Thread Üzerinde");

        ResultReceiver resultReceiver = intent.getParcelableExtra("receiver");

        int sleepTime = intent.getIntExtra("sleepTime",1);
        int sayac = 0;

        while(sayac < sleepTime) {

            try {
                Thread.sleep(1000);
                Bundle bundle = new Bundle();
                bundle.putInt("donguSirasi", sayac);
                resultReceiver.send(15,bundle); // Bundle ile veri gönderildi.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }// uyuma işlemi worker thread üzerinde çalıştığı için telefonu kitlemez.
            sayac++;
        }


    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"ONDESTROY "+Thread.currentThread().getName()+" Thread Üzerinde");
        super.onDestroy();
    }
}
