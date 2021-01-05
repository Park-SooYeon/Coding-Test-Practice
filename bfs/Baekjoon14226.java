package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/14226
  이모티콘
  푼 날짜 : 2021-01-05
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon14226 {

  static class Data {
    int totalTime;
    int clipBoard;
    int emojiCount;

    Data(int totalTime, int clipBoard, int emojiCount) {
      this.totalTime = totalTime;
      this.clipBoard = clipBoard;
      this.emojiCount = emojiCount;
    }
  }

  private static int getMinTime(int s) {
    Queue<Data> queue = new LinkedList<>();
    // emojiCount 값이 동일해도 clipBoard 값에 따라 연산이 달라지므로 2차원 배열로 방문확인
    boolean[][] visited = new boolean[s + 1][s + 1];
    int totalTime = 0;
    int clipBoard = 0;
    int emojiCount = 1;

    queue.offer(new Data(totalTime, clipBoard, emojiCount));

    while(!queue.isEmpty()) {
      Data now = queue.poll();
      totalTime = now.totalTime;
      clipBoard = now.clipBoard;
      emojiCount = now.emojiCount;

      if(emojiCount == s) {
        return totalTime;
      }

      if(clipBoard != emojiCount) {
        queue.offer(new Data(totalTime + 1, emojiCount, emojiCount)); // copy
        visited[emojiCount][clipBoard] = true;
      }

      if(clipBoard != 0 && emojiCount + clipBoard <= s && !visited[emojiCount + clipBoard][clipBoard]) {
        queue.offer(new Data(totalTime + 1, clipBoard, emojiCount + clipBoard)); // paste
        visited[emojiCount + clipBoard][clipBoard] = true;
      }

      if(emojiCount - 1 > 0 && !visited[emojiCount - 1][clipBoard]) {
        queue.offer(new Data(totalTime + 1, clipBoard, emojiCount - 1)); // delete
        visited[emojiCount - 1][clipBoard] = true;
      }
    }

    return totalTime;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int s = Integer.parseInt(br.readLine());
    int totalTime = getMinTime(s);

    bw.write(totalTime + "");

    bw.flush();
    bw.close();
    br.close();
  }
}