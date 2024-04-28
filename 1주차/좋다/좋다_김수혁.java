import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
처음 떠올린 방식은 이분탐색
그런데 이분탐색을 어떻게 반복해서 하지?

근데 while문 true로 해놓고 하면 될듯

*/

public class BOJ_1253_좋다 {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력받기
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i= 0; i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(nums);

        int answer = 0;

        for(int i = 0 ; i < N; i++){
            int left = 0;
            int right = N-1;

            while(true){
                if(i== left){
                    left++;
                }
                if(i== right){
                    right--;
                }


                if(left >= right) break;

                if(nums[left] + nums[right] > nums[i]){
                    right--;
                }else if(nums[left] + nums[right] < nums[i]){
                    left++;
                }else{
                    answer++;
                    break;
                }

            }
        }
        System.out.println(answer);
    }

}
