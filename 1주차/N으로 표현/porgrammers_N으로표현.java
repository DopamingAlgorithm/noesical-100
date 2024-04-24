package programmers;

import java.util.ArrayList;
import java.util.List;

public class porgrammers_N으로표현 {
    /**
     * 1. NNN.. 형태로 이어진 케이스는 따로 생각
     * 2. 사칙연산 케이스 DP로 생각 가능
     * - 한 번 사용 : N
     * - 두 번 사용 : NN, N+N, N-N, N*N, N/N
     * - 세 번 사용 : NNN, 한 번 사용 * 세 번 사용 * 4(사칙연산 횟수) 번 케이스, 세 번 사용 * 한 번 사용 * 4(사칙연산 횟수) 번 케이스, 두 번 사용 * 두 번 사용 * 4(사칙연산 횟수) 번 케이스
     *
     * -> dp[i] = (dp[i - j] (+, -, *, /) dp[j]) + 1[NNN... 형태의 특별 케이스 하나 추가]
     * dp는 각 횟수번에서 도출된 값들 리스트 저장해야할듯
     * 사실 합집합 개념으로 가야해서 중복제거 해주면 더 빠른거 확실한데... 안해도 통과 되겠지?
     */
    public int solution(int N, int number) {
        int answer = -1;

        List<Integer>[] caseArr = new List[9];
        for (int i = 0; i < 9; i++) caseArr[i] = new ArrayList<>();

        caseArr[1].add(N);
        if(number == N) return 1;

        C : for (int i = 2; i <= 8 ; i++) {
            int NN = (caseArr[i - 1].get(0) * 10) + N;
            if(NN == number){
                answer = i;
                break;
            }
            caseArr[i].add(NN);

            for (int j = 1; j < i; j++) {
                for(int a : caseArr[j]){
                    for(int b : caseArr[i - j]){
                        int add = a + b;
                        int subtract = a - b;
                        int multiply = a * b;
                        int divide = Integer.MIN_VALUE;
                        if(b != 0) divide = a / b;

                        if(number == add || number == subtract || number == multiply){
                            answer = i;
                            break C;
                        }

                        if(number == divide && b != 0){
                            answer = i;
                            break C;
                        }

                        caseArr[i].add(add);
                        caseArr[i].add(subtract);
                        caseArr[i].add(multiply);
                        if(b != 0) caseArr[i].add(divide);
                    }
                }
            }
        }

        return answer;
    }
}
