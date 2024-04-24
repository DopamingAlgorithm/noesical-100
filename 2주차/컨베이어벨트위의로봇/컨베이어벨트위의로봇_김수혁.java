import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 컨베이어의 길이 : N
 * 벨트의 길이 : 2N
 * 벨트가 한 칸 회전 시 -> 1~2N-1 칸은 다음 번호의 칸으로 이동, 2N 칸은 1번칸으로 이동
 * i번 칸의 내구도는 Ai
 * 1번 칸의 위치 : 올리는 위치, N번 칸의 위치 : 내리는 위치
 * 로봇은 컨베이어 벨트 위에서 스스로 이동 가능! 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동 시 그 칸의 내구도는 즉시 1만큼 감소
 *
 * 과정
 * 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동, 만약 이동 불가하면 이동 X
 * 1. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
 * 종료되었을 때 몇 번째 단계가 진행 중이었는지 구해보자. 가장 처음 수행되는 단계는 1번째 단계이다.
 *
 * 입력
 * N, K
 * A1, A2, A3 ... A2n
 *
 * 제한
 * 2<=N<=100
 * 1<=K<=2N
 * 1<=Ai<=1000
 *
 * 내가 생각하는 문제 유형 : 시뮬레이션
 * 내가 생각하는 문제 해결 방식 : 각 번호에 해당하는 과정을 함수화하고 각 함수를 호출하기 전 내구도가 0인 칸의 개수를 체크한다.
 *
 * 예상 시간 복잡도 : O(N)?
 *
 */


public class Main {

    static int N, K;

    // 내구도 배열
    static int[] durab;
    // 벨트 배열
    static boolean[] robot;  // belt에는 로봇이 있다 없다만 판단하면 되기 때문에, boolean 배열로 해도 될듯? 흠 근데 처음에 1번칸부터 N번칸 이런식으로 있는데 얘네가 도는거니까 Map을 써야할까

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        durab = new int[2*N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 2*N; i++){
            durab[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int cnt = 0;

        while(cnt < K){
            ans++;

            rotateBelt();
            moveRobot();
            putRobot();
            cnt = countNotDurable();
        }

        System.out.println(ans);

    }

    //벨트 회전 함수
    private static void rotateBelt(){
        int temp = durab[2*N-1];

        for(int i = 2*N-1; i > 0; i--){
            durab[i] = durab[i-1];
        }
        for(int i = N-1; i > 0; i--){
            robot[i] = robot[i-1];
        }
        durab[0] = temp;
        robot[0] = false;
        robot[N-1] = false;
    }

    // 로봇 이동 함수
    private static void moveRobot(){
        for(int i = N-1; i > 0; i--){
            // 만약 robot[i-1]에 로봇이 있고, robot[i]에는 로봇이 없고 내구성이 1이상이면
            if(!robot[i] && durab[i] > 0 && robot[i-1]){
                robot[i-1] = false;
                robot[i] = true;
                durab[i]--;
                // 로봇 이동 후에 마지막에 로봇이 있으면 내려줘야함
                robot[N-1]=false;
            }
        }
    }

    // 로봇 투입 함수
    private static void putRobot(){
        if(durab[0] > 0){
            robot[0] = true;
            durab[0]--;
        }
    }

    // 내구도가 0인 칸을 세는 함수
    private static int countNotDurable(){
        int cnt = 0;
        for(int i = 0; i < 2*N; i++){
            if(durab[i]==0) cnt++;
        }

        return cnt;
    }

    private static void printArray1(boolean[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println("belt[i]: "+arr[i]);
        }
    }

    private static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println("arr[i]: "+arr[i]);
        }
    }

}
