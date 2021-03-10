package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/16948
  데스 나이트
  푼 날짜 : 2021-03-10
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon16948 {
  private static final int[] dx = {-2, -2, 0, 0, 2, 2};
  private static final int[] dy = {-1, 1, -2, 2, -1, 1};

  private static class Position {
    int x = 0;
    int y = 0;
    int cnt = 0;

    Position(int x, int y, int cnt) {
      this.x = x;
      this.y = y;
      this.cnt = cnt;
    }
  }

  private static int solution(int n, int r1, int c1, int r2, int c2) {
    int answer = 0;
    Queue<Position> q = new LinkedList<Position>();
    boolean[][] visited = new boolean[n][n];
    
    visited[r1][c1] = true;
    q.offer(new Position(r1, c1, 0));

    while(!q.isEmpty()) {
      Position current = q.poll();

      if(current.x == r2 && current.y == c2) {
        answer = current.cnt;
        return answer;
      }
      
      for(int i = 0 ; i < 6 ; i++) {
        int nextX = current.x + dx[i];
        int nextY = current.y + dy[i];
        
        if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;
        if(visited[nextX][nextY]) continue;

        q.add(new Position(nextX, nextY, current.cnt + 1));
        visited[nextX][nextY] = true;
      }
    }

    return -1;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int r1 = Integer.parseInt(st.nextToken());
    int c1 = Integer.parseInt(st.nextToken());
    int r2 = Integer.parseInt(st.nextToken());
    int c2 = Integer.parseInt(st.nextToken());
    
    sb.append(solution(n, r1, c1, r2, c2));

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}