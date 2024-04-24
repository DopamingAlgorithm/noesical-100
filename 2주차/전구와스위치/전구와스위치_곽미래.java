package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제]
 * N개의 스위치와 N개의 전구
 * i번째 스위치를 누르면 i-1, i, i+1 세개의 전구상태 변경
 * 1번 스위치는 1, 2번 변경 N번 스위치는 N-1, N번 스위치 변경
 * N개의 전구와 상태가 있을 때 스위치를 최소 몇번 누르면 되는가
 *
 * 풀이]
 * 첫번째 전구를 킬지 끌지가 정해지면
 * 바로 옆의 전구가 정해짐
 * 2가지 경우에 대해 차례차례 답이랑 비교해나가는 그리디
 *
 * 시간복잡도]
 * N
 */
public class boj_2138_전구와스위치 {

	static int N;
	static boolean[] isLightOn1, isLightOn2, isLightOnAnswer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isLightOn1 = new boolean[N+2];
		isLightOn2 = new boolean[N+2];
		String status = br.readLine();
		for(int i=1; i<=N; i++) {
			isLightOn1[i] = (status.charAt(i-1) == '1');
			isLightOn2[i] = (status.charAt(i-1) == '1');
		}
		isLightOnAnswer = new boolean[N+2];
		status = br.readLine();
		for(int i=1; i<=N; i++) {
			isLightOnAnswer[i] = (status.charAt(i-1) == '1');
		}

		int result1 = makeAnswer(isLightOn1, 2, 0);
		changeState(isLightOn2, 1);
		int result2 = makeAnswer(isLightOn2, 2, 1);

		if(result1 == -1 && result2 == -1) System.out.println(-1);
		else if(result1 == -1) System.out.println(result2);
		else if(result2 == -1) System.out.println(result1);
		else System.out.println(Math.min(result1, result2));
	}

	private static int makeAnswer(boolean[] state, int str, int count) {
		for(int i=str; i<=N; i++) {
			if(state[i-1] != isLightOnAnswer[i-1]) {
				changeState(state, i);
				count++;
			}
		}

		return state[N] == isLightOnAnswer[N] ? count : -1;
	}
	private static void changeState(boolean[] state, int i) {
		state[i] = !state[i];
		state[i+1] = !state[i+1];
		state[i-1] = !state[i-1];
	}
}
