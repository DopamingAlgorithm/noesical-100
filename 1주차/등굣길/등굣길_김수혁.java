// 최대 시간 복잡도
// 100! / 10! * 10!
// 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000 / 13,168,189,440,000  ≈ 7.0863845963 × 10^42

// 점화식 : map[i][j] = map[i-1][j] + map[i][j-1]
// 그냥 간단한 고등학교 때 배운 확률과 통계 문제
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        // 선언부
        int[][] map = new int[n+1][m+1];
        int mod = 1000000007;
        
        // map 웅덩이 표시
        for(int i = 0; i < puddles.length; i++){
            map[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        // (1,1)을 갈 수 있는 경우의 수는 1
        map[1][1] = 1;
        
        // 1부터 m까지, 1부터 n까지 돌면서
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                // 웅덩이 만나면 continue
                if(map[i][j] == -1) continue;
                // 왼쪽 혹은 위로 값이 있으면 값 더해주기
                // 놀랍게도 여기에 이걸 안붙여주면 틀림... 이유는 수가 너무 커서
                if(map[i-1][j] > 0) map[i][j] += map[i-1][j]%mod;
                if(map[i][j-1] > 0) map[i][j] += map[i][j-1]%mod;
            }
        }
        
        answer = map[n][m]%mod;
        return answer;
    }
}
