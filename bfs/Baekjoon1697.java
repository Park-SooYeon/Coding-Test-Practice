package bfs;

/*
문제 출처 : https://www.acmicpc.net/problem/1697
숨바꼭질
푼 날짜 : 2020-12-29
*/
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon1697 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n, k, cnt;

    n = sc.nextInt();
    k = sc.nextInt();

    cnt = bfs(n, k);

    System.out.println(cnt);

    sc.close();
  }

  private static int bfs(int n, int k) {
    int min = 0; int max = 100000;
    boolean[] visited = new boolean[max + 1];
    Queue<Position> q = new LinkedList<>();

    q.add(new Position(n, 0));
    visited[n] = true;

    while(true) {
      Position p = q.remove();
      int currentPosition = p.position;
      int currentCount = p.count;

      if(currentPosition == k) {
        return currentCount;
      }

      if(currentPosition > min && !visited[currentPosition - 1]) {
        q.add(new Position(currentPosition - 1, currentCount + 1));
        visited[currentPosition - 1] = true;
      }

      if(currentPosition < max && !visited[currentPosition + 1]) {
        q.add(new Position(currentPosition + 1, currentCount + 1));
        visited[currentPosition + 1] = true;
      }

      if(currentPosition <= (max / 2) && !visited[2 * currentPosition]) {
        q.add(new Position(2 * currentPosition, currentCount + 1));
        visited[2 * currentPosition] = true;
      }
    }
  }
}

class Position {
  int position;
  int count;
  Position(int position, int count) {
    this.position = position;
    this.count = count;
  }
}