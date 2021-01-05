package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11053
  가장 긴 증가하는 부분 수열
  푼 날짜 : 2021-01-05
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon11053 {

  private static int getMaxLength(int[] a) {
    int n = a.length;
    int maxLength = 0;
    int[] m = new int[n];

    for(int i = 0 ; i < n ; i++) {
      int max = 0;
      // 이전 값보다 현재 값이 클때, 순열 길이가 가장 긴 값 찾기
      for(int j = 0 ; j < i ; j++) {
        if(a[i] > a[j]) max = Math.max(max, m[j]);
      }
      m[i] = max + 1;
      maxLength = Math.max(maxLength, m[i]);
    }

    return maxLength;
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

    int maxLength = getMaxLength(a);

    bw.write(maxLength + "");

    bw.flush();
    bw.close();
    br.close();
  }
}