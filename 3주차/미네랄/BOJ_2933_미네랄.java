import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 동굴은 R행 C열
 * 각 칸은 비어있거나 미네랄 포함, 네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은 클러스터
 *
 * 창영은 동굴 왼쪽, 상근은 오른쪽
 * 턴 번갈아가며 막대 던짐
 *
 * 막대를 던지기 전 던질 높이 정함
 * 막대는 땅과 수평
 * 막대가 날아가다 미네랄 만나면 미네랄 파괴 막대는 멈춤
 *
 * 클러스터가 분리될 수 있음
 * 새롭게 생성된 클러스터가 떠 있으면 중력에 의해 바닥으로 떨어짐
 * 클러스터 모양은 변경 x
 * 합쳐질 수 있음 밑에 클러스터가 있으면
 *
 * 1. 높이가 주어지면 해당 높이와 일치하는 위치의 미네랄을 파괴한다
 * 2. 미네랄이 파괴된 시점에서 클러스터 중 공중에 있는 애를 찾는다.
 * 3. 만약 있다면 위에서 아래로 쭉 내린다.
 * 4. 음 만약 내리는데 클러스터가 있다면 합친다.
 *
 * 모든 높이는 1과 R사이
 * 높이 1은 행렬의 가장 바닥, 높이 R은 가장 위
 * 첫번째 막대는 왼 -> 오로 던짐
 * 두번째는 오 -> 왼
 * 이렇게 번갈아가면서
 *
 */

public class Main {
    static class Node{
        int r;
        int c;
        public Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static char[][] cave;
    static int R, C;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Queue<Node> cluster = new LinkedList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        cave = new char[R][C];

        for(int i=0; i<R; i++){
            cave[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            int temp = Integer.parseInt(st.nextToken());
            int height = R - temp;

            notenoughminerals(height, i);
            visited = new boolean[R][C];

            for(int j=0; j<C; j++){
                if(cave[R-1][j]=='x' && !visited[R-1][j]){
                    bfs(R-1, j);
                }
            }

            boolean downFlag = false;
            for(int m = 0; m<R; m++){
                if(downFlag)break;
                for(int n = 0; n<C; n++){
                    if(cave[m][n]=='x' && !visited[m][n]){
                        bfs(m,n);
                        downClusters();
                        downFlag=true;
                        break;
                    }
                }
            }
        }
        printMap();
    }

    private static void notenoughminerals(int height, int idx){
        int dir = getDirection(idx);
        if(dir == 0){
            for(int i = 0; i < C; i++){
                if(cave[height][i]=='x'){
                    cave[height][i]='.';
                    break;
                }
            }
        } else {
            for(int i = C-1; i >= 0; i--){
                if(cave[height][i]=='x'){
                    cave[height][i]='.';
                    break;
                }
            }
        }
    }

    private static int getDirection(int idx){
        if(idx%2 == 0){
            return 0;
        } else {
            return 1;
        }
    }

    private static void bfs(int r, int c){
        Queue<Node>q = new LinkedList<>();
        cluster.clear();
        cluster.add(new Node(r,c));
        q.offer(new Node(r,c));
        visited[r][c] = true;
        while(!q.isEmpty()){
            Node temp = q.poll();
            for(int d = 0; d < 4; d++){
                int nr = temp.r + dx[d];
                int nc = temp.c + dy[d];
                if(nr>=0 && nc>=0 && nr<R && nc<C && !visited[nr][nc] && cave[nr][nc]=='x'){
                    visited[nr][nc] = true;
                    cluster.add(new Node(nr,nc));
                    q.offer(new Node(nr,nc));
                }
            }
        }
    }

    private static void downClusters(){
        ArrayList<Node> list = new ArrayList<>();
        int i = 0;
        int minus = 0;
        while(!cluster.isEmpty()){
            list.add(cluster.poll());
        }
        int size = list.size();
        changeCave(0, size, list, '.');
        while (true){
            if(checkCanDown(minus+1, size, list))minus++;
            else break;
        }
        changeCave(minus, size, list, 'x');
    }

    private static boolean checkCanDown(int minus, int size, ArrayList<Node> list){
        for(int i = 0; i < size; i++){
            int nr = list.get(i).r+minus;
            int nc = list.get(i).c;
            if(nr>=R||cave[nr][nc]=='x') return false;
        }
        return true;
    }

    private static void changeCave(int minus, int size, ArrayList<Node> list, char c){
        for(int i = 0; i < size; i++){
            int nr = list.get(i).r+minus;
            int nc = list.get(i).c;
            cave[nr][nc]=c;
        }
    }

    private static void printMap(){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                System.out.print(cave[i][j]);
            }
            System.out.println();
        }
    }
}
