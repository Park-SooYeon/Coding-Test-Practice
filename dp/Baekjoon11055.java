package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11055
  가장 큰 증가 부분 수열
  푼 날짜 : 2021-01-23
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon11055 {
  
  private static int getMaxSum(int n, int[] arr) {
    int maxSum = 0;
    int[] m = new int[n];

    for(int i = 0 ; i < n ; i++) {
      m[i] += arr[i];
      for(int j = 0 ; j < i ; j++) {
        if(arr[i] > arr[j]) {
          m[i] = Math.max(m[i], m[j] + arr[i]);
        }
      }
      maxSum = Math.max(maxSum, m[i]);
    }

    return maxSum;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n ; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int maxSum = getMaxSum(n, arr);

    bw.write(maxSum + "");

    bw.flush();
    bw.close();
    br.close();
  }
}