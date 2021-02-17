package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/2133
  타일 채우기
  푼 날짜 : 2021-02-17
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 풀이 참조 : https://mizzo-dev.tistory.com/entry/baekjoon2133
public class Baekjoon2133 {
  private static int getNumberOfCases(int n) {
    int[] m = new int[n + 1];

    if(n % 2 != 0) return 0; // n이 홀수일 경우, 모든 경우의 수가 0

    m[0] = 1; // 특수 케이스 계산을 위한 더미데이터
    m[2] = 3;

    for(int i = 4 ; i <= n ; i += 2) { // n이 짝수일 경우, n까지 경우의 수 구하기
      m[i] = 3 * m[i - 2]; // 이전케이스에 n=2인 케이스를 추가했을 때의 경우의 수 계산
      for(int j = 0 ; j < i - 2 ; j += 2) {
        m[i] += (2 * m[j]); // 중복 케이스를 제외하고 순서 변경한 특수케이스에 대한 경우의 수 계산
      }
    }

    return m[n];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    int cases = getNumberOfCases(n);
    
    bw.write(cases + "");

    bw.flush();
    bw.close();
    br.close();
  }
}