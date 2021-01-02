package graph;

/*
  문제 출처 : https://www.acmicpc.net/problem/1707
  이분 그래프
  푼 날짜 : 2021-01-02
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon1707 {
  static final int RED = 1;
  static final int BLUE = -1;

  private static String isBipartiteGraph(int v, ArrayList<ArrayList<Integer>> graph) {
    int[] visited = new int[v + 1];
    
    for(int i = 1 ; i <= v ; i++) {
      if(visited[i] != 0) continue;
      Queue<Integer> queue = new LinkedList<>();
      
      queue.add(i);
      visited[i] = RED;

      while(!queue.isEmpty()) {
        int currentVertex = queue.remove();
        int currentColor = visited[currentVertex];
  
        ArrayList<Integer> nowList = graph.get(currentVertex);
  
        for(int nextVertex : nowList) {
          int nextColor = visited[nextVertex];
          if(nextColor == currentColor) return "NO";
          if(nextColor == 0) {
            visited[nextVertex] = currentColor == RED ? BLUE : RED;
            queue.add(nextVertex);
          }
        }
      }
    }

    return "YES";
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int k = Integer.parseInt(br.readLine());
    
    for(int i = 0 ; i < k ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
      for(int j = 0 ; j <= v ; j++) {
        graph.add(new ArrayList<Integer>());
      }

      for(int j = 0 ; j < e ; j++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        graph.get(a).add(b);
        graph.get(b).add(a);
      }

      String result = isBipartiteGraph(v, graph);
      sb.append(result + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}