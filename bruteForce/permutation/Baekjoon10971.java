package bruteForce.permutation;

/*
  문제 출처 : https://www.acmicpc.net/problem/10971
  외판원 순회 2
  푼 날짜 : 2021-02-23
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon10971 {
  private static int n, startPosition = 0, minCost = Integer.MAX_VALUE;
  private static int[][] w;
  private static boolean[] visited;

  // start에서 end로 이동, cnt : 방문한 도시의 개수, sum : 필요한 비용
  private static void dfs(int start, int end, int cnt, int sum) {
    if(cnt == n && end == startPosition) { // 모든 곳을 순회하고 다시 원래 위치로 돌아왔을 때 최소 비용 계산
      minCost = Math.min(minCost, sum);
      return;
    }

    // 다음 이동할 도시 찾기
    for(int i = 0 ; i < n ; i++) {
      if(visited[i]) continue;
      if(w[end][i] == 0) continue; // 갈 수 없는 경우
      if(sum + w[end][i] > minCost) continue; // 이미 총 비용이 최소 비용보다 큰 경우에는 더 탐색하지 않음
      visited[i] = true;
      dfs(end, i, cnt + 1, sum + w[end][i]);
      visited[i] = false;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Integer.parseInt(br.readLine());
    w = new int[n][n];
    visited = new boolean[n];
    
    for(int i = 0 ; i < n ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < n ; j++) {
        w[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 0 ; i < n ; i++) {
      startPosition = i;
      dfs(i, i, 0, 0);
    }

    bw.write(minCost + "");

    bw.flush();
    bw.close();
    br.close();
  }
}