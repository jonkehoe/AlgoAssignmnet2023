import java.util.*;

public class Prim {
    public static List<Edge> primMST(Graph graph, int start) {
        List<Edge> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.vertices];
        int[] dist = new int[graph.vertices];
        int[] parent = new int[graph.vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(-1, start, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.to]) {
                continue;
            }
            visited[edge.to] = true;
            parent[edge.to] = edge.from;
            result.add(edge);
            for (Edge e : graph.adj[edge.to]) {
                if (!visited[e.to] && e.weight < dist[e.to]) {
                    dist[e.to] = e.weight;
                    pq.offer(e);
                }
            }
        }
        return result;
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
