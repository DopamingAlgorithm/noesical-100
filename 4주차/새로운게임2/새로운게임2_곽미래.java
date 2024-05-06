package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제]
 * NXN 체스판, K개의 말
 * 상하좌우 이동
 * 한턴에서 1번 말부터 K번까지 차례로 이동
 * 한 말이 이동할 때 올려져있는 말도 함께 이동
 * 말이 4개 이상 쌓이는 순간 게임 종료
 *
 * A번 말 이동 룰
 * 1. 흰색인 경우 그 칸으로 이동. 이동하려는 칸에 말이 있으면 겹침
 * - 말 위에 다른 말이 있을 경우 함께 이동
 * 2. 빨간색인 경우 이동한 후 A번 말과 그 위에 말들의 쌓여있는 순서를 바꾼다
 * 3. 파란색인 경우 A번 말의 이동방향을 반대로 하고 한칸 이동한다. 방향을 바꾼 후 칸이 파란색이면 이동하지 않고 가만히 있는다.
 * 4. 체스판을 벗어나는 경우 파란색과 같은 경우
 *
 * 조건]
 * 4 <= N <= 12 : 체스판 크기
 * 4 <= K <= 10 : 말의 개수
 * 0:흰색, 1:빨간색, 2:파란색
 * 이동방향 = 1:우, 2:좌, 3:상, 4:하
 * 게임이 종료되는 턴의 번호 출력
 * 1000보다 크거나 종료되지 않으면 -1 출력
 *
 * 풀이]
 *
 *
 */
public class boj_17837_새로운게임2 {
	static int N, K;
	static int[][] map, horse;
	static List<Integer>[][] piece;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N+1][N+1];
		piece = new List[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				piece[i][j] = new ArrayList<>();
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		horse = new int[K+1][3];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			horse[i][0] = r;
			horse[i][1] = c;
			horse[i][2] = Integer.parseInt(st.nextToken());

			piece[r][c].add(i);
		}

		System.out.println(simulation());
	}

	private static int simulation() {
		int[] dr = {0, 0, 0, -1, 1};
		int[] dc = {0, 1, -1, 0, 0};

		int count = 0;
		while(count < 1000) {
			count++;

			for(int i=1; i<=K; i++) {
				int r = horse[i][0];
				int c = horse[i][1];
				int d = horse[i][2];

				int nr = r + dr[d];
				int nc = c + dc[d];

				if(isOutOfRange(nr, nc)) {
					int nd = d % 2 == 0 ? d -1 : d + 1;
					horse[i][2] = nd;
					if(map[r+dr[nd]][c+dc[nd]] != 2) i--;
					continue;
				}

				if(map[nr][nc] == 0) {
					int idx = piece[r][c].indexOf(i);
					piece[nr][nc].addAll(piece[r][c].subList(idx, piece[r][c].size()));
					piece[r][c] = piece[r][c].subList(0, idx);
					horse[i][0] = nr;
					horse[i][1] = nc;
					for(Integer j: piece[nr][nc]) {
						horse[j][0] = nr;
						horse[j][1] = nc;
					}
				} else if(map[nr][nc] == 1) {
					int idx = piece[r][c].indexOf(i);
					List<Integer> temp = piece[r][c].subList(idx, piece[r][c].size());
					Collections.reverse(temp);

					piece[nr][nc].addAll(temp);
					piece[r][c] = piece[r][c].subList(0, idx);
					for(Integer j: piece[nr][nc]) {
						horse[j][0] = nr;
						horse[j][1] = nc;
					}
				} else if(map[nr][nc] == 2) {
					int nd = d % 2 == 0 ? d -1 : d + 1;
					horse[i][2] = nd;

					if(isOutOfRange(r+dr[nd], c+dc[nd])) continue;

					if(map[r+dr[nd]][c+dc[nd]] != 2) i--;
				}

				if(piece[nr][nc].size() > 3) {
					return count;
				}
			}
		}
		return -1;
	}

	private static boolean isOutOfRange(int nr, int nc) {
		return nr < 1 || nr > N || nc < 1 || nc > N;
	}
}
