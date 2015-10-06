package com.amdudda;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // set up our cost information
        ArrayList<Price> costinfo = new ArrayList<Price>();
        try { costinfo = getCost(); }
        catch (Exception e){
            System.out.println(e.toString());
        }
        ArrayList<Cereal> our_cereals = getData();
        HashMap<Cereal,Integer> order = getOrder(our_cereals);


        /*debug: print out price info
        for (Price p:costinfo) {
            System.out.println(p.getIngredient());
            for (Integer i:p.getPriceinfo().keySet() ) {
                System.out.println("Up to " + i + "kg : $" + p.getPriceinfo().get(i));
            }
        }

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
        */
        System.out.println("testing rice array");
        ArrayList<Integer> test = costinfo.get(1).sortedQtys();
        for (Integer i: test) System.out.println(i);

        for (Price x:costinfo) {
            System.out.println("250kg of " + x.getIngredient() + " costs $" + x.getTotalCost(250) );
        }
    }


    public static ArrayList<Cereal> getData() throws IOException {
        // Read in data from recipies.txt and create our cereals from that.
        // set up our data streams
        File f = new File("./data/recipies.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        // read in our first line of data
        String c_info = br.readLine();

        // set up variables to hold the data we read in
        int eod;
        ArrayList<Cereal> c_list = new ArrayList<Cereal>();
        ArrayList<Double> cereal = new ArrayList<Double>();
        Integer arrsize = Integer.parseInt(c_info.substring(c_info.indexOf(":")+1));

        // next line of the file contains our first row of data
        c_info = br.readLine();

        // and start processing it
        while (!(c_info.equals("/*")) && c_info != null ) {
            eod = c_info.indexOf(",");
            String cname = c_info.substring(0,eod);
            c_info = c_info.substring(eod+1);

            for (int i=0; i<arrsize; i++) {
                if ( c_info.indexOf(",") == -1 ) { eod = c_info.length(); }
                else { eod = c_info.indexOf(","); }
                Double amt = Double.parseDouble(c_info.substring(0, eod));
                cereal.add(amt);
                if (c_info.indexOf(",") != -1) c_info = c_info.substring(eod + 1);
            }
            Cereal nc = new Cereal(cname,cereal.get(0),cereal.get(1),cereal.get(2),
                    cereal.get(3), cereal.get(4));

            // move to the next line
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
            o.put(c, qty);  // for now, 800 units per cereal in the order
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
