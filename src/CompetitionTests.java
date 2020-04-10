import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {

        String file = "/Users/lihang/Desktop/TCD/Year2/PathFinding/input/input-B.txt";
        CompetitionDijkstra dijkstra = new CompetitionDijkstra(file,1,1,1);

    }

    @Test
    public void testFWConstructor() {
        String file = "/Users/lihang/Desktop/TCD/Year2/PathFinding/input/input-M.txt";
        CompetitionFloydWarshall fw = new CompetitionFloydWarshall(file, 1,1,1);

    }

    //TODO - more tests
//    @Test
//    public void test(){
//        String t = "  4 653 0.03590000";
//        String[] last = new String[3];
//        String[] tmp = t.split("\\ +");
//        int index = 0;
//        if(tmp.length==4){
//
//            System.arraycopy(tmp,1,last,0,last.length);
//        }
//        else{
//            last = tmp;
//        }
//        for(String x : last){
//            System.out.println(x);
//        }
//        //System.out.print(a+"-"+b+"-"+c);
//    }


}