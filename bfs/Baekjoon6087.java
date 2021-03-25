package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/6087
  레이저 통신
  푼 날짜 : 2021-03-25
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon6087 {
  private static final int[] dx = {-1, 1, 0, 0};
  private static final int[] dy = {0, 0, -1, 1};

  private static class Laser {
    int x;
    int y;
    int direction; // 현재 레이저의 진행 방향을 저장할 변수
    int curveCount = 0;

    Laser(int x, int y) {
      this.x = x;
      this.y = y;
      this.direction = -1; // 초기에는 이동 방향이 없음
    }

    Laser(int x, int y, int direction, int curveCount) {
      this.x = x;
      this.y = y;
      this.direction = direction;
      this.curveCount = curveCount;
    }
  }

  private static Laser getInitLaserPosition(char[][] map) {
    int W = map[0].length;
    int H = map.length;
    Laser laser = null;

    for(int i = 0 ; i < H ; i++) {
      for(int j = 0 ; j < W ; j++) {
        if(map[i][j] == 'C') {
          laser = new Laser(i, j);
          map[i][j] = '.';
          return laser;
        }
      }
    }

    return laser;
  }

  // 커브에만 거울을 설치하면 되므로 커브의 개수가 곧 거울의 개수가 된다
  private static int getMinCurve(char[][] map, Laser laser) {
    int minCurveCount = Integer.MAX_VALUE;
    int W = map[0].length;
    int H = map.length;
    Queue<Laser> q = new LinkedList<Laser>();
    int[][] visited = new int[H][W]; // 시작위치에서 (i, j) 위치까지의 커브개수를 저장할 변수

    for(int i = 0 ; i < H ; i++) {
      for(int j = 0 ; j < W ; j++) {
        visited[i][j] = Integer.MAX_VALUE;
      }
    }
    
    q.offer(laser);
    visited[laser.x][laser.y] = 0;

    while(!q.isEmpty()) {
      Laser currentLaser = q.poll();

      if(map[currentLaser.x][currentLaser.y] == 'C') {
        minCurveCount = Math.min(currentLaser.curveCount, minCurveCount);
      }

      for(int i = 0 ; i < 4 ; i++) {
        int nextX = currentLaser.x + dx[i];
        int nextY = currentLaser.y + dy[i];
        int nextDirection = i;
        int nextCount = currentLaser.curveCount;

        if(nextX < 0 || nextX >= H || nextY < 0 || nextY >= W) continue;
        if(map[nextX][nextY] == '*') continue;
        
        // 방향이 90도로 꺽여지는 경우, 커브 개수 추가
        // 레이저 처음 시작 위치에서의 커브 개수는 추가되면 안됨
        if(currentLaser.direction != i && currentLaser.direction != -1) {
          nextCount++;
        }

        if(visited[nextX][nextY] < nextCount) {
          continue;
        }

        visited[nextX][nextY] = nextCount;
        q.offer(new Laser(nextX, nextY, nextDirection, nextCount));
      }
    }

    return minCurveCount; // 시작 위치에서 커브 계산이 되는 것을 빼줌
  }

  private static int solution(char[][] map) {
    int answer = 0;
    
    Laser laser = getInitLaserPosition(map);

    answer = getMinCurve(map, laser);

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int W = Integer.parseInt(st.nextToken());
    int H = Integer.parseInt(st.nextToken());
    char[][] map = new char[H][W];

    for(int i = 0 ; i < H ; i++) {
      map[i] = br.readLine().toCharArray();
    }

    int answer = solution(map);

    bw.write(answer + "");

    bw.flush();
    bw.close();
    br.close();
  }
}