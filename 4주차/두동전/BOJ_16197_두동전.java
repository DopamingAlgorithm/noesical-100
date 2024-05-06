package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16197_두동전 {
    static int R, C, answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] move = new int[10];
    static char[][] map;
    static int[] coin1Start;
    static int[] coin2Start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        answer = -1;
        map = new char[R][C];
        int coinCnt = 0;

        for(int r = 0; r < R; r++){
            map[r] = br.readLine().toCharArray();
            for(int c = 0; c < C; c++){
                if(coinCnt == 2) break;

                if(map[r][c] == 'o'){
                    if(coinCnt == 0) coin1Start = new int[]{r, c};
                    else coin2Start = new int[]{r, c};
                    coinCnt++;
                }
            }
        }
        permutation(0);

        System.out.println(answer);
    }

    static void permutation(int moveIdx){
        if(moveIdx == 10){
            int moveCnt = moveCoin();
            if(answer == -1) answer = moveCnt;
            else if(answer > moveCnt && moveCnt != -1) answer = moveCnt;
            return;
        }

        for (int m = 0; m < 4; m++) {
            move[moveIdx] = m;
            permutation(moveIdx + 1);
        }
    }

    static int moveCoin(){
        boolean isCoin1Out = false;
        boolean isCoin2Out = false;
        int coin1Row = coin1Start[0];
        int coin1Col = coin1Start[1];
        int coin2Row = coin2Start[0];
        int coin2Col = coin2Start[1];
        int cnt = 0;

        for(int d : move){
            cnt++;

            if(!isCoin1Out){
                coin1Row += dr[d];
                coin1Col += dc[d];

                if(checkCoinDrop(coin1Row, coin1Col)) isCoin1Out = true;

                else{
                   if(!checkMoveAble(coin1Row, coin1Col)){
                       coin1Row -= dr[d];
                       coin1Col -= dc[d];
                   }
                }
            }

            if(!isCoin2Out){
                coin2Row += dr[d];
                coin2Col += dc[d];

                if(checkCoinDrop(coin2Row, coin2Col)) isCoin2Out = true;
                else{
                    if(!checkMoveAble(coin2Row, coin2Col)){
                        coin2Row -= dr[d];
                        coin2Col -= dc[d];
                    }
                }
            }

            if(isCoin1Out != isCoin2Out) break;
        }

        if(isCoin1Out != isCoin2Out) return cnt;
        else return -1;
    }

    static boolean checkMoveAble(int row, int col){
        return map[row][col] != '#';
    }

    static boolean checkCoinDrop(int row, int col){
        return row < 0 || row >= R || col < 0 || col >= C;
    }

}
