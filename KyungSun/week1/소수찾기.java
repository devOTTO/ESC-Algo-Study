package week1;

import java.util.ArrayList;
import java.util.List;

public class 소수찾기 {

	static String input = "17";
	static List<Character> numbersList;
	static List<Integer> result;
	static boolean[] visit;

	static class Solution {
		public int solution(String numbers) {

			int answer = 0;
			numbersList = new ArrayList<>();
			result = new ArrayList<>();
			for (int i = 0; i < numbers.length(); i++) {
				numbersList.add(numbers.charAt(i));
			}

			visit = new boolean[numbersList.size()];

			for (int i = 0; i < numbersList.size(); i++) {
				if (numbersList.get(i) != '0') {
					visit[i] = true;
					DFS(String.valueOf(numbersList.get(i)));
					visit[i] = false;
				}
			}

			answer = result.size();
			//System.out.println(answer);
			return answer;
		}

		public void DFS(String now) {

			int check = Integer.parseInt(now);

			boolean flag = true;

			for (int i = 2; i*i <= check; i++) {
				if ( check % i == 0) {

					flag = false;
					break;
				}
			}

			if (flag == true && check != 1) {
				
				if(!result.contains(check))
					result.add(check);
				
				//System.out.println(check);

			}

			for (int i = 0; i < visit.length; i++) {
				if (visit[i] == false) {
					String next = now + numbersList.get(i);
					visit[i] = true;
					DFS(next);
					visit[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) {

		new Solution().solution(input);

	}

}
