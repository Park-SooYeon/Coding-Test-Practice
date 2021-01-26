package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11722
  가장 긴 감소하는 부분 수열
  푼 날짜 : 2021-01-26
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon11722 {

  private static int getMaxLength(int n, int[] arr) {
    int maxLength = 0;
    int[] m = new int[n]; // 각 위치에서 감소하는 수열의 최대 길이를 저장할 변수
    
    for(int i = 0 ; i < n ; i++) {
      int max = 0;
      for(int j = 0 ; j < i ; j++) {
        if(arr[i] < arr[j]) max = Math.max(max, m[j]);
      }
      m[i] = max + 1;
      maxLength = Math.max(maxLength, m[i]);
    }

    return maxLength;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n ; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int maxLength = getMaxLength(n, arr);

    bw.write(maxLength + "");

    bw.flush();
    bw.close();
    br.close();
  }
}