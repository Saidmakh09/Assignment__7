import java.util.Scanner;

public class DirectedGraphMatrixChecker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the size of the matrix
        System.out.print("Enter n (matrix size): ");
        int n = sc.nextInt();

        int[][] mat = new int[n][n];
        System.out.println("Enter the " + n + "x" + n + " matrix entries (row by row):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        if (isValidDirectedAdjacencyMatrix(mat)) {
            System.out.println("Yes — this is a valid adjacency matrix for a directed graph.");
        } else {
            System.out.println("No — this cannot represent a simple directed graph (entries must be 0 or 1).");
        }

        sc.close();
    }


    //Checks whether the given square matrix is a valid adjacency matrix
    //for a simple directed graph

    private static boolean isValidDirectedAdjacencyMatrix(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            if (mat[i].length != n) {
                // not square
                return false;
            }
            for (int j = 0; j < n; j++) {
                int v = mat[i][j];
                if (v != 0 && v != 1) {
                    // adjacency matrix for a simple directed graph
                    // must use only 0 (no edge) or 1 (edge)
                    return false;
                }
            }
        }
        return true;
    }
}
