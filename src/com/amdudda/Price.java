package com.amdudda;

import java.util.ArrayList;
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

    public Double getTotalCost(double weight) {
        // returns the total cost of an ingredient based on the quantity ordered.
        Double totalCost = 0d;
        HashMap<Integer,Double> costs = this.getPriceinfo();
        ArrayList<Integer> max_wt = this.sortedQtys();

        for (Integer w:max_wt){
            if (w == 0) totalCost = costs.get(w);
            if (weight < w) {
                totalCost = costs.get(w);
                break;
            }
        }

        return totalCost*weight;
    }

    private ArrayList<Integer> sortedQtys() {
        // returns hashmap keys, sorted in order, with "0" (our dummy number for "default") moved to the end.
        ArrayList<Integer> to_sort = new ArrayList<Integer>();
        int to_move;
        // first read in the keyset
        for (Integer i:this.getPriceinfo().keySet()) {
            to_sort.add(i);
        }
        // now sort it
        for (int i=0; i<to_sort.size(); i++) {
            for (int j=0; j<to_sort.size(); j++){
                if (to_sort.get(i) < to_sort.get(j)) {
                    to_move = to_sort.get(i);
                    to_sort.set(i,to_sort.get(j));
                    to_sort.set(j,to_move);
                } // end if
            } // end for j
        }  // end for i

        // move the value associated with zero to the end of the array
        int movelast = to_sort.remove(0);
        to_sort.add(movelast);

        // then return the sorted arraylist
        return to_sort;
    }
}
