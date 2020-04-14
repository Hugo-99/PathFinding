import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    public static final double KM_TO_M = 1000;

    CompetitionDijkstra (String filename, int sA, int sB, int sC){
            this.sA = sA;
            this.sB = sB;
            this.sC = sC;

            Edge[] edges = null;
            int index = 0;
            if(filename!=null){
                BufferedReader rd;
                try {
                    rd = new BufferedReader(new FileReader(filename));
                    if(rd!=null){
                        this.V = Integer.parseInt(rd.readLine());
                        this.amount = Integer.parseInt(rd.readLine());
                        edges = new Edge[this.amount];
                        String line = rd.readLine();
                        while (line != null) {
                            String[] mc = new String[3];
                            String[] tmp = line.split("\\ +");
                            if (tmp.length > 3) {
                                System.arraycopy(tmp, 1, mc, 0, mc.length);
                            } else {
                                mc = tmp;
                            }
                            int src = Integer.parseInt(mc[0]);    // src vertex
                            int des = Integer.parseInt(mc[1]);    // des vertex
                            double weight = Double.parseDouble(mc[2]);    // weight
                            Edge e = new Edge(src, des, weight);
                            edges[index] = e;
                            line = rd.readLine();
                            index++;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.edges = edges;
    }


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
        int slowest = Math.min(Math.min(sA,sB),sC);
        int fastest = Math.max(Math.max(sA,sB),sC);
        if(edges==null || slowest<50 || fastest>100){
            return -1;
        }
        double maxDistance = getLongestDistance();
        if(maxDistance<0) return -1;
        return (int) Math.ceil((maxDistance*KM_TO_M)/slowest);
    }

    public double getLongestDistance(){
        double longest = -1;
        for(int i=0; i<V; i++){
            double[] dist = runDijkstra(i);
            for(int j=0; j<V; j++){
                if(i==j) continue;
                if(dist[j]>longest){
                    longest = dist[j];
                }
                if(longest==Double.MAX_VALUE){
                    return -1;
                }
            }
        }
        return longest;
    }

    public double[] runDijkstra(int srcV){
        double[] distanceTo = new double[this.V];
        for(int i=0; i<distanceTo.length; i++) distanceTo[i] = Double.MAX_VALUE;

        MinPriorityQueue pq = new MinPriorityQueue(this.V);

        distanceTo[srcV] = 0;
        pq.put(srcV,0.0);
        while(!pq.isEmpty()){
            int tmp = pq.removeMin();
            ArrayList<Edge> adjacent = getAdjacent(tmp);
            released(adjacent, distanceTo, pq);
        }

        return distanceTo;
    }

    public void released(ArrayList<Edge> adjacent, double[] distanceTo, MinPriorityQueue pq){
        for(Edge e : adjacent){
            int src = e.src();
            int des = e.des();
            if(distanceTo[des]>distanceTo[src]+e.getWeight()){
                distanceTo[des] = distanceTo[src]+e.getWeight();
                if(pq.contains(des)){
                    pq.decreaseKey(des,distanceTo[des]);
                }
                else{
                    pq.put(des,distanceTo[des]);
                }
            }
        }
    }

    public ArrayList<Edge> getAdjacent(int V){
        ArrayList<Edge> adjacent = new ArrayList<>();
        for(Edge e : this.edges){
            if(e.isSrc(V)){
                adjacent.add(e);
            }
        }
        return adjacent;
    }

}