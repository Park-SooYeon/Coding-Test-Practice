package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/1463
  1로 만들기
  푼 날짜 : 2020-12-26
*/
import java.util.Scanner;

public class Baekjoon1463 {
  private static int[] dp;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int number;

    number = sc.nextInt();
    dp = new int[number + 1];

    // System.out.println(dpTopDown(number));
    System.out.println(dpBottomUp(number));

    sc.close();
  }

  private static int dpTopDown(int number) {
    if(number == 1) return 0;

    dp[number] = dpTopDown(number - 1) + 1;
    if(number % 2 == 0) {
      int b = dpTopDown(number / 2) + 1;
      dp[number] = Math.min(b, dp[number]);
    }
    if(number % 3 == 0) {
      int c = dpTopDown(number / 3) + 1;
      dp[number] = Math.min(c, dp[number]);
    }
    return dp[number];
  }

  private static int dpBottomUp(int number) {
    int[] m = new int[number + 1];

    for (int i = 2; i <= number; i++){
      m[i] = m[i-1] + 1;
      if (i % 2 == 0) m[i] = Math.min(m[i], m[i/2] + 1);
      if (i % 3 == 0) m[i] = Math.min(m[i], m[i/3] + 1);
    } 

    return m[number];
  }
}