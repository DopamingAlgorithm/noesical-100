import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 문제]
 * 모든 사람이 심사를 받는데 걸리는 시간을 최소로 만들기
 * n : 입국 심사 기다리는 사람 수
 * times : 각 심사관이 한명을 심사하는데 걸리는 시간
 *
 *
 */
public class 입국심사_곽미래 {

	public long solution(int n, int[] times) {
		long answer = 0;
		Arrays.sort(times);
		long left = 0;
		long right = times[times.length-1] * (long)n; //모든 사람이 가장 느리게 심사받음

		while(left <= right) {
			long mid = (left + right) / 2;
			long complete = 0;
			for (int i = 0; i < times.length; i++)
				complete += mid / times[i];
			if (complete < n) // 해당 시간에는 모든 사람이 검사받을 수 없다.
				left = mid + 1;
			else {
				right = mid - 1;
				answer = mid; // 모두 검사받았으나, 더 최솟값이 있을 수 있다.
			}
		}
		return answer;
	}

	public static void main(String[] args) {

	}
}
