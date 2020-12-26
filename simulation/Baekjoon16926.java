package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/16926
  배열 돌리기1
  푼 날짜 : 2020-12-26
*/
import java.util.Scanner;

public class Baekjoon16926 {
  static int n, m, r, square;
  static int[][] arr;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    r = sc.nextInt();

    square = Math.min(m, n) / 2;
    arr = new int[n][m];
 
    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < m ; j++) {
        arr[i][j] = Integer.parseInt(sc.next());
      }
    }

    for(int k = 0 ; k < r ; k++) {
      rotationArray();
    }

    print();
    
    sc.close();
  }

  private static void rotationArray() {
    for(int s = 0 ; s < square ; s++) {
      int left = s;
      int top = s;
      int right = m - 1 - s;
      int bottom = n - 1 - s;

      int tmp = arr[s][s];
      for (int i = left; i < right; i++)  arr[top][i] = arr[top][i + 1];
      for (int i = top; i < bottom; i++)  arr[i][right] = arr[i + 1][right];
      for (int i = right; i > left; i--)  arr[bottom][i] = arr[bottom][i - 1];
      for (int i = bottom; i > top; i--)  arr[i][left] = arr[i - 1][left];
      arr[top + 1][left] = tmp;
    }
    
  }

  private static void print() {
    for (int[] n : arr) {
      for (int m : n) {
          System.out.print(m + " ");
      }
      System.out.println();
    }
  }
}