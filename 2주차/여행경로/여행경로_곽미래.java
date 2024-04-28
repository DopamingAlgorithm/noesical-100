package programmers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 문제]
 * ICN 공항에서 출발해 모든 항공권을 사용하는 경로
 *
 * 조건]
 * 공항은 알파벳 대문자 3글자
 * 3 <= 공항수 <= 10000
 * tickets[a][b] = a에서 b로 가는 항공권
 * 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return
 *
 * 풀이]
 *
 *
 */
public class PGMS_여행경로 {
	static boolean[] visited;
	static ArrayList<String> allRoute;

	public static String[] solution(String[][] tickets) {
		String[] answer = {};
		int cnt = 0;
		visited = new boolean[tickets.length];
		allRoute = new ArrayList<>();

		dfs("ICN", "ICN", tickets, cnt);

		Collections.sort(allRoute);
		answer = allRoute.get(0).split(" ");

		return answer;
	}

	public static void dfs(String str, String route, String[][] tickets, int cnt) {
		if(cnt == tickets.length) {
			allRoute.add(route);
			return;
		}

		for(int i=0; i<tickets.length; i++) {
			if(str.equals(tickets[i][0]) && !visited[i]) {
				visited[i] = true;
				dfs(tickets[i][1], route+ " "+tickets[i][1], tickets, cnt+1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(solution(new String[][]{{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}));
	}
}
