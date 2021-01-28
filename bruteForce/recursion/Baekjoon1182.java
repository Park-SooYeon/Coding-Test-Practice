package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/1182
  부분수열의 합
  푼 날짜 : 2021-01-28
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1182 {
  static int cnt = 0;

  private static void backtrack(int s, int[] arr, int sum, int index) {
    if(index >= arr.length) {
      if(sum == s) cnt++;
      return;
    }

    backtrack(s, arr, sum + arr[index], index + 1); // arr[index]의 값을 더했을 경우
    backtrack(s, arr, sum, index + 1); // arr[index]의 값을 안 더했을 경우
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());
    int[] arr = new int[n];
    
    st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n ; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    backtrack(s, arr, 0, 0);
    if(s == 0) cnt--; // s가 0일 경우, 공집합도 개수로 포함하기 때문에 공집합 수 제거

    bw.write(cnt + "");

    bw.flush();
    bw.close();
    br.close();
  }
}