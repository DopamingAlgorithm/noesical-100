import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * 10000 이하의 자연수로 이루어진 N짜리 수열
 * 연속된 부분합 중 S이상이 되는 것 중 가장 짧은 것의 길이
 *
 *
 * 조건]
 * 10 <= N <= 100,000
 * 0 <= S <= 100,000,000
 *
 *
 * 풀이]
 *
 *
 */
public class 부분합_곽미래 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		long[] nums = new long[N+1];
		for(int i=1; i<=N; i++) {
			nums[i] = nums[i-1] + Integer.parseInt(st.nextToken());
		}

		long min = N+1;
		long sum = 0;
		int left=1, right=1;
		while(left <= right && right <= N) {
			sum = nums[right] - nums[left-1];
			if(sum >= S) {
				min = Math.min(min, right-left+1);
				if(left == right) break;
				left++;
			} else {
				right++;
			}
		}
		System.out.println(min == N+1 ? 0 : min);
	}
}
