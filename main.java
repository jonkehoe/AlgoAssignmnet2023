import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the name of the file containing the graph: ");
        String filename = input.nextLine();
        System.out.print("Enter the starting vertex: ");
        int startVertex = input.nextInt();

        try {
            Graph graph = readGraphFromFile(filename);
            int[] spt = Dijkstra.dijkstraSPT(graph, startVertex);
            System.out.println("Shortest path tree from vertex " + startVertex + ":");
            for (int i = 0; i < graph.vertices; i++) {
                System.out.println(i + ": " + spt[i]);
            }
            System.out.println();
            System.out.println("Minimum spanning tree using Kruskal's algorithm:");
            for (Edge edge : Kruskal.kruskalMST(graph)) {
                System.out.println(edge.from + " -- " + edge.to + " : " + edge.weight);
            }
            System.out.println();
            System.out.println("Minimum spanning tree using Prim's algorithm:");
            for (Edge edge : Prim.primMST(graph, startVertex)) {
                System.out.println(edge.from + " -- " + edge.to + " : " + edge.weight);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format.");
        }
    }

    public static Graph readGraphFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int vertices = scanner.nextInt();
        Graph graph = new Graph(vertices);
        int edges = scanner.nextInt();
        for (int i = 0; i < edges; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(from, to, weight);
        }
        scanner.close();
        return graph;
    }
}


