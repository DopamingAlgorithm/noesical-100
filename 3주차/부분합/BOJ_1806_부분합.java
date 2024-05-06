import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 일단 N이 10만 이하, S가 1억 이하 인걸 보니 반복문으로 하면 안된다
 * 이분탐색 방법을 이용하면 안되나?
 */

public class Main {
    static int N,S;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        
        while(start <= N && end <= N){
            if(sum >= S && min > end - start){
                min = end - start;
            }
            
            // 우선 더한 값이 S보다 커져야 함
            // 그래서 sum이 S보다 작으면 sum에 arr의 end값을 더해줌 그때까지 end를 1씩 증가시켜주기
            // 그리고 S보다 작을때까지 start를 증가시키면서 가장 짧은 구간 찾기
            if(sum < S) {
                sum += arr[end++];
            } else {
                sum -= arr[start++];
            }
        }
        
        if(min == Integer.MAX_VALUE) System.out.println("0");
        else System.out.println(min);

    }
}
