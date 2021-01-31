package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11054
  가장 긴 바이토닉 부분 수열
  푼 날짜 : 2021-01-31
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon11054 {
  
  private static int getMaxLength(int[] a) {
    int n = a.length;
    int[] l_dp = new int[n]; // 왼쪽에서 오름차순 수열 구함
    int[] r_dp = new int[n]; // 오른쪽에서 내림차순 수열 구함
    int max = 0;

    // 앞에서부터 오름차순 수열 최대길이 구하기
    for(int i = 0 ; i < n ; i++) {
      l_dp[i] = 1;
      for(int j = 0 ; j < i ; j++) {
        if(a[j] < a[i]) l_dp[i] = Math.max(l_dp[i], l_dp[j] + 1);
      }
    }

    // 뒤에서부터 내림차순 수열 최대 길이 구하기
    for(int i = n - 1 ; i >= 0 ; i--) {
      r_dp[i] = 1;
      for(int j = n - 1 ; j > i ; j--) {
        if(a[i] > a[j]) r_dp[i] = Math.max(r_dp[i], r_dp[j] + 1);
      }
    }

    // 바이토닉 수열 길이 구하기
    for(int i = 0 ; i < n ; i++) {
      max = Math.max(l_dp[i] + r_dp[i], max);
    }

    return max - 1; // 오름차순에서 내림차순으로 변경되는 기준이 되는 숫자가 중복되므로 하나 제거
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

    int max = getMaxLength(a);

    bw.write(max + "");

    bw.flush();
    bw.close();
    br.close();
  }
}