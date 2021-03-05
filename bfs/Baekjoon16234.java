package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/16234
  인구 이동
  푼 날짜 : 2021-03-05
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16234 {
  private static final int[] dx = {-1, 0, 1, 0};
  private static final int[] dy = {0, -1, 0, 1};
  private static int n, l, r;
  private static int[][] a;
  private static List<Position> union;
  private static boolean[][] visited;
  private static int unionPerson = 0;

  private static class Position {
    int x;
    int y;
    Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static void bfs(int x, int y) {
    Queue<Position> q = new LinkedList<>();
    unionPerson = a[x][y];
    
    visited[x][y] = true;
    q.add(new Position(x, y));
    union.add(new Position(x, y));

    while(!q.isEmpty()) {
      Position currentPosition = q.remove();
      int currentX = currentPosition.x;
      int currentY = currentPosition.y;
      
      for(int i = 0 ; i < 4 ; i++) {
        int nextX = currentX + dx[i];
        int nextY = currentY + dy[i];

        if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;
        if(visited[nextX][nextY]) continue;

        int diff = Math.abs(a[currentX][currentY] - a[nextX][nextY]);
        if(diff >= l && diff <= r) {
          visited[nextX][nextY] = true;
          q.add(new Position(nextX, nextY));
          union.add(new Position(nextX, nextY));
          unionPerson += a[nextX][nextY];
        }
      }
    }
  }

  private static int solution() {
    int answer = 0;
    boolean flag = true;

    while(flag) {
      visited = new boolean[n][n];
      flag = false;
      answer++;
      
      // 인구 이동 수행
      for(int i = 0 ; i < n ; i++) {
        for(int j = 0 ; j < n ; j++) {
          if(visited[i][j]) continue;
          // 인접한 두 나라의 인구 차이 계산후 나라 연결
          union = new ArrayList<>();
          bfs(i, j);

          // 연결된 나라가 없으면 탐색하지 않은 다른 나라 탐색
          if(union.size() <= 1) continue;
          // 연결된 나라가 있으면 인구 이동을 수행하므로 다음 인구 이동도 탐색해야함
          flag = true;
          
          // 연결된 나라에 인구 이동
          int avgPerson = unionPerson / union.size();
          for(Position pos : union) {
            int x = pos.x;
            int y = pos.y;
            a[x][y] = avgPerson;
          }
        }
      }
    }

    return answer - 1;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    l = Integer.parseInt(st.nextToken());
    r = Integer.parseInt(st.nextToken());
    a = new int[n][n];

    for(int i = 0 ; i < n ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < n ; j++) {
        a[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int result = solution();

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}