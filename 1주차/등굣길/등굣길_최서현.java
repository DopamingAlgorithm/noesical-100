/* 등굣길
하, 우로만 탐색하면서 물에 빠지지않고 학교에 도착하는 최단거리를 1,000,000,007로 나눈 나머지를 리턴하자
격자는 mxn크기이며, 집은 (0,0) 학교는 (m-1, n-1)에 있음
puddles[][] : 물이 잠긴 지역을 담은 이차원배열
*/
import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {

        int[][] map = new int[n][m];
        for(int[] puddle : puddles) map[puddle[1]-1][puddle[0]-1] = -1;
        map[0][0] = 1;

        //dp: 각 노드를 순서대로 돌면서 경로갯수 누적
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                //웅덩이를 만나면 dp계산을 위해 해당 위치를 0으로(경로갯수0) 초기화
                if(map[i][j] == -1) {
                    map[i][j] = 0;
                    continue;
                }
                //map[i][j] = map[i-1][j] + map[i][j-1]이므로
                if(i!=0) map[i][j] += map[i-1][j] % 1000000007;
                if(j!=0) map[i][j] += map[i][j-1] % 1000000007;
            }
        }

        int answer = map[n-1][m-1] % 1000000007;
        return answer;
    }

}