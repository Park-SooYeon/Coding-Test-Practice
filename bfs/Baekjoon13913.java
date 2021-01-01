package bfs;

/*
문제 출처 : https://www.acmicpc.net/problem/13913
숨바꼭질 4
푼 날짜 : 2021-01-01
*/
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Baekjoon13913 {
  static int min = 0;
  static int max = 100000;
  static int[] parent = new int[max + 1];

  private static int bfs(int n, int k) {
    if(n == k) return 0;
    if(n > k) return n - k;

    boolean[] visited = new boolean[max + 1];
    Queue<Position13913> q = new LinkedList<>();

    q.add(new Position13913(n, 0));
    visited[n] = true;

    while(!q.isEmpty()) {
      Position13913 p = q.remove();
      int currentPosition = p.position;
      int currentCount = p.count;

      if(currentPosition == k) {
        return currentCount;
      }

      if(currentPosition > min && !visited[currentPosition - 1]) {
        q.add(new Position13913(currentPosition - 1, currentCount + 1));
        visited[currentPosition - 1] = true;
        parent[currentPosition - 1] = currentPosition;
      }

      if(currentPosition < max && !visited[currentPosition + 1]) {
        q.add(new Position13913(currentPosition + 1, currentCount + 1));
        visited[currentPosition + 1] = true;
        parent[currentPosition + 1] = currentPosition;
      }

      if(currentPosition <= (max / 2) && !visited[2 * currentPosition]) {
        q.add(new Position13913(2 * currentPosition, currentCount + 1));
        visited[2 * currentPosition] = true;
        parent[2 * currentPosition] = currentPosition;
      }
    }
    return 0;
  }

  private static String path(int n, int k) {
    StringBuilder sb = new StringBuilder();
    if(n == k) return k + "";
    if(n > k) {
        for(int i = n ; i > k ; i--) sb.append(i + " ");
        sb.append(k);
        return sb.toString();
    }
    
    Stack<Integer> stack = new Stack<>();
    int index = k;
    stack.push(k);

    while(index != n) {
      int next = parent[index];
      stack.push(next);
      index = next;
    }

    while(!stack.isEmpty()) {
      int currentPosition = stack.pop();
      sb.append(currentPosition + " ");
    }
    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n, k, cnt;
    String path;

    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    cnt = bfs(n, k);
    path = path(n, k);

    System.out.println(cnt);
    System.out.println(path);

    br.close();
  }
}

class Position13913 {
  int position;
  int count;

  Position13913(int position, int count) {
    this.position = position;
    this.count = count;
  }
}