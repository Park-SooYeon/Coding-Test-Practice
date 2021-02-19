package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/1248
  맞춰봐
  푼 날짜 : 2021-02-19
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1248 {
  private static int n;
  private static int[] a;
  private static char[][] s;

  private static boolean check(int index) {
    for(int i = 0 ; i <= index ; i++) {
      int sum = 0;
      // a[i] ~ a[j] 까지의 합을 계산
      for(int j = i ; j <= index ; j++) {
        sum += a[j];
        // 하나라도 조건이 유효하지 않으면 false를 리턴
        if(sum == 0 && s[i][j] != '0') return false;
        if(sum > 0 && s[i][j] != '+') return false;
        if(sum < 0 && s[i][j] != '-') return false;
      }
    }
    return true;
  }

  private static void print() {
    StringBuilder sb = new StringBuilder();
    for(int i = 0 ; i < a.length ; i++) {
      sb.append(a[i] + " ");
    }
    System.out.println(sb.toString());
    System.exit(0);
  }

  private static void dfs(int cnt) {
    if(cnt == n) { // 모든 수가 정해지면 a 배열의 숫자 출력
      print();
      return;
    }

    // -10 ~ 10 까지의 숫자만 사용할 수 있음
    for(int j = -10 ; j <= 10 ; j++) {
      a[cnt] = j;
      // a에 넣은 값이 유효한 값인지 확인 후, 유효한 값이면 다음 숫자 구함
      if(check(cnt)) dfs(cnt + 1);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Integer.parseInt(br.readLine());
    a = new int[n];
    s = new char[n][n];
  
    char[] inputCharArray = br.readLine().toCharArray();
    int index = 0;
    for(int i = 0 ; i < n ; i++) {
      for(int j = i ; j < n ; j++) {
        s[i][j] = inputCharArray[index++];
      }
    }

    dfs(0);

    bw.flush();
    bw.close();
    br.close();
  }
}