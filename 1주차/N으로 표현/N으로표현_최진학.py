"""
[문제파악]
1. 한국에 N개의 도시
2. 두 도시 사이에 길이 있을 수도, 없을 수도 있음
3. 여행 경로가 가능한 것인지 판단
4. 단, 다른 곳을 경유해서 도착해도 됨

[input]
1. 첫줄에 도시 N (N은 200 이하)
2. 여행 계획에 속한 도시들 수 M (M은 1000 이하)
3. N개의 정수
4. i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미
	- A와 B가 연결되어있으면 B와 A도 연결되어있다
5. 1이면 연결, 0이면 연결 X
6. 마지막에는 여행 계획이 주어짐
7. 도시의 번호는 1부터 N까지 차례대로 매겨져 있음


[구현방식]
- 그래프 포인트 잡고 탐색 가능한지 판별하면 될듯
- 근데 관건은 다른 도시를 경유해도 되니 결국 순서대로 가기만 하면 된다는 걸
- 그럼 일단,
	1) 다음 경로에 해당하는 도시를 갈 수 있는지 판별
		1-1) 갈 수 있는 경우, 그냥 가면 됨
		1-2) 갈 수 없는 경우, 갈 수 있는 다른 도시 경유해서 가는 식으로 판별


[개선지점]
- union-find를 쓰면 해결된다 그것도 '겁나쉽게' ^^ ㅂㄷㅂㄷ
- union-find : 그래프 알고리즘, 두 노드가 같은 그래프에 속하는지 판별해줌
- union 연산(그래프 합치기) + find 연산(그래프에서 찾기)
- 무식하게 걍 경유지 탐색으로 찾아봐야하면 분명 내가 한것처럼 재귀 반복문 써도 됨 (or BFS)
- 근데 비효율적임 ㅇㅇ, 알고리즘 귀찮다고 대충하고 도망치면 나중에 이렇게 또 마주함..
- 그리고 이렇게 값 변경이 없고, 확인만 하는 문제에선 union-find가 더 효율적(이라고 함)
"""

# 현재 노드의 부모 찾을 때까지 최상단을 찾아 올라감
def find(parent, x):
    if x != parent[x]:
        parent[x] = find(parent, parent[x])
    return parent[x]


# 최상단을 따져서 둘을 합쳐줌 (동작원리 이해?!)
def union(parent, a, b):
    a = find(parent, a)
    b = find(parent, b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


if __name__ == '__main__':
    city = int(input())
    tour_city = int(input())
    connection = [list(map(int, input().split(' '))) for _ in range(tour_city)]
    tour_plan = list(map(int, input().split(' ')))
    success_plan = True

    parent = [_ for _ in range(city)]

    for i in range(city):
        print(i)
        for j in range(city):
            if connection[i][j] == 1:  # 둘이 연결되어 있다? 그럼 union 진행
                union(parent, i, j)


    print('YES' if all(parent[tour_plan[0] - 1] == parent[_ - 1] for _ in tour_plan) else 'NO')
