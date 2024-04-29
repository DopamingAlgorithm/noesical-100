package programmers;

import java.util.Arrays;

public class programmers_입국심사 {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long min = 1;
        long max = times[times.length - 1] * (long)n;
        long answer = max;

        while(min <= max){
            long sum = 0;   //처리 가능한 사람 수
            long mid = (min + max) / 2;

            for(int i = 0; i < times.length; i++) sum += mid / times[i];

            if(n <= sum){ //mid 시간동안 처리 가능한 사람 수가 n보다 크거나 같으면 답 비교 후 갱신
                answer = Math.min(answer, mid);
                max = mid - 1;
            }else{
                min = mid + 1;
            }
        }

        return answer;
    }
}
