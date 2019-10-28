/**
 * Write a description of class AirlineMonteCarlo here.
 * 
 * Information about Boeing 787-8 Dreamliner came from Boeing's website (https://www.boeing.com/commercial/787/)
 *
 * @author Shruthi Ravichandran with help for creating the table from Java2s Tutorials (http://www.java2s.com/Tutorial/Java)
 * @version 10 28 2019
 */
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AirlineMonteCarlo
{
    private final int NUM_SIMULATIONS = 100000;
    private int seats;
    private double noShowProb;
    private int ticketsSold;
    private int overbooked;
    private double averageSeatsFilled;
    private double percentage;
    
    /**
    * Constructor for objects of class RPSMatch
     */
    public AirlineMonteCarlo(int s, int t, double p)
    {
        this.seats = s;
        this.ticketsSold = t;
        this.noShowProb = p;
    }
    
    /**
     * Simulates one flight
     * @return numShow (number of people who show up)
     */
    public int simulateOneFlight()
    {
        int numShow = 0; //number of people who show up
        for (int i = 1; i <= ticketsSold; i++)
        {
           if (Math.random() > noShowProb)
           {
                numShow = numShow + 1;
           }
        }
        return numShow;
    }
    
    /**
     * Runs the simulation
     */
    public void runSimulation()
    {
        int runningTotal = 0; //total seats filled out of all simulations
        overbooked = 0; //number of times out of all simulations the plane was overbooked
        for (int k = 1; k <= NUM_SIMULATIONS; k++)
        {
            int numShow = simulateOneFlight();
            runningTotal = runningTotal + numShow;
            if (numShow > seats)
            {
                overbooked = overbooked + 1;
            }
        }
        averageSeatsFilled = (double) runningTotal / NUM_SIMULATIONS;
        percentage = (double) overbooked * 100 / NUM_SIMULATIONS;
    }
  
    
    /**
     * Prints out the results
     */
    public void reportResults()
    {
      System.out.println("Simulation: " + ticketsSold + " tickets sold for " + seats + " seats; no-show probability = " + noShowProb);
      System.out.println("Based on " + NUM_SIMULATIONS + " simulations: ");
      System.out.println("Average seats filled: " + averageSeatsFilled);
      System.out.println("Number of times overbooked: " + overbooked + " (" + percentage + " percent)");
    }

    /**
     * Creates a table of maximum number of seats can be booked on Boeing 787-8 Dreamliner to maximize profits
     *       while still keeping overbooking under 10% for four different "no show probabilities" (0.02, 0.03, 0.05, 0.08)
     */
    public static void createTable()
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        Object rowData[][] = { { "Boeing 787-8 Dreamliner", "248", "250", "2", "3.899", "12.168" },
            { "Boeing 787-8 Dreamliner", "248", "252","3", "5.315", "12.167"}, {"Boeing 787-8 Dreamliner", "248", "256" , "5", "5.483", "10.199"}, {"Boeing 787-8 Dreamliner", "248", "264" , "8", "9.754", "14.181"}};
        Object columnNames[] = { "Airline", "Available Seats", "Seats Sold", "% No Show", "% Overbooking", "% Overbooked by selling one more seat" };
        JTable table = new JTable(rowData, columnNames);
    
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(300, 150);
        frame.setVisible(true);

    }  
    
     /**
     * Creates on instance of AirlineMonteCarlo with hard-coded parameters then runs the simulation, reports the results, and creates a table
     */
    public static void main(String[] args)
    {
      AirlineMonteCarlo mySim = new AirlineMonteCarlo(136,140,0.04);
      mySim.runSimulation();
      mySim.reportResults();
      mySim.createTable();
    }
}
