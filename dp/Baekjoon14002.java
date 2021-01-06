package dp;

/*
  문제 출처 : https://www.acmicpc.net/problem/14002
  가장 긴 증가하는 부분 수열 4
  푼 날짜 : 2021-01-06
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon14002 {
  static int[] prev;

  private static int[] getDPArray(int[] a) {
    int size = a.length;
    int[] m = new int[size];

    for(int i = 0 ; i < size ; i++) {
      m[i] = 1;
      prev[i] = -1;
      for(int j = 0 ; j < i ; j++) {
        if(a[i] > a[j] && m[i] <= m[j]) {
          m[i] = m[j] + 1;
          prev[i] = j; // 역추적을 위한 이전 순열 index 저장
        }
      }
    }

    return m;
  }

  private static String getMaxAndPath(int[] a, int[] m) {
    StringBuilder sb = new StringBuilder();
    int index = 0;
    int max = 0;

    // 최대값 찾기
    for(int i = 0 ; i < m.length ; i++) {
      if(m[i] > max) {
        max = m[i];
        index = i;
      }
    }

    sb.append(max + "\n");

    // 경로 찾기
    Stack<Integer> stack = new Stack<>();
    stack.push(index);

    while(true) {
      int prevIndex = prev[index];
      if(prevIndex == -1) break;
      stack.push(prevIndex);
      index = prevIndex;
    }

    while(!stack.isEmpty()) {
      int currentIndex = stack.pop();
      sb.append(a[currentIndex] + " ");
    }
    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] a = new int[n];
    prev = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n ; i++) {
      a[i] = Integer.parseInt(st.nextToken());
    }

    int[] m = getDPArray(a);
    String result = getMaxAndPath(a, m);

    bw.write(result);

    bw.flush();
    bw.close();
    br.close();
  }
}