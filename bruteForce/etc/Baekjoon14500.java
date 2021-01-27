package bruteForce.etc;

/*
  문제 출처 : https://www.acmicpc.net/problem/14500
  테트로미노
  푼 날짜 : 2021-01-27
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon14500 {
  static int[] X = {1, 0, -1, 0};
  static int[] Y = {0, 1, 0, -1};
  static int n, m, max;
  static int[][] arr;
  static boolean[][] visited;

  // 'ㅗ' 모양을 제외한 나머지 테트로미노 모형들 만들기
  private static void makeTetromino(int x, int y, int cnt, int sum) {
    if(cnt == 4) {
      max = Math.max(max, sum);
      return;
    }

    for(int i = 0 ; i < 4 ; i++) {
      int nextX = x + X[i];
      int nextY = y + Y[i];

      if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
      if(visited[nextX][nextY]) continue;

      visited[nextX][nextY] = true;
      makeTetromino(nextX, nextY, cnt + 1, sum + arr[nextX][nextY]);
      visited[nextX][nextY] = false;
    }
  }

  // 'ㅗ' 모양 테트로미노 만들기
  // + 모양의 전체 합에서 가장 작은 값을 가지는 가지 값을 제거한다.
  private static void makeExecptionTetromino(int x, int y) {
    int wing = 4;
    int sum = arr[x][y];
    int min = Integer.MAX_VALUE;

    for(int i = 0 ; i < 4 ; i++) {
      int nextX = x + X[i];
      int nextY = y + Y[i];

      if(wing <= 2) return;
      if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) {
        wing--;
        continue;
      }

      sum += arr[nextX][nextY];
      min = Math.min(min, arr[nextX][nextY]);
    }

    if(wing == 4) sum -= min; // 날개가 모두 살아있을 경우, 가장 작은 수를 가진 날개 값 제거
    max = Math.max(max, sum);
  }

  private static void getMaxSum() {
    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < m ; j++) {
        makeTetromino(i, j, 0, 0);
        makeExecptionTetromino(i, j);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    arr = new int[n][m];
    visited = new boolean[n][m];

    for(int i = 0 ; i < n ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < m ; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    getMaxSum();

    bw.write(max + "");

    bw.flush();
    bw.close();
    br.close();
  }
}