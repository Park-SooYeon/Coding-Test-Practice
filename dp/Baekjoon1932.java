package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/1932
  정수 삼각형
  푼 날짜 : 2021-01-20
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1932 {
  private static int getMaxSum(int n, int[][] tree) {
    int max = 0;
    int[][] m = new int[n][n];

    m[0][0] = tree[0][0];
    for(int i = 1 ; i < n ; i++) {
      for(int j = 0 ; j <= i ; j++) {
        if(j == 0) m[i][j] = m[i - 1][j] + tree[i][j];
        else if(j == i) m[i][j] = m[i - 1][j - 1] + tree[i][j];
        else m[i][j] = Math.max(m[i - 1][j] + tree[i][j], m[i - 1][j - 1] + tree[i][j]);
        max = Math.max(max, m[i][j]);
      }
    }

    return max;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[][] tree = new int[n][n];
    
    for(int i = 0 ; i < n ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0 ; st.hasMoreTokens() ; j++) {
        tree[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int max = getMaxSum(n, tree);

    bw.write(max + "");

    bw.flush();
    bw.close();
    br.close();
  }
}