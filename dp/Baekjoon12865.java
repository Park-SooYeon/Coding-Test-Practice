package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/12865
  평범한 배낭
  푼 날짜 : 2021-03-12
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon12865 {
  private static int solution(int n, int k, int[][] items) {
    int answer = 0;
    int[][] dp = new int[n + 1][k + 1]; // 아이템과 무게에 따른 가치합의 최대값을 저장할 변수

    for(int i = 1 ; i <= n ; i++) { // i : 아이템 번호
      for(int j = 1 ; j <= k ; j++) { // j : 무게
        dp[i][j] = dp[i - 1][j]; // 기본값으로 이전 아이템에서의 가치합의 최대합 저장
        if(j - items[i][0] >= 0) { // 남는 무게가 존재하면 이전 아이템에서 구한 가치와 현재 아이템을 추가한 후의 가치 중 큰 값을 저장
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - items[i][0]] + items[i][1]);
        }
      }
    }

    answer = dp[n][k];

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[][] items = new int[n + 1][2];

    for(int i = 1 ; i <= n ; i++) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      items[i][0] = w;
      items[i][1] = v;
    }

    int result = solution(n, k, items);

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}