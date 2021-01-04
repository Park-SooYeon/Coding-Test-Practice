package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/2193
  이친수
  푼 날짜 : 2021-01-04
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2193 {
  private static long dp(int n) {
    long[][] m = new long[n + 1][2];

    m[1][1] = 1;
    for(int i = 2; i <= n ; i++) {
      // 마지막 자리가 0일 경우, 뒤에 1, 0이 올 수 있음
      // 마지막 자리가 1일 경우, 뒤에 0만 올 수 있음
      m[i][0] = m[i - 1][1] + m[i - 1][0];
      m[i][1] = m[i - 1][0];
    }

    return m[n][0] + m[n][1];
  }

  private static long dp2(int n) {
    long[] m = new long[n + 1];

    m[1] = m[2] = 1;
    for(int i = 3 ; i <= n ; i++) {
      m[i] = m[i - 1] + m[i - 2];
    }

    return m[n];
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