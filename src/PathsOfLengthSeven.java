import java.util.*;

public class PathsOfLengthSeven {
    // Edge class to store destination vertex and edge weight
    static class Edge {
        int to;
        int weight;
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Adjacency list representation
    private final Map<Integer, List<Edge>> graph = new HashMap<>();

    public static void main(String[] args) {
        PathsOfLengthSeven solver = new PathsOfLengthSeven();
        solver.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);

        // Read number of vertices and edges
        System.out.print("Number of vertices, edges: ");
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Build the graph
        System.out.println("Enter edges (from to weight):");
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to   = sc.nextInt();
            int w    = sc.nextInt();
            graph.computeIfAbsent(from, k -> new ArrayList<>())
                    .add(new Edge(to, w));
        }

        // Read start and end vertices
        System.out.print("Start vertex (u): ");
        int u = sc.nextInt();
        System.out.print("End vertex (w): ");
        int w = sc.nextInt();

        // Prepare for DFS
        boolean[] visited = new boolean[n + 1];      // assume vertices are 1..n
        List<Integer> path = new ArrayList<>();
        path.add(u);
        visited[u] = true;

        System.out.println("\nAll simple paths from " + u + " to " + w + " with total weight = 7:");
        dfs(u, w, 0, visited, path);

        sc.close();
    }


    private void dfs(int current, int target, int weightSum,
                     boolean[] visited, List<Integer> path) {
        // If we've reached the target and sum == 7, print the path
        if (current == target) {
            if (weightSum == 7) {
                System.out.println(path);
            }
            // Regardless of sum, don’t extend beyond the target
            return;
        }

        // Prune any path whose sum already exceeds 7
        if (weightSum > 7) return;

        // Explore outgoing edges
        List<Edge> edges = graph.getOrDefault(current, Collections.emptyList());
        for (Edge e : edges) {
            if (!visited[e.to]) {
                int newSum = weightSum + e.weight;
                if (newSum <= 7) {
                    // Choose
                    visited[e.to] = true;
                    path.add(e.to);

                    // Explore
                    dfs(e.to, target, newSum, visited, path);

                    // Un‐choose
                    path.remove(path.size() - 1);
                    visited[e.to] = false;
                }
            }
        }
    }
}
