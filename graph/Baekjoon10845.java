package graph;

/*
  문제 출처 : https://www.acmicpc.net/problem/10845
  큐
  푼 날짜 : 2020-12-25
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon10845 {
  static ArrayList<Integer> queue = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int commandCount;

    commandCount = Integer.parseInt(br.readLine());

    for(int i = 0 ; i < commandCount ; i++) {
      String[] splitCommand = br.readLine().split(" ");
      switch(splitCommand[0]) {
        case "push":
          int number = Integer.parseInt(splitCommand[1]);
          queue.add(number);
          break;
        case "pop":
          if(queue.isEmpty()) sb.append(-1 + "\n");
          else sb.append(queue.remove(0) + "\n");
          break;
        case "empty":
          if(queue.isEmpty()) sb.append(1 + "\n");
          else sb.append(0 + "\n");
          break;
        case "front":
          if(queue.isEmpty()) sb.append(-1 + "\n");
          else sb.append(queue.get(0) + "\n");
          break;
        case "back":
          if(queue.isEmpty()) sb.append(-1 + "\n");
          else sb.append(queue.get(queue.size() - 1) + "\n");
          break;
        case "size":
          sb.append(queue.size() + "\n");
          break;
      }
    }

    System.out.println(sb);
  }
}