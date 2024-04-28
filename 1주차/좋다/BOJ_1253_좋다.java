package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        if(N >= 3) {
            Arrays.sort(nums);

            for(int i = N - 1; i >= 0; i--){
                int smallIdx = 0;
                int largeIdx = N - 1;

                while(smallIdx < largeIdx){
                    int sum = nums[smallIdx] + nums[largeIdx];

                    if(sum == nums[i]){
                        if(largeIdx == i){
                            largeIdx--;
                            continue;
                        }
                        if(smallIdx == i){
                            smallIdx++;
                            continue;
                        }
                        answer++;
                        break;
                    }

                    if(sum > nums[i]){
                        largeIdx--;
                        continue;
                    }

                    if(sum < nums[i]){
                        smallIdx++;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
