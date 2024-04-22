/**
 문제
 - 1,1에서 m,n까지 가는
 - 최단경로 개수 구하기
 - 오른쪽, 아래쪽 방향으로만 이동 가능
 - 1,000,000,007로 나눈 나머지

 제약
 - 1 <= m, n <= 100
 - 0 <= 물이 잠긴 지역 <= 10

 풀이
 - dp
 - dp[i][j] = i,j까지 오기위한 방법의 수
 - 좌, 하로만 움직이기 때문에 최단은 알아서 구해짐

 시간복잡도
 - 100*100
 */
public class 등굣길_곽미래 {
	public int solution(int m, int n, int[][] puddles) {
		int[][] dp = new int[n+1][m+1];
		for(int i=0; i<puddles.length; i++) {
			dp[puddles[i][1]][puddles[i][0]] = -1;
		}

		dp[1][1] = 1;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(dp[i][j] == -1) continue;
				if(dp[i-1][j] > 0) dp[i][j] += dp[i-1][j];
				if(dp[i][j-1] > 0) dp[i][j] += dp[i][j-1];
				dp[i][j] %= 1_000_000_007;
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {

	}
}
