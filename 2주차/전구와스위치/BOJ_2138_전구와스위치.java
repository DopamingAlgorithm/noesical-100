package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2138_전구와스위치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = Integer.MAX_VALUE;
        char[] originalLight = br.readLine().toCharArray();
        char[] changedLight = br.readLine().toCharArray();
        boolean[] originalArr1 = new boolean[N];
        boolean[] originalArr2 = new boolean[N];
        boolean[] changedArr = new boolean[N];

        originalArr2[0] = true;
        originalArr2[1] = true;

        for (int i = 0; i < N; i++) if(originalLight[i] != changedLight[i]) changedArr[i] = true;

        answer = Math.min(answer, checkPushCount(0, N, originalArr1, changedArr));
        answer = Math.min(answer, checkPushCount(1, N, originalArr2, changedArr));

        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.println(answer);
    }


    static int checkPushCount(int cnt, int N, boolean[] arr, boolean[] changedArr){
        boolean isAble = true;

        for (int i = 1; i < N; i++) {
            if(arr[i - 1] != changedArr[i - 1]){
                arr[i - 1] = !arr[i - 1];
                arr[i] = !arr[i];
                if(i != N - 1) arr[i + 1] = !arr[i + 1];
                cnt++;
            }
        }

        for (int i = 0; i < N; i++) {
            if(arr[i] != changedArr[i]){
                isAble = false;
                break;
            }
        }

        if(isAble) return cnt;
        else return Integer.MAX_VALUE;
    }
}
