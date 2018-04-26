package com.kadirkuruca.services;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void startIntentService(View view) {

        ResultReceiver myResultReceiver = new MyResultReceiver(null);

        Intent intent = new Intent(Main2Activity.this,MyIntentService.class);
        intent.putExtra("receiver",myResultReceiver); // ResultReceiver i servise gönderme işlemi
        intent.putExtra("sleepTime",5);
        startService(intent);
    }

    //Servisten arayüze veri göndermek için ResultReceiver kullanılacaktır.
    public class MyResultReceiver extends ResultReceiver{

        private final String TAG = MyResultReceiver.class.getSimpleName();

        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData) {

            super.onReceiveResult(resultCode, resultData);
            if(resultCode == 15 && resultData != null){

                //Servisten gelen mesajı UI arayüzde kullanabilmek için Handler yapısı kullanılmalıdır.
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG," onReceiveResult Metodu Çağrıldı."+" Sonuc : "+resultData.get("donguSirasi"));
                        Toast.makeText(Main2Activity.this,"Sonuc : "+resultData.get("donguSirasi"),Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }
    }
}
