package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇 {
    static class Belt{
        int beltNum;
        int durability;
        boolean isRobotOn;

        Belt(int beltNum, int durability){
            this.beltNum = beltNum;
            this.durability = durability;
            this.isRobotOn = false;
        }

    }

    static int N, K;
    static Belt[] map; //0과 N - 1번에서 승/하차
    static boolean[] checkRobots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Belt[2 * N];
        checkRobots = new boolean[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 2 * N; i++) map[i] = new Belt(i, Integer.parseInt(st.nextToken()));

        int answer = 0;
        while(K > checkDurability()){
            answer++;
            moveMap();
            moveRobot();
            onLoad();
        }

        System.out.println(answer);
    }


    public static void moveMap(){
        Belt temp = map[(2 * N) - 1];
        for (int i = (2 * N) - 1; i > 0; i--) map[i] = map[i - 1];
        map[0] = temp;

        for (int i = N - 1; i > 0; i--) checkRobots[i] = checkRobots[i - 1];
        offLoad();
        checkRobots[0] = false;
    }

    public static void moveRobot(){
        for (int idx = checkRobots.length - 1; idx >= 0; idx--) {
            if(!checkRobots[idx]) continue;

            int nextIdx = idx + 1;
            if(nextIdx == 2 * N) nextIdx = 0;

            if(map[nextIdx].durability > 0 && !map[nextIdx].isRobotOn){
                map[idx].isRobotOn = false;
                map[nextIdx].isRobotOn = true;
                map[nextIdx].durability -= 1;
                checkRobots[idx] = false;
                checkRobots[nextIdx] = true;
            }
        }
        offLoad();
    }

    public static void onLoad(){
        if(map[0].durability > 0){
            checkRobots[0] = true;
            map[0].durability -= 1;
            map[0].isRobotOn = true;
        }
    }

    public static void offLoad(){
        if(map[N - 1].isRobotOn){
            map[N - 1].isRobotOn = false;
            checkRobots[N - 1] = false;
        }
    }

    public static int checkDurability(){
        int cnt = 0;
        for (Belt belt : map) {
            if(belt.durability == 0) cnt++;
        }

        return cnt;
    }
}
