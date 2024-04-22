import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 첫번째 전구를 킬때와 안킬때로 나누면?
 * 시간 복잡도는 O(n)
 *
 * 2번째부터 n번째 전구까지 내 앞 전구가 원하는 대로 켜져있거나 꺼져있지 않으면 스위치를 누르고
 * 원하늗내로 켜져있거나 꺼져있으면 그냥 넘어간다
 *
 * 마지막에 값 체크해주고 안맞으면 -1 출력
 *
 */


public class Main {

    static int N;
    static int[] first_bulb_on;
    static int[] first_bulb_off;
    static int[] answer_arr;
    static int first_on_answer, first_off_answer;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String origin = br.readLine();
        String expect = br.readLine();

        first_bulb_on = new int[N];
        first_bulb_off = new int[N];
        answer_arr = new int[N];

        first_on_answer = 0;
        first_off_answer = 0;

        // 배열에 값 넣어주기
        for(int i = 0; i < N; i++){
            first_bulb_on[i] = origin.charAt(i)-'0';
            first_bulb_off[i] = origin.charAt(i)-'0';
            answer_arr[i] = expect.charAt(i)-'0';
        }

        // 맨 첫 전구 켜주기
        first_bulb_on[0] = 1 - first_bulb_on[0];
        first_bulb_on[1] = 1 - first_bulb_on[1];
        first_on_answer++;

        decideBulbOn();

        if(first_bulb_on[N-1] != answer_arr[N-1]) first_on_answer=100002;
        if(first_bulb_off[N-1] != answer_arr[N-1]) first_off_answer=100002;
        if(first_on_answer==100002 && first_off_answer==100002) System.out.println(-1);
        else System.out.println(Math.min(first_on_answer, first_off_answer));
    }

    private static void decideBulbOn(){
        for(int i = 1; i < N; i++){
            // 첫번째 전구 켜진 배열과 목표 배열 비교하기
            // 내가 현재 가리키는 idx의 앞 idx의 값이 목표 값과 맞는지 확인하고 다르다면 전구를 켠다
            if(first_bulb_on[i-1] != answer_arr[i-1]){
                // 전구 켜기
                first_bulb_on[i-1] = 1 - first_bulb_on[i-1];
                first_bulb_on[i] = 1 - first_bulb_on[i];
                first_on_answer++;
                // 근데 다음꺼는 현재 인덱스가 n-1인지 확인해줘야 함
                if(i != N-1){
                    first_bulb_on[i+1] = 1 - first_bulb_on[i+1];
                }
            }

            // 첫번째 전구 꺼진 배열과 목표 배열 비교하기
            if(first_bulb_off[i-1] != answer_arr[i-1]){
                // 전구 켜기
                first_bulb_off[i-1] = 1 - first_bulb_off[i-1];
                first_bulb_off[i] = 1 - first_bulb_off[i];
                first_off_answer++;
                if(i != N-1){
                    first_bulb_off[i+1] = 1 - first_bulb_off[i+1];
                }
            }
        }
    }

    private static void printArr(int[] arr){
        for(int i = 0; i < N; i++){
            System.out.println("arr[i]: "+arr[i]);
        }
    }
}
