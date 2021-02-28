package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/10942
  팰린드롬?
  푼 날짜 : 2021-02-28
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon10942 {
  private static boolean isPalindrome(int[] a, int s, int e) {
    int left = s;
    int right = e;

    while(left < right) {
      if(a[left] != a[right]) return false;
      left++;
      right--;
    }

    return true;
  }

  private static boolean[][] getDp(int[] a, int n) {
    boolean[][] dp = new boolean[n + 1][n + 1];

    // 길이가 1일 경우, 무조건 팰린드롬
    for(int i = 1 ; i <= n ; i++) {
      dp[i][i] = true;
    }

    // 길이가 2일 경우, 2개의 수가 같을 경우 팰린드롬
    for(int i = 1 ; i <= n - 1 ; i++) {
      if(a[i] == a[i + 1]) dp[i][i + 1] = true;
    }

    // 길이가 3이상일 경우, 양 끝의 숫자가 같고, 안의 숫자가 팰린드롬일 경우에만 팰린드롬
  for(int i = 2 ; i < n ; i++) {
      for(int j = 1 ; j <= n - i ; j++) {
        if(a[j] == a[j + i] && dp[j + 1][j + i - 1]) {
          dp[j][j + i] = true;
        }
      }
    }

    return dp;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n + 1];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 1 ; i <= n ; i++) {
      a[i] = Integer.parseInt(st.nextToken());
    }
    boolean[][] dp = getDp(a, n);

    int m = Integer.parseInt(br.readLine());

    for(int i = 0 ; i < m ; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      int result = dp[s][e]? 1 : 0;
      sb.append(result + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}