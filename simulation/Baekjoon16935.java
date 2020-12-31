package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/16935
  배열 돌리기3
  푼 날짜 : 2020-12-31
*/
import java.util.Scanner;

public class Baekjoon16935 {
  static int n, m, r, square;
  static int[][] arr;

  private static void rotationArray(String command) {
    switch(command) {
      case "1": // 상하 반전
        for(int i = 0 ; i < (n / 2) ; i++) {
          for(int j = 0 ; j < m ; j++) {
            int temp = arr[i][j];
            arr[i][j] = arr[n - i - 1][j];
            arr[n - i - 1][j] = temp;
          }
        }
        break;
      case "2": // 좌우 반전
        for(int i = 0 ; i < n ; i++) {
          for(int j = 0 ; j < (m / 2) ; j++) {
            int temp = arr[i][j];
            arr[i][j] = arr[i][m - j - 1];
            arr[i][m - j - 1] = temp;
          }
        }
        break;
      case "3": // 오른쪽으로 90도 회전
        int[][] rightRotatedArr = new int[m][n];
        for(int i = 0 ; i < n ; i++) {
          for(int j = 0 ; j < m ; j++) {
            rightRotatedArr[j][n - i - 1] = arr[i][j];
          }
        }
        arr = rightRotatedArr;
        int temp3 = n;
        n = m;
        m = temp3;
        break;
      case "4": // 왼쪽으로 90도 회전
        int[][] leftRotatedArr = new int[m][n];
        for(int i = 0 ; i < n ; i++) {
          for(int j = 0 ; j < m ; j++) {
            leftRotatedArr[m - j - 1][i] = arr[i][j];
          }
        }
        arr = leftRotatedArr;
        int temp4 = n;
        n = m;
        m = temp4;
        break;
      case "5": // 4등분 후, 시계방향으로 회전
        int[][] rotatedArr5 = new int[n][m];
        for(int i = 0 ; i < (n / 2) ; i++) {
          for(int j = 0 ; j < (m / 2) ; j++) {
            rotatedArr5[i][(m / 2) + j] = arr[i][j];
          }
        }
        for(int i = 0 ; i < (n / 2) ; i++) {
          for(int j = (m / 2) ; j < m ; j++) {
            rotatedArr5[(n / 2) + i][j] = arr[i][j];
          }
        }
        for(int i = (n / 2) ; i < n ; i++) {
          for(int j = 0 ; j < (m / 2) ; j++) {
            rotatedArr5[i - (n / 2)][j] = arr[i][j];
          }
        }
        for(int i = (n / 2) ; i < n ; i++) {
          for(int j = (m / 2) ; j < m ; j++) {
            rotatedArr5[i][j - (m / 2)] = arr[i][j];
          }
        }
        arr = rotatedArr5;
        break;
      case "6": // 4등분 후, 반시계방향으로 회전
        int[][] rotatedArr6 = new int[n][m];
        for(int i = 0 ; i < (n / 2) ; i++) {
          for(int j = 0 ; j < (m / 2) ; j++) {
            rotatedArr6[(n / 2) + i][j] = arr[i][j];
          }
        }
        for(int i = 0 ; i < (n / 2) ; i++) {
          for(int j = (m / 2) ; j < m ; j++) {
            rotatedArr6[i][j - (m / 2)] = arr[i][j];
          }
        }
        for(int i = (n / 2) ; i < n ; i++) {
          for(int j = 0 ; j < (m / 2) ; j++) {
            rotatedArr6[i][(m / 2) + j] = arr[i][j];
          }
        }
        for(int i = (n / 2) ; i < n ; i++) {
          for(int j = (m / 2) ; j < m ; j++) {
            rotatedArr6[i - (n / 2)][j] = arr[i][j];
          }
        }
        arr = rotatedArr6;
        break;
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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    r = sc.nextInt();

    square = Math.min(m, n) / 2;
    arr = new int[n][m];
 
    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < m ; j++) {
        arr[i][j] = sc.nextInt();
      }
    }

    for(int k = 0 ; k < r ; k++) {
      String command = sc.next();
      rotationArray(command);
    }

    print();
    
    sc.close();
  }
}