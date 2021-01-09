package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/14890
  경사로
  푼 날짜 : 2021-01-09
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon14890 {

  private static boolean isPath(int n, int l, int[] height) {
    boolean[] hasRamp = new boolean[n];
    boolean isPath = true;

    for(int i = 0 ; i < n - 1 ; i++) {
      int diff = height[i] - height[i + 1];
      // 같은 높이
      if(diff == 0) continue;

      // 다른 높이
      if(diff != 1 && diff != -1) return false; // 높이 차이가 1이 아니면 지나가지 못함

      // 오르막길
      if(diff == -1) {
        for(int j = i ; j > i - l ; j--) {
          // 경사로를 만들 수 없으면 지나가지 못함
          if(j < 0 || height[i] != height[j] || hasRamp[j]) return false;
          hasRamp[j] = true;
        }
      }

      // 내리막길
      if(diff == 1) {
        for(int j = i + 1 ; j <= i + l ; j++) {
          // 경사로를 만들 수 없으면 지나가지 못함
          if(j >= n || height[i + 1] != height[j] || hasRamp[j]) return false;
          hasRamp[j] = true;
        }
      }
    }

    return isPath;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());
    int[][] map = new int[n][n];
    int[][] reverseMap = new int[n][n];

    for(int i = 0 ; i < n ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < n ; j++) {
        map[i][j] = reverseMap[j][i] = Integer.parseInt(st.nextToken());
      }
    }

    int pathCount = 0;
    for(int i = 0 ; i < n ; i++) {
      // 가로길 검사
      int[] height1 = map[i];
      boolean isPath1 = isPath(n, l, height1);
      if(isPath1) pathCount++;

      // 세로길 검사
      int[] height2 = reverseMap[i];
      boolean isPath2 = isPath(n, l, height2);
      if(isPath2) pathCount++;
    }

    bw.write(pathCount + "");

    bw.flush();
    bw.close();
    br.close();
  }
}