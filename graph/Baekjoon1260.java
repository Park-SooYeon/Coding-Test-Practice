package graph;

/*
문제 출처 : https://www.acmicpc.net/problem/1260
DFS와 BFS
푼 날짜 : 2020-12-31
*/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon1260 {
  static StringBuilder sb = new StringBuilder();
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] visited;

  private static void dfs(int v) {
    if (visited[v]) return;
    ArrayList<Integer> nowList = graph.get(v);

    visited[v] = true;
    sb.append(v + " ");
    for (int i = 0; i < nowList.size(); i++) {
      int next = nowList.get(i);
      dfs(next);
    }
  }

  private static void bfs(int v) {
    Queue<Integer> queue = new LinkedList<>();

    queue.add(v);
    visited[v] = true;
    sb.append(v + " ");
    while(!queue.isEmpty()) {
      int number = queue.remove(); // queue.poll() 도 가능
      ArrayList<Integer> nowList = graph.get(number);

      for(int i = 0 ; i < nowList.size() ; i++) {
        int next = nowList.get(i);
        if(!visited[next]) {
          queue.add(next);
          visited[next] = true;
          sb.append(next + " ");
        }
      }
    }
    visited[v] = false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n, m, v;

    n = sc.nextInt();
    m = sc.nextInt();
    v = sc.nextInt();

    graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < m; i++) {
      int a = sc.nextInt();
      int b = sc.nextInt();

      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    for (int i = 0; i <= n; i++) {
      Collections.sort(graph.get(i));
    }

    visited = new boolean[n + 1];
    dfs(v);
    sb.append("\n");
    visited = new boolean[n + 1];
    bfs(v);

    System.out.println(sb);

    sc.close();
  }
}