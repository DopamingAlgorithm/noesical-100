import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1->2로 가는 경우의 수
 * 1->2, 1->3->4-4->5->2, 1->3->5->2, 1->4->5->2, 1->5->2, 1->4->3->5->2
 * 이 중 선택해야 하는 것 : 1->3, 1->3->5->2, 1->5->2
 *
 * 정답에서는 최단 거리가 짧은 노드부터 DP를 진행
 * 즉, 다익스트라 + DP
 *
 * dp[2]=1로 초기화 후
 * 1. 최단거리가 가장 짧은 5번 노드부터 시작하여
 * 2. 5번보다 짧은 노드를 넣고
 * 3. 다음으로 짧은 노드를 넣고
 * 4. 이런식
 *
 * 마지막으로 dp[1]을 출력
 *
 */

public class BOJ_2176_합리적인이동경로 {
    private static class Node{
        int next;
        int cost;
        public Node(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }
    private static class pq implements Comparable<pq>{
        int index;
        int dist;
        public pq(int index, int dist){
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(pq o){
            return this.dist - o.dist;
        }
    }
    private static int N,M;
    private static List<Node>[] connections;
    private static int[] dist, dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        connections = new List[N+1];

        for(int i = 1; i <= N; i++){
            connections[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            connections[a].add(new Node(b,c));
            connections[b].add(new Node(a,c));
        }

        dist = new int[N+1];
        dp = new int[N+1];
        dijkstra(N);
        System.out.println(getDp(N));
    }

    private static void dijkstra(int n){
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[2] = 0;

        boolean[] visited = new boolean[N+1];
        PriorityQueue<pq> prq = new PriorityQueue<>();
        prq.add(new pq(2,0));

        while(!prq.isEmpty()){
            pq now = prq.poll();
            if(visited[now.index]) continue;
            visited[now.index] = true;

            for(Node next: connections[now.index]){
                // 다음 갈 곳의 dist값이 현재까지의 dist + 현재 위치에서 다음 갈 곳까지의 비용보다 크면
                if(dist[next.next] > dist[now.index] + next.cost){
                    // 갱신
                    dist[next.next] = dist[now.index] + next.cost;
                    prq.add(new pq(next.next, dist[next.next]));
                }
            }
        }
    }

    private static int getDp(int n){
        PriorityQueue<pq> distPq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++){
            distPq.add(new pq(i, dist[i]));
        }
        distPq.poll();

        dp[2] = 1;

        while(!distPq.isEmpty()){
            pq now = distPq.poll();

            for(Node node: connections[now.index]){
                if(dist[node.next] < dist[now.index]){
                    dp[now.index] += dp[node.next];
                }
            }
            if(now.index == 1) break;
        }
        return dp[1];
    }

}
