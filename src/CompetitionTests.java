import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Dijkstra's Algorithm
 *  I use a min priority queue for sorting the edges and weight of the intersections.
 *  I also implement an Edge class to store the data more efficiently.
 *  The time complexity of this algorithm is O(V + ElogV).
 *  For the timeRequiredforCompetition method, the time complexity is O(V^2).
 *
 * Floyd Warshall's Algorithm
 *  This algorithm works on a 2D array with 3 nested loop.
 *  The time complexity of this algorithm is O(V^3).
 *  For the timeRequiredforCompetition method, the time complexity is O(V^2).
 */

@RunWith(JUnit4.class)
public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {

        String file = "input-N.txt";
        CompetitionDijkstra dijkstra = new CompetitionDijkstra(file,50,60,70);
        int output = dijkstra.timeRequiredforCompetition();
        assertEquals("The time required for competition with normal speed:",160,output);

        file = "input-E.txt";
        dijkstra = new CompetitionDijkstra(file,50,60,70);
        output = dijkstra.timeRequiredforCompetition();
        assertEquals("The time required for competition with normal speed:",28,output);

        dijkstra = new CompetitionDijkstra(file,1,1,1);
        output = dijkstra.timeRequiredforCompetition();
        assertEquals("The time required for competition with speed below 50:",-1,output);

        dijkstra = new CompetitionDijkstra(file,200,200,200);
        output = dijkstra.timeRequiredforCompetition();
        assertEquals("The time required for competition with speed above 100:",-1,output);

        file = null;
        dijkstra = new CompetitionDijkstra(file,50,50,50);
        output = dijkstra.timeRequiredforCompetition();
        assertEquals("The time required for competition for filename with null",-1,output);
    }

    @Test
    public void testFWConstructor() {
        String file = "input-N.txt";
        CompetitionFloydWarshall fw = new CompetitionFloydWarshall(file, 50,60,70);
        int output = fw.timeRequiredforCompetition();
        assertEquals("The time required for competition:",160,output);

        file = "input-E.txt";
        fw = new CompetitionFloydWarshall(file,50,60,70);
        output = fw.timeRequiredforCompetition();
        assertEquals("The time required for competition with normal speed:",28,output);

        fw = new CompetitionFloydWarshall(file,1,1,1);
        output = fw.timeRequiredforCompetition();
        assertEquals("The time required for competition with speed below 50:",-1,output);

        fw = new CompetitionFloydWarshall(file,200,200,200);
        output = fw.timeRequiredforCompetition();
        assertEquals("The time required for competition with speed above 100:",-1,output);

        file = null;
        fw = new CompetitionFloydWarshall(file,50,50,50);
        output = fw.timeRequiredforCompetition();
        assertEquals("The time required for competition for filename with null:",-1,output);
    }


}