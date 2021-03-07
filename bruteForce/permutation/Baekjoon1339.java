package bruteForce.permutation;

/*
  문제 출처 : https://www.acmicpc.net/problem/1339
  단어 수학
  푼 날짜 : 2021-03-07
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Baekjoon1339 {
  private static int n, max;
  private static char[][] words;
  private static boolean[] selected;
  private static List<Character> alphabet;
  private static Map<Character, Integer> map;

  private static int calc() {
    int sum = 0;

    for(int i = 0 ; i < n ; i++) {
      int temp = 0;
      for(char c : words[i]) {
        temp *= 10; // 자리수를 하나씩 올려줌
        temp += map.get(c);
      }
      sum += temp;
    }

    return sum;
  }

  private static void permutation(int depth) {
    if(depth == alphabet.size()) {
      int temp = calc();
      max = Math.max(max, temp);
      return;
    }

    for(int i = 0 ; i < 10 ; i++) {
      if(selected[i]) continue;
      selected[i] = true;
      map.put(alphabet.get(depth), i);
      permutation(depth + 1);
      selected[i] = false;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Integer.parseInt(br.readLine());
    max = Integer.MIN_VALUE;
    words = new char[n][];
    selected = new boolean[10];
    alphabet = new ArrayList<Character>();
    map = new HashMap<Character, Integer>();

    for(int i = 0 ; i < n ; i++) {
      char[] inputChar = br.readLine().toCharArray();
      words[i] = inputChar; // 입력받은 문자열을 character 배열에 저장

      // 알파벳 중복 제거
      for(char c : inputChar) {
        if(alphabet.contains(c)) continue;
        alphabet.add(c);
      }
    }

    permutation(0);

    bw.write(max + "");

    bw.flush();
    bw.close();
    br.close();
  }
}