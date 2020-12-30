package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11727
  2xn 타일링 2
  푼 날짜 : 2020-12-30
*/
import java.util.Scanner;

public class Baekjoon11727 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int[] m = new int[n + 1];

    m[0] = 1;
    m[1] = 1;
    for(int i = 2 ; i <= n ; i++) {
      m[i] = (m[i - 1] + 2 * m[i - 2]) % 10007;
    }

    System.out.println(m[n]);

    sc.close();
  }
}