/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class CompetitionFloydWarshall {

    public static final double KM_TO_M = 1000;
    private int sA;
    private int sB;
    private int sC;
    private int V;
    private int amount;
    private double[][] dist;
    private ArrayList<ArrayList<Integer>> combinations;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;

        double[][] dist = null;

        BufferedReader rd;
        try{
            rd = new BufferedReader(new FileReader(filename));
            this.V = Integer.parseInt(rd.readLine());
            this.amount = Integer.parseInt(rd.readLine());

            // initialise the distance 2D array
            dist = new double[this.V][this.V];
            for(int i=0; i<this.V; i++){
                for(int j=0; j<this.V; j++){
                    if(i==j){
                        dist[i][j] = 0;
                    }
                    else{
                        dist[i][j] = Double.MAX_VALUE;
                    }
                }
            }

            String line = rd.readLine();
            while(line!=null){
                String[] mc = new String[3];
                String[] tmp = line.split("\\ +");
                if(tmp.length>3){
                    System.arraycopy(tmp,1,mc,0,mc.length);
                }
                else{
                    mc = tmp;
                }
                int src = Integer.parseInt(mc[0]);    // src vertex
                int des = Integer.parseInt(mc[1]);    // des vertex
                dist[src][des] = Double.parseDouble(mc[2]);    // weight
                line = rd.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        this.dist = dist;
        runFW();
        //printFW();
        System.out.print(timeRequiredforCompetition());
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        int slowest = Math.min(Math.min(sA,sB),sC);
        int fastest = Math.max(Math.max(sA,sB),sC);
        if(dist==null || slowest<50 || fastest>100){
            return -1;
        }
        double maxDistance = getLongestDistance();
        if(maxDistance<0) return -1;
        return (int) Math.ceil((maxDistance*KM_TO_M)/slowest);
    }

    public double getLongestDistance(){
        double longest = -1;
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(i==j) continue;
                if(dist[i][j]>longest){
                    longest = dist[i][j];
                }
                if(longest==Double.MAX_VALUE){
                    return -1;
                }
            }
        }
        return longest;
    }

    public void runFW(){
        for(int k=0; k<this.V; k++){
            for(int i=0; i<this.V; i++){
                for(int j=0; j<this.V; j++){
                    if(this.dist[i][j]>this.dist[i][k]+this.dist[k][j]){
                        this.dist[i][j] = this.dist[i][k]+this.dist[k][j];
                    }
                }
            }
        }
    }

    public void printFW(){
        for(int i=0; i<this.V; i++){
            for(int j=0; j<this.V; j++){
                double tmp = this.dist[i][j];
                if(tmp==Double.MAX_VALUE){
                    System.out.print("inf ");
                }
                else{
                    System.out.print( tmp +"  ");
                }
            }
            System.out.print("\n");
        }
    }
}