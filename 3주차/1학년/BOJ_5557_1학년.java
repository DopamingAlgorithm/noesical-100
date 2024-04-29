package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//N번째 수는 N - 1 +- N번쨰 수
public class BOJ_5557_1학년 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;

        int[] nums = new int[N - 1];
        long[][] dp = new long[N - 1][21];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) nums[i] = Integer.parseInt(st.nextToken());
        result = Integer.parseInt(st.nextToken());

        dp[0][nums[0]] = 1;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if(dp[i - 1][j] != 0){
                    int plus = j + nums[i];
                    int minus = j - nums[i];
                    if(0 <= plus && plus <= 20) dp[i][plus] += dp[i - 1][j];
                    if(0 <= minus && minus <= 20) dp[i][minus] += dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N-2][result]);
    }
}
