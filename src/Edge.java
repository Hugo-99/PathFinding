public class Edge {

    private int src;
    private int des;
    private int weight;

    Edge(int src, int des, int weight){
        this.src = src;
        this.des = des;
        this.weight = weight;
    }

    public int getSrc() {
        return src;
    }

    public int getDes() {
        return des;
    }

    public int getWeight() {
        return weight;
    }

    public boolean hasVertex(int V){
        if(this.src == V || this.des == V){
            return true;
        }
        return false;
    }

    public int other(int V){
        if(this.src == V){
            return this.des;
        }
        else {
            return this.src;
        }
    }
}
