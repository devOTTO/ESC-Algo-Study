package week1;

import java.util.LinkedList;
import java.util.Queue;

public class 타겟넘버 {

	static int[] numbers = {1,1,1,1,1};
	static int target = 3;
	
	static class Solution {
		
		static class calc{
			int index;
			int number;
			
			public calc(int index, int number) {
				super();
				this.index = index;
				this.number = number;
			}
		}
		
		static Queue<calc> queue;
		
	    public int solution(int[] numbers, int target) {
	        int answer = 0;
	        
	        queue = new LinkedList<>();
	        
	        queue.offer(new calc(0, numbers[0]));
	        queue.offer(new calc(0, (-1) * numbers[0]));
	        
	        while(!queue.isEmpty()) {
	        	calc now = queue.poll();
	        	
	        	if(now.index + 1 < numbers.length) {
	        		queue.offer(new calc(now.index+1, now.number + numbers[now.index+1]));
	        		queue.offer(new calc(now.index+1, now.number - numbers[now.index+1]));	        		
	        	}else if(now.index + 1 == numbers.length){
	        		if(now.number == target)
	        			answer++;
	        	}
	        }
	        //System.out.println(answer);
	        return answer;
	    }
	}
	
	public static void main(String[] args) {
		
		new Solution().solution(numbers, target);

	}

}
