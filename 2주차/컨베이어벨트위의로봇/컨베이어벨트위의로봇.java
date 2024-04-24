import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 컨베이어 벨트 위의 로봇 re
 * 길이가 N인 컨베이어 벨트가 있고, 길이가 2N인 벨트가 이 벨트를 위아래로 감싸 돈다
 * 벨트가 한칸 회전하면 1부터 2N-1까지의 칸은 다음번호칸으로 이동, 2N은 1번으로 감
 * 이때 1번칸을 올리는위치 / N번칸을 내리는위치라 명명
 * 로봇을 올리는 위치에 올리거나 어떤 칸으로 이동하면 그 칸의 내구도는 1만큼 감소
 *
 * 1. 벨트가 각 칸위에 있는 로봇과 함께 한칸 회전
 * 2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한칸 이동. 이동못하면 가마니
 * (로봇이 이동하기 위해서 이동하려는 칸에 로봇이 없으며, 칸의 내구도가 1이상 남아야함)
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올린다
 * 4. 내구도가 0인칸의 갯수가 K개 이상이면 과정을 종료한다. 그렇지않으면 1번으로 돌아감
 *
 * 종료되었을때 몇번째가 진행중이었는지 구하자!
 *
 * 아이디어 : 그냥 시물레이션문제구낭..
 * (1) 컨베이어벨트 한칸 움직인다
 * (1-1) n위치에 로봇이 있다면 내려준다
 * (1-2) 다음위치가 내구도 1이상이어야함
 * (1-3) 2N위치에 있다면 로봇을 올려준다.
 * (2) 내구도 0이 K개 이상이면 종료
 *
 */
public class 컨베이어벨트위의로봇_최서현 {
    static int N,K,blank,result;
    static int[] container;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        container = new int[N*2+1];
        check = new boolean[N*2+1];
        blank = 0;
        result = 0;
        container[0] = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<N*2+1; i++) container[i] = Integer.parseInt(st.nextToken());

        while(true){
            ++result;
            moveContainer();
            stepBy();
            if(blank >= K) break;
        }

        System.out.println(result);
    }

    //벨트가 로봇과 함께 한칸 나아간다.
    static void moveContainer() {
        container[0] = container[N * 2];
        check[0] = check[N * 2];
        for (int i = N * 2; i > 1; i--) {
            container[i] = container[i - 1];
            check[i] = check[i - 1];
        }
        container[1] = container[0];
        check[1] = check[0];

    }

    //가장먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한칸 이동(내구도 1이상)
    static void stepBy() {
        if (check[N] == true) {
            check[N] = false;
        }

        for (int i = N - 1; i > 0; i--) {
            if (check[i] == false)
                continue;
            if (container[i + 1] > 0 && check[i + 1] == false) {
                check[i] = false;
                check[i + 1] = true;
                if (--container[i + 1] == 0)
                    blank++;
            }
        }
        if (container[1] > 0 && check[1] == false) {
            check[1] = true;
            if (--container[1] == 0)
                blank++;
        }

        if (check[N] == true)
            check[N] = false;

    }

}