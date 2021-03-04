package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11060
  점프 점프
  푼 날짜 : 2021-03-04
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon11060 {
  private static int solution(int n, int[] a) {
    int answer = -1;
    int[] dp = new int[n];

    if(n == 1) return 0; // 길이가 1이면 뛸 필요가 없다
    if(a[0] == 0) return -1; // 처음부터 뛸 수 없으면 계산할 필요가 없다

    for(int i = 0 ; i < n ; i++) {
      dp[i] = Integer.MAX_VALUE;
    }

    dp[0] = 0; // 처음 뛰는 위치

    // i 위치에서 뛰는 모든 경우를 확인
    for(int i = 0 ; i < n ; i++) {
      if(dp[i] == Integer.MAX_VALUE) continue; // 방문한 적이 없는 곳에서는 계산 X
      for(int j = 1 ; j <= a[i] ; j++) {
        if(i + j >= n) continue; // 범위를 넘어갈 수 없음
        dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
      }
    }

    answer = dp[n - 1] == Integer.MAX_VALUE? -1 : dp[n - 1];

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n ; i++) {
      a[i] = Integer.parseInt(st.nextToken());
    }

    int result = solution(n, a);

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}