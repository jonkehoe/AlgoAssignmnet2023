import java.util.*;

public class Kruskal {
    public static List<Edge> kruskalMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        int[] parent = new int[graph.vertices];
        for (int i = 0; i < graph.vertices; i++) {
            parent[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        for (List<Edge> edges : graph.adj) {
            pq.addAll(edges);
        }
        while (!pq.isEmpty() && result.size() < graph.vertices - 1) {
            Edge edge = pq.poll();
            int parent1 = find(parent, edge.from);
            int parent2 = find(parent, edge.to);
            if (parent1 != parent2) {
                result.add(edge);
                parent[parent1] = parent2;
            }
        }
        return result;
    }

    private static int find(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }
}

class Graph {
    int vertices;
    List<Edge>[] adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        adj = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to, int weight) {
        adj[from].add(new Edge(from, to, weight));
        adj[to].add(new Edge(to, from, weight));
    }
}

class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
