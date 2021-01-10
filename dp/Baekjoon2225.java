package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/2225
  합분해
  푼 날짜 : 2021-01-10
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon2225 {
  private static int dp(int n, int k) {
    int[][] m = new int[k + 1][n + 1]; // k번 더해서 n이 되는 경우의 수를 저장

    for(int i = 1 ; i <= k ; i++) {
      m[i][0] = 1; // 0도 더하는 수에 추가
    }

    for(int i = 1 ; i <= k ; i++) {
      for(int j = 1 ; j <= n ; j++) {
        m[i][j] = (m[i][j - 1] + m[i - 1][j]) % 1000000000;
      }
    }

    return m[k][n];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int result = dp(n, k);

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}