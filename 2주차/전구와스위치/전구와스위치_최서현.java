import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 전구와 스위치
 * N개의 스위치와 N개의 전구가 있으며 꺼졌거나 켜져있음
 * i번 스위치를 누르면 i-1,i,i+1의 세개의 전구 상태가 바뀜
 * N개의 전구들의 현재상태와 우리가 만들고자 하는 상태가 주어졌을때
 * 그 상태를 만들기 위해 스위치를 최소 몇번 누르면 될까
 *
 * i번만 바뀐다고 가정 => 비교해서 다르면 누르면 됨
 * i, i+1만 바뀐다고 가정 => 마찬가지로 비교해서 다르면 누르면 됨
 * i-1, i, i+1 바뀌면? => i를 기준으로 바꿔도 i-1에 의해 바뀐다
 * => 그렇다면 역발상으로, 기준점 자체를 i-1로 설정해버린다면?
 * 즉, i,i+1,i+2라고 생각하며 스위치를 누르면 된다.
 *
 * 주의할점은 1눌러 1,2를 바꾸는건 이 로직에선 불가능 => 0번을 누를순없음
 * => 걍 눌렀다/안눌렀다 둘다판단 ㄱ
 * 3(N)을 눌러 3만 바꾸는건 원래도 불가능 => N-1까지만 진행하는게 맞음
 */
public class 전구와스위치_최서현 {
    public static int[] targetStatus;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //전구의 갯수

        int[] currStatus = new int[N]; //현재상태
        char[] tmp = br.readLine().toCharArray();
        for(int i=0; i<N; i++) currStatus[i] = tmp[i] - '0';

        targetStatus = new int[N]; //목표상태
        tmp = br.readLine().toCharArray();
        for(int i=0; i<N; i++) targetStatus[i] = tmp[i] - '0';

        //첫상태를 눌렀다/ 안눌렀다로 나눈 후 각자 진행한다.(앞에두개)
        int[] currStatus1 = Arrays.copyOf(currStatus, N); //눌렀다
        currStatus1[0] = currStatus1[0]==1? 0:1;
        currStatus1[1] = currStatus1[1]==1? 0:1;
        int[] currStatus2 = Arrays.copyOf(currStatus, N); //안눌렀다

        //스위치 누르기
        int cnt1 = pushSwitch(currStatus1);
        if(cnt1!=-1) cnt1++;
        int cnt2 = pushSwitch(currStatus2);

        //둘다 -1이면 -1을 리턴
        if(cnt1 == -1 || cnt2 == -1) {
            if(cnt1 == cnt2) System.out.println(-1);
            else System.out.println(Math.max(cnt1, cnt2));
        } else System.out.println(Math.min(cnt1,cnt2));
    }


    //반복문 돌면서 target과 똑같아지도록 스위치를 누른다
    public static int pushSwitch(int[] status){
        int cnt = 0;
        for(int i=0; i<N-1; i++){
            if(status[i] != targetStatus[i]){
                //타겟과 현재 상태가 다르다면 변환
                status[i] = status[i]==1? 0:1;
                if(i+1 < N) status[i+1] = status[i+1]==1? 0:1;
                if(i+2 < N) status[i+2] = status[i+2]==1? 0:1;
                cnt++;
            }
        }
        // 정답과 같다면 cnt를, 아니면 -1을 반환
        if(status[N-1] == targetStatus[N-1])  return cnt;
        else return -1;
    }
}