package bruteForce.etc;

/*
  문제 출처 : https://www.acmicpc.net/problem/2309
  일곱 난장이
  푼 날짜 : 2020-12-25
*/
import java.util.Scanner;
import java.util.Arrays;

public class Baekjoon2309 {
  static int[] result, lengths;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    lengths = new int[9];
    result = new int[7];

    for(int i = 0 ; i < lengths.length ; i++) {
      lengths[i] = sc.nextInt();
    }

    Arrays.sort(lengths);

    comb(0, 0, 0);

    sc.close();
  }

  private static void comb(int index, int length, int total) {
    if(length == 7) {
      if(total == 100) {
        for(int item : result) {
          System.out.println(item);
        }
        System.exit(0);
      }
      return;
    }

    for(int i = index ; i < 9; i++) {
      result[length] = lengths[i];
      comb(i + 1, length + 1, total + lengths[i]);
    }
  }
}
