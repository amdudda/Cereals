package com.amdudda;

import java.util.HashMap;

/**
 * Created by amdudda on 10/5/15.
 */
public class Price {
    // class to store and retrieve cost info

    private String product;
    private Cereal cereal;
    private HashMap <Integer,Double> priceinfo;
    // the HashMap stores an integer, which is the cutoff quantity for a given price

    // Constructor
    public Price(Cereal c){
        this.cereal = c;
        this.product = c.getName();  // redundant but ease of reference is good
        this.priceinfo = new HashMap <Integer,Double>();
    }

    // a bunch of getters and setters
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Cereal getCereal() {
        return cereal;
    }

    public void setCereal(Cereal cereal) {
        this.cereal = cereal;
    }

    public HashMap<Integer, Double> getPriceinfo() {
        return priceinfo;
    }

    public void setPriceinfo(HashMap<Integer, Double> priceinfo) {
        this.priceinfo = priceinfo;
    }
}
