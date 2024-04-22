/* N으로 표현
주어지는 N을 통해 사칡연산으로 number를 만들 수 있을때, N의 최소사용횟수를 구하자.

N을 사용한 횟수를 통해 풀이할 수 있다.
N이 5일때,
[1번 리스트] : 5
[2번 리스트] : 5+5, 5-5, 5*5, 5/5, 55
[3번 리스트] : 5+(5+5), 5*(5+5),... (5*5)*5, 555 (괄호가 바뀌면 다른 셈으로 인식함에 주의)

3번리스트를 보면 1번리스트와 2번리스트를 통해 만들수있다.
즉, 작은문제를 통해 큰문제를 해결 할 수 있다.

최솟값이 8보다 크면 -1을 리턴하므로, 8개의 리스트만 만든다.
*/
import java.util.*;
class Solution {
    public int solution(int N, int number) {
        String n = Integer.toString(N);

        //(1) 연산의 결과를 저장할 8개의 리스트를 만들어준다. (각 번호 조합은 Set으로, 중복불허)
        List<Set<Integer>> setList = new ArrayList<>();
        for(int i=0; i<9; i++) setList.add(new HashSet<>());

        //(2) 1번리스트엔 자기자신만 들어갈 수 있다
        setList.get(1).add(N);

        //(3) 2번리스트부터 탐색 시작 (이전 리스트를 이용하여 모든 가능한 조합을 찾는다)
        if(number == N) return 1;

        for(int i=2; i<9; i++){ // 2번은 1번+번, 3번은 1번+2번, 4번은 1번+3번 & 2번+2번으로 표현 가능...
            for(int j=1; j<=i/2; j++){
                //괄호가 있을땐 다른것으로 인식하므로 순서바꿔 두번 실행한다.
                unionSet(setList.get(i), setList.get(i-j), setList.get(j));
                unionSet(setList.get(i), setList.get(j), setList.get(i-j));
            }
            //사칙연산말고 그냥 숫자연속에 대해서도 추가
            setList.get(i).add(Integer.parseInt(n.repeat(i)));
            for(int num : setList.get(i)){
                if(num == number) return i;
            }
        }
        return -1;
    }

    //합집합을 만든다. 두 이전리스트들에서 값(계산결과)을 꺼내서 새로운 조합을 만듦
    public void unionSet(Set<Integer> union, Set<Integer> a, Set<Integer> b){
        for(int n1 : a){
            for(int n2 : b){
                union.add(n1+n2);
                union.add(n1-n2);
                union.add(n1*n2);
                if(n2 != 0){
                    union.add(n1/n2);
                }
            }
        }
    }
}