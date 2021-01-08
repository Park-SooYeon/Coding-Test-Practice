package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/14499
  주사위 굴리기
  푼 날짜 : 2021-01-08
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon14499 {
  static int[] X = {0, 0, -1, 1};
	static int[] Y = {1, -1, 0, 0};
  static int n, m, x, y, k;
  static int[][] map;
  static int[] dice = new int[6]; // 0: 상단, 1: 위, 2: 왼쪽, 3:오른쪽, 4: 아래쪽, 5: 하단
  static int diceTop = 0, diceBottom = 5;
  static StringBuilder sb = new StringBuilder();

  private static void goDice(int command, int nextX, int nextY) {
    int temp = dice[diceBottom];
    x = nextX; y = nextY;

    switch(command) {
      case 1: // 동
      dice[diceBottom] = dice[3];
      dice[3] = dice[diceTop];
      dice[diceTop] = dice[2];
      dice[2] = temp;
      break;
      case 2: // 서
      dice[diceBottom] = dice[2];
      dice[2] = dice[diceTop];
      dice[diceTop] = dice[3];
      dice[3] = temp;
      break;
      case 3: // 북
      dice[diceBottom] = dice[1];
      dice[1] = dice[diceTop];
      dice[diceTop] = dice[4];
      dice[4] = temp;
      break;
      case 4: // 남
      dice[diceBottom] = dice[4];
      dice[4] = dice[diceTop];
      dice[diceTop] = dice[1];
      dice[1] = temp;
      break;
    }

    int mapNumber = map[x][y];
    if(mapNumber == 0) {
      map[x][y] = dice[diceBottom];
    } else {
      dice[diceBottom] = map[x][y];
      map[x][y] = 0;
    }

    sb.append(dice[diceTop] + "\n");
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    map = new int[n][m];

    for(int i = 0 ; i < n ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < m ; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < k ; i++) {
      int command = Integer.parseInt(st.nextToken());
      int nextX = x + X[command - 1];
			int nextY = y + Y[command - 1];
			// 범위 벗어나면 명령 무시
			if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;
      goDice(command, nextX, nextY);
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}