package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/2667
  단지번호붙이기
  푼 날짜 : 2021-01-29
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Baekjoon2667 {
  static int[] X = {1, 0, -1, 0};
  static int[] Y = {0, 1, 0, -1};

  private static class Data {
    int x;
    int y;
    Data(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  
  private static int getCount(int x, int y, int[][] map, boolean[][] visited) {
    int n = map.length;
    int count = 1;
    Queue<Data> q = new LinkedList<>();

    q.add(new Data(x, y));
    visited[x][y] = true;

    while(!q.isEmpty()) {
      Data current = q.remove();

      for(int i = 0 ; i < 4 ; i++) {
        int nextX = current.x + X[i];
        int nextY = current.y + Y[i];

        if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) continue;
        if(visited[nextX][nextY]) continue;

        if(map[nextX][nextY] == 1) {
          count++;
          q.add(new Data(nextX, nextY));
          visited[nextX][nextY] = true;
        }
      }
    }

    return count;
  }

  private static List<Integer> getTotalCount(int[][] map) {
    List<Integer> label = new ArrayList<>(); // 단지 번호 정보
    int n = map.length;
    boolean[][] visited = new boolean[n][n];
    
    for(int i = 0 ; i < n ; i++) {
      for(int j = 0 ; j < n ; j++) {
        if(map[i][j] == 1 && !visited[i][j]) {
          // 연결된 단지 개수 확인하기
          label.add(getCount(i, j, map, visited));
        }
      }
    }

    Collections.sort(label); // 오름차순으로 정렬
    
    return label;
  }
  
  private static void print(List<Integer> label) {
    StringBuilder sb = new StringBuilder();
    int length = label.size();

    sb.append(length + "\n");

    for(int i = 0 ; i < length ; i++) {
      sb.append(label.get(i) + "\n");
    }

    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[][] map = new int[n][n];

    for(int i = 0 ; i < n ; i++) {
      String input = br.readLine();
      for(int j = 0 ; j < n ; j++) {
        map[i][j] = input.charAt(j) - '0';
      }
    }

    List<Integer> label = getTotalCount(map);

    print(label);

    bw.flush();
    bw.close();
    br.close();
  }
}