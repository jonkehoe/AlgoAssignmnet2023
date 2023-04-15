import java.util.*;

public class Dijkstra {
    public static int[] dijkstraSPT(Graph graph, int start) {
        int[] dist = new int[graph.vertices];
        boolean[] visited = new boolean[graph.vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.offer(start);
        while (!pq.isEmpty()) {
            int vertex = pq.poll();
            if (visited[vertex]) {
                continue;
            }
            visited[vertex] = true;
            for (Edge edge : graph.adj[vertex]) {
                int to = edge.to;
                int weight = edge.weight;
                if (dist[vertex] != Integer.MAX_VALUE && dist[vertex] + weight < dist[to]) {
                    dist[to] = dist[vertex] + weight;
                    pq.offer(to);
                }
            }
        }
        return dist;
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
