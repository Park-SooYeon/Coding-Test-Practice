package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/16197
  두 동전
  푼 날짜 : 2021-03-11
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon16197 {
  private static final int[] dx = {0, 0, -1, 1};
  private static final int[] dy = {-1, 1, 0, 0};
  private static int n, m, answer;
  private static char[][] map;

  private static List<int[]> getCoinPosition() {
    List<int[]> coin = new ArrayList<int[]>();
    
    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < m ; j++) {
        if(map[i][j] == 'o') {
          coin.add(new int[]{i, j});
        }
      }
    }

    return coin;
  }

  private static void dfs(int x1, int y1, int x2, int y2, int count) {
    if(count >= 10) return; // 버튼을 10회 이상 누르면 안됨
    
    
    for(int i = 0 ; i < 4 ; i++) {
      boolean drop1 = false; // 첫번째 코인이 떨어졌는지를 나타내는 변수
      boolean drop2 = false;
      int nextX1 = x1 + dx[i];
      int nextY1 = y1 + dy[i];
      int nextX2 = x2 + dx[i];
      int nextY2 = y2 + dy[i];

      if(nextX1 < 0 || nextX1 >= n || nextY1 < 0 || nextY1 >= m) {
        drop1 = true;
      }
      if(nextX2 < 0 || nextX2 >= n || nextY2 < 0 || nextY2 >= m) {
        drop2 = true;
      }
      
      if(drop1 && drop2) continue; // 둘 다 떨어지면 안됨
      if(drop1 || drop2) { // 둘 중 하나가 떨어진 경우
        answer = Math.min(answer, count + 1);
        continue;
      }

      // 벽에 부딪혔을 경우, 움직이지 않음
      if(map[nextX1][nextY1] == '#') {
        nextX1 = x1;
        nextY1 = y1;
      }
      if(map[nextX2][nextY2] == '#') {
        nextX2 = x2;
        nextY2 = y2;
      }

      dfs(nextX1, nextY1, nextX2, nextY2, count + 1);
    }
  }

  private static void solution() {
    answer = Integer.MAX_VALUE;

    List<int[]> coin = getCoinPosition();
    int[] coin1 = coin.get(0);
    int[] coin2 = coin.get(1);

    dfs(coin1[0], coin1[1], coin2[0], coin2[1], 0);

    // 하나만 떨어뜨릴 수 없거나 버튼 클릭이 10회 넘을 경우에는 -1
    if(answer == Integer.MAX_VALUE) answer = -1;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new char[n][m];

    for(int i = 0 ; i < n ; i++) {
      char[] inputCharacter = br.readLine().toCharArray();
      map[i] = inputCharacter;
    }

    solution();

    bw.write(answer + "");

    bw.flush();
    bw.close();
    br.close();
  }
}