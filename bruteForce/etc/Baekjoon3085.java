package bruteForce.etc;

/*
  문제 출처 : https://www.acmicpc.net/problem/3085
  사탕 게임
  푼 날짜 : 2021-01-04
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon3085 {

  private static int findMaxCandy(char[][] arr) {
    int n = arr.length;
    int ans = 0;

    // 우측으로 연속된 개수 구하기
    for(int i = 0 ; i < n ; i++) {
      int cnt = 1;
      for(int j = 1 ; j < n ; j++) {
        if (arr[i][j] == arr[i][j - 1]) {
          cnt++;
          continue;
        }
        ans = Math.max(ans, cnt);
        cnt = 1;
      }
      ans = Math.max(ans, cnt);
    }

    // 아래쪽으로 연속된 개수 구하기
    for (int i = 0; i < n; i++) {
      int cnt = 1;
      for (int j = 1; j < n; j++) {
        if (arr[j][i] == arr[j - 1][i]) {
          cnt++;
          continue;
        }
        ans = Math.max(ans, cnt);
        cnt = 1;
      }
      ans = Math.max(ans, cnt);
    }

    return ans;
  }

  private static int getMaxCandy(char[][] arr) {
    int n = arr.length;
    int maxCandy = findMaxCandy(arr);

    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < n ; j++) {
        char currentCandy = arr[i][j];
        
        // 위, 아래 사탕 변경
        if(i + 1 < n) {
          char bottomCandy = arr[i + 1][j];
          if(currentCandy != bottomCandy) {
            // 순서 변경
            arr[i][j] = bottomCandy;
            arr[i + 1][j] = currentCandy;
            // 체크
            maxCandy = Math.max(maxCandy, findMaxCandy(arr));
            // 원래대로 복구
            arr[i][j] = currentCandy;
            arr[i + 1][j] = bottomCandy;
          }
        }

        // 좌, 우 사탕 변경
        if(j + 1 < n) {
          char rightCandy = arr[i][j + 1];
          if(currentCandy != rightCandy) {
            // 순서 변경
            arr[i][j] = rightCandy;
            arr[i][j + 1] = currentCandy;
            // 체크
            maxCandy = Math.max(maxCandy, findMaxCandy(arr));
            // 원래대로 복구
            arr[i][j] = currentCandy;
            arr[i][j + 1] = rightCandy;
          }
        }
      }
    }
    
    return maxCandy;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    char[][] arr = new char[n][n];

    for(int i = 0 ; i < n ; i++) {
      String input = br.readLine();
      arr[i] = input.toCharArray();
    }

    int cnt = getMaxCandy(arr);

    bw.write(cnt + "");

    bw.flush();
    bw.close();
    br.close();
  }
}