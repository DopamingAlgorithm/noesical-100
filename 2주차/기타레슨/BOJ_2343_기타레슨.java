package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2343_기타레슨 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        long min = 1;
        long max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            max += nums[i];
        }

        long answer = max;

        while(min <= max){
            int cnt = 0;
            long sum = 0;
            long mid = (min + max) / 2;

            int idx = 0;
            while(idx < N) {
                if(nums[idx] > mid){
                    cnt = Integer.MAX_VALUE;
                    break;
                }

                sum += nums[idx];

                if(idx == N - 1){
                    cnt++;
                    if(sum <= mid) idx++;
                    if(sum > mid) sum = 0;
                    continue;
                }

                if(sum >= mid){
                    cnt++;
                    if(sum > mid){
                        sum = 0;
                        continue;
                    }
                    sum = 0;
                }

                idx++;
            }


            if(cnt > M) min = mid + 1;
            if(cnt <= M){
                answer = mid;
                max = mid - 1;
            }

        }

        System.out.println(answer);
    }
}
