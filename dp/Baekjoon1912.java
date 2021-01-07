package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/1912
  연속합
  푼 날짜 : 2021-01-07
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1912 {

  private static int getMaxSum(int[] arr) {
    int max = 0;
    int size = arr.length;
    int[] m = new int[size]; // index 이전 숫자 범위에서 연속되는 수의 합 중, 최대값을 담을 배열

    max = arr[0];
    m[0] = arr[0];

    for(int i = 1 ; i < size ; i++) {
      m[i] = Math.max(m[i - 1] + arr[i], arr[i]);
      max = Math.max(m[i], max);
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