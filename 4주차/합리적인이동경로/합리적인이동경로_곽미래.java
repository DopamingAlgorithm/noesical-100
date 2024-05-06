package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제]
 * S에서 T로 이동
 * T에 가까워지며 이동하는 경우 합리적인 이동경로
 * 합리적인 이동경로는 최단거리가 아닐 수도 있음
 * 그래프에 대해 합리적인 이동경로 개수를 구해라
 * S=1, T=2
 *
 * 조건]
 * 정점의 개수 : 1 <= N <= 1000
 * 간선의 개수 : 1 <= M <= 100000
 * A : 정점
 * B : 정점
 * 간선 길이 : 0 < C <= 10000
 *
 * 풀이]
 * S에서 T로 갈때 X를 거칠 수 있다면
 * X-> T < S ->T 가 되어야 합리적인 이동경로
 *
 * T에서 부터 각 노드까지의 비용을 계산 - 다익스트라
 * dp를 사용해서 각 노드에서 T까지의 합리적인 이동경로 개수 계산
 * 최단 거리가 짧은 노드부터 계산
 *
 */
public class boj_2176_합리적인이동경로 {
	static int N, M;
	static int[] dist;
	static List<Node>[] edges;

	static class Node {
		int next, cost;
		public Node(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
	}

	static class Point implements Comparable<Point>{
		int index, dist;
		public Point(int index, int dist) {
			this.index = index;
			this.dist = dist;
		}

		@Override
		public int compareTo(Point o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new List[N+1];
		for (int i=1; i<=N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edges[a].add(new Node(b, c));
			edges[b].add(new Node(a, c));
		}


		dijkstra();
		System.out.println(dp());
	}

	static void dijkstra() {
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[2] = 0;
		boolean[] visited = new boolean[N+1];

		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.add(new Point(2, 0));
		visited[2] = true;

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for(Node next : edges[curr.index]) {
				if(dist[next.next] > dist[curr.index] + next.cost) {
					dist[next.next] = dist[curr.index] + next.cost;
					queue.add(new Point(next.next, dist[next.next]));
				}
			}
		}
	}

	static int dp() {
		PriorityQueue<Point> queue = new PriorityQueue<>();
		for (int i=1; i<=N; i++) {
			queue.add(new Point(i, dist[i]));
		}
		queue.poll();

		int[] dp = new int[N+1];
		dp[2] = 1;

		while (!queue.isEmpty()) {
			Point curr = queue.poll();

			for(Node node : edges[curr.index]) {
				if(dist[node.next] < dist[curr.index]) {
					dp[curr.index] += dp[node.next];
				}
			}

			if(curr.index == 1) break;
		}

		return dp[1];
	}
}
