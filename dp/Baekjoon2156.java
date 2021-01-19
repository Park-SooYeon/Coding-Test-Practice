package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/2156
  포도주 시식
  푼 날짜 : 2021-01-19
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2156 {
  private static int getMax(int n, int[] glasses) {
    int[] m = new int[n];

    if(n == 1) return glasses[0];
    if(n == 2) return glasses[0] + glasses[1];
    
    m[0] = glasses[0];
    m[1] = glasses[0] + glasses[1];
    // oox, oxo, xoo 일 경우를 고려해주어야함
    m[2] = Math.max(m[1], Math.max(m[0] + glasses[2], glasses[1] + glasses[2]));
    for(int i = 3; i < n ; i++) {
      m[i] = Math.max(m[i - 1], Math.max(m[i - 2] + glasses[i], m[i - 3] + glasses[i - 1] + glasses[i]));
    }

    return m[n - 1];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] glasses = new int[n];

    for(int i = 0 ; i < n ; i++) {
      glasses[i] = Integer.parseInt(br.readLine());
    }

    int max = getMax(n, glasses);

    bw.write(max + "");

    bw.flush();
    bw.close();
    br.close();
  }
}