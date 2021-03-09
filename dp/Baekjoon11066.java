package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11066
  파일 합치기
  푼 날짜 : 2021-03-09
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 풀이 참조 : https://m.blog.naver.com/tjdwns0920/221135677693
public class Baekjoon11066 {
  // start와 end 사이의 파일들의 총 합을 구하는 함수
  private static int sum(int[] sum, int start, int end) {
    if(start == 0) return sum[end];
    else return sum[end] - sum[start - 1];
  }

  private static int solution(int k, int[] files) {
    int[][] dp = new int[k][k]; // i~j까지 파일을 합치는데 필요한 최소 비용을 저장할 배열
    int[] sum = new int[k];

    sum[0] = files[0];
    for(int i = 1 ; i < k ; i++) {
      sum[i] = sum[i - 1] + files[i];
    }

    for(int i = 0 ; i < k - 1 ; i++) {
      dp[i][i + 1] = files[i] + files[i + 1];
    }
    
    for(int gap = 2 ; gap < k ; gap++) {
      for(int i = 0 ; i + gap < k ; i++) {
        int j = i + gap;
        dp[i][j] = Integer.MAX_VALUE;

        for(int s = i ; s < j ; s++) {
          dp[i][j] = Math.min(dp[i][j], dp[i][s] + dp[s + 1][j] + sum(sum, i, j));
        }
      }
    }
    return dp[0][k - 1];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int t = Integer.parseInt(br.readLine());

    for(int n = 0 ; n < t ; n++) {
      int k = Integer.parseInt(br.readLine());
      int[] files = new int[k];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int i = 0 ; i < k ; i++) {
        files[i] = Integer.parseInt(st.nextToken());
      }

      int result = solution(k, files);
      sb.append(result + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}