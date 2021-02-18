package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/2529
  부등호
  푼 날짜 : 2021-02-18
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon2529 {
  private static int n;
  private static char[] arr; // 주어진 부등호 개수를 저장할 배열
  private static boolean[] visited = new boolean[10]; // 0 ~ 9 까지 사용된 정수 체크에 사용
  private static List<String> ans = new ArrayList<>(); // 모든 가능한 경우의 수를 저장할 리스트

  private static boolean check(int a, int b, int symbol) {
    if (symbol == '<' && a < b) return true;
    if (symbol == '>' && a > b) return true;
    return false;
  }

  // num : 현재 선택된 숫자들, index : 현재 선택된 숫자들의 개수
  private static void dfs(String num, int index) {
    if (index == n + 1) { // 주어진 부등호의 개수보다 선택된 숫자가 한개 더 많아야한다.
      ans.add(num);
      return;
    }

    for (int i = 0; i <= 9; i++) {
      if (visited[i]) continue;
      // 추가된 숫자가 없을 때나 부등호 관계를 만족할 때에 경우의 수 추가
      if (index == 0 || check(Character.getNumericValue(num.charAt(index - 1)), i, arr[index - 1])) {
          visited[i] = true;
          dfs(num + i, index + 1);
          visited[i] = false;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    n = Integer.parseInt(br.readLine());
    arr = new char[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n ; i++) {
      arr[i] = st.nextToken().charAt(0);
    }

    dfs("", 0);
    Collections.sort(ans);

    sb.append(ans.get(ans.size() - 1) + "\n");
    sb.append(ans.get(0));

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}