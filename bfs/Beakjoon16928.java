package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/16928
  뱀과 사다리 게임
  푼 날짜 : 2021-03-02
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beakjoon16928 {
  private static final int MAP_SIZE = 101;
  private static final int[] dice = {1, 2, 3, 4, 5, 6};

  private static int solution(int n, int m, int[] map) {
    int answer = 0;
    boolean[] visited = new boolean[MAP_SIZE];
    int[] count = new int[MAP_SIZE]; // 각 위치에서 주사위 굴린 횟수를 저장할 변수
    Queue<Integer> q = new LinkedList<>();

    visited[1] = true;
    q.add(1); // 1번 칸에서 시작

    while(!q.isEmpty()) {
      int pos = q.remove();
      int currentCount = count[pos];

      if(pos == 100) {
        answer = currentCount;
        return answer;
      }
      
      // 주사위 굴리기
      for(int j = 0 ; j < 6 ; j++) {
        int nextPos = pos + dice[j];

        if(nextPos > 100) continue;
        if(visited[nextPos]) continue;

        visited[nextPos] = true;

        // 뱀이나 사다리를 탈 경우, 올라가거나 내려간 위치로 이동
        if(map[nextPos] != 0) { 
          nextPos = map[nextPos];
          if(visited[nextPos]) continue;
        }

        count[nextPos] = currentCount + 1;
        visited[nextPos] = true;
        q.add(nextPos);
      }
    }

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int[] map = new int[MAP_SIZE];

    for(int i = 0 ; i < n ; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      map[x] = y;
    }

    for(int i = 0 ; i < m ; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      map[u] = v;
    }

    int answer = solution(n, m, map);

    bw.write(answer + "");

    bw.flush();
    bw.close();
    br.close();
  }
}