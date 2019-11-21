package com.example.andriodchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goshop(View v)
    {
        startActivity(new Intent(MainActivity.this, Shopping.class));
    }

    public void goexit(View v)
    {
        finish();
    }
}
