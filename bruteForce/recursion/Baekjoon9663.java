package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/9663
  N-Queen
  푼 날짜 : 2021-03-21
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon9663 {
  private static int N, count = 0;
  // index는 열 정보, 배열 내부 값은 행 정보를 저장
  private static int[] map;

  private static boolean isPossible(int col) {
    for(int i = 0 ; i < col ; i++) {
      // 같은 열에 위치할 경우
      if(map[col] == map[i]) {
        return false;
      }

      // 대각선 상에 위치할 경우 (열의 차와 행의 차가 같을 경우에는 대각선에 놓임)
      if(Math.abs(col - i) == Math.abs(map[col] - map[i])) {
        return false;
      }
    }
    
    return true;
  }

  private static void nQueen(int index) {
    if(index == N) {
      count++;
      return;
    }

    for(int i = 0 ; i < N ; i++) {
      // (index, i)에 퀸 배치
      map[index] = i;

      if(isPossible(index)) {
        nQueen(index + 1);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    map = new int[N];

    nQueen(0);

    bw.write(count + "");

    bw.flush();
    bw.close();
    br.close();
  }
}