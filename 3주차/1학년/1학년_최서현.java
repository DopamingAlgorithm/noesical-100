import java.io.BufferedReader;
import java.io.InputStreamReader; 

/* 1학년
	dp[i][j]를 i번쨰수까지계산했을때 j를만들수있는경우의수라고 정의
	dp[1][8] = 1 (8=8)
	dp[2][5] = 1 (8-3=5)
	
	이때 dp[i-1]과 dp[i]의 관계는
	dp[i-1][j]!=0일때 
	dp[i][j+ar[i]] += dp[i-1][j]
	dp[i][j-ar[i]] += dp[i-1][j]
*/
public class Main {    
	static int N;    
	static int[] ar;   
	static long[][] dp;    
	
	public static void main(String[] args) throws Exception {
	        // TODO Auto-generated method stub        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());       
		ar = new int[N];        
		dp = new long[N][21];        
		String[] tmp = br.readLine().split(" ");        
		for(int i = 0; i < tmp.length; i++){            
			ar[i] = Integer.parseInt(tmp[i]);        
		}                 
		
		dp[0][ar[0]] = 1;        
		for(int i = 1; i < N-1; i++){            
			for(int j = 0; j <= 20; j++){                
				if(dp[i-1][j] != 0){                    
					if(j-ar[i] >= 0){                        
						dp[i][j-ar[i]] += dp[i-1][j];                                           
					}                    
					if(j+ar[i] <= 20){                                               
						dp[i][j+ar[i]] += dp[i-1][j];                    
					}                
				}            
			}        
		}        
		
		System.out.println(dp[N-2][ar[N-1]]);    
		}
	}
