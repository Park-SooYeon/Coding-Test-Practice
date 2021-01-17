package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11057
  오르막 수
  푼 날짜 : 2021-01-17
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon11057 {
  private static final int MOD_NUMBER = 10007;

  private static int dp(int n) {
    int[][] m = new int[n + 1][10];

    m[1][0] = m[1][1] = m[1][2] = m[1][3] = m[1][4] = m[1][5] = m[1][6] = m[1][7] = m[1][8] = m[1][9] = 1;
    for(int i = 2 ; i <= n ; i++) {
      for(int j = 0 ; j < 10 ; j++) {
        for(int k = 0 ; k <= j ; k++) {
          m[i][j] = (m[i][j] + m[i - 1][k]) % MOD_NUMBER;
        }
      }
    }

    return (m[n][0] + m[n][1] + m[n][2] + m[n][3] + m[n][4] + m[n][5] + m[n][6] + m[n][7] + m[n][8] + m[n][9]) % MOD_NUMBER;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    int result = dp(n);

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}