import java.io.File;
import java.util.*;

public class Graph {
    int vertexCt;  // Number of vertices in the graph.
    int[][] capacity;  // Adjacency  matrix.
    String graphName;  //The file from which the graph was created.
    int[][] residual;
    Boolean[] visited;

    public Graph() {
        this.vertexCt = 0;
        this.graphName = "";
    }


    public int getVertexCt() {
        return vertexCt;
    }

    public boolean addEdge(int source, int destination, int cap) {
        if (source < 0 || source >= vertexCt) return false;
        if (destination < 0 || destination >= vertexCt) return false;
        capacity[source][destination] = cap;
        residual[source][destination] = cap;



        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nThe Graph " + graphName + " \n");

        for (int i = 0; i < vertexCt; i++) {
            for (int j = 0; j < vertexCt; j++) {
                sb.append(String.format("%5d", capacity[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void makeGraph(String filename) {
        try {
            graphName = filename;
            Scanner reader = new Scanner(new File(filename));
            vertexCt = reader.nextInt();
            visited = new Boolean[vertexCt];
            capacity = new int[vertexCt][vertexCt];
            residual = new int[vertexCt][vertexCt];
            for (int i = 0; i < vertexCt; i++) {
                for (int j = 0; j < vertexCt; j++) {
                    capacity[i][j] = 0;
                    residual[i][j] = 0;

                }
            }
            while (reader.hasNextInt()) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                int cap = reader.nextInt();
                if (!addEdge(v1, v2, cap))
                    throw new Exception();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean AugmentPath(int a, int b, int[] parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        clearVisited();
        visited[a] = true;
        for (int i = 0; i < vertexCt; i++){
            parent[i] = -1;
        }
        while (!queue.isEmpty() && !visited[b]) {
            int c = queue.poll();
            for (int d = 0; d < vertexCt; d++) {
                if (residual[c][d] > 0 && !visited[d]) {
                    parent[d] = c;
                    visited[d] = true;
                    queue.add(d);
                    if (d == b) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int FordFulkerson() {
        int a = 0;
        int b = vertexCt-1;
        int flowTotal = 0;
        int parent[] = new int[vertexCt];

        while (AugmentPath(a, b, parent)) {
            int availFlow = 999999999;
            String flowPath = " " + b;
            for (int v = b; v != a; v = parent[v]) {

                int prev = parent[v];
                flowPath = " " + prev + flowPath;
                availFlow = Math.min(availFlow, residual[prev][v]);
            }
            System.out.println("found flow " + availFlow + ":" + flowPath);


            for (int v = b; v != a; v = parent[v]) {
                int prev = parent[v];
                residual[prev][v] -= availFlow;
                residual[v][prev] += availFlow;

            }

            flowTotal += availFlow;
        }
        System.out.println("Produced: " + flowTotal);
        System.out.println();

        for (int i = 0; i < vertexCt; i++){
            for (int j = 0; j < vertexCt; j++){
                if (capacity[i][j] > 0 && capacity[i][j] - residual[i][j] > 0){
                    System.out.println("Edge(" + i + "," + j + ") transports: " +
                            (capacity[i][j] - residual[i][j]) + " cases");

                }
            }
        }
        System.out.println();
        return flowTotal;
    }

    public void clearVisited() {
        for (int i = 0; i < vertexCt; i++) {
            visited[i] = false;
        }
    }

    public void minCut(int[][] originalMatrix) {
        clearVisited();
        visit(0);
        System.out.println("Min Cut:");
        for (int i = 0; i < originalMatrix.length; i++) {
            for (int j = 0; j < originalMatrix.length; j++) {
                if (originalMatrix[i][j] > 0 && !visited[j] && (visited[i])) {
                    System.out.println("Edge (" + i + ", " + j
                            + ") transports " + (originalMatrix[i][j]) + " cases");
                }
            }
        }
    }
    public void visit(int a) {
        visited[a] = true;
        for (int i = 0; i < residual.length; i++) {
            if (!visited[i] && residual[a][i] != 0) {
                visit(i);
            }
        }
    }


    public static void main(String[] args) {
        Graph graph0 = new Graph();
        graph0.makeGraph("demands1.txt");
        System.out.print(graph0);
        graph0.FordFulkerson();
        graph0.minCut(graph0.capacity);
    }
}