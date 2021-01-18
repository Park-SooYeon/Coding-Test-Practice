package bruteForce.permutation;

/*
  문제 출처 : https://www.acmicpc.net/problem/10974
  모든 순열
  푼 날짜 : 2021-01-18
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon10974 {
  // https://dundung.tistory.com/59 를 참조하여 재귀가 아닌 swap으로 구현
  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static boolean getNextPermutation(int[] arr) {
    int i = arr.length - 1;

    while(i > 0 && arr[i] <= arr[i - 1]) {
      i--;
    }

    if(i <= 0) return false;

    int j = arr.length - 1;
    while(arr[i - 1] >= arr[j]) {
      j--;
    }

    swap(arr, i - 1, j);

    j = arr.length - 1;
    while(i < j) {
      swap(arr, i, j);
      i++;
      j--;
    }

    return true;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];

    for(int i = 1 ; i <= n ; i++) {
      arr[i - 1] = i;
    }

    do {
      for(int i = 0 ; i < n ; i++) {
        sb.append(arr[i] + " ");
      }
      sb.append("\n");
    } while(getNextPermutation(arr));

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}