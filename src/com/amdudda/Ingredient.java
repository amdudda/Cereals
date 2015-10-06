package com.amdudda;

/**
 * Created by amdudda on 10/5/2015.
 */

public class Ingredient {

    private String name;
    private double weight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Ingredient(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public double getCost() {
        // todo: a way to extract the per-kg price based on the order, but hardcoded for now
        // returns cost of a given amount of an ingredient
        switch (this.name) {
            case "rice": {
                if (this.weight < 100) {
                    return this.weight * .50;
                } else if (this.weight < 500) {
                    return this.weight * .43;
                } else {
                    return this.weight * .30;
                }

            }
            case "corn": {
                if (this.weight < 80) {
                    return this.weight * .50;
                } else {
                    return this.weight * .41;
                }

            }
            case "sugar": {
                if (this.weight < 200) {
                    return this.weight * 1.15;
                } else {
                    return this.weight * .95;
                }

            }
            case "salt": {
                return this.weight * .70;

            }
            case "oats": {
                if (this.weight < 70) {
                    return this.weight * .65;
                }
                else {
                    return this.weight * .52;
                }
            }
            default:
                return -3d;
        } // end switch
    }
}
