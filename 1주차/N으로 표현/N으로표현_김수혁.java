import java.util.*;

/**

풀이
각 Set 배열에는 기본적으로 자기 자신 값이 들어간다
예) 5, 55, 555 이런식으로
N이 1개면 5
N이 2개면 55, 10, 0, 25, 1
N이 3개면 (N이 1개일때 (+, -, *, /) N이 2개일때), (N이 2개일때 (+,-,*,/) N이 1개일때)
N이 4개면 (N이 2 (+ , -, *, /) N이 2), (N이 1 (+,-,*,/) N이 3), (N이 3 (+, -, *, /) N이 1)
이런식으로 진행된다

얘는 8개가 넘어가면 -1을 리턴하기 때문에 시간이 매우 짧다고 판단하여 시간복잡도를 따로 구하지 않았다.

*/


class Solution {
    public int solution(int N, int number) {
        int answer = -1;    // 최솟값이 8보다 크면 -1을 리턴하기 때문에 기본을 -1로 설정
        
        int selfNum = N;
        Set<Integer>[] nArr = new HashSet[9];
        
        // 각 배열에는 무조건 5, 55, 555가 있어야 함
        for(int i = 1; i < 9; i++){
            nArr[i] = new HashSet<>();
            nArr[i].add(selfNum);
            selfNum = selfNum*10 + N;
        }
        
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < i; j++){
                for(int a : nArr[j]){
                    for(int b : nArr[i-j]){
                        nArr[i].add(a+b);
                        nArr[i].add(a-b);
                        nArr[i].add(b-a);
                        nArr[i].add(a*b);
                        if(b != 0) {
                            nArr[i].add(a/b);
                        }
                        if(a != 0) {
                            nArr[i].add(b/a);
                        }
                    }
                }
            }
        }
        
        for(int i = 1; i < 9 ; i++){
            if(nArr[i].contains(number)){
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}
