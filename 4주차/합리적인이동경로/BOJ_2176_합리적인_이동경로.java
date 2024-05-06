package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 현재 노드에서 2로가는 최단 > 다음 노드에서 2로가는 최단 인 경우 찾으라는 뜻
 * 말 드럽게어려움. 문제는 다익스트라 하고 이전 값하고 비교하면 됨
 */
public class BOJ_2176_합리적인_이동경로 {
    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static int[] dist, dp;
    static List<List<Pair>> edge;

    static void dijkstra(int start) {
        dist[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.first));
        pq.add(new Pair(dist[start], start));

        while (!pq.isEmpty()) {
            Pair node = pq.poll();
            int cn = node.second;
            int cd = node.first;

            if (cd > dist[cn]) continue;

            for (Pair neighbor : edge.get(cn)) {
                int nn = neighbor.second;
                int weight = neighbor.first;

                if (dist[cn] + weight < dist[nn]) {
                    dist[nn] = dist[cn] + weight;
                    pq.add(new Pair(dist[nn], nn));
                }

                if (dist[cn] > dist[nn])
                    dp[cn] += dp[nn];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edge = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            edge.add(new ArrayList<>());
        }

        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dp = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edge.get(A).add(new Pair(C, B));
            edge.get(B).add(new Pair(C, A));
        }

        dp[2] = 1;
        dijkstra(2);
        System.out.println(dp[1]);
    }
}