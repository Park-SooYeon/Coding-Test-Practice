package graph;

/*
  문제 출처 : https://www.acmicpc.net/problem/10866
  덱
  푼 날짜 : 2020-12-30
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon10866 {
  static ArrayList<Integer> deque = new ArrayList<>();
  static StringBuilder sb = new StringBuilder();

  private static void commands(String command, int number) {
    switch(command) {
      case "push_front":
        deque.add(0, number);
        break;
      case "push_back":
        deque.add(number);
        break;
      case "pop_front":
        int popFrontNumber = deque.isEmpty()? -1 : deque.remove(0);
        sb.append(popFrontNumber + "\n");
        break;
      case "pop_back":
        int popBackNumber = deque.isEmpty()? -1 : deque.remove(deque.size() - 1);
        sb.append(popBackNumber + "\n");
        break;
      case "empty":
        int isEmpty = deque.isEmpty()? 1 : 0;
        sb.append(isEmpty + "\n");
        break;
      case "front":
        int frontNumber = deque.isEmpty()? -1 : deque.get(0);
        sb.append(frontNumber + "\n");
        break;
      case "back":
        int backNumber = deque.isEmpty()? -1 : deque.get(deque.size() - 1);
        sb.append(backNumber + "\n");
        break;
      case "size":
        sb.append(deque.size() + "\n");
        break;
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int commandCount;

    commandCount = Integer.parseInt(br.readLine());

    for(int i = 0 ; i < commandCount ; i++) {
      String[] splitCommand = br.readLine().split(" ");
      int number = splitCommand.length != 1 ? Integer.parseInt(splitCommand[1]) : 0;
      commands(splitCommand[0], number);
    }

    System.out.println(sb);
  }
}