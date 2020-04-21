import java.util.Scanner;
import java.io.PrintStream;

public class Yachke {

	static int computeMax(int B, int n, int[] cost) {
		// Preconditions: cost[0..n-1] contains positive integers where c[i] is the
		// minimum cost of a property in segment i of Yonge Street. B is a positive
		// integer.
		// Postcondition: returns the maximum number of properties Mrs. Yachke
		// can buy within a budget of B such that no two properties are in adjacent
		// segments of Yonge Street.

		// By utilizing The Knapsack problem we did in class
		int[][] M = new int[n][B + 1];
		// initialize the table
		for (int i = 0; i <= B; i++) {
			M[0][i] = 0;
		}
		for (int i = 0; i < n; i++) {
			M[i][0] = 0;
		}
		// case when you only have 2
		for (int i = 0; i <= B; i++) {
			if (cost[0] <= i) {
				M[0][i] = 1;
			} else {
				M[0][i] = 0;
			}
			if (cost[0] < cost[1] && cost[0] <= i) {
				M[1][i] = 1;
			} else if (cost[1] <= i) {
				M[1][i] = 1;
			} else {
				M[1][i] = 0;
			}
		}

		for (int i = 2; i < n; i++) {
			for (int j = 0; j <= B; j++) {
				if (cost[i] > j) {
					M[i][j] = M[i - 1][j];
				} else {
					M[i][j] = Math.max(M[i - 1][j], 1 + M[i - 2][j - cost[i]]);
				}
			}
		}

		return M[n - 1][B];
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintStream out = System.out;

		int B = in.nextInt();
		while (B > 0) {
			int n = in.nextInt();
			int[] cost = new int[n];
			for (int i = 0; i < n; i++)
				cost[i] = in.nextInt();
			out.println(computeMax(B, n, cost));
			B = in.nextInt();
		}
	}
}