package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/14501
  퇴사
  푼 날짜 : 2021-01-09
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon14501 {

  private static int getMaxPrice(int n, int[] t, int[] p) {
    int[] m = new int[n + 2];

    for(int i = n ; i > 0 ; i--) {
      // 일 마치는 날이 n + 1인 일은 하지 않음
      if(i + t[i] > n + 1) m[i] = m[i + 1];
      // i번째날의 일을 시작하면 i + t[i]일 후부터 다시 일할 수 있음
			// i번째날의 일을 안했을 때와 했을 때의 최대 이익 비교
      else m[i] = Math.max(m[i + 1], m[i + t[i]] + p[i]);
    }

    return m[1];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] t = new int[n + 1];
    int[] p = new int[n + 1];
    
    for(int i = 1 ; i <= n ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      t[i] = Integer.parseInt(st.nextToken());
      p[i] = Integer.parseInt(st.nextToken());
    }

    int maxPrice = getMaxPrice(n, t, p);

    bw.write(maxPrice + "");

    bw.flush();
    bw.close();
    br.close();
  }
}