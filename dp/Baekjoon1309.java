package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/1309
  동물원
  푼 날짜 : 2021-01-16
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1309 {
  private static int MOD_NUMBER = 9901;

  private static int dp(int n) {
    int[][] m = new int[n + 1][3];

    m[1][0] = 1; m[1][1] = 1; m[1][2] = 1;
    for(int i = 2 ; i <= n ; i++) {
      m[i][0] = (m[i - 1][0] + m[i - 1][1] + m[i-1][2]) % MOD_NUMBER;
      m[i][1] = (m[i - 1][0] + m[i - 1][2]) % MOD_NUMBER;
      m[i][2] = (m[i - 1][0] + m[i - 1][1]) % MOD_NUMBER;
    }

    return (m[n][0] + m[n][1] + m[n][2]) % MOD_NUMBER;
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