package com.example.andriodchallenge;

import java.io.Serializable;

public class Item implements Serializable
{
    int quan, cost;
    String name;

    public Item (String name, int quan)
    {
        this.name = name;
        this.quan = quan;

        if(name.equals("Apple"))
            this.cost = 20*quan;
        else if(name.equals("Orange"))
            this.cost = 10*quan;
        else
            this.cost = 30*quan;
    }

    public int getQuan() {
        return quan;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
