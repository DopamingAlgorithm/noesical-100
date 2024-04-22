

시작 시간 오후 10시 51분

문제 풀이를 위한 고민)

턴 마다 선택할 수 있는 경우의 수
1) N을 한번 더 붙인다 5 = 55
2) 더한다 5 + 5
3) 뺀다  5 - 5
4) 곱한다 5  5
5) 나눈다 5  5

2로 11

22  2






import java.util.;

class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        int now = N;
        SetInteger[] setArr = new HashSet[9];
        for(int i=1;i9;i++) {
            setArr[i] = new HashSet();
            setArr[i].add(now);
            now = now10 + N;
        }

        for(int i=1;i9;i++) {
            for(int j=1;ji;j++) {
                for(Integer a  setArr[j]) {
                    for(Integer b  setArr[i-j]) {
                        setArr[i].add(a+b);
                        setArr[i].add(a-b);
                        setArr[i].add(b-a);
                        setArr[i].add(ab);
                        if(a!=0) {
                            setArr[i].add(ba);
                        }
                        if (b!=0) {
                            setArr[i].add(ab);
                        }
                    }
                }
            }
        }

        for(int i=1;i9;i++) {
            if(setArr[i].contains(number)) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}