package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5656 {

	static int N, W, H , result;
	static int[][] map;
	static Queue<ball> queue;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static class ball {
		int y;
		int x;
		int dist;

		public ball(int y, int x, int dist) {
			super();
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		queue = new LinkedList<>();

		
		for (int i = 1; i <= test_case; i++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			result = Integer.MAX_VALUE;
			map = new int[H + 1][W + 1];
			for (int j = 1; j <= H; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= W; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			} // 입력값 초기화

			DFS(N);
			
			System.out.println("#"+i +" "+ result);

		}

	}

	public static void DFS(int count) { //완전탐색

		
		
		if (count == 0) { // 탐색 후 최소값 갱신

			int sum = 0;
			
			
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= W; j++) {
					
					if(map[i][j] != 0)
						sum++;
				}
				
			}
			
			result = Math.min(result, sum);
			return;
		}
		



		for (int i = 1; i <= W; i++) {

			int[][] copy = new int[H + 1][W + 1];
			
			
			
			for (int m = 0; m <= H; m++) { // 현재 depth의 map 복사
				copy[m] = Arrays.copyOf(map[m], W+1);
			}
			
			
			for (int j = 1; j <= H; j++) { // 완전 탐색 
				if (map[j][i] != 0) {

					queue.offer(new ball(j, i, map[j][i]));
					break;
				}
			}

			while (!queue.isEmpty()) { // 연쇄적으로 폭발하는 블럭을 탐색후 제거 
				ball now = queue.poll();

				map[now.y][now.x] = 0;
				
				
				for (int d = 0; d < 4; d++) {

					int ny = now.y;
					int nx = now.x;
					int dist = 0;
					while (true) {
						dist++;
						ny += dy[d];
						nx += dx[d];

						if (isRange(ny, nx) == false) 
							break;

						if (dist >= now.dist)
							break;

						if (map[ny][nx] > 1) {
							queue.offer(new ball(ny, nx, map[ny][nx]));
						}
						
						map[ny][nx] = 0;

					}
				}
			}

						
			
			for (int j = 1; j <= W; j++) { // 폭발 후 블럭을 내림
				int[] down = new int[H + 1];
				int index = H;
				
				for (int k = H; k >= 1; k--) {
					
					if (map[k][j] != 0)
						down[index--] = map[k][j];
				}
				for (int k = H; k >= 1; k--) {
					map[k][j] = down[k];
				}
			}
			

			DFS(count - 1);
			
			for (int m = 0; m <= H; m++) { // map 복구  
				map[m] = Arrays.copyOf(copy[m], W+1);
			}
			
		}
		

	}

	public static boolean isRange(int ny, int nx) { // 범위 확인 메소드

		if (ny < 1 || ny > H || nx < 0 || nx > W)
			return false;
		return true;
	}

}
