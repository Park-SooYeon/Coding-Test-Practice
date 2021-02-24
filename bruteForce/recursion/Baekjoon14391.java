package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/14391
  종이 조각
  푼 날짜 : 2021-02-24
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 풀이 참조 : https://whereisusb.tistory.com/230
public class Baekjoon14391 {
  private static int n, m, maxScore = 0;
  private static int[][] arr;
  private static int[][] visited;

  private static void check() {
    int sum = 0;
    // 가로로 잘랐을 경우
    for(int i = 0 ; i < n ; i++) {
      int tempNumber = 0;
      for(int j = 0 ; j < m ; j++) {
        // 가로로 연속되는 경우
        if(visited[i][j] == 1) {
          tempNumber *= 10; // 자릿수 올림
          tempNumber += arr[i][j];
        } else {
          sum += tempNumber;
          tempNumber = 0; // 연속되지 않으므로 수 초기화
        }
      }
      sum += tempNumber;
    }
    // 세로로 잘랐을 경우
    for(int i = 0 ; i < m ; i++) {
      int tempNumber = 0;
      for(int j = 0 ; j < n ; j++) {
        // 세로로 연속되는 경우
        if(visited[j][i] == 0) {
          tempNumber *= 10; // 자릿수 올림
          tempNumber += arr[j][i];
        } else {
          sum += tempNumber;
          tempNumber = 0; // 연속되지 않으므로 수 초기화
        }
      }
      sum += tempNumber;
    }
    maxScore = Math.max(maxScore, sum);
  }

  private static void dfs(int x, int y) {
    // 모든 행 선택 완료
    if(x >= n) {
      check();
      return;
    }

    // 한 행 선택 완료
    if(y >= m) {
      dfs(x + 1, 0);
      return;
    }

    // 해당 좌표 가로 선택
    visited[x][y] = 1;
    dfs(x, y + 1);
    
    // 해당 좌표 세로 미선택
    visited[x][y] = 0;
    dfs(x, y + 1);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    arr = new int[n][m];
    visited = new int[n][m];

    for(int i = 0 ; i < n ; i++) {
      char[] input = br.readLine().toCharArray();
      for(int j = 0 ; j < m ; j++) {
        arr[i][j] = input[j] - '0';
      }
    }

    dfs(0,0);

    bw.write(maxScore + "");

    bw.flush();
    bw.close();
    br.close();
  }
}