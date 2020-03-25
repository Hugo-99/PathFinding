import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */

    private int sA;
    private int sB;
    private int sC;
    private int V;
    private int amount;
    private Edge[] edges;

    CompetitionDijkstra (String filename, int sA, int sB, int sC){
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;

        Edge[] edges = null;
        int index = 0;

        BufferedReader rd;
        int[] E = new int[3];
        try{
            rd = new BufferedReader(new FileReader(filename));
            this.V = Integer.parseInt(rd.readLine());
            this.amount = Integer.parseInt(rd.readLine());
            edges = new Edge[this.amount];
            String line = rd.readLine();
            while(line!=null){
                String[] tmp = line.split(" ");
                E[0] = Integer.parseInt(tmp[0]);    // src vertex
                E[1] = Integer.parseInt(tmp[1]);    // des vertex
                E[2] = Integer.parseInt(tmp[2]);    // weight
                Edge e = new Edge(E[0],E[1],E[2]);
                edges[index] = e;
                line = rd.readLine();
                index++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        this.edges = edges;
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

    public int[] getShortestPath(int srcV){
        isValidVertex(srcV);
        int[] distanceTo = new int[this.V];
        for(int i=0; i<distanceTo.length; i++) distanceTo[i] = Integer.MAX_VALUE;

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Set<Edge> isVisited = new HashSet<>();

        distanceTo[srcV] = 0;
        pq.add(new Vertex(srcV,0));
        while(!pq.isEmpty()){
            Vertex tmp = pq.remove();
            ArrayList<Edge> adjEdges = getAdjacent(tmp);
            for(Edge e : adjEdges){

            }
        }

        return null;
    }

    public void released(){

    }

    public void isValidVertex(int vertex){
        if(vertex<0 || vertex>=V){
            throw new IllegalArgumentException("Vertex "+ vertex + "is outside the given range.");
        }
    }

    public ArrayList<Edge> getAdjacent(Vertex V){
        ArrayList<Edge> adjacent = new ArrayList<>();
        for(Edge e : this.edges){
            if(e.hasVertex(V.vertex)){
                adjacent.add(e);
            }
        }
        return adjacent;
    }

}