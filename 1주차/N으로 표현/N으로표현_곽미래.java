import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 문제]
 - N와 사칙연산으로 number을 표현하는
 - 가장 작은 N 사용 횟수

 제약]
 - 1 <= N <= 9
 - 1 <= number <= 32000
 - 수식은 괄호와 사칙연산
 - 나누기 연산의 나머지 무시
 - 최솟값이 8보다 크면 -1 return

 풀이]
 N이 5일 때
 - 1개 [5]
 - 2개 [55, 10, 0, 25, 1]
 - 3개 [555, 60, 15, 5, 30, 6, 5, 20, 4, ...]
 3개일 때는 1개일 때와 2개일 때의 사칙연산(+, -, x, /)로 구할 수 있음
 여기서 x, /, -의 경우 순서에 따라 결과가 달라지기 때문에 순서를 고려해야함

 시간복잡도]
 -

 */
public class N으로표현_곽미래 {

	public int solution(int N, int number) {
		if(N == number) return 1;
		List<Set<Integer>> nums = new ArrayList<>();
		nums.add(new HashSet<>());
		nums.add(new HashSet<>());
		nums.get(1).add(N);
		nums.add(new HashSet<>());
		nums.get(2).add(N*10 + N);
		nums.get(2).add(N+N);
		nums.get(2).add(0);
		nums.get(2).add(N*N);
		nums.get(2).add(1);
		if(nums.get(2).contains(number)) return 2;

		int count = 2;
		while(++count <= 8) {
			nums.add(new HashSet<>());
			nums.get(count).add(createRepeatedNumber(N, count));
			for(int i=1; i<=count/2; i++) {
				for(int num1 : nums.get(i)) {
					for(int num2 : nums.get(count-i)) {
						nums.get(count).add(num1 + num2);
						nums.get(count).add(num1 - num2);
						nums.get(count).add(num1 * num2);
						if(num1 != 0) nums.get(count).add(num1 / num2);
						nums.get(count).add(num2 - num1);
						nums.get(count).add(num2 * num1);
						if(num2 != 0) nums.get(count).add(num2 / num1);
					}
				}
			}
			for (int num : nums.get(count)) {
				if(num == number) return count;
			}
		}
		return -1;
	}

	public static int createRepeatedNumber(int num, int count) {
		int result = 0;
		for (int i = 0; i < count; i++) {
			result = result * 10 + num;
		}
		return result;
	}

	public static void main(String[] args) {

	}
}
