package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/6603
  로또
  푼 날짜 : 2021-02-25
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon6603 {
  private static final int choiceCount = 6; // k개 중 선택해야할 숫자의 개수
  private static int[] choiceNumber = new int[choiceCount]; // 선택한 숫자를 담을 배열
  private static StringBuilder sb = new StringBuilder();
  private static int k;
  private static int[] s;

  private static void print() {
    for(int i = 0 ; i < choiceCount ; i++) {
      sb.append(choiceNumber[i] + " ");
    }
    sb.append("\n");
  }

  private static void combination(int index, int count) {
    if(count == 6) {
      print();
      return;
    }

    for(int i = index ; i < k ; i++) {
      choiceNumber[count] = s[i];
      combination(i + 1, count + 1);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    while(true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      k = Integer.parseInt(st.nextToken());
      if(k == 0) break; // 입력의 마지막 줄

      s = new int[k];
      for(int i = 0 ; i < k ; i++) {
        s[i] = Integer.parseInt(st.nextToken());
      }
      
      combination(0, 0);

      sb.append("\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}