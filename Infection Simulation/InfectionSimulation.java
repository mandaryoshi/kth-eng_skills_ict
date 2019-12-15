package infectionsimulation;

import java.io.*;
import static java.lang.System.exit;
import static java.lang.Thread.sleep;
import java.util.*;

public class InfectionSimulation {

    static int days = 0;                                                        // length of the simulation
    static int deaths = 0;                                                      // amount of humans dying to the sickness
    static int getting_sick = 0;                                                // amount of humans becoming sick
    static int recovered = 0;                                                   // amount of humans recovered from the disease
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("Dimension of the population matrix N (where NxN is the matrix)");
        Scanner input = new Scanner(System.in);
        int in1 = input.nextInt();
        //int in1 = 50;
        
        Human [][] pop = new Human [in1][in1];
        // Creating the matrix
        for (int i = 0; i < pop.length; i++){
            for (int j = 0; j < pop[i].length; j++){
                pop[i][j] = new Human(i, j, 0);
                //pop[i][j] = 0;
                System.out.print(pop[i][j].status + " ");
                if (j == pop[i].length-1)
                    System.out.println();
            }
        }       
        
        //inputs
        /*
        Random rand = new Random();
        int in2 = 100;
        int in3min = 4;
        int in3max = 8;
        int in4 = 0;
        pop[25][25].status = 1;
        pop[25][25].sickness_period = in3min + rand.nextInt(in3max - in3min + 1);
        */
        
        
        System.out.println("Probability of getting sick in %");
        int in2 = input.nextInt();
        Random rand = new Random();
        System.out.println("Length of sickness: min days");
        int in3min = input.nextInt();
        System.out.println("Length of sickness: max days");
        int in3max = input.nextInt();
        System.out.println("Probability of death in %");
        int in4 = input.nextInt();
        
        System.out.println("Sick people:");
        // Input of initially sick people
        while (true) {
            try {
                System.out.println("Input X coordinate or type 999 to simulate");
                int x = input.nextInt();
                if (x == 999) {
                    break;
                }
                else {
                    System.out.println("Input Y coordinate");
                    int y = input.nextInt();
                    pop[x][y].status = 1;
                    pop[x][y].sickness_period = in3min + rand.nextInt(in3max - in3min + 1);         // random length based on min and max values
                }
            }
            catch (Exception e) {
                System.out.println("Wrong input! Try again!");
            }
        }
        

        // Display the matrix with infected humans
        for (int i = 0; i < pop.length; i++){
            for (int j = 0; j < pop[i].length; j++){
                System.out.print(pop[i][j].status + " ");
                if (j == pop[i].length-1)
                    System.out.println();
            }
        }
        System.out.println();
        
        // Simulation begins
        while (true) {
            days++;
            
            // Reset got_sick status at the beginning of the next day
            for (int i = 0; i < pop.length; i++){
                for (int j = 0; j < pop[i].length; j++){
                    pop[i][j].got_sick = false;
                }
            }
            
            // for every sick human, check the neighbors
            for (int i = 0; i < pop.length; i++){
                for (int j = 0; j < pop[i].length; j++){
                    if ((pop[i][j].status == 1) && (pop[i][j].got_sick == false) &&(pop[i][j].sickness_period > 0)) {
                        try { //top left neighbor
                            if (pop[i-1][j-1].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    // if random event happens, change status to sick and assign the following variables
                                    pop[i-1][j-1].status = 1;
                                    pop[i-1][j-1].got_sick = true;
                                    getting_sick++;
                                    pop[i-1][j-1].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        try { //top
                            if (pop[i-1][j].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    pop[i-1][j].status = 1;
                                    pop[i-1][j].got_sick = true;
                                    getting_sick++;
                                    pop[i-1][j].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        try { //top right
                            if (pop[i-1][j+1].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    pop[i-1][j+1].status = 1;
                                    pop[i-1][j+1].got_sick = true;
                                    getting_sick++;
                                    pop[i-1][j+1].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        try { // left
                            if (pop[i][j-1].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    pop[i][j-1].status = 1;
                                    pop[i][j-1].got_sick = true;
                                    getting_sick++;
                                    pop[i][j-1].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        try { // right
                            if (pop[i][j+1].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    pop[i][j+1].status = 1;
                                    pop[i][j+1].got_sick = true;
                                    getting_sick++;
                                    pop[i][j+1].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        try { // bottom left
                            if (pop[i+1][j-1].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    pop[i+1][j-1].status = 1;
                                    pop[i+1][j-1].got_sick = true;
                                    getting_sick++;
                                    pop[i+1][j-1].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        try { // bottom
                            if (pop[i+1][j].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    pop[i+1][j].status = 1;
                                    pop[i+1][j].got_sick = true;
                                    getting_sick++;
                                    pop[i+1][j].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        try { // bottom right
                            if (pop[i+1][j+1].status == 0) {
                                int val = rand.nextInt(100);
                                if(val <= in2) {
                                    pop[i+1][j+1].status = 1;
                                    pop[i+1][j+1].got_sick = true;
                                    getting_sick++;
                                    pop[i+1][j+1].sickness_period = in3min + rand.nextInt(in3max - in3min);
                                }
                            }
                        }
                        catch(Exception e) {
                        }
                        // if death random even occurs, change status to 2 (dead)
                        if (in4 != 0) {
                            boolean val = rand.nextInt(100/in4)==0;
                            if(val == true) {
                                pop[i][j].status = 2;
                                deaths++;
                            }
                        }
                        
                        // at the end of the day, reduce sickness period of the current human
                        pop[i][j].sickness_period--;
                        if (pop[i][j].sickness_period == 0) {
                            pop[i][j].status = 9;
                            recovered++;
                        }
                    }
                }
            }
            
            // the loop continues until there are no sick left
            boolean continue_app = false;
            
            for (int i = 0; i < pop.length; i++){
                for (int j = 0; j < pop[i].length; j++){
                    if (pop[i][j].status == 1)
                        continue_app = true;
                }
            }
            
            if (continue_app == false) {
                for (int i = 0; i < pop.length; i++){
                    for (int j = 0; j < pop[i].length; j++){
                        System.out.print(pop[i][j].status + " ");
                        if (j == pop[i].length-1)
                            System.out.println();
                    }
                }
                break;
            }
        }
        
        // Output interface
        System.out.println("What statistics do you want to see? Enter the according number:");
        System.out.println("1. Total days");
        System.out.println("2. Total deaths");
        System.out.println("3. Total people getting sick excluding patient(s) zero");
        System.out.println("4. Average deaths per day");
        System.out.println("5. Average people getting sick per day");
        System.out.println("6. Average recoveries per day");
        System.out.println("7. All from the above");
        System.out.println("");
        System.out.println("0. Exit");
        
        while (true) {
            //int query = 5;
            int query = input.nextInt();
            
            if (query == 1){
                System.out.println("Total days: " + days);
            }
            if (query == 2){
                System.out.println("Total deaths: " + deaths);
            }
            if (query == 3){
                System.out.println("Total people getting sick excluding the initial sick: " + getting_sick);
            }
            if (query == 4){
                double deaths_per_day = (double)deaths/(double)days;
                System.out.println("Average deaths per day: " + deaths_per_day);
            }
            if (query == 5){
                double sick_per_day = (double)getting_sick/(double)days;
                System.out.println("Average people getting sick per day: " + sick_per_day);
                //exit(0);
            }
            if (query == 6){
                double recovered_per_day = (double)recovered/(double)days;
                System.out.println("Average recoveries per day: " + recovered_per_day);
            }
            if (query == 7){
                System.out.println("Total days: " + days);
                System.out.println("Total deaths: " + deaths);
                System.out.println("Total people getting sick excluding the initial sick: " + getting_sick);
                double deaths_per_day = (double)deaths/(double)days;
                System.out.println("Average deaths per day: " + deaths_per_day);
                double sick_per_day = (double)getting_sick/(double)days;
                System.out.println("Average people getting sick per day: " + sick_per_day);
                double recovered_per_day = (double)recovered/(double)days;
                System.out.println("Average recoveries per day: " + recovered_per_day);
            }
            if (query == 0){
                exit(0);
            }
            sleep(50);
        }
    }
    
    // Human class constructor
    public static class Human {
        int x;                                                                  // x coord
        int y;                                                                  // y coord
        int status = 0;                                                         // 0 - healthy; 1 - sick; 2 - dead; 9 - recovered
        boolean got_sick = false;                                               // got sick today
        int sickness_period = 0;
        Human(int i){
            status = i;
        }
        Human(int i, boolean j){
            status = i;
            got_sick = j;
        }
        Human(int i, int j, int k){
            x = i;
            y = j;
            status = k;
        }
    }
}
