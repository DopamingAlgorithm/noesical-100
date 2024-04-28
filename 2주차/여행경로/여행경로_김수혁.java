import java.util.*;

class Solution {
    int N;
    String[] answer;
    Map<String, List<String>> map = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        
        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            String start = ticket[0];
            String end = ticket[1];

            if (!map.containsKey(start)) {
                List<String> list = new ArrayList<>();
                list.add(end);
                map.put(start, list);
            } else {
                map.get(start).add(end);
            }
        }
        
        // 경로 저장하는 배열
        String[] path = new String[N + 1];
        // 시작지점 설정
        path[0] = "ICN";
        dfs(0, "ICN", path);
        
        return answer;
    }
    
    private void dfs(int depth, String start, String[] path){
        if(depth == N){
            if(answer == null){
                answer = new String[N+1];
                for(int i = 0; i < N+1; i++){
                    answer[i] = path[i];
                }
            } else {
                for (int i = 0; i < N + 1; i++) {
                    if (path[i].compareTo(answer[i]) < 0) {
                        break;
                    } else if (path[i].compareTo(answer[i]) > 0) {
                        return;
                    }
                }
                for (int i = 0; i < N + 1; i++) {
                    answer[i] = path[i];
                }
            }
            return;
        }
        
        if(!map.containsKey(start)) return;
        
        List<String> list = map.get(start);
        for(int i = 0; i< list.size(); i++){
            String t = list.get(0);
            path[depth + 1] = t;
            list.remove(t);
            dfs(depth+1, t, path);
            list.add(t);
        }
    }
}
