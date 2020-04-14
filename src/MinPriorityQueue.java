public class MinPriorityQueue {

    private int[] vertices;
    private double[] dist;
    private int[] index;    // index start from 1
    private int n;

    MinPriorityQueue(int size){
        if(size>0){
            this.vertices = new int[size+1];
            this.dist = new double[size+1];
            this.index = new int[size+1];
            this.n = 0;
            for(int i=0; i<size; i++){
                this.index[i] = -1;
            }
        }
    }

    public boolean isEmpty(){
        return n==0;
    }

    public boolean contains(int vertex){
        return index[vertex]!=-1;
    }

    public void decreaseKey(int vertex, double cost){
        dist[vertex] = cost;
        swim(index[vertex]);
    }

    public int removeMin(){
        if(n==0) return -1;
        int tmp = vertices[1];
        swap(1,n--);
        sink(1);
        index[tmp] = -1;
        dist[tmp] = 0;
        vertices[n+1] = -1;
        return tmp;
    }

    public void put(int vertex, double cost){
        n++;
        index[vertex] = n;
        vertices[n] = vertex;
        dist[vertex] = cost;
        swim(n);
    }

    private void swap(int a, int b){
        int tmp = vertices[a];
        vertices[a] = vertices[b];
        vertices[b] = tmp;
        index[vertices[a]] = a;
        index[vertices[b]] = b;
    }

    private boolean greater(int a, int b){
        return dist[vertices[a]]>dist[vertices[b]];
    }

    private void swim(int k){
        while(k>1 &&  greater(k/2,k)){
            swap(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k){
        if(n<1) return;
        while(k*2<=n){
            int j = k*2;
            if(j<n  && greater(j,j+1)){
                j++;
            }
            else if(!greater(k,j)){
                break;
            }
            swap(k,j);
            k=j;
        }
    }
}
