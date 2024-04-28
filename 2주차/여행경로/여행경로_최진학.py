"""
[프로그래머스] 43164, 여행경로
(https://school.programmers.co.kr/learn/courses/30/lessons/43164)

[문제파악]
1. 주어진 항공권 모두 사용
2. '항상' [ICN]에서 출발
3. 주어지는 공항수 3개 이상 10,000개 이하
4. [a, b]는 a -> b라는 의미
5. 주어진 항공권 모두 사용해야 함
6. 가능한 경로 2개 이상? => 앞서는 경로 return
7. 무조건 모든 도시를 방문할 수 있음

[입력]



[구현방법]
1. 경로 탐색 문제인 것 같은데?
2. 모든 도시를 방문할 수 있다니까 ㅇㅇ

- 1.5시간 허비
이거 dict로 푸는 방식은 대체 누가 생각한거여...

[보완점]
- 어렵다.. 삽질 오지게 하다가 답 보고 풀었는데도 온전히 이해 못했음
- 다시 풀자...

"""

from collections import defaultdict


def solution(tickets):
    ticket_dict = defaultdict(list)

    # 출발지, 목적지로 dict 만들기
    for start, end in tickets:
        ticket_dict[start].append(end)

    # 목적지 기준으로 내림차순으로 정렬한다
    # 정렬해뒀으니 알파벳 순서상 가장 빠른 것이 나옴
    # pop()은 pop(0)과 달리 속도도 빠름
    for key in ticket_dict.keys():
        ticket_dict[key].sort(reverse=True)

    answer = []
    path = ["ICN"]  # ICN이 무조건 첫번째니까 냅다 넣음

    # DFS 실행
    while path:
        now = path[-1]  # 현위치는 당연히 path에 넣어둔 가장 마지막이겠죠

        # 현재 위치가 ticket_dict에도 없거나, 티켓 자체도 소진했다면
        if now not in ticket_dict or len(ticket_dict[now]) == 0:
            answer.append(path.pop())  # path에 밀어넣어둔 경로를 하나씩 꺼내서 answer에 밀어넣음
        else:  # 그런게 아니면 path에 넣어
            path.append(ticket_dict[now].pop())

    return answer[::-1]  # 역방향 토해내면 그게 정답