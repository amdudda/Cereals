package com.amdudda;

import java.util.HashMap;

/**
 * Created by amdudda on 10/5/15.
 */
public class Price {
    // class to store and retrieve cost info

    private String ingredient;
    private HashMap <Integer,Double> priceinfo;
    // the HashMap stores an integer, which is the cutoff quantity for a given price

    // Constructor
    public Price(String ing){
        this.ingredient = ing;
        this.priceinfo = new HashMap <Integer,Double>();
    }

    // a bunch of getters and setters

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public HashMap<Integer, Double> getPriceinfo() {
        return priceinfo;
    }

    public void setPriceinfo(HashMap<Integer, Double> priceinfo) {
        this.priceinfo = priceinfo;
    }

    // and some other methods
    public void addPriceInfo(Integer maxunits, Double cost){
        this.priceinfo.put(maxunits,cost);
    }
}
