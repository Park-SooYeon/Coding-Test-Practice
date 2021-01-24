package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/1261
  알고스팟
  푼 날짜 : 2021-01-24
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon1261 {
  private static final int[] X = {1, 0, -1, 0};
  private static final int[] Y = {0, 1, 0, -1};

  private static class PositionData {
    int x;
    int y;
    int count; // 벽 부신 횟수

    PositionData(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }

  private static int getMinBreakWallCount(int n, int m, int[][] map) {
    if(n == 1 && m == 1) return 0;
    int minCount = Integer.MAX_VALUE;
    boolean[][] visited = new boolean[n][m];
    Deque<PositionData> q = new ArrayDeque<>();

    q.add(new PositionData(0, 0, 0));
    visited[0][0] = true;

    while(!q.isEmpty()) {
      PositionData current = q.poll();
      int currentX = current.x;
      int currentY = current.y;
      int currentCount = current.count;

      for(int i = 0 ; i < 4 ; i++) {
        int nextX = currentX + X[i];
        int nextY = currentY + Y[i];

        if(nextX == n - 1 && nextY == m - 1) {
          minCount = Math.min(minCount, currentCount);
          continue;
        }
        if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
        if(visited[nextX][nextY]) continue;
        
        if(map[nextX][nextY] == 0) q.addFirst(new PositionData(nextX, nextY, currentCount));
        else q.addLast(new PositionData(nextX, nextY, currentCount + 1));
        visited[nextX][nextY] = true;
      }

    }

    return minCount;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int[][] map = new int[n][m];

    for(int i = 0 ; i < n ; i++) {
      String input = br.readLine();
      // input.charAt(j) - '0' 으로도 map에 값 세팅할 수 있음
      // map[i][j] = input.charAt(j) - '0'; 과 동일
      for(int j = 0 ; j < m ; j++) map[i][j] = Integer.parseInt(input.substring(j, j + 1));
    }

    int result = getMinBreakWallCount(n, m, map);
    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}