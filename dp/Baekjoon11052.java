package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/11052
  카드 구매하기
  푼 날짜 : 2020-12-31
*/
import java.util.Scanner;

public class Baekjoon11052 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    int[] p = new int[n + 1];
    int[] m = new int[n + 1];

    for(int i = 1 ; i <= n ; i++) {
      p[i] = sc.nextInt();
    }

    for(int i = 1 ; i <= n ; i++) {
      for(int j = 1 ; j <= i ; j++) {
        m[i] = Math.max(m[i], p[j] + m[i - j]);
      }
    }

    System.out.println(m[n]);

    sc.close();
  }
}