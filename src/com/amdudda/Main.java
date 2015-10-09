package com.amdudda;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        // set up our cost information
        ArrayList<Price> costinfo = new ArrayList<Price>();
        try { costinfo = getCost(); }
        catch (Exception e){
            System.out.println(e.toString());
        }
        ArrayList<Cereal> our_cereals = getData();

        // determine how much of each cereal has been ordered
        HashMap<Cereal,Integer> order = getOrder(our_cereals);

        // and print out the total cost of the entire order
        Double totalCost = calculateTotalCost(order,our_cereals,costinfo);
        System.out.printf("\nTotal cost for entire order: $%.2f", totalCost);
    }

    private static double calculateTotalCost(HashMap<Cereal, Integer> o, ArrayList<Cereal> oc, ArrayList<Price> ci) {
        // calculates the total cost
        double grand_total=0d, toadd, curval;
        int units;

        /*
        Logic for program:
        Each cereal has a getIngredients method that takes the units ordered for that cereal and returns
        a hashmap with the total weight of ingredients in kg for that cereal.
        Create fake cereal that holds the total weights for all ingredients.
        Then return getTotalCost for each ingredient and add it to grand_total.
        */
        // create and populate hashmap storing total weight of all ingredients for our fake cereal
        HashMap<String,Double> totals = new HashMap<String,Double>();
        // initialize the Hashmap with the list of ingredients - pick the first cereal in the oc ArrayList
        // just to extract the list of possible ingredients
        Set<String> list_of_ingrs = oc.get(0).getAllIngredients().keySet();
        for (String i: list_of_ingrs) {
            totals.put(i,0d);
        } // end for each
        HashMap<String,Double> cur_c_ings;

        // go through each cereal and calculate how much of each ingredient needs to be added to
        // our total so we can generate a grand-total price for the entire order.
        for (Cereal c:oc) {
            cur_c_ings = c.getAllIngredients();
            for (String ing:cur_c_ings.keySet()) {
                units = o.get(c);  // how many units of that cereal
                toadd = cur_c_ings.get(ing) * units;
                curval = totals.get(ing);
                totals.put(ing,curval+toadd);
            }
        }
        Cereal fakecereal = new Cereal("all cereals in the order",totals);

        // print out the weight and cost of all cereals for the order
        printCostInfo(fakecereal,ci);

        // now run through price and calculate the total cost for each ingredient
        for (Price p:ci) {
            String ing = p.getIngredient();
            grand_total += p.getTotalCost(fakecereal.getIngredientWeight(ing));
        } // end for

        return grand_total;
    } // end calculateTotalCost

    private static void printCostInfo(Cereal fakecereal, ArrayList<Price> ci) {
        fakecereal.printIngredientsKg();
        System.out.println("Cost of ingredients:");
        for (Price p:ci) {
            String ingredient = p.getIngredient();
            String output = ("\t" + ingredient + ": ");
            HashMap<String, Double> fc_allingr = fakecereal.getAllIngredients();
            output += String.format("$%.2f",p.getTotalCost(fc_allingr.get(ingredient)));
            System.out.print(output);
        }
    }  // end printCostInfo

    public static ArrayList<Cereal> getData() throws IOException {
        // Read in data from recipies.txt and create our cereals from that.
        // set up our data streams
        File f = new File("./data/recipies.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        // read in our first line of data
        String c_info = br.readLine();

        // set up variables to hold the data we read in
        int eod;  // variable to store the index of the string to stop at when reading in the next bit of data
        ArrayList<Cereal> c_list = new ArrayList<Cereal>();
        HashMap<String, Double> cereal = new HashMap<String, Double>();
        ArrayList<String> ingredients = new ArrayList<String>();

        c_info = c_info.substring(c_info.indexOf(":")+1);
        while (c_info != "" && c_info != null) {
            if ( c_info.indexOf(",") == -1 ) { eod = c_info.length(); }
            else { eod = c_info.indexOf(","); }
            String ingr = c_info.substring(0, eod);
            ingredients.add(ingr);
            if (c_info.indexOf(",") != -1) {
                c_info = c_info.substring(eod + 1);
            } else {
                c_info = "";
            } // end if-else
        }

        Integer arrsize = ingredients.size();

        // next line of the file contains our first row of data
        c_info = br.readLine();

        // and start processing it
        while (!(c_info.equals("/*")) && c_info != null ) {
            eod = c_info.indexOf(",");
            String cname = c_info.substring(0,eod);
            c_info = c_info.substring(eod+1);

            for (String i:ingredients) {
                if ( c_info.indexOf(",") == -1 ) { eod = c_info.length(); }
                else { eod = c_info.indexOf(","); }
                // our source data is in grams, but our program expects kilograms
                Double amt = Double.parseDouble(c_info.substring(0, eod)) / 1000;
                cereal.put(i,amt);
                if (c_info.indexOf(",") != -1) c_info = c_info.substring(eod + 1);
            }
            // create the cereal
            Cereal nc = new Cereal(cname,cereal);
            // add the cereal to the array of cereals
            c_list.add(nc);
            // move to the next line and don't forget we need a new hashmap of data
            // (cereal.clear() clears the HM we just created rather than allowing us to generate new data)
            cereal = new HashMap<String,Double>();
            c_info = br.readLine();
        } // end while

        // close our datastreams
        br.close();
        fr.close();

        // and return our array
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
            o.put(c, qty);
        }
        // close the scanner
        s.close();
        return o;
    }

    protected static ArrayList<Price> getCost() throws IOException {
        // created arraylist storing pricing info for each ingredient
        ArrayList<Price> pricelist = new ArrayList<Price>();
        String ing;
        int maxqty;
        double cost;
        boolean found = false;

        // gonna read in a bunch of data - set up our data streams
        String fname = "./data/cost.txt";
        File f = new File(fname);
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        // read in our first line
        String line = br.readLine();
        // and start processing our data
        while ( !(line.equals("/*")) && line != null) {
            // extract the data we need to work with
            ing = line.substring(0,line.indexOf(" "));
            maxqty = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" ")));
            cost = Double.parseDouble(line.substring(line.lastIndexOf(" ") + 1));
            // if the arraylist already contains the product, just add maxqty and cost to the priceinfo Hashmap
            // otherwise, add the new price object to the arraylist

            // Done: details of getting the data read in and stored correctly
            for (Price c:pricelist ) {
                // if the ingredient name already exists, add the price info to the hashmap
                if (c.getIngredient().equals(ing)) {
                    c.addPriceInfo(maxqty,cost);
                    found = true;
                    break;
                } // end if
            } // end for-each
            if (!found) {
                Price np = new Price(ing);
                np.addPriceInfo(maxqty,cost);
                pricelist.add(np);
            }

            // reset found to false and move to the next line
            found = false;
            line = br.readLine();
        }

        //close our filestreams
        br.close();
        fr.close();

        // return our arraylist of pricing info
        return pricelist;
    }

}
