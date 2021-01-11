package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/15988
  1, 2, 3 더하기 3
  푼 날짜 : 2021-01-11
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon15988 {
  private static final int maxN = 1000000;
  private static final long MOD_NUMBER = 1000000009L;

  private static long[] makeDP() {
    long[] m = new long[maxN + 1];
    
    m[1] = 1; m[2] = 2; m[3] = 4;
    for(int i = 4 ; i <= maxN ; i++) {
      // 점화식 : m[i] = m[i - 1] + m[i - 2] + m[i - 3]
      m[i] = (m[i - 1] + m[i - 2] + m[i - 3]) % MOD_NUMBER;
    }

    return m;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int t = Integer.parseInt(br.readLine());

    // 케이스 수행때마다 반복 계산을 수행하지 않기 위해 주어진 값까지 m 값 구하기
    long[] m = makeDP();
    
    for(int i = 0 ; i < t ; i++) {
      int n = Integer.parseInt(br.readLine());

      sb.append(m[n] + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}