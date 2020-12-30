package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/9095
  1, 2, 3 더하기
  푼 날짜 : 2020-12-30
*/
import java.util.Scanner;

public class Baekjoon9095 {
  static int cnt;

  private static void sum(int n, int sum) {
    if(sum > n) return;
    if(n == sum) {
      cnt++;
      return;
    }

    sum(n, sum + 1);
    sum(n, sum + 2);
    sum(n, sum + 3);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();

    int t = sc.nextInt();

    for(int i = 0 ; i < t ; i++) {
      int n = sc.nextInt();
      cnt = 0;
  
      sum(n, 0);

      sb.append(cnt + "\n");
    }

    System.out.println(sb);

    sc.close();
  }
}