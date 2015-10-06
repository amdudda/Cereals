package com.amdudda;
import java.util.ArrayList;

/**
 * Created by amdudda on 10/5/2015.
 */
public class Cereal {

    private String name;
    private double corn;
    private double rice;
    private double sugar;
    private double salt;
    private double oats;

    //Creating Constructor
    public Cereal(String cerealName, double corn, double rice, double sugar, double salt, double oats) {
        this.name = cerealName;
        this.corn = corn;
        this.rice = rice;
        this.sugar = sugar;
        this.salt = salt;
        this.oats = oats;

    }

    //Creating Setter and Getter
    public double getSalt() {
        return salt;
    }

    public double getSugar() {
        return sugar;
    }

    public String getName() {
        return name;
    }

    public double getCorn() {
        return corn;
    }

    public double getRice() {
        return rice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCorn(double corn) {
        this.corn = corn;
    }

    public void setRice(double rice) {
        this.rice = rice;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public double wt_g_in_kg(double grams) {
        return grams / 1000;
    }

    public ArrayList<Ingredient> getIngredients(int units) {
        // note that we convert this into a weight in kg.
        ArrayList<Ingredient> ing_list = new ArrayList<Ingredient>();
        ing_list.add(new Ingredient("rice", units * this.wt_g_in_kg(this.rice)));
        ing_list.add(new Ingredient("corn", units * this.wt_g_in_kg(this.corn)));
        ing_list.add(new Ingredient("sugar", units * this.wt_g_in_kg(this.sugar)));
        ing_list.add(new Ingredient("salt", units * this.wt_g_in_kg(this.salt)));
        ing_list.add(new Ingredient("oats", units * this.wt_g_in_kg(this.oats)));
        return ing_list;
    }
}