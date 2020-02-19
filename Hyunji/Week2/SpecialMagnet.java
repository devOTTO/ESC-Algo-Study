package Hyunji.Week2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SpecialMagnet {
    static int[][] map = new int[4][8];
    static int[] count = {1,2,4,8};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = Integer.parseInt(sc.nextLine());
        int[] testResult = new int[testCase];

        for(int i=0; i<testCase; i++){
            int rotateNum = Integer.parseInt(sc.nextLine());

            for(int j=0; j<4; j++){
                String[] temp = sc.nextLine().split(" ");
                for(int k=0; k<8; k++){
                    map[j][k] = Integer.parseInt(temp[k]);
                }
            }

            for(int j=0; j<rotateNum; j++){
                String[] temp = sc.nextLine().split(" ");

                int index = Integer.parseInt(temp[0]);
                int dir = Integer.parseInt(temp[1]);

                solve(index-1, dir);
            }

            for(int j=0; j<4; j++){
                if(map[j][0] == 1){
                    testResult[i] += count[j];
                }
            }
        }

        for(int i=0; i<testCase; i++){
            System.out.println("#"+(i+1)+" "+testResult[i]);
        }

    }

    public static void solve(int index, int dir){
        Queue<circle> q = new LinkedList<>();
        boolean[] visited = new boolean[4];

        q.add(new circle(index, dir));
        visited[index] = true;

        while(!q.isEmpty()){
            circle c = q.poll();

            if(c.num-1 >= 0){  // left check
                int curr6 = map[c.num][6];
                int check2 = map[c.num-1][2];

                if(curr6 != check2 && !visited[c.num-1]){
                    q.add(new circle(c.num-1, (c.dir * -1) ));
                    visited[c.num-1] = true;
                }
            }
            if((c.num+1) < 4){  // right check
                int curr2 = map[c.num][2];
                int check6 = map[c.num+1][6];

                if(curr2 != check6 && !visited[c.num+1]){
                    q.add(new circle(c.num+1, (c.dir * -1) ));
                    visited[c.num+1] = true;
                }
            }
            rotate(c.num, c.dir);
        }
    }

    public static void rotate(int index, int dir){
        if(dir == 1){   // clockwise
            int oldFirstValue = map[index][7];

            for(int i=7; i>0; i--){
                map[index][i] = map[index][i-1];
            }
            map[index][0] = oldFirstValue;
        }

        if(dir == -1){   // counterClockwise
            int oldLastValue = map[index][0];

            for(int i=0; i<7; i++){
                map[index][i] = map[index][i+1];
            }
            map[index][7] = oldLastValue;
        }
    }

    public static class circle{
        int num;
        int dir;

        public circle(int num, int dir){
            this.dir = dir;
            this.num = num;
        }
    }
}
