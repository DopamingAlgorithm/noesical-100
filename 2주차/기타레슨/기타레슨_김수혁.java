import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 블루에이에는 총 N개의 강의가 들어간다.
 * 강의의 순서가 바뀌면 안된다 => 정렬 X
 * 블루레이는 같은 크기여야 함
 *
 */

public class Main {

    static int N,M;
    static int[] videos;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        videos = new int[N];
        st = new StringTokenizer(br.readLine());
        
        int sum = 0;
        int maxVal = 0;
        
        for(int i = 0; i < N; i++){
            videos[i] = Integer.parseInt(st.nextToken());
            sum += videos[i];
            maxVal = Math.max(maxVal, videos[i]);
        }
        System.out.println(getSol(maxVal, sum, M));
    }
    
    private static int getCount(int mid){
        int cnt = 1;
        int now = mid;
        for(int i = 0; i < N; i++){
            if(now < videos[i]){
                now = mid;
                cnt++;
            }
            now -= videos[i];
        }
        return cnt;
    }
    
    private static int getSol(int start, int end, int target){
        while(start < end){
            int mid = (start+end)/2;
            if(getCount(mid) > target){
                start = mid + 1;
            } else {
               end = mid; 
            }
        }
        return start;
    }
}
