package com.amdudda;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by amdudda on 10/5/2015.
 */
public class Cereal {

    private String name;
    private HashMap<String,Double> ingredients;
    /*  I think I'm moving these into a hashmap... yup, much easier handling of code!
        Eventually I'll want to create an Ingredient object that holds the name and quantity, but this
        works well for now.
    private double corn;
    private double rice;
    private double sugar;
    private double salt;
    private double oats;
    */

    //Creating Constructor
    public Cereal(String cerealName, double corn, double rice, double sugar, double salt, double oats) {
        this.name = cerealName;
        /*
        this.corn = corn;
        this.rice = rice;
        this.sugar = sugar;
        this.salt = salt;
        this.oats = oats;
        */
        this.ingredients = new HashMap<String,Double>();
        // TODO: need to automate read-in of ingredient names.
        this.ingredients.put("corn",corn);
        this.ingredients.put("rice",rice);
        this.ingredients.put("sugar",sugar);
        this.ingredients.put("salt",salt);
        this.ingredients.put("oats",oats);
    }

    // overloading constructor for future code implementation
    public Cereal(String cerealName, HashMap<String,Double> ingList) {
        this.name = cerealName;
        this.ingredients = ingList;
    }
    // end constructors

    //Creating Setter and Getter
    public String getName() {
        return name;
    }

    public Double getIngredient(String ingName) {
        return this.ingredients.get(ingName);
    }

    public HashMap<String,Double> getAllIngredients() {
        return this.ingredients;
    }

    /* old getter/setters
    public double getSalt() {
        return salt;
    }

    public double getSugar() {
        return sugar;
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
*/

    // and miscellaneous methods

    private double wt_g_in_kg(double grams) {
        return grams / 1000;
    }

    protected ArrayList<Double> getIngredients(int units) {
        // note that we convert this into a weight in kg.
        // returns the total weight for a given cereal's order
        ArrayList<Double> ing_list = new ArrayList<Double>();
        for (String ing:this.ingredients.keySet()) {
            ing_list.add(units * this.wt_g_in_kg(this.getIngredient(ing)));
        }
        return ing_list;
    }

    protected void printIngredients() {
        // prints out our ingredients
        System.out.printf("Ingredients for %s:\n", this.name);
        for (String ing:this.ingredients.keySet()) {
            System.out.printf("\t" + ing + ": %.2f", this.getIngredient(ing));
        }
    }
}