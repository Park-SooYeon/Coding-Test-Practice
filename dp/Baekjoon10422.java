package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/10422
  괄호
  푼 날짜 : 2021-03-23
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 풀이 참조 : https://devowen.com/263
public class Baekjoon10422 {
  private static final long MOD_NUMBER = 1000000007L;
  private static int strLength = 5001;

  private static long[] solution() {
    long[] dp = new long[strLength];
    long[] dp2 = new long[strLength];

    dp[0] = dp[2] = 1;
    dp2[0] = dp2[2] = 1;

    // 문자열 길이가 홀수일 경우, 올바른 괄호 문자열을 만들 수 없다.
    for(int i = 4 ; i < strLength ; i += 2) {
      for(int j = 0 ; j <= i - 2 ; j += 2) {
        dp2[i] += dp2[j] * dp2[i - 2 - j];
        dp2[i] %= MOD_NUMBER;
      }
    }

    return dp;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());

    long[] dp = solution();

    for(int i = 0 ; i < T ; i++) {
      int L = Integer.parseInt(br.readLine());

      sb.append(dp[L] + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}