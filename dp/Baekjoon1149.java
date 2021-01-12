package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/1149
  RGB거리
  푼 날짜 : 2021-01-12
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1149 {
  private static final int RED = 0;
  private static final int GREEN = 1;
  private static final int BLUE = 2;

  private static int getMinCost(int n, int[][] cost) {
    int minCost = Integer.MAX_VALUE;
    int[][] m = new int[n + 1][3];

    m[1][RED] = cost[1][RED]; m[1][GREEN] = cost[1][GREEN]; m[1][BLUE] = cost[1][BLUE];
    for(int i = 2 ; i <= n ; i++) {
      m[i][RED] = Math.min(m[i - 1][GREEN], m[i - 1][BLUE]) + cost[i][RED];
      m[i][GREEN] = Math.min(m[i - 1][RED], m[i - 1][BLUE]) + cost[i][GREEN];
      m[i][BLUE] = Math.min(m[i - 1][RED], m[i - 1][GREEN]) + cost[i][BLUE];
    }

    minCost = Math.min(m[n][RED], m[n][GREEN]);
    minCost = Math.min(m[n][BLUE], minCost);

    return minCost;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[][] cost = new int[n + 1][3];
    
    for(int i = 1 ; i <= n ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < 3 ; j++) {
        cost[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int minCost = getMinCost(n, cost);

    bw.write(minCost + "");

    bw.flush();
    bw.close();
    br.close();
  }
}