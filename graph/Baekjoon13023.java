package graph;

/*
  문제 출처 : https://www.acmicpc.net/problem/13023
  ABCDE
  푼 날짜 : 2020-12-30
*/
import java.util.Scanner;
import java.util.ArrayList;

public class Baekjoon13023 {
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] visited;

  private static void dfs(int now, int relations) {
    if(relations == 4) {
      System.out.println(1);
      System.exit(0);
    }

    ArrayList<Integer> nowList = graph.get(now);

    visited[now] = true;
    for(int i = 0 ; i < nowList.size() ; i++) {
      int next = nowList.get(i);
      if(!visited[next]) dfs(next, relations + 1);
    }
    visited[now] = false;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n, m;

    n = sc.nextInt();
    m = sc.nextInt();
    sc.nextLine();

    graph = new ArrayList<>();
    visited = new boolean[n];

    for(int i = 0;i<n;i++) {
      graph.add(new ArrayList<Integer>());
    }

    for(int i = 0 ; i < m ; i++) {
      String[] splitString = sc.nextLine().split(" ");
      int a = Integer.parseInt(splitString[0]);
      int b = Integer.parseInt(splitString[1]);

      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    for(int i = 0 ; i < n ; i++) {
      dfs(i, 0);
    }

    System.out.println(0);

    sc.close();
  }
}