package programmers;

import java.util.*;

class programmers_여행경로 {
    String[][] tickets2;
    boolean[] visited;
    PriorityQueue<String> answer = new PriorityQueue<>();

    public String[] solution(String[][] tickets) {
        tickets2 = tickets;
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", 0);
        return answer.peek().split(" ");
    }

    public void dfs(String now, String path, int cnt){
        if(cnt == tickets2.length){
            answer.offer(path);
            return;
        }

        for(int i = 0; i < tickets2.length; i++){
            if(!visited[i] && now.equals(tickets2[i][0])){
                visited[i] = true;
                dfs(tickets2[i][1], path +" "+ tickets2[i][1], cnt + 1);
                visited[i] = false;
            }
        }
    }
}