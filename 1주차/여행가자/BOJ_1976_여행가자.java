package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976_여행가자 {

    static int N, M;
    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        group = new int[N];

        init();

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++){
                int connect = Integer.parseInt(st.nextToken());
                if(connect == 1){
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int check = -1;


        if(M == 1){
            System.out.println("YES");
            return;
        }

        for(int i = 0; i < M; i++){
            if(i == 0){
                check = group[Integer.parseInt(st.nextToken()) - 1];
                continue;
            }

            if(check != group[Integer.parseInt(st.nextToken()) - 1]){
                System.out.println("NO");
                return;
            }

        }

        System.out.println("YES");

    }

    public static void init(){
        for (int i = 0; i < N; i++) {
            group[i] = i;
        }
    }

    public static int find(int a) {
        if(group[a] == a) return a;
        else return group[a] = find(group[a]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) group[b] = a;
        else group[a] = b;

    }
}
