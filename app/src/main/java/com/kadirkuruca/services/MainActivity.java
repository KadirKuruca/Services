package com.kadirkuruca.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {
        Intent intent = new Intent(this,MyStartedService.class);
        intent.putExtra("sleepTime",15);
        startService(intent);
    }

    public void stopService(View view) {

        Intent intent = new Intent(this,MyStartedService.class);
        stopService(intent);
    }

    public void intentServiceButton(View view) {

        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
