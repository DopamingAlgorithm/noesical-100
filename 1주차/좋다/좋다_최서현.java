import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/** 좋다
 * N개의 수 중에서 어떤 수가 다른수 두개의 합으로 나타낼 수 있다면
 * 그 수를 좋다고 한다. N개의 수가 주어지면 그중 좋은 수의 개수는 몇개?
 * (수의 위치가 다르면 값이 같아도 다른수이다)
 *
 * Hint) 정렬+투포인터 알고리즘 => 시간복잡도가 O(N)
 * 정렬한 상태로 투포인터를 통해 각 덧셈의 후보셋팅을 정답에 가깝게 만든다.
 * 이때 주의할점은 음수가 허용되므로 right 포인터를 맨 뒤에 둬야한단 점이다.
 *  ex) -1 0 1 일때 -1+1=0이 될 수 있음
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer str = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(str.nextToken());

        //(1) 오름차순 정렬
        Arrays.sort(arr);

        //(2) left, right로 포인터를 나누어서 더하며 좋은수를 찾는다.
        // current보다 크다면 더 작은수와 더해야하므로 right값을 움직이는 식으로 동작
        int result = 0;
        for(int curr=0; curr<N; curr++){
            int left = 0;
            int right = N-1;
            while(true) {
                if(left == curr) left++;
                else if(right == curr) right--;
                //위처럼 크로스된 상태에선 더이상 탐색이 무의미 => 좋은수 아님
                if(left >= right) break;

                if(arr[left]+arr[right] > arr[curr]) right--; //너무크니까 큰수줄여줌
                else if(arr[left]+arr[right] < arr[curr]) left++; //너무작으니까 작은수 키워줌
                else { //합이 curr일경우 좋은수 갯수 증가
                    result++;
                    break;
                }
            }
        }
        System.out.println(result);
    }

}