package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution4013 {

	static List[] chain;

	static class turn {

		int target;
		int dir;

		public turn(int target, int dir) {
			super();
			this.target = target;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());
		for (int i = 1; i <= test_case; i++) {

			chain = new ArrayList[4];
			
			int k = Integer.parseInt(br.readLine());

			for (int j = 0; j < 4; j++) {
				chain[j] = new ArrayList<Integer>();

				String[] input = br.readLine().split(" ");
				for (int m = 0; m < 8; m++) {
					
					chain[j].add(Integer.parseInt(input[m]));
				}
				
			} // 초기화

			for (int j = 0; j < k; j++) {

				String[] com = br.readLine().split(" ");
				int target = Integer.parseInt(com[0]);
				int dir = Integer.parseInt(com[1]);
				solve(target - 1, dir); 
				
				/*
				 * System.out.println(); for (int c = 0; c < 4; c++) {
				 * System.out.println(chain[c]); }
				 */
				 
			} // 명령어 수행
			int score = 1;
			int result = 0;
			for (int j = 0; j < 4; j++) {
				if(chain[j].get(0).equals(Integer.valueOf(1))) 
					result += score;
	
				score *= 2;
			} //점수 계산
			System.out.println("#"+i +" "+result);

		}
	}

	public static void solve(int target, int dir) {

		Queue<turn> action = new LinkedList<>(); // 회전할 톱니

		action.offer(new turn(target, dir));
		int sdir = dir;
		for (int i = target; i < 3; i++) {

			if (!chain[i].get(2).equals(chain[i + 1].get(6))) {
				dir *= -1;
				action.offer(new turn(i + 1, dir));
			} else
				break;
			
			
		} // target의 오른쪽 방향 극성 비교, 다르면 큐에 넣음 

		dir = sdir; // 초기방향 
		for (int i = target; i > 0; i--) {

			if (!chain[i].get(6).equals(chain[i - 1].get(2))) {
				dir *= -1;
				action.offer(new turn(i - 1, dir));
			} else
				break;
			
			
		} // target의 왼쪽방향 

		while(!action.isEmpty()) { // 큐에서 빼면서 톱니를 돌림 
			turn now = action.poll();
			
			int t = now.target;
			int d = now.dir;
			
			if(d == 1) { // 시계방향
				int temp = (int) chain[t].get(7);
				chain[t].remove(7);
				chain[t].add(0, temp);
				
			}else if(d == -1) { // 반시계방향 
				int temp = (int) chain[t].get(0);
				chain[t].remove(0);
				chain[t].add(temp);
				
			}
			
			
		}
	}

}
