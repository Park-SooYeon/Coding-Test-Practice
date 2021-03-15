package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/17143
  낚시왕
  푼 날짜 : 2021-03-15
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon17143 {
  private static final int[] dx = {0, -1, 1, 0, 0};
  private static final int[] dy = {0, 0, 0, 1, -1};
  private static int R, C, M;
  private static int[][] map;
  private static Map<Integer, Shark> sharks = new HashMap<>();

  private static class Shark {
    int x, y, direction, velocity, size;

    Shark(int x, int y, int velocity, int direction, int size) {
      this.x = x;
      this.y = y;
      this.velocity = velocity;
      this.direction = direction;
      this.size = size;
    }
  }

  private static int catchShark(int position) {
    int sharkSize = 0; // 잡힌 상어의 크기

    for(int i = 1 ; i <= R ; i++) {
      if(map[i][position] != 0) {
        sharkSize = map[i][position];
        sharks.remove(map[i][position]);
        map[i][position] = 0; // 잡은 상어의 위치 초기화
        break;
      }
    }

    return sharkSize;
  }

  private static void moveShark() {
    Queue<Shark> q = new LinkedList<>();
    int[][] temp = new int[R + 1][C + 1];

    for(Integer key : sharks.keySet()) {
      Shark shark = sharks.get(key);
      map[shark.x][shark.y] = 0; // 기존 상어 위치 초기화

      int velocity = shark.velocity;

      // 상하 이동시 상어가 실제로 이동하는 거리 : 상어의 속력 % ((열의 크기 - 1) * 2)
      if(shark.direction == 1 || shark.direction == 2) {
        velocity = velocity % ((R - 1) * 2);
      } else { // 좌우 이동시 상어가 실제로 이동하는 거리 : 상어의 속력 % ((행의 크기 - 1) * 2)
        velocity = velocity % ((C - 1) * 2);
      }

      for(int i = 0 ; i < velocity ; i++) {
        // 벽 끝일 때, 이동 방향 변경
        if(shark.direction == 1 && shark.x == 1) {
          shark.direction = 2;
        } else if (shark.direction == 2 && shark.x == R) {
          shark.direction = 1;
        } else if (shark.direction == 3 && shark.y == C) {
          shark.direction = 4;
        } else if (shark.direction == 4 && shark.y == 1) { 
          shark.direction = 3;
        }

        shark.x += dx[shark.direction];
        shark.y += dy[shark.direction];
      }

      q.add(shark);
    }

    // 같은 위치에 있는 상어 제거 및 상어 위치 변경
    while(!q.isEmpty()) {
      Shark shark = q.poll();
      int x = shark.x;
      int y = shark.y;
      int size = shark.size;

      if(temp[x][y] == 0) {
        temp[x][y] = size;
        continue;
      }

      // 경쟁에서 진 상어들은 제거
      if(temp[x][y] > size) {
        sharks.remove(size);
        continue;
      }

      if(temp[x][y] < size) {
        sharks.remove(temp[x][y]);
        temp[x][y] = size;
      }
    }

    // 상어 이동된 map 배열로 변경
    map = temp;
  }

  private static int solution() {
    int answer = 0;

    // 낚시왕이 오른쪽으로 한 칸씩 이동
    for(int position = 1 ; position <= C ; position++) {
      // 낚시왕이 땅과 제일 가까운 상어를 잡는다.
      answer += catchShark(position);
      // 상어 이동
      moveShark();
    }

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[R + 1][C + 1];

    for(int i = 1 ; i <= M ; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()); // 상어 위치
      int c = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken()); // 상어 속력
      int d = Integer.parseInt(st.nextToken()); // 상어 이동 방향
      int z = Integer.parseInt(st.nextToken()); // 크기

      Shark shark = new Shark(r, c, s, d, z);

      sharks.put(z, shark); // 상어의 크기를 key 값으로 저장
      map[r][c] = z;
    }

    int result = solution();

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}