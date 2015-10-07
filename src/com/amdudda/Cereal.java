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

    public double getOats() {
        return oats;
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

    public void setOats(double oats) {
        this.oats = oats;
    }

    // and miscellaneous methods

    private double wt_g_in_kg(double grams) {
        return grams / 1000;
    }

    protected ArrayList<Double> getIngredients(int units) {
        // note that we convert this into a weight in kg.
        // returns the total weight for a given cereal's order
        ArrayList<Double> ing_list = new ArrayList<Double>();
        ing_list.add(units * this.wt_g_in_kg(this.rice));
        ing_list.add(units * this.wt_g_in_kg(this.corn));
        ing_list.add(units * this.wt_g_in_kg(this.sugar));
        ing_list.add(units * this.wt_g_in_kg(this.salt));
        ing_list.add(units * this.wt_g_in_kg(this.oats));
        return ing_list;
    }

    protected void printIngredients() {
        // prints out our ingredients
        System.out.printf("Ingredients for %s:\n", this.name);
        System.out.printf("\tCorn: %.2f", this.corn);
        System.out.printf("\tRice: %.2f", this.rice);
        System.out.printf("\tSugar: %.2f", this.sugar);
        System.out.printf("\tSalt: %.2f", this.salt);
        System.out.printf("\tOats: %.2f", this.oats);

    }
}