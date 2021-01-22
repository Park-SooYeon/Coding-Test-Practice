package bruteForce.permutation;

/*
  문제 출처 : https://www.acmicpc.net/problem/10819
  차이를 최대로
  푼 날짜 : 2021-01-22
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon10819 {
  static int max = 0;

  private static void permutation(int n, int[] arr, int[] temp, boolean[] visited, int count) {
    if(count == n) {
      int sum = 0;
      for(int i = 0 ; i < n - 1 ; i++) sum += Math.abs(temp[i] - temp[i + 1]);
      max = Math.max(max, sum);
      return;
    }

    for(int i = 0 ; i < n ; i++) {
      if(visited[i]) continue;
      visited[i] = true;
      temp[count] = arr[i];
      permutation(n, arr, temp, visited, count + 1);
      visited[i] = false;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    int[] temp = new int[n];
    boolean[] visited = new boolean[n];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n ; i++) arr[i] = Integer.parseInt(st.nextToken());

    permutation(n, arr, temp, visited, 0);

    bw.write(max + "");

    bw.flush();
    bw.close();
    br.close();
  }
}