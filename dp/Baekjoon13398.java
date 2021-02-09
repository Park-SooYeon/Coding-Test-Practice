package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/13398
  연속합 2
  푼 날짜 : 2021-02-09
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 풀이 참조 : https://steady-coding.tistory.com/181
public class Baekjoon13398 {
  private static int getMaxSum(int[] arr) {
    int n = arr.length;
    int max = arr[0];
    // [n][0] : n번째 수를 제거하지 않았을 경우, 연속되는 수의 합 최대 값
    // [n][1] : n번째 수를 제거했을 경우, 연속되는 수의 합 최대 값
    int[][] m = new int[n][2];

    m[0][0] = arr[0];
    m[0][1] = arr[0];

    for(int i = 1 ; i < n ; i++) {
      m[i][0] = Math.max(m[i - 1][0] + arr[i], arr[i]);
      m[i][1] = Math.max(m[i - 1][0], m[i - 1][1] + arr[i]);
      max = Math.max(max, Math.max(m[i][0], m[i][1]));
    }

    return max;
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

    int max = getMaxSum(arr);

    bw.write(max + "");

    bw.flush();
    bw.close();
    br.close();
  }
}