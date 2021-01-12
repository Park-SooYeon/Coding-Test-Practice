package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/13549
  숨바꼭질 3
  푼 날짜 : 2021-01-12
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon13549 {
  private static final int MAX = 100000;

  private static class Data {
    int pos;
    int time;

    Data(int pos, int time) {
      this.pos = pos;
      this.time = time;
    }
  }
  
  private static int getMinTime(int n, int k) {
    Deque<Data> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[MAX + 1];
    
    queue.add(new Data(n, 0));
    visited[n] = true;

    // 가중치에 따른 BFS 큐에 넣는 순서 : https://www.acmicpc.net/board/view/38887#comment-69010
    while(!queue.isEmpty()) {
      Data current = queue.poll();
      int currentPos = current.pos;
      int currentTime = current.time;

      visited[currentPos] = true;

      if(currentPos == k) return currentTime;

      if(currentPos * 2 <= MAX && !visited[currentPos * 2]) {
        queue.addFirst(new Data(currentPos * 2, currentTime));
      }

      if(currentPos + 1 <= MAX && !visited[currentPos + 1]) {
        queue.add(new Data(currentPos + 1, currentTime + 1));
      }
      if(currentPos - 1 >= 0 && !visited[currentPos - 1]) {
        queue.add(new Data(currentPos - 1, currentTime + 1));
      }

    }
     
    return 0;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int minTime = Integer.MAX_VALUE;

    if(n >= k){
      minTime = n - k;
    } else {
      minTime = getMinTime(n, k);
    }

    bw.write(minTime + "");

    bw.flush();
    bw.close();
    br.close();
  }
}