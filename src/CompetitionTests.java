import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {

        String file = "/Users/lihang/Desktop/TCD/Year2/PathFinding/input/input-B.txt";
        CompetitionDijkstra dijkstra = new CompetitionDijkstra(file,1,1,1);
        int[] output = dijkstra.getShortestPath(1);
        for(int i=0; i<output.length; i++){
            System.out.println("From 1 to "+i+" distance is "+output[i]);
        }
    }

    @Test
    public void testFWConstructor() {
        //TODO
    }

    //TODO - more tests

}