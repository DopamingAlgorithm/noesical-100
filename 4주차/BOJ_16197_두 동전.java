import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 보드 크기 : N x M
 * o : 동전
 * . : 빈칸
 * # : 벽
 *
 * BFS 이용하면 될 듯?
 *
 * 1. 동전이 하나만 떨어져있는지 확인
 * 2. 동전 움직이기
 * 3. 벽이 있는지 확인하기
 * 4. 동전을 움직였을 때, 두 개의 동전의 상태를 구하기
 * 5. 1번 반복
 */


public class BOJ_16197_두동전 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int N, M;
    private static char[][] map;

    private static class Coin{
        int r1, c1, r2, c2, cnt;
        boolean state1, state2;

        public Coin(int r1, int c1, int r2, int c2, int cnt, boolean state1, boolean state2){
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
            this.state1 = state1;
            this.state2 = state2;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int cnt = 0;
        int fcr = 0, fcc = 0;
        Coin coin = null;

        for(int i = 0; i < N; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                // 동전이면
                if(line[j] == 'o'){
                    // 첫번째 동전이면
                    if(cnt==0){
                        //좌표 기억하기
                        fcr = i; fcc = j; cnt++;
                    } else {
                        coin = new Coin(fcr, fcc, i, j, 0, true, true);
                    }
                } else {
                    map[i][j] = line[j];
                }
            }
        }

        int btnCnt = bfs(coin);

        if(btnCnt == Integer.MAX_VALUE){
            btnCnt = -1;
        }

        System.out.println(btnCnt);
    }

    private static int bfs(Coin coin){
        int cnt = Integer.MAX_VALUE;

        Queue<Coin> q = new LinkedList<>();
        q.offer(coin);

        while(!q.isEmpty()){
            Coin c = q.poll();

            if(c.cnt > 10)  continue;

            if(!c.state1 || !c.state2){
                if(!c.state1 && !c.state2)continue;
                cnt = Math.min(cnt, c.cnt);
                continue;
            }

            for(int d = 0; d < 4; d++){
                int nr1 = c.r1 + dx[d];
                int nc1 = c.c1 + dy[d];

                int nr2 = c.r2 + dx[d];
                int nc2 = c.c2 + dy[d];

                // 둘 다 범위 안이면
                if(isInRange(nr1, nc1) && isInRange(nr2, nc2)){
                    // 둘다 벽이 아니면
                    if(map[nr1][nc1] != '#' && map[nr2][nc2] != '#'){
                        q.offer(new Coin(nr1, nc1, nr2, nc2, c.cnt+1, true, true));
                    }

                    // 첫번째 동전만 벽을 만나는 경우
                    if(map[nr1][nc1] == '#' && map[nr2][nc2] != '#'){
                        q.offer(new Coin(c.r1, c.c1, nr2, nc2, c.cnt+1, true, true));
                    }

                    // 두번째 동전만 벽을 만나는 경우
                    if(map[nr1][nc1] != '#' && map[nr2][nc2] == '#'){
                        q.offer(new Coin(nr1, nc1, c.r2, c.c2, c.cnt+1, true, true));
                    }
                }

                // 첫 번째 동전은 범위 밖이고 두번째 동전은 범위 안인 경우
                if(!isInRange(nr1, nc1) && isInRange(nr2, nc2)){
                    // 두번째 동전이 벽을 만나는 경우
                    if(map[nr2][nc2] == '#'){
                        q.offer(new Coin(nr1, nc1, c.r2, c.c2, c.cnt+1, false, true));
                    }

                    // 두번째 동전이 벽을 만나지 않는 경우
                    if(map[nr2][nc2] != '#'){
                        q.offer(new Coin(nr1, nc1, nr2, nc2, c.cnt+1, false, true));
                    }
                }

                // 첫 번째 동전은 범위 안이고 두번째 동전은 범위 밖인 경우
                if(isInRange(nr1, nc1) && !isInRange(nr2, nc2)){
                    // 첫번째 동전이 벽을 만나는 경우
                    if(map[nr1][nc1] == '#'){
                        q.offer(new Coin(c.r1, c.c1, nr2, nc2, c.cnt+1, true, false));
                    }
                    // 첫번째 동전이 벽을 만나지 않는 경우
                    if(map[nr1][nc1] != '#'){
                        q.offer(new Coin(nr1, nc1, nr2, nc2, c.cnt+1, true, false));
                    }
                }
            }
        }
        return cnt;
    }

    private static boolean isInRange(int r, int c){
        return r>=0 && c>=0 && r<N && c<M;
    }
}
