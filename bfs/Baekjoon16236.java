package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/16236
  아기 상어
  푼 날짜 : 2021-03-17
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16236 {
  private static final int[] dx = {0, 0, 1, -1};
  private static final int[] dy = {-1, 1, 0, 0};
  private static int N;
  private static int[][] map;
  private static Shark shark;

  private static class Position {
    int x;
    int y;
    
    Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static class Fish {
    int x;
    int y;
    int dist;

    Fish(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }
  }

  private static class Shark {
    int x;
    int y;
    int size = 2;
    int eatingCount = 0;

    Shark(int x, int y) {
      this.x = x;
      this.y = y;
    }

    boolean isEatable(int x, int y) {
      return map[x][y] != 0 && map[x][y] < this.size;
    }

    void eatFish(Fish fish) {
      map[fish.x][fish.y] = 0;
      this.eatingCount++;

      // 자신의 크기와 같은 수의 물고기를 먹으면 크기가 증가
      if(this.eatingCount == this.size) {
        this.size++;
        this.eatingCount = 0;
      }

      // 먹은 물고기의 위치로 변경
      this.x = fish.x;
      this.y = fish.y;
    }
  }

  private static List<Fish> getEatableFishes(int x, int y) {
    List<Fish> eatableFishes = new ArrayList<Fish>();
    Queue<Position> q = new LinkedList<Position>();
    int[][] dist = new int[N][N]; // 상어의 위치에서 물고기 사이의 거리를 저장할 배열

    for(int i = 0 ; i < N ; i++) {
      for(int j = 0 ; j < N ; j++) {
        dist[i][j] = -1;
      }
    }

    q.offer(new Position(x, y));
    dist[x][y] = 0;

    while(!q.isEmpty()) {
      Position sharkPos = q.poll();
      int currentX = sharkPos.x;
      int currentY = sharkPos.y;

      for(int i = 0 ; i < 4 ; i++) {
        int nextX = sharkPos.x + dx[i];
        int nextY = sharkPos.y + dy[i];

        if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
        if(dist[nextX][nextY] != -1) continue; // 방문한 곳 다시 방문 X
        if(map[nextX][nextY] > shark.size) continue; // 상어 크기보다 큰 물고기가 있는 곳에는 방문 X

        // 초기 상어 위치에서 이동 거리 계산
        dist[nextX][nextY] = dist[currentX][currentY] + 1;

        // 상어가 물고기를 먹을 수 있을 경우, 먹을 수 있는 물고기 저장
        if(shark.isEatable(nextX, nextY)) {
          eatableFishes.add(new Fish(nextX, nextY, dist[nextX][nextY]));
          q.add(new Position(nextX, nextY));
          continue;
        }

        q.add(new Position(nextX, nextY)); // 동일한 크기의 물고기는 그냥 지나감
      }
    }

    return eatableFishes;
  }

  private static Fish closestFish(List<Fish> eatableFishes) {
    // dist, x, y 순으로 오름차순 정렬
    eatableFishes.sort((o1, o2) -> {
      if(o1.dist != o2.dist) return Integer.compare(o1.dist, o2.dist);
      if(o1.x != o2.x) return Integer.compare(o1.x, o2.x);
      return Integer.compare(o1.y, o2.y);
      // 아래와 같이 정렬 순서를 지정할 수도 있다.
      // if(o1.dist == o2.dist && o1.x == o2.x) return Integer.compare(o1.y, o2.y);
      // if(o1.dist == o2.dist) return Integer.compare(o1.x, o2.x);
      // return Integer.compare(o1.dist, o2.dist);
    });

    return eatableFishes.get(0);
  }

  private static int solution() {
    int answer = 0;

    while(true) {
      if(shark.x == 1 && shark.y == 2) {
        System.out.println("여기 멈춰");
      }
      // 먹을 수 있는 물고기가 존재하는지 확인
      List<Fish> eatableFishes = getEatableFishes(shark.x, shark.y);

      // 먹을 수 없으면 바로 탐색 종료
      if(eatableFishes.size() == 0) {
        return answer;
      }

      // 먹을 수 있을 경우, 가장 가까운 물고기 찾기
      Fish fish = closestFish(eatableFishes);

      // 가까운 물고기 먹은 후, 상어 위치를 물고기 먹은 위치로 초기화
      shark.eatFish(fish);

      // 물고기까지 이동한 거리를 시간으로 추가
      answer += fish.dist;
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    map = new int[N][N];

    for(int i = 0 ; i < N ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < N ; j++) {
        int state = Integer.parseInt(st.nextToken());

        if(state == 9) {
          shark = new Shark(i, j); // 상어의 정보를 저장하는 객체
          map[i][j] = 0; // 초기 상어 위치에는 물고기가 없으므로 계산에 포함되면 안된다.
          continue;
        }
        map[i][j] = state;
      }
    }

    int answer = solution();

    bw.write(answer + "");

    bw.flush();
    bw.close();
    br.close();
  }
}