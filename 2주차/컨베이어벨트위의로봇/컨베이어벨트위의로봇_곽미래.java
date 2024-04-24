package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class boj_20055_컨베이어벨트위의로봇 {

	static int N, K;
	static int[] durability;
	static boolean[] robot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		durability = new int[2*N];
		robot = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i< durability.length; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}

		int step = 0;
		while(isOk()) {
			// 1. 벨트 & 로봇 회전
			int lastD = durability[durability.length-1];
			for(int i=durability.length-1; i >0; i--) {
				durability[i] = durability[i-1];
			}
			durability[0] = lastD;

			for(int i=N-1; i >0; i--) {
				robot[i] = robot[i-1];
			}
			robot[N-1] = robot[0] = false;


			// 2. 로봇 이동
			for(int i=N-1; i >0; i--) {
				if(robot[i-1] && !robot[i] && durability[i] >= 1) {
					durability[i]--;
					robot[i] = true;
					robot[i-1] = false;
				}
			}

			// 3. 로봇 올리기
			if(durability[0] > 0) {
				robot[0] = true;
				durability[0]--;
			}

			step++;
		}
		System.out.println(step);
	}

	static boolean isOk() {
		int cnt = 0;
		for(int i=0; i< durability.length; i++) {
			if(durability[i] == 0) cnt++;
			if(cnt >= K) return false;
		}
		return true;
	}
}
