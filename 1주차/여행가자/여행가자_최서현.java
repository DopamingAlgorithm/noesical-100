import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 여행가자
 * 도시 N개와 그 사이 길에대한 정보가 이차원 배열로 주어진다.
 * 길은 양방향이며, 경유를하거나 같은나라를 중복방문해도 상관없으니
 * 주어진 여행계획에 따라 방문하는것이 가능한지 계산해라.
 *
 * 즉 어떻게든 연결이 되어있는지를 검사하면됨
 * => 연결리스트 만들어서 BFS돌리거나 유니온앤파인드..
 */

public class Main {
    static int n,m;
    static List<ArrayList<Integer>> graph;
    static int[] plan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); //도시의 수
        m = Integer.parseInt(br.readLine()); //여행계획속 도시의 수
        graph = new ArrayList<>(); //연결정보
        plan = new int[m]; //여행계획

        StringTokenizer st;

        //연결정보의 index를 맞춰주기 위해 0번Index를 셋팅
        graph.add(new ArrayList<>());
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine()," ");
            graph.add(new ArrayList<>());

            for(int j=1; j<=n; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp == 1) graph.get(i).add(j);
                if(i==j) graph.get(i).add(j); //본인은 본인과 이어져있음에 주의한다.
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<m; i++) plan[i] = Integer.parseInt(st.nextToken());

        //BFS를 통해서 모든 길을 탐색하며 모든 출발지/목적지가 이어져있는지 확인한다.
        for(int i=0; i<m-1; i++) {
            if(!bfs(plan[i], plan[i+1])){
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }

    static boolean bfs(int start, int end){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] check = new boolean[n+1];
        q.offer(start);
        check[start] = true;

        while(!q.isEmpty()){
            int curr = q.poll();
            //curr가 1이면 1과 연결된 모든 노드 찾아서
            for(int i=0; i<graph.get(curr).size(); i++){
                int next = graph.get(curr).get(i);
                if(next == end) return true;
                if(!check[next]){
                    q.offer(next);
                    check[next] = true;
                }
            }
        }

        return false; //이어지지않았을때
    }
}