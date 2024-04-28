
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제]
 * - N개의 수에서 두 개의 합으로 나타낼 수 있는 수의 개수
 */
public class 좋다_곽미래 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] nums = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(nums);

		int count = 0;
		for(int i=0; i<N; i++) {
			int left = 0;
			int right = N-1;
			while(true) {
				if(left == i) left++;
				if(right == i) right--;

				if(left >= right) break;

				if(nums[left] + nums[right] > nums[i]) right--;
				else if(nums[left] + nums[right] < nums[i]) left++;
				else {
					count++;
					break;
				}
			}
		}
		System.out.println(count);
	}
}
