package com.example.andriodchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Transaction extends AppCompatActivity {

    LinearLayout display;
    ArrayList<Item> items;
    TextView change, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        display = findViewById(R.id.display);
        items = new ArrayList<Item>();
        items = (ArrayList<Item>) getIntent().getSerializableExtra("FINALLIST");

        for(int x=0; x<items.size(); x++)
        {
            TextView fruits = new TextView(this);

            fruits.setText(items.get(x).getQuan() + "   " + items.get(x).getName() + "   " + items.get(x).getCost());
            fruits.setTextSize(30);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 10, 0, 0);
            fruits.setBackgroundColor(Color.DKGRAY);
            fruits.setTextColor(Color.WHITE);
            fruits.setLayoutParams(lp);
            display.addView(fruits);
        }

        change = findViewById(R.id.change);
        total = findViewById(R.id.total);

        change.setText("Total Amount Paid: " + getIntent().getStringExtra("FINALTOTAL"));
        total.setText("Total Change: " + getIntent().getStringExtra("FINALCHANGE"));
    }

    public void gotomain(View v)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
}
