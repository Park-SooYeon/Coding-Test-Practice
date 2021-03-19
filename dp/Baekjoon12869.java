/*
  문제 출처 : https://www.acmicpc.net/problem/12869
  뮤탈리스크
  푼 날짜 : 2021-03-19
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon12869 {
  private static final int[][] damage = {{9, 3, 1}, {9, 1, 3}, {3, 9 ,1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};
  private static int[][][] dp = new int[61][61][61]; // dp 배열에 a, b, c의 체력에서 모든 scv의 hp가 0일 때까지의 최소 공격횟수 저장

  private static int attack(int a, int b, int c) {
    int minAttack = Integer.MAX_VALUE;
    int[] scv = new int[]{a, b, c};
    
    if(a <= 0 && b <= 0 && c <= 0) {
      return 0;
    }
    
    Arrays.sort(scv);
    a = scv[0] < 0 ? 0 : scv[0];
    b = scv[1] < 0 ? 0 : scv[1];
    c = scv[2] < 0 ? 0 : scv[2];

    // 이미 계산한 것은 다시 계산하지 않음
    if(dp[a][b][c] != 0) {
      return dp[a][b][c];
    }
    
    // 이후 가능한 모든 공격에 대해 진행
    for(int i = 0 ; i < 6 ; i++) {
      minAttack = Math.min(minAttack, attack(a - damage[i][0], b - damage[i][1], c - damage[i][2]) + 1);
    }
    
    dp[a][b][c] = minAttack;
    return minAttack;
  }

  private static int solution(int N, int[] scv) {
    int answer = 0;

    answer = attack(scv[0], scv[1], scv[2]);

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int[] scv = new int[3];

    st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < N ; i++) {
      scv[i] = Integer.parseInt(st.nextToken());
    }

    int answer = solution(N, scv);

    bw.write(answer + "");

    bw.flush();
    bw.close();
    br.close();
  }
}