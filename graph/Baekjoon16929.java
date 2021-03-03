package graph;

/*
  문제 출처 : https://www.acmicpc.net/problem/16929
  Two Dots
  푼 날짜 : 2021-03-03
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon16929 {
  private static final int[] X = {-1, 0, 1, 0};
  private static final int[] Y = {0, 1, 0, -1};
  private static int n, m;
  private static char[][] map;
  private static boolean[][] visited;

  private static boolean dfs(int prevX, int prevY, int x, int y, char color) {
    if(visited[x][y]) return true;

    visited[x][y] = true;

    for(int i = 0 ; i < 4 ; i++) {
      int nextX = x + X[i];
      int nextY = y + Y[i];

      if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
      if(map[nextX][nextY] != color) continue; // 같은 색상이 아니면 움직이지 않음
      if(prevX == nextX && prevY == nextY) continue; // 이전 위치로 가는 방향인 경우 움직이지 않음

      if(dfs(x, y, nextX, nextY, map[nextX][nextY])) return true;
    }

    return false;
  }

  private static String solution() {
    String answer = "No";

    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < m ; j++) {
        if(!visited[i][j]) {
          boolean isLoop = dfs(-1, -1, i, j, map[i][j]);
          if(isLoop) return "Yes";
        }
      }
    }

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new char[n][m];
    visited = new boolean[n][m];

    for(int i = 0 ; i < n ; i++) {
      char[] splitString = br.readLine().toCharArray();
      for(int j = 0 ; j < m ; j++) {
        map[i][j] = splitString[j];
      }
    }

    String result = solution();

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}