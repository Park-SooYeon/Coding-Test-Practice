package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/16931
  겉넓이 구하기
  푼 날짜 : 2021-02-16
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon16931 {
  private static int[] X = {0, 1, 0, -1};
  private static int[] Y = {-1, 0, 1, 0};
  
  private static int getArea(int[][] map) {
    int n = map.length;
    int m = map[0].length;
    int area = 0;

    // 윗면과 바닥면의 넓이
    area += 2 * n * m;

    // 옆면에서 봤을 때의 넓이
    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < m ; j++) {
        // 현재 블록과 주위 블록 확인
        for(int k = 0 ; k < 4 ; k++) {
          int nextX = i + X[k];
          int nextY = j + Y[k];

          // 현재 블록이 가장자리에 있는 블록이면 현재 블록의 높이만큼 넓이에 추가
          if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
            area += map[i][j];
            continue;
          }

          // 현재 블록과 주위 블록의 높이차 계산 후 넓이에 추가
          if(map[nextX][nextY] < map[i][j]) area += (map[i][j] - map[nextX][nextY]);
        }
      }
    }

    return area;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[][] map = new int[n][m];

    for(int i = 0 ; i < n ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < m ; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int area = getArea(map);

    bw.write(area + "");

    bw.flush();
    bw.close();
    br.close();
  }
}