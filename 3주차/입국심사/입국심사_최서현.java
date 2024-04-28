/* 입국심사
이분탐색
시간안에 검사 가능하면 해당값으로 결과를 초기화, 그리고 끝값늘리기
안되면 시작값 늘려가며 진행
*/

class Solution {
    public long solution(int n, int[] times) {
        long answer = binarySearch(n,times);
        return answer;
    }
    private static final long START = 1L;
    private static final long END = 1_000_000_000L * 1_000_000_000L;
    private static long binarySearch(int n, int[] times){
        long start = START;
        long end = END;
        long result = 0;
        while(start <= end){
            long mid = (start + end) / 2;
            if(check(mid,times,n)){
                result = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return result;
    }
    private static boolean check(long mid, int[] times, int n){
        long sum = 0;
        for(int time : times){
            sum += mid/time;
        }
        if(sum >= n){
            return true; 
        }else{
             return false;
        }
    }
}
