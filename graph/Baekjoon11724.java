package graph;

/*
  문제 출처 : https://www.acmicpc.net/problem/11724
  연결 요소의 개수
  푼 날짜 : 2021-01-01
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon11724 {
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] visited;

  private static void dfs(int v) {
    if(visited[v]) return;

    ArrayList<Integer> nowList = graph.get(v);
    visited[v] = true;

    for(int next: nowList) {
      if(!visited[next]) {
        dfs(next);
        visited[next] = true;
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int cnt = 0;

    graph = new ArrayList<>();
    visited = new boolean[n + 1];
    for(int i = 0 ; i <= n ; i++) {
      graph.add(new ArrayList<Integer>());
    }

    for(int i = 0 ; i < m ; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    for(int i = 1 ; i <= n ; i++) {
      if(visited[i]) continue;
      cnt++;
      dfs(i);
    }

    System.out.println(cnt);

    br.close();
  }
}