import java.util.*;

/**
* 일단 입국심사 기다리는 수랑 걸리는 시간 보면 절대 완전탐색 하면 안됨
* 
* 음 적게 걸리는 심사관한테 많이 배정되고 많이 걸리는 심사관한테 적게 배정하면?
*
* times 배열의 최고 값 기준으로 이분탐색하면서
* mid값으로 각 심사관이 몇명 검색할 수 있는지 계산해보자
*
* 1,000,000,000분 = 16,666,666.666시간 = 694,444.444일 = 23,148.148달 = 1,929.012년 = 예수 나오고 20세기까지
* 한명 심사하는데 20세기 걸린다고? 심사관 뭐냐?
*/

class Solution {
    
    public long solution(int n, int[] times) {
        long answer = 0;
        
        // printArr(times);
        // 우선 times 배열을 정렬한다.
        Arrays.sort(times);
        // printArr(times);
        
        long start = 1;
        long mid = 0;
        // 최악의 경우 : times[times의 길이 -1] * 사람수(n)
        long end = (long) times[times.length-1]*n;
        long sum;
        
        // testcase 6번에서 계속 실패
        // 흠...
        // start = end일때도 반복되야 하는데 ㅋㅋㅋ
        // while(start < end)에서 while(start <= end)로 변경하니 통과
        
        // min이 max보다 작은 동안만 반복
        while(start <= end){
            sum = 0;
            mid = (start + end) / 2;
            
            for(int i = 0; i < times.length; i++){
                long takeTime = mid / times[i];
                sum += takeTime;
            }
            
            if(sum >= n){
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            
        }
        
        return answer;
    }
    
    static private void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+", ");
        }
        System.out.println();
    }
}
