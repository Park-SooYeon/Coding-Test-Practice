package bruteForce.etc;

/*
  문제 출처 : https://www.acmicpc.net/problem/1476
  날짜 계산
  푼 날짜 : 2021-01-05
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1476 {

  private static int getNowYear(int e, int s, int m) {
    int year = 1;
    int earthYear = 1;
    int sunYear = 1;
    int moonYear = 1;

    while(true) {
      if(earthYear == e && sunYear == s && moonYear == m) break;

      year++;
      earthYear = earthYear == 15 ? 1 : earthYear + 1;
      sunYear = sunYear == 28 ? 1 : sunYear + 1;
      moonYear = moonYear == 19 ? 1 : moonYear + 1;
    }

    return year;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int e = Integer.parseInt(st.nextToken());
    int s = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int year = getNowYear(e, s, m);

    bw.write(year + "");

    bw.flush();
    bw.close();
    br.close();
  }
}