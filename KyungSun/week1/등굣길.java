package week1;

public class 등굣길 {

	static int m = 4;
	static int n = 3;
	static int[][] puddles = { { 2, 2 } };

	static class Solution {
		static int[][] dp;
		static int mod = 1000000007;
		
		public int solution(int m, int n, int[][] puddles) {

			dp = new int[n + 1][m + 1];

			for (int i = 0; i < puddles.length; i++) {
				dp[puddles[i][1]][puddles[i][0]] = -1;
			}

			solve(n, m);

			int answer = dp[n][m];

			/*
			 * for (int i = 1; i <= n; i++) { for (int j = 1; j <= m; j++) {
			 * System.out.print(dp[i][j] + " "); } System.out.println(); }
			 */

			return answer;
		}

		public int solve(int n, int m) {

			if (dp[n][m] == -1)
				return 0;
			else if (dp[n][m] != 0)
				return dp[n][m];
			else if (m == 1 && n == 1)
				return 1;
			else if (n == 1)
				return dp[n][m] = solve(n, m - 1);
			else if (m == 1)
				return dp[n][m] = solve(n - 1, m);
			else
				return dp[n][m] = (solve(n - 1, m) + solve(n, m - 1)) % mod;
		}
	}

	public static void main(String[] args) {

		new Solution().solution(m, n, puddles);

	}

}
