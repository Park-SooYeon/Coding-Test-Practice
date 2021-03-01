package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11048
  이동하기
  푼 날짜 : 2021-03-01
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon11048 {
  private static final int[] dx = {1, 0, 1};
  private static final int[] dy = {0, 1, 1};

  private static int solution(int n, int m, int[][] map) {
    int answer = 0;
    int[][] dp = new int[n + 1][m + 1];

    dp[1][1] = map[1][1];

    for(int i = 1; i <= n ; i++) {
      for(int j = 1 ; j <= m ; j++) {
        for(int k = 0 ; k < 3; k++) {
          int nextX = i + dx[k];
          int nextY = j + dy[k];

          if(nextX > n || nextY > m) continue;

          dp[nextX][nextY] = Math.max(dp[nextX][nextY], dp[i][j] + map[nextX][nextY]);
        }
      }
    }

    answer = dp[n][m];

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[][] map = new int[n + 1][m + 1];

    for(int i = 1; i <= n ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j <= m ; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = solution(n, m, map);

    bw.write(answer + "");

    bw.flush();
    bw.close();
    br.close();
  }
}