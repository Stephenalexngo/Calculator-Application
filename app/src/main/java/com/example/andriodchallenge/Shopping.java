package com.example.andriodchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Shopping extends AppCompatActivity{

    String itemfruit;
    int total,cost;
    ArrayList<Item> shopfruit;
    EditText fruitcount;
    LinearLayout cartlayout;
    TextView totalmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Spinner fruitspin = findViewById(R.id.fruitspin);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            fruitspin.setDropDownVerticalOffset(175);
            fruitspin.setDropDownHorizontalOffset(50);
        }
        ArrayAdapter<String> fruitadapt = new ArrayAdapter<String>(Shopping.this,
                R.layout.spinner_item, getResources().getStringArray(R.array.fruits));
        fruitadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fruitspin.setAdapter(fruitadapt);

        fruitspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemfruit = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        shopfruit = new ArrayList<Item>();
        fruitcount = findViewById(R.id.fruitcount);
        cartlayout = findViewById(R.id.cartlayout);
        totalmoney = findViewById(R.id.total);
        total = 0;
    }

    public void addcart(View v)
    {
        if(TextUtils.isEmpty(fruitcount.getText()))
            Toast.makeText(this,"Please Enter Quantity",Toast.LENGTH_SHORT).show();
        else
        {
            shopfruit.add(new Item(itemfruit,Integer.parseInt(fruitcount.getText().toString())));
            total += (shopfruit.get(shopfruit.size()-1).getCost());

            Toast.makeText(this,"Successful Added to Cart :D",Toast.LENGTH_SHORT).show();
            fruitcount.setText("");
            displaycart();
        }
    }

    public void displaycart()
    {
        cartlayout.removeAllViews();
        totalmoney.setText("Total: " + total);

        for(int x=0; x<shopfruit.size(); x++)
        {
            TextView fruits = new TextView(this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 10, 0, 0);
            fruits.setText("   " + shopfruit.get(x).getQuan() + "   " + shopfruit.get(x).getName() + "   " + shopfruit.get(x).getCost());
            fruits.setBackgroundColor(Color.DKGRAY);
            fruits.setTextSize(30);
            fruits.setTextColor(Color.WHITE);
            fruits.setLayoutParams(lp);

            final Button del = new Button(this);
            del.setText("Delete");
            del.setId(x);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    total -= shopfruit.get(del.getId()).getCost();
                    shopfruit.remove(del.getId());
                    displaycart();
                }
            });

            cartlayout.addView(fruits);
            cartlayout.addView(del);
        }
    }

    public void checkout(View v)
    {
        if(!shopfruit.isEmpty()) {
            Intent intent = new Intent(Shopping.this, Checkout.class);
            intent.putExtra("TOTAL", Integer.toString(total));
            intent.putExtra("LIST", shopfruit);
            startActivity(intent);
        }
        else
            Toast.makeText(this,"Nothing to Checkout", Toast.LENGTH_SHORT).show();
    }
}
