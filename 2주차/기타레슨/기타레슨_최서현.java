import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 기타 레슨
 * 블루레이에는 N개의 강의가 들어가는데 순서가 바뀌면 안됨
 * 즉 i와 j번 강의를 같은 블루레이에 녹화하려면 i와 j사이 모든 강의도 같은 블루레이에!
 * 얼마나 팔릴지 모르니까 갯수는 가급적 줄일것임. M개의 블루레이에 전부 녹화할것임
 * 이때 블루레이 크기(녹화 가능한 길이)는 최소이며 M개 블루레이는 모두 같은크기
 * 강토의 각 강의 길이가 분단위로 주어질때 가능한 블루레이 크기중 최소?
 *
 * 입력 : 강의의 수 N과 M이 주어진다. 다음줄엔 강의순서대로 분단위로 주어짐
 * 각 강의는 10000분을 넘지 않는다.
 *
 * 아이디어 : 순서를 바꿀수없으니까... 블루레이 크기를 지정하고 가능한지 확인하자
 * 이진탐색을 이용하여 어디쯤이 적합한지 찾을 수 있음....
 * 총 영상이 60분이면 30분에서 시작?..
 */
public class 기타레슨_최서현 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); //강의의 수
        int M = Integer.parseInt(st.nextToken()); //블루레이 갯수
        st = new StringTokenizer(br.readLine(), " ");

        int min = 0, max=0;
        int[] runtimes = new int[N]; //강의당 분
        for(int i=0; i<N; i++) {
            runtimes[i] = Integer.parseInt(st.nextToken());
            max += runtimes[i]; //블루레이 용량의 최대값은 전부합한값
            if(min < runtimes[i]) min = runtimes[i]; //블루레이 용량의 최솟값은 개중 가장큰것
        }

        //이분탐색 시작
        while(min<=max){
            int middle = (min+max)/2; //중앙값
            int sum=0, cnt=1; //한 블루레이당 총용량, 블루레이갯수
            for(int i=0; i<N; i++){
                //더했을때 블루레이 용량 넘어가면 새 블루레이 꺼냄
                if(sum+runtimes[i] > middle){
                    cnt++;
                    sum=0;
                }
                sum += runtimes[i];
            }
            if(cnt > M) min = middle + 1; //갯수가 M개보다 크면 용량 키워야하므로 +1
            else max = middle-1; //갯수가 M개보다 작으면 용량 줄여야하므로 -1
        }

        System.out.println(min);
    }
}