package bruteForce.bitMask;

/*
  문제 출처 : https://www.acmicpc.net/problem/11723
  집합
  푼 날짜 : 2020-12-29
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon11723 {
  private static StringBuilder sb = new StringBuilder();
  private static int bitSet = 0; // 00000000 00000000 00000000 00000000
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n;

    n = Integer.parseInt(br.readLine());

    for(int i = 0 ; i < n ; i++) {
      String[] splitCommand = br.readLine().split(" ");
      int number = splitCommand.length == 2? Integer.parseInt(splitCommand[1]) : 0;
      commands(splitCommand[0], number);
    }

    bw.write(sb.toString());
    bw.close();
    br.close();
  }

  private static void commands(String command, int number) {
    switch(command) {
      case "add": // or
        bitSet |= (1 << (number - 1));
        break;
      case "remove": // ~and
        bitSet &= ~(1 << (number - 1));
        break;
      case "check": // and
        int result = (bitSet & (1 << (number -1))) != 0? 1 : 0;
        sb.append(result + "\n");
        break;
      case "toggle": // xor
        bitSet ^= (1 << (number - 1));
        break;
      case "all": // 1과 or
        bitSet |= (~0);
        break;
      case "empty": // 0과 and
        bitSet &= 0;
        break;
    }
  }
}