package com.example.andriodchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Checkout extends AppCompatActivity {

    TextView topay;
    EditText paying;
    int total,change;
    ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent intent = getIntent();
        topay = findViewById(R.id.topay);
        paying = findViewById(R.id.paying);

        items = new ArrayList<Item>();
        items = (ArrayList<Item>) getIntent().getSerializableExtra("LIST");

        topay.setText("Total Amount: " + intent.getStringExtra("TOTAL"));
        total = Integer.parseInt(intent.getStringExtra("TOTAL"));
    }

    public void gototrans(View v)
    {
        if(TextUtils.isEmpty(paying.getText()))
            Toast.makeText(this,"Please Enter Your Payment",Toast.LENGTH_SHORT).show();
        else if(total > Integer.parseInt(paying.getText().toString()))
            Toast.makeText(this,"Insufficient Amount",Toast.LENGTH_SHORT).show();
        else
        {
            change = Integer.parseInt(paying.getText().toString()) - total;
            Intent intent = new Intent(Checkout.this,Transaction.class);
            intent.putExtra("FINALLIST",items);
            intent.putExtra("FINALTOTAL",paying.getText().toString());
            intent.putExtra("FINALCHANGE",Integer.toString(change));
            startActivity(intent);
        }
    }

    public void backer(View v)
    {
        finish();
    }
}
