package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/15990
  1, 2, 3 더하기 5
  푼 날짜 : 2021-01-02
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon15990 {
  static int divNumber = 1000000009;

  private static int[][] init() {
    int max = 100000;
    int[][] m = new int[max + 1][3];

    m[1][0] = 1;
    m[2][1] = 1;
    m[3][0] = 1; m[3][1] = 1; m[3][2] = 1;
    for(int i = 4; i <= max ; i++) {
      m[i][0] = (m[i-1][1] + m[i-1][2]) % divNumber;
      m[i][1] = (m[i-2][0] + m[i-2][2]) % divNumber;
      m[i][2] = (m[i-3][0] + m[i-3][1]) % divNumber;
    }

    return m;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int t = Integer.parseInt(br.readLine());
    int[][] m = init();
    
    for(int i = 0 ; i < t ; i++) {
      int n = Integer.parseInt(br.readLine());
      
      int result = 0;
      for(int j = 0 ; j < 3 ; j++) {
        result += m[n][j];
        result %= divNumber;
      }
      sb.append(result + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}