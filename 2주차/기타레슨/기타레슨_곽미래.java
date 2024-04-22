package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제]
 * N개의 강의를 M개로 분리
 * 각 강의별로 시간이 존재
 * 한 섹션에 존재하는 강의 시간을 최소로 만들어야함
 *
 * 조건]
 * 1 <= N <= 100,000
 * 1 <= M <= N
 * 각 강의 길이 <= 10,000
 *
 * 풀이]
 * 이진탐색
 * low = 각 강의 시간 중 최대 값
 * high = 각 강의 시간을 모두 더한 값
 * mid = (high + low)/2
 * mid 값을 기준으로 블루레이 계산
 * - 블루레이 개수가 더 많다면 강의시간을 늘려야하니  low = mid +1
 * - 블루레이 개수가 더 적다면 강의시간을 줄여야하니 high = mid -1;
 *
 */
public class boj_2343_기타레슨 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] lecture = new int[N];
		int high = 0;
		int low = 0;
		for(int i=0; i<N; i++) {
			lecture[i] = Integer.parseInt(st.nextToken());
			high += lecture[i];
			low = Math.max(low, lecture[i]);
		}

		int mid = 0;
		int sum, count;
		while(low <= high) {
			mid = (low+high)/2;
			sum = count = 0;
			for(int i=0; i<N; i++) {
				if(sum + lecture[i] > mid) {
					sum = 0;
					count++;
				}
				sum += lecture[i];
			}

			if(sum > 0) count++;

			if(count <= M) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(low);
	}
}
