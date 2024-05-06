import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제]
 * 동굴 RxC
 * 각 칸은 비어있거나 미네랄 포함
 * 네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은 클러스터
 * 막대가 날라가다 미네랄을 만나면 미네랄 파괴, 막대 멈춤
 * 미네랄이 파괴되면 클러스터가 분리될 수도 있음
 * 클러스터가 떠있는 경우 중력에 의해 바닥으로 떨어진다
 * 클러스터는 다른 클러스터나 땅을 만나기 전까지 계속 떨어진다
 * 클러스터가 다른 클러스터 위에 떨어지면 합쳐짐
 * 동굴에 있는 미네랄의 모양과 막대 높이가 주어지면
 * 모든 막대를 던지고 난 후 미네랄 모양을 구해라
 *
 * 조건]
 * 1 <= R,C <= 100
 * . : 빈칸
 * x : 미네랄
 * 1 <= N <= 100
 * 막대는 왼쪽 오른쪽 번갈아 떨어짐
 *
 *
 * 풀이]
 * 빡구현
 *
 */
public class 미네랄_곽미래 {
	static int R, C, N;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		visited = new boolean[R][C];
		int order;
		boolean isLeft = true;
		for(int i=0; i<N; i++) {
			order = Integer.parseInt(st.nextToken());
			// 막대 던지기
			throwBar(isLeft, R - order);

			// 막대 방향 전환
			isLeft = !isLeft;

			// 미네랄 재 클러스터링
			clustering();
		}

		print();
	}

	static void throwBar(boolean isLeft, int r){
		if(isLeft) {
			for(int i=0; i<C; i++) {
				if(map[r][i] == 'x') {
					map[r][i] = '.';
					break;
				}
			}
		} else {
			for(int i=C-1; i>=0; i--) {
				if(map[r][i] == 'x') {
					map[r][i] = '.';
					break;
				}
			}
		}
	}

	static void clustering() {
		outer:
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'x' && !visited[i][j]) {
					if(findCluster(i, j)) break outer;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				visited[i][j] = false;
			}
		}
	}

	static boolean findCluster(int i, int j){
		visited[i][j] = true;

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[]{i, j});
		ArrayList<int[]> points = new ArrayList<>();
		int height = 0;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			height = Math.max(height, curr[0]);

			for(int d=0; d<4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				if(isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] == '.') continue;
				visited[nr][nc] = true;
				queue.add(new int[]{nr, nc});
			}
			points.add(curr);
		}

		// 내려갈 곳이 있다면
		if(height < R-1) {
			moveCluster(points);
			return true;
		}
		return false;
	}

	static void moveCluster(ArrayList<int[]> points) {
		int move = 1;
		for(int[] curr : points) {
			map[curr[0]][curr[1]] = '.';
		}

		outerLoop:
		while(true){
			for (int[] curr : points) {
				if (curr[0] + move == R || map[curr[0] + move][curr[1]] == 'x') {
					move--;
					break outerLoop;
				}
			}
			move++;
		}

		for (int[] curr : points) {
			map[curr[0] + move][curr[1]] = 'x';
		}
	}

	static boolean isOutOfRange(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}

	static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
