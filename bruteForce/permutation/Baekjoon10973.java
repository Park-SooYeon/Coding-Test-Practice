package bruteForce.permutation;

/*
  문제 출처 : https://www.acmicpc.net/problem/10973
  이전 순열
  푼 날짜 : 2021-01-13
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon10973 {

  /*
    풀이
    1. A[i-1] > A[i]를 만족하는 가장 큰 i를 찾는다.
    2. j >= i 이면서 A[j] < A[i-1]를 만족하는 가장 큰 j를 찾는다.
    3. A[i-1]과 A[j]를 swap한다.
    4. A[i]부터 순열을 뒤집는다.
  */
  private static String getPrevPermutation(int[] numbers) {
    StringBuilder sb = new StringBuilder();
    int i = 0, j = 0;

    for(int n = 1 ; n < numbers.length ; n++) {
      if(numbers[n - 1] > numbers[n]) i = n;
    }

    if(i == 0) return sb.append("-1").toString();
  
    for(int n = i ; n < numbers.length ; n++) {
      if(numbers[n] < numbers[i - 1]) j = n;
    }

    int temp = numbers[i - 1];
    numbers[i - 1] = numbers[j];
    numbers[j] = temp;

    int k = numbers.length - 1;
    while(i < k) {
      int temp2 = numbers[i];
      numbers[i] = numbers[k];
      numbers[k] = temp2;
      i++;
      k--;
    }

    for(int m = 0 ; m < numbers.length ; m++) {
      sb.append(numbers[m] + " ");
    }
    return sb.toString();
   }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());

    int[] numbers = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    for(int i = 0 ; i < n ; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    String nextPermutation = getPrevPermutation(numbers);

    bw.write(nextPermutation);

    bw.flush();
    bw.close();
    br.close();
  }
}