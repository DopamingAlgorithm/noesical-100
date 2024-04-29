package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 앞에서 부터 더하기
 * 2. 누적합이 기준 숫자(S) 넘어가면 길이 측정 - 투포인터, 앞 포인터 ~ 뒷 포인터 길이
 * 3. 누적 합이 S 이하가 될때까지 앞에서부터 빼주기
 */
public class BOJ_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        int front = 0;
        int back = 0;
        int sum = 0;
        int len = 0;
        int answer = Integer.MAX_VALUE;


        while(back < N){
            len++;
            sum += nums[back++];
            if(sum >= S){
                while(sum >= S){
                    answer = Math.min(answer, len);
                    sum -= nums[front++];
                    len--;
                }
            }
        }

        if(answer == Integer.MAX_VALUE) answer = 0;
        System.out.println(answer);
    }
}
