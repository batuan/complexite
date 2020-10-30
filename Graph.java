import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Graph {
    int[][] A;
    int n;

    public Graph(int numSommet) {
        this.n = numSommet;
        A = new int[numSommet][numSommet];
    }

    void addEdge(int i, int j){
        A[i-1][j-1] = 1;
        A[j-1][i-1] = 1;
    }

    void removeEdge(int i, int j) {
        A[i-1][j-1] = 0;
        A[j-1][i-1] = 0;
    }

    int[][] complementGraph() {
        int[][] B = new int[A.length][A.length];
        for(int i = 0; i < B.length; i++){
            for (int j = 0; j < B.length; j++) {
                B[i][j] = 1 - A[i][j];
                if(i == j) B[i][j] = 0;
            }
        }
        return B;
    }

    void printGraph(int[][] A){
        for(int i = 0; i < A.length; i++){
            for (int j = 0; j < A.length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(1,3 );
        graph.addEdge(1,4);
        graph.addEdge(2, 3);
        graph.addEdge(2,5);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        //System.out.println(graph.testZoneVide(new int[]{1, 1, 0, 0, 0}));
        //System.out.println(graph.testZoneVideMaximaleComplete(new int[]{1, 1, 0, 0, 0, 1}));
        //graph.printGraph(graph.A);
        //graph.printGraph(graph.complementGraph());
        graph.testZoneVideMaximaleIncomplete(graph.A);
    }

    boolean testZoneVide(int[] X){
        for (int i = 0; i < n; i++) {
            boolean trouver = false;
            for (int j = i; j < n; j ++){
                if((X[i] == 1) && (X[j] == 1) && (A[i][j] == 1)){
                    return false;
                }
            }
        }
        return true;
    }

    boolean testZoneVideMaximale(int[] X){
        if(testZoneVide(X)) {
            System.out.println("X est zone Vide");
        } else {
            System.out.println("X n'est pas zone vide");
            return false;
        }
        for(int i = 0; i < n; i++) {
            if(X[i] == 0) {
                X[i] = 1;
                boolean result = testZoneVide(X);
                if (result) return false;
                else X[i] = 0;
            }
        }
        return true;
    }

    boolean testZoneVideMaximaleComplete(int[] X) {
        if(testZoneVide(X)) {
            System.out.println("X est zone Vide");
        } else {
            System.out.println("X n'est pas zone vide");
            return false;
        }
        int count = 0;
        for (int i = 0; i < n; i ++) {
            if (X[i] == 1) {
                count ++;
            }
        }
        int[] test = new int[n];
        for(int i = 0; i < n; i++) {
            test[i] = 0;
        }
        int[] result = trouverZoneVide(count + 1,0, test);
        if(result!=null) {
            System.out.println("X n'est pas zone vide maximal, la zone vide maximal est: ");
            for(int i = 0; i < X.length; i++) {
                System.out.print(result[i] + " ");
            }
            return false;
        } else return true;
    }

    int numberTest = 0;
    int[] trouverZoneVide(int k, int x, int[] X) {
        if((x <= (n - 1))) {
            X[x] = 1;
            int[] result = trouverZoneVide(k, x+1, X);
            if (result != null) return result;
            X[x] = 0;
            result = trouverZoneVide(k, x+1, X);
            if (result != null) return result;
        } else {
            numberTest++;
//            for(int i = 0; i < X.length; i++) {
//                System.out.print(X[i] + " ");
//            }
            //System.out.println();
            int count = 0;
            for (int i = 0; i < n; i ++){
                if (X[i] == 1) count += 1;
            }
            if(count == k) {
                boolean kq = testZoneVide(X);
                if (kq) return X;
            }
        }
        return null;
    }

    //Greedy Algorithm

    /**
     * Algorithm 1 Greedy
     * Require: a graph G = (V, E)
     * ==============================
     * W ← V
     * S ← ∅
     * while W != ∅ do
     *  Find a vertex v ∈ W with minimum degree in G[W]
     *  W ← W \ NG[v]
     *  S ← S ∪ {v}
     * end while
     * return S
     * ==============================
     * NG[v] = neighbourhood of vertex v and v;
     * @param A
     * @return
     */
    boolean testZoneVideMaximaleIncomplete(int[][] A) {
        int[] zoneVide = new int[A.length];
        int[] setVertex = new int[A.length];
        Arrays.fill(setVertex, 1);
        int sum = IntStream.of(setVertex).sum();
        while(sum != 0) {
            //Find a vertex v ∈ W with minimum degree in G[W]
            int vertex = minDegree(A, setVertex);
            zoneVide[vertex] = 1;
            setVertex[vertex] = 0;
            for(int i = 0; i < n; i++) {
                if(A[vertex][i]==1) setVertex[i]=0;
            }
            sum = IntStream.of(setVertex).sum();
        }
        for(int i = 0; i < n; i++) {
            System.out.print(zoneVide[i] + " ");
        }
        System.out.println();
        return true;
    }

    int minDegree(int[][] graph, int[] subtractVertex) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < graph.length; i++) {
            if (subtractVertex[i] == 0) {
            }
            else {
                int degree = 0;
                for(int j = 0; j < graph.length; j++) {
                    if(subtractVertex[i] !=0 && graph[i][j] == 1) degree++;
                }
                if (degree < min) {
                    min = degree;
                    index = i;
                }
            }
        }
        return index;
    }


}
