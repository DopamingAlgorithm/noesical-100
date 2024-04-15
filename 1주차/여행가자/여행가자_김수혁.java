import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// union find로도 풀어보기
// 시간복잡도는 n^2

public class 여행가자 {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // graph 초기화
        graph = new ArrayList[N+1];
        for(int i = 1; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }

        // 연결된 정보 집어넣기
        for(int i = 1;i <N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<N+1;j++){
                int val = Integer.parseInt(st.nextToken());
                if(val == 1){
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        // flag가 true면 Yes 출력, false면 No 출력
        boolean flag = true;

        int start = Integer.parseInt(st.nextToken());

        for(int i=1; i<M;i++){
            int end = Integer.parseInt(st.nextToken());

            // 이거땜에 개빡쳤음 고려 안해주면 문제 안풀림...
            if(start == end) continue;

            if(!bfs(start, end)){
                flag = false;
                break;
            }
            start = end;
        }

        if(flag){
            System.out.println("YES");
        } else {
            // No가 아니라 NO임 이거땜에 2번 틀림...
            System.out.println("NO");
        }

    }

    static boolean bfs(int start, int end){
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited = new boolean[N+1];
        visited[start] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int next : graph[now]){
                if(next == end) return true;
                if (visited[next]) continue;
                visited[next] = true;
                queue.offer(next);
            }
        }

        return false;
    }

}
