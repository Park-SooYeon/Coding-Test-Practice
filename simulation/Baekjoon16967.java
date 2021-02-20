package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/16967
  배열 복원하기
  푼 날짜 : 2021-02-20
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon16967 {
  private static int h, w, x, y;
  
  private static int[][] getArray(int[][] b) {
    int[][] a = new int[h][w];

    for(int i = 0 ; i < h + x ; i++) {
      for(int j = 0 ; j < w + y ; j++) {
        if(i < h && j < y || i < x && j < w) { // 배열 a에만 포함되는 경우
          a[i][j] = b[i][j];
        } else if(i >= x && i < h && j >= y && j < w) { // 배열 a, b 모두에 포함되는 경우
          a[i][j] = b[i][j] - a[i - x][j - y];
        } else if(i >= x && j >= y) { // 배열 b에만 포함되는 경우
          a[i - x][j - y] = b[i][j];
        }
      }
    }

    return a;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());
    h = Integer.parseInt(st.nextToken());
    w = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());
    int[][] b = new int[h + x][w + y];

    for(int i = 0 ; i < h + x ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < w + y ; j++) {
        b[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] a = getArray(b);

    for(int i = 0 ; i < h ; i++) {
      for(int j = 0 ; j < w ; j++) {
        sb.append(a[i][j] + " ");
      }
      sb.append("\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}