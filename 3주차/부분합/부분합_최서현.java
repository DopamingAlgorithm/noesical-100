import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 부분합
투포인터로 풀어보자
연속된수의 합이 S밑이면 end를 오른쪽,이상이면 start를왼쪽..
*/
public class Main {
	static int n, s;
	static int[] arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		s = Integer.valueOf(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int num = Integer.valueOf(st.nextToken());
			arr[i] = num;
		}
    
		int start = 0;
		int end = 0;
		int minLen = Integer.MAX_VALUE;
		int maxSum = arr[end];
		
		while(end < n && start < n) {
			if(maxSum >= s) {
				minLen = Math.min(minLen, end - start + 1);
				int startNum = arr[start++];
				maxSum -= startNum;
				continue;
			}
			
			if(start > end) {
				break;
			}
			if(end == n - 1) {
				break;
			}
			int num = arr[++end];
			maxSum += num;
		}
		
		System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
	}
}
