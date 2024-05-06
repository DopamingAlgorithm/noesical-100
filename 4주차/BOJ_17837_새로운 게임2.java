import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 말이 4개 이상 쌓이면 게임 끝
 *
 * A번 말이 이동하려는 칸이 흰색이면 이동
 * 이미 있으면 -> 위에 놓기
 *
 */

public class BOJ_17837_새로운게임2 {

    private static int N,K;
    private static int[][] color, horse;
    private static LinkedList<Integer>[][] map;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N][N];
        horse = new int[K][3];
        map = new LinkedList[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = new LinkedList<>();
            }
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r, c, d;
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            d = Integer.parseInt(st.nextToken());

            if(d==1)d=0;
            else if(d==4)d=1;

            horse[i][0] = r;
            horse[i][1] = c;
            horse[i][2] = d;

            map[r][c].add(i);
        }
        game();
    }

    private static void game(){
        for(int t = 1; t <= 1000; t++){
            for(int k = 0; k < K; k++){
                int r = horse[k][0];
                int c = horse[k][1];
                int d = horse[k][2];
                int num = search(k, r, c);

                int nr = r + dx[d];
                int nc = c + dy[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N || color[nr][nc] == 2){
                    horse[k][2] = d = (d+2)%4;
                    nr = r+dx[d];
                    nc = c+dy[d];

                    if(nr < 0 || nr>=N || nc<0 || nc>=N || color[nr][nc]==2) continue;
                }

                if(move(r,c,nr,nc,num,color[nr][nc])){
                    System.out.println(t);
                    return;
                }
            }
        }
        System.out.println("-1");
    }

    private static boolean move(int r, int c, int nr, int nc, int num, int order){
        while(map[r][c].size() > num){
            int temp = -1;
            if(order == 0){
                temp = map[r][c].remove(num);
            } else {
                temp = map[r][c].removeLast();
            }

            horse[temp][0] = nr;
            horse[temp][1] = nc;
            map[nr][nc].add(temp);
        }

        if(map[nr][nc].size() >= 4) return true;
        return false;
    }

    private static int search(int n, int r, int c){
        for(int i = 0; i < map[r][c].size(); i++){
            if(map[r][c].get(i) == n){
                return i;
            }
        }
        return -1;
    }

}
