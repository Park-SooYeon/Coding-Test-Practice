package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/1495
  기타리스트
  푼 날짜 : 2021-03-16
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1495 {
  private static int solution(int N, int S, int M, int[] V) {
    int answer = -1;
    boolean[][] dp = new boolean[N + 1][M + 1]; // n번째 노래일 때, 연주 가능한 볼륨 정보

    dp[0][S] = true;

    for(int i = 1 ; i <= N ; i++) {
      for(int j = 0 ; j <= M ; j++) {
        if(!dp[i - 1][j]) continue; // 이전 노래에서 연주 가능한 볼륨이 아니면 계산하지 않음

        int volum1 = j + V[i];
        int volum2 = j - V[i];

        if(volum1 <= M) {
          dp[i][volum1] = true;
        }

        if(volum2 >= 0) {
          dp[i][volum2] = true;
        }
      }
    }

    for(int i = M ; i >= 0 ; i--) { // 마지막 곡에서 가능한 가장 큰 볼륨 찾기
      if(dp[N][i]) {
        answer = i;
        break;
      }
    }

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[] V = new int[N + 1];

    st = new StringTokenizer(br.readLine());
    for(int i = 1 ; i <= N ; i++) {
      V[i] = Integer.parseInt(st.nextToken());
    }

    int result = solution(N, S, M, V);

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}