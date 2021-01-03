package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/10844
  쉬운 계단 수
  푼 날짜 : 2021-01-03
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon10844 {
  static final int MOD_NUMBER = 1000000000;

  private static long dp(int n) {
    long sum = 0;
    int[][] m = new int[n + 1][10];

    for(int i = 1 ; i < 10 ; i++) m[1][i] = 1;
    for(int i = 2 ; i <= n ; i++) {
      for(int j = 0 ; j < 10 ; j++) {
        // 점화식 : dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]
        if(j == 0) m[i][j] = m[i - 1][j + 1] % MOD_NUMBER;
        else if(j == 9) m[i][j] = m[i - 1][j - 1] % MOD_NUMBER;
        else m[i][j] = (m[i - 1][j - 1] + m[i - 1][j + 1]) % MOD_NUMBER;
      }
    }

    for(int i = 0 ; i < 10 ; i++) {
      sum += m[n][i];
    }

    return sum % MOD_NUMBER;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    long cnt = dp(n);

    bw.write(cnt + "");

    bw.flush();
    bw.close();
    br.close();
  }
}