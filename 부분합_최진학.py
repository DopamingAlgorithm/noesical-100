"""
[백준] 1806, 부분합 (https://www.acmicpc.net/problem/1806)

10 15
5 1 3 5 10 7 4 9 2 8

[문제파악]
1. 길이 N짜리 수열 (10,000 이하의 자연수)
2. 연속된 수들의 부분합 중에 그 합이 S이상이 되는 것들을 구하기
3. 그 중에서 가장 짧은 것의 길이를 구할 것


[입력]
1. 첫줄에 N, S
2. 둘쨋줄에 수열 (수열의 원소는 공백으로 구분, 10,000이하 자연수

[출력]
1. 첫줄에 구하고자하는 최소의 길이 출력
2. 그 합을 만드는 것이 불가능하면 0을 출력

[풀이방식]
1. 조합으로 구해가지고 조합 숫자들 더하고, 개중에 가장 짧은 것들 중에서 만족하면 나머지는 볼 것 없이 패스하는 식으로 하면 안되나?
2. 근데 이게 이전에 구했던 값들 중에 2개 -> 3개로 넘어갈 때, DP로 저장해뒀으면 상당히 편하겠죠? (중복 제거, 같은 작업 반복 X)
3. 이거 DP 문제인 것 같은데요 ㅋㅋㅋ
4. 배열을 어떻게 만들어야하지 저장해두려면


[보완점]
1. 맨 처음 조합으로 구현 (실패 - 메모리 초과)
2. 모든 조합을 구해서 메모리 터지는 것으로 판단
    2-1. 조합 중 부분합이 S 이상이면 exit(0) 추가 (실패 - 시간 초과)
3. 방향을 잘못 잡고 있다고 판단 
"""


def combinations(nums, k, target):
    def backtrack(start, comb, target):
        if len(comb) == k:
            # 조합의 합이 목표값 이상인 경우에만 출력하고 종료
            # 조합을 구하는 코드이면서 동시에 문제 풀이 코드
            if sum(comb) >= target:
                print(len(comb))
                exit(0)
            return
        for i in range(start, len(nums)):
            comb.append(nums[i])
            backtrack(i + 1, comb, target)
            comb.pop()

    backtrack(0, [], target)


if __name__ == '__main__':
    N, S = map(int, input().split())
    numbers = list(map(int, input().split()))

    for length in range(1, S+1):
        combinations(numbers, length, S)
