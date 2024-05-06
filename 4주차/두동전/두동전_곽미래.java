package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제]
 * NxM 보드, 두개의 동전
 * 버튼은 상하좌우 이동 4가지
 * 벽 -> 동전 이동 x
 * 칸이 없으면 동전은 떨어짐
 * 두 동전 중 하나만 보드에서 떨어뜨리는 최소 횟수
 *
 * 조건]
 * 1 <= N, M <= 20
 * 보드 상태
 * - o : 동전
 * - . : 빈칸
 * - # : 벽
 * 두 동전을 떨어뜨릴 수 없거나 버튼을 10번보다 많이 눌러야하면 -1 출력
 *
 * 풀이]
 * 구현 문제
 * 4방향 돌면서 BFS 때려
 *
 */
public class boj_16197_두동전 {
	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Coin{
		int count;
		Point coin1;
		Point coin2;

		public Coin(int count, Point coin1, Point coin2) {
			this.count = count;
			this.coin1 = coin1;
			this.coin2 = coin2;
		}
	}

	static int N, M, MIN_COUNT = 11;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		Point coin1 = null;
		Point coin2 = null;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'o') {
					if(coin1 == null) coin1 = new Point(i, j);
					else if(coin2 == null) coin2 = new Point(i, j);
				}
			}
		}

		dropCoin(new Coin(0, coin1, coin2));
		System.out.println(MIN_COUNT == 11 ? -1 : MIN_COUNT);
	}

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void dropCoin(Coin coin) {
		boolean[][][][] visited = new boolean[N][M][N][M];
		Queue<Coin> queue = new ArrayDeque<>();
		queue.add(coin);
		visited[coin.coin1.r][coin.coin1.c][coin.coin2.r][coin.coin2.c] = true;
		while (!queue.isEmpty()) {
			Coin curr = queue.poll();
			if(curr.count >= 10) break;

			for(int d=0; d<4; d++) {
				int nr1 = curr.coin1.r + dr[d];
				int nc1 = curr.coin1.c + dc[d];
				int nr2 = curr.coin2.r + dr[d];
				int nc2 = curr.coin2.c + dc[d];

				if(!isOutOfRange(nr1, nc1) && map[nr1][nc1] == '#') {
					nr1 = curr.coin1.r;
					nc1 = curr.coin1.c;
				}
				if(!isOutOfRange(nr2, nc2) && map[nr2][nc2] == '#') {
					nr2 = curr.coin2.r;
					nc2 = curr.coin2.c;
				}

				if(isOutOfRange(nr1, nc1) && isOutOfRange(nr2, nc2)) continue;
				else if(isOutOfRange(nr1, nc1) || isOutOfRange(nr2, nc2)) {
					MIN_COUNT = Math.min(MIN_COUNT, curr.count+1);
					return;
				}

				if(!visited[nr1][nc1][nr2][nc2]) {
					queue.add(new Coin(curr.count+1, new Point(nr1, nc1), new Point(nr2, nc2)));
					visited[nr1][nc1][nr2][nc2] = true;
				}
			}
		}
	}

	public static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}
