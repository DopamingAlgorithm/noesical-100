import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * - N개의 도시와 길이 있을 때
 * - 여행 경로가 가능한지 확인하기
 * - 가능하면 YES, 아니면 NO
 *
 * 제약]
 * - N <=  200
 * - M <= 1000
 * - i, j가 연결되었다면 양방향 연결
 *
 * 풀이]
 * - 모든 지점에서 모든 지점까지 갈 수 있는지 여부를 구하자 (플로이드워셜)
 * - 그 이후엔 여행 경로를 첨부터보며 파악만하면댐
 *
 * 시간복잡도]
 * -
 *
 */
public class 여행가자_곽미래 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] isConnected = new int[N+1][N+1];
		int INF = Integer.MAX_VALUE;
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				isConnected[i][j] = (Integer.parseInt(st.nextToken()) == 1? 1 : INF);
				if(i==j) isConnected[i][j] = 1;
			}
		}

		int[] route = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}

		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(isConnected[i][k] == INF || isConnected[k][j] == INF) continue;
					isConnected[i][j] = isConnected[i][k] + isConnected[k][j];
				}

			}
		}

		boolean isPossible = true;
		for(int i=0; i<M-1; i++) {
			int src = route[i];
			int dst = route[i+1];
			if(isConnected[src][dst] == INF) {
				isPossible = false;
				break;
			}
		}
		System.out.println(isPossible ? "YES" : "NO");
	}
}
