package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/14503
  로봇 청소기
  푼 날짜 : 2021-01-30
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon14503 {
  private static final int[] X = {-1, 0, 1, 0};
  private static final int[] Y = {0, 1, 0, -1}; // 북, 동, 남, 서 방향으로 이동
  private static int n, m, count;
  private static int[][] map;

  private static void dfs(int x, int y, int d, int[][] map) {
    // 현재 위치 청소
    if(map[x][y] == 0) {
      map[x][y] = -1;
      count++;
    }

    for(int i = d - 1 ; i >= d - 4 ; i--) {
      // 로봇청소기의 왼쪽 방향 확인
      int nextDirection = i < 0 ? i + 4 : i;
      int nextX = x + X[nextDirection];
      int nextY = y + Y[nextDirection];

      if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
      if(map[nextX][nextY] == 1 || map[nextX][nextY] == -1) continue;
      // 왼쪽 방향 청소
      dfs(nextX, nextY, nextDirection, map);
    }

    // 4방향 모두 확인하고 이동할 방향이 없으면 후진
    int backDirection = d - 2 < 0 ? d + 2 : d - 2;
    int backX = x + X[backDirection];
    int backY = y + Y[backDirection];
    if(map[backX][backY] == 1) {
      System.out.println(count);
      System.exit(0);
    } else {
      dfs(backX, backY, d, map);
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new int[n][m];

    st = new StringTokenizer(br.readLine());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());

    for(int i = 0 ; i < n ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < m ; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    dfs(r, c, d, map);

    bw.flush();
    bw.close();
    br.close();
  }
}