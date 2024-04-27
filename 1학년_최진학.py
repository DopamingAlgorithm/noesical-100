"""
[문제파악]
백준 5557, 1학년 (https://www.acmicpc.net/problem/5557)

1. 마지막 두 숫자 사이에 '='을 넣음
2. 나머지 숫자 사이에는 '+' or '-'을 넣어 등식을 만듦
3. 올바른 등식 만들기
4. 음수 모름, 20 넘는 수 모름
5. 따라서 왼쪽부터 계산할 때, 중간 계산에 나오는 모든 수가 0 이상 20 이하여야 함
6. 올바른 등식의 수를 구하는 프로그램을 작성하시오


[Input]
1. 첫줄에 N (3 <= N <= 100)
2. 둘째줄에 정수 N개 (0 이상, 9 이하)


[풀이방식]
1. 보자마자 떠오르는 방식은 N개의 정수를 배열로 때려넣고 하나씩 꺼내서 +, - 다 해보는 것
2. 재귀! 그렇게 비용이 크지 않을지도? 일단 해보자



[보완점]
1. 보기좋게 시간 초과 ㅋㅋㅋ
2. 이거 DP 문제임 ㅋㅋ 하... (40짜리 돌리니까 안그래도 한 세월 걸리긴하드라 ㅎ...)
3. DP는 볼수록 신기하다 이런 아이디어 어케 내는거임

"""


N = int(input())
numbers = list(map(int, input().split()))
dp = [[0] * 21 for _ in range(N)]  # 20까지 계산 가능하니까 배열을 21까지 만들어놓기

# 첫번째는 계산하고 시작해야지
dp[0][numbers[0]] = 1

# N-1인 이유는? -> 마지막 숫자는 result가 되어야하니까
for i in range(1, N-1):
    # 아래 for문의 의미 : j까지 만들 수 있는 숫자들을 찾아 맹글어보자!!
    # 20까지 가능해서 20까지 반복문
    for j in range(21):

        # 더해도 20이하면 저장
        if j + numbers[i] <= 20:
            dp[i][j+numbers[i]] += dp[i-1][j]

        # 빼도 0이상이면 저장
        if j - numbers[i] >= 0:
            dp[i][j-numbers[i]] += dp[i-1][j]

# 결과값!
# = 마지막 숫자(N-2)까지 사용해서, 결과값(numbers[-1])을 만들 수 있는 갯수는 몇개?
# 참고로 N-1은 결과값이다 (이점을 유의할 것)
print(dp[N-2][numbers[-1]])


# [재귀 코드] - TIme Out
# result = 0
#
#
# def recursion(numbers, cal_result, index, current_result):
#     print(numbers[index:], cal_result, index, current_result)
#     global result
#     if index == len(numbers):
#         if current_result == cal_result:
#             result += 1
#         return
#
#     if current_result < 0 or 20 < current_result: return
#
#     recursion(numbers, cal_result, index + 1, current_result + numbers[index])
#     recursion(numbers, cal_result, index + 1, current_result - numbers[index])
#
#
# if __name__ == '__main__':
#     N = int(input())
#     numbers = list(map(int, input().split()))
#     target_result = numbers.pop()  # 마지막 숫자는 결과값이니까 빼버리기
#     recursion(numbers, target_result, 1, numbers[0])  # 초기 배열, 결과값, 시작 인덱스는 0번 이후 1번, 현재값은 0번 인덱스
#     print(result)

