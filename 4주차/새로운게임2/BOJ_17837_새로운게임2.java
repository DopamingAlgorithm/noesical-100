package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. map은 각 칸이 stack
 * 2. 이동 판별은 deque
 * 3. 각 Unit은 클래스로 지정(x, y, 방향, 번호)
 * 4. 각 유닛 정보를 순서대로 저장한 unitInfo 배열
 * 5. map 한칸 한칸도 Tile Class로 만들자(stack, 색깔)
 * 6. 파랑 칸을 만나서 반대로 두 칸 튕겨질 때, 만약 도착 위치가 파랑칸이면 한 칸만 이동(맵 밖도 파랑칸임)
 *
 * [ 진행 ]
 * 0. 턴 + 1
 * 	- turn > 1000이면 종료
 * 1. 이동
 * 	- unitInfo 배열에서 해당 unit 위치 및 방향 찾기
 * 	- unit 위치 Tile에서 해당 번호 나올때까지 stack에서 pop, 이동 deque에 넣기
 * 	- stack에서 뽑을때마다 unit 이동 방향으로 이동시키기
 * 2. 타일 색 확인
 * 	- Tile 색 확인
 * 	- 아무것도 아니면 그냥 이동
 * 	- 빨간색이면 move deque에서 해당 칸으로 poll(이동한 애들만 역순)
 * 	- 파랑칸 or 맵 밖이면 현재 말(최하단 말)만 방향 180도 전환, 전환된 방향으로 1칸  이동
 * 		- 1칸 더 이동, 이 때 도착칸이 파란색(or 맵 밖이면) 현재에서 종료.
 * 3. 이동 완료 후 현재 칸 체크
 * 	- 현재 unit이 있는 위치의 tile에 몇 개의 말이 있는지 size()로 체크
 * 	- size() >= 4 이면 즉시 종료
 */

public class BOJ_17837_새로운게임2 {
    static class Unit{
        int row;
        int col;
        int num;
        int dir;

        Unit(int row, int col, int num, int dir){
            this.row = row;
            this.col = col;
            this.num = num;
            this.dir = dir;
        }

        //방향 180도 전환
        void changeDirection(){
            //0 <-> 1, 2 <-> 3 으로 방향 변경
            if(dir < 2) dir = 1 - dir;
            else dir = 5 - dir;
        }

        //이동
        void move(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    static class Tile{
        Stack<Unit> units;
        int color; // 0기본, 1 빨강, 2 파랑

        Tile(int color){
            units = new Stack<>();
            this.color = color;
        }
    }

    static Tile[][] map;
    static Deque<Unit> moveDeque = new ArrayDeque<>(); //이동 저장용 deque
    static Unit[] unitsInfo; //unit 정보 저장용
    static int N, K, turn;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static boolean isAnswerExist = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        turn = 0;

        map = new Tile[N][N];
        unitsInfo = new Unit[K];

        //map 만들기
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int color = Integer.parseInt(st.nextToken());
                map[i][j] = new Tile(color);
            }
        }

        //Unit 정보 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;

            Unit unit = new Unit(row, col, i, dir);
            unitsInfo[i] = unit;
            map[row][col].units.push(unit);
        }

        //1. turn 체크
        W : while(turn < 1000){
            //1.1 turn 증가
            turn += 1;

            //말 이동 시작
            for (int num = 0; num < K; num++) {
                //2. 이동 목록에 저장
                reserveUnitMove(num);
                //3. 타일 색 확인
                checkTileColor(num);
                //4. 해당 타일 높이 확인
                if(checkTileHight(num) >= 4){
                    isAnswerExist = true;
                    break W;
                }
            }
        }

        if(!isAnswerExist) turn = -1;
        System.out.println(turn);
    }

    //2. 이동 목록에 저장
    static void reserveUnitMove(int num){
        //1. 이동할 말 위치 확인
        int row = unitsInfo[num].row;
        int col = unitsInfo[num].col;
        int dir = unitsInfo[num].dir;

        //2. 이동할 말이 있는 칸에 위치한 모든 말 이동 배열로 옮기기
        while(!map[row][col].units.isEmpty()){
            Unit unit = map[row][col].units.pop();
            unit.move(unit.row + dr[dir], unit.col + dc[dir]);
            moveDeque.push(unit);
            if(unit.num == num) break;
        }
    }

    //3. 타일 색 확인
    static void checkTileColor(int num){
        //1.  이동한 위치 확인
        int row = unitsInfo[num].row;
        int col = unitsInfo[num].col;

        //2. 타일 색 확인
        //- 만약 row, col이 맵 밖일 경우 파란칸으로 지정
        int color = 0;
        if(OOB(row, col)) color = 2;
        else color = map[row][col].color;


        switch (color){
            case 0: //일반 칸
                while(!moveDeque.isEmpty()) map[row][col].units.push(moveDeque.poll());
                break;
            case 1: // 빨간 칸
                while(!moveDeque.isEmpty()) map[row][col].units.push(moveDeque.pollLast());
                break;
            case 2: //파란 칸
                unitsInfo[num].changeDirection();
                int dir = unitsInfo[num].dir;
                int nextRow = row + dr[dir];
                int nextCol = col + dc[dir];

                //한번 더 이동시 파랑칸이 아니면, 이동
                if(!OOB(nextRow + dr[dir], nextCol + dc[dir]) && map[nextRow + dr[dir]][nextCol + dc[dir]].color != 2){
                    nextRow += dr[dir];
                    nextCol += dc[dir];

                    for (Unit unit:moveDeque) unit.move(nextRow, nextCol);

                    if(map[nextRow][nextCol].color == 0) while(!moveDeque.isEmpty()) map[nextRow][nextCol].units.push(moveDeque.poll());
                    else while(!moveDeque.isEmpty()) map[nextRow][nextCol].units.push(moveDeque.pollLast());
                    break;
                }

                for (Unit unit:moveDeque) unit.move(nextRow, nextCol);
                while(!moveDeque.isEmpty()) map[nextRow][nextCol].units.push(moveDeque.poll());
                break;
        }
    }

    static int checkTileHight(int num){
        //1. 확인할 칸 위치 저장
        int row = unitsInfo[num].row;
        int col = unitsInfo[num].col;

        //2. 높이 return
        return map[row][col].units.size();
    }

    static boolean OOB(int row, int col){ return row < 0 || row >= N || col < 0 || col >= N; }

}
