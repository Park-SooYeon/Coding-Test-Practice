package bruteForce.etc;

/*
  문제 출처 : https://www.acmicpc.net/problem/6064
  카잉 달력
  푼 날짜 : 2021-02-01
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


// 풀이 참조 : https://mygumi.tistory.com/325
public class Baekjoon6064 {

  // 최대공약수
  private static int gcd(int x, int y) {
    while(y != 0) {
      int r = x % y;
      x = y;
      y = r;
    }
    return x;
  }

  // 최소공배수
  private static int lcm(int x, int y) {
    return x * y / gcd(x, y);
  }

  private static int getYear(int m, int n, int x, int y) {
    int k = x % (m + 1); // x의 값은 고정
    int tempY = x;

    for(int j = 0 ; j < n ; j++) {
      int ty = tempY % n == 0 ? n : tempY % n;
      if(ty == y) break;
      tempY = ty + m;
      k += m;
    }

    // 구한 년도 값이 최소공배수를 넘으면 표현할 수 없음
    if(k > lcm(m, n)) return -1;
    return k;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int t = Integer.parseInt(br.readLine());
    for(int i = 0 ; i < t ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      int year = getYear(m, n, x, y);
      sb.append(year + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}