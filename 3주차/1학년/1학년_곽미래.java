import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * 숫자들이 주어질 때 올바른 등식의 수를 구하기
 * +, - 가능
 *
 * 조건]
 * 3 <= N <= 100
 * 0 <= 수 <= 9
 * 음수 X
 * 20 넘는 수 X
 *
 * 풀이]
 * 브루트포스 = 2^(100-2)? 이게 맞냐
 * DP를 사용해보자
 * - 0이상 20이하, 최대 100개 = 21 * 100
 * - 개수를 저장햇
 *
 */
public class 1학년_곽미래 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		long[][] dp = new long[N][21];
		dp[1][nums[1]] = 1;
		int n;
		for(int i=2; i<N; i++) {
			n = nums[i];
			for (int k=0; k<=20; k++){
				if(k-n >= 0) dp[i][k-n] += dp[i-1][k];
				if(k+n <= 20) dp[i][k+n] += dp[i-1][k];
			}
		}
		System.out.println(dp[N-1][nums[N]]);
	}
}
