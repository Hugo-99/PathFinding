import java.util.Comparator;

public class Vertex implements Comparator<Vertex> {

    public int vertex;
    public int cost;

    Vertex(){

    }

    Vertex(int vertex, int cost){
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compare(Vertex v1, Vertex v2) {
        if (v1.cost < v2.cost)
            return -1;
        if (v1.cost > v2.cost)
            return 1;
        return 0;
    }
}
