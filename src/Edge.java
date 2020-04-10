public class Edge {

    private int src;
    private int des;
    private double weight;

    Edge(int src, int des, double weight){
        this.src = src;
        this.des = des;
        this.weight = weight;
    }

    public int src() {
        return src;
    }

    public int des() {
        return des;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isSrc(int V){
        if(this.src == V){
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
