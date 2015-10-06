package com.amdudda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Cereal> our_cereals = getData();
        HashMap<Cereal,Integer> order = getOrder(our_cereals);
        /*
        Ingredient rice = new Ingredient("rice",units_sold * rc.getRice()/1000);
        Ingredient sugar = new Ingredient("sugar", units_sold * rc.getSugar()/1000);
        System.out.println(rice.getName() + " " + rice.getWeight() + "kg = $" + rice.getCost() );
        System.out.println(sugar.getName() + " " + sugar.getWeight() + "kg = $" + sugar.getCost() );
        System.out.println("rice and sugar for " + units_sold + " units = $" + (rice.getCost() + sugar.getCost()));
*/
        double grand_total = 0;
        for (Cereal c : our_cereals) {
            int units_sold = order.get(c);
            ArrayList<Ingredient> recipe = c.getIngredients(units_sold);
            System.out.println(c.getName() + ":  " + units_sold + " units");
            double totalcost = 0;
            for (Ingredient ing : recipe) {
                System.out.println(ing.getName() + " " + ing.getWeight() + "kg = $" + ing.getCost());
                totalcost += ing.getCost();
            }
            System.out.println("total cost is: $" + totalcost + "\n");
            grand_total += totalcost;
        }
        System.out.println("Grand total for this order is: $" + grand_total);
    }


    public static ArrayList<Cereal> getData(){
        ArrayList<Cereal> c_list = new ArrayList<Cereal>();
        Cereal rc = new Cereal("Rice Crunchies", 0, 400, 20, 1, 0);
        c_list.add(rc);
        Cereal mf = new Cereal("Morning Flakes",350, 0, 40, 1, 0);
        c_list.add(mf);
        Cereal tz = new Cereal("Trianglz", 200, 200, 35, .5, 0);
        c_list.add(tz);
        Cereal h = new Cereal("Hoops", 120, 0, 25, 1, 350);
        c_list.add(h);
        return c_list;
    }


    public static HashMap<Cereal,Integer> getOrder(ArrayList<Cereal> oc) {
        // todo: we probably want this to get turned into an Order object, but let's just test the data for nwo.
        // generates our order
        // create scanner for user input
        Scanner s = new Scanner(System.in);
        int qty; // qty ordered
        HashMap<Cereal,Integer> o = new HashMap<Cereal,Integer>();
        for (Cereal c:oc){
            System.out.println("How many units of " + c.getName() + " were ordered?");
            qty = s.nextInt();
            o.put(c, qty);  // for now, 800 units per cereal in the order
        }
        // close the scanner
        s.close();
        return o;
    }

}
