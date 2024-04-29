package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 좌, 우 번갈아가며 막대기 던지기
 * 2. 막대기 닿으면 해당 칸 삭제
 * 3. dfs or bfs로 구역 나누기
 * 4. 바닥 혹은 다른 미네랄에 닿아있지 않은 구역은 떨어트리기
 */
public class BOJ_2933_미네랄 {
    static class Cluster{
        int x;
        int y;

        Cluster(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int R, C, floatClusterCnt;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Cluster[] floatClusters = new Cluster[5000]; //클러스터 많아야 최대 5천개

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = R - 1; i >= 0; i--) map[i] = br.readLine().toCharArray();

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int row = Integer.parseInt(st.nextToken());
            throwStick(row, i % 2);
            visited = new boolean[R][C];
            chackFloatCluster();
        }
    }

    static void throwStick(int row, int player){
        //왼쪽 차래
        if(player == 0){
            for (int i = 0; i < C; i++) {
                if(map[row][i] != '.'){
                    map[row][i] = '.';
                    return;
                }
            }
        }else{ // 오른쪽 차례
            for (int i = C - 1; i >= 0; i--) {
                if(map[row][i] != '.'){
                    map[row][i] = '.';
                    return;
                }
            }
        }
    }

    static void chackFloatCluster(){
        for (int j = 0; j < C; j++) {
            if(map[0][j] != '.' && !visited[0][j]) findNotFloatClusterDfs(0, j);
        }

        floatClusterCnt = 0;

        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[0][j] != '.' && !visited[0][j]){
                    findFloatClusterDfs(i, j, floatClusterCnt);
                }
            }
        }
    }

    static void findNotFloatClusterDfs(int row, int col){
        visited[row][col] = true;

        for (int d = 0; d < 4; d++) {
            int nextRow = row + dr[d];
            int nextCol = col + dc[d];

            if(OOB(nextRow, nextCol)) continue;

            if(map[nextRow][nextCol] != '.' && !visited[nextRow][nextCol]){
                findNotFloatClusterDfs(nextRow, nextCol);
            }
        }
    }

    static void findFloatClusterDfs(int row, int col, int floatClusterCnt){
        visited[row][col] = true;

        for (int d = 0; d < 4; d++) {
            int nextRow = row + dr[d];
            int nextCol = col + dc[d];

            if(OOB(nextRow, nextCol)) continue;

            if(map[nextRow][nextCol] != '.' && !visited[nextRow][nextCol]){
                if(floatClusters[floatClusterCnt] == null) floatClusters[floatClusterCnt] = new Cluster(nextRow, nextCol);
                else{
                    floatClusters[floatClusterCnt].x = nextRow;
                    floatClusters[floatClusterCnt].y = nextCol;
                }
                findFloatClusterDfs(nextRow, nextCol, ++floatClusterCnt);
            }
        }
    }

    static void downFloatCluster(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[0][j] != '.' && !visited[0][j]){
                    findNotFloatClusterDfs(0, j);
                }
            }
        }
    }

    static boolean OOB(int row, int col){
        return row < 0 || row >= R || col < 0 || col > C;
    }
}
