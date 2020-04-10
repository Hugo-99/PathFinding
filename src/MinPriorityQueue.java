public class MinPriorityQueue {

    private int[] vertices;
    private double[] dist;
    private int maxSize;
    private int n;

    MinPriorityQueue(int size){
        if(size>0){
            this.vertices = new int[size];
            this.dist = new double[size];
            this.maxSize = size;
            this.n = 0;
            for(int i=0; i<size; i++){
                this.dist[i] = -1;
            }
        }
    }

    public boolean isEmpty(){
        return n==0;
    }

    public int size(){
        return n;
    }

    public boolean contains(int index){
        return dist[index]!=-1;
    }

    public void decreaseKey(int index, double cost){
        if(!contains(index)){
            System.out.println("this vertex is no in the list");
        }
        else{
            dist[index] = cost;
        }
    }

    public int removeMin(){
        if(n==0) return -1;
        int tmp = vertices[0];
        swap(0,n - 1);
        sink(0);
        n--;
        dist[n+1] = -1;
        vertices[n+1] = 0;
        return tmp;
    }

    public void put(int vertex, double cost){
        if(contains(vertex)){
            System.out.print("this vertex is already existed in the list");
            return;
        }
        vertices[n] = vertex;
        dist[n] = cost;
        swim(n);
        n++;
    }

    private void swap(int a, int b){
        int tmpV = vertices[a];
        double tmpD = dist[a];
        vertices[a] = vertices[b];
        dist[a] = this.dist[b];
        vertices[b] = tmpV;
        dist[b] = tmpD;
    }

    private boolean greater(int a, int b){
        return dist[a]>dist[b];
    }

    private void swim(int k){
        while(k>1 && greater(k/2,k)){
            swap(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k){
        while(k*2<=n){
            int j = k*2;
            if(j<n && greater(j,j+1)){
                j++;
            }
            if(!greater(j,j+1)){
                break;
            }
            swap(k,j);
            k=j;
        }
    }
}
