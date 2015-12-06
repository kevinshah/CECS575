package com.example.kevin.unisalestorm;

/**
 * Created by Kevin on 12/3/15.
 */
public class Electronics extends Categories{

String ItemDescription;
double price;

    public Electronics() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }
}
