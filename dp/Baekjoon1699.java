package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/1699
  제곱수의 합
  푼 날짜 : 2021-01-08
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1699 {

  private static int getMinNumber(int n) {
    int[] m = new int[n + 1]; // 제곱항 최소 개수를 저장할 변수

    for(int i = 1 ; i <= n ; i++) {
      m[i] = i;
      // 점화식 : m[n] = min(m[n - i^2] + 1)
      for (int j = 1; j * j <= i; j++){
        if (m[i] > m[i - j * j] + 1){
          m[i] = m[i - j * j] + 1;
        }
      }
    }

    return m[n];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    int min = getMinNumber(n);

    bw.write(min + "");

    bw.flush();
    bw.close();
    br.close();
  }
}