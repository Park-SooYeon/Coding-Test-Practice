package bruteForce.etc;

/*
  문제 출처 : https://www.acmicpc.net/problem/1748
  수 이어 쓰기 1
  푼 날짜 : 2021-02-22
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1748 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    String n = br.readLine();
    int number = Integer.parseInt(n);
    int numberLength = n.length();

    int totalLength =0;
    int temp = 9; // 1 ~ 9 : 9개, 10 ~ 99 : 90개, 100 ~ 999 : 900개, ...

    // 주어진 수에서 자릿수 - 1만큼의 총 자릿수 계산
    // ex) 120 : 십의 자리수와 일의 자리수의 총 자릿수 계산 (1 * 9 + 2 * 99)
    for(int i = 1 ; i < numberLength ; i++) {
      totalLength += i * temp;
      temp *= 10;
    }

    // 위에서 구해주지 않은 마지막 자릿수에서의 총 자릿수 계산
    // ex) 120 : 백의 자리수의 총 자릿수 계산 (100 ~ 120까지의 총 자릿수 계산)
    int last = (int) (number - Math.pow(10, numberLength - 1) + 1) * numberLength;
    totalLength += last;

    bw.write(totalLength + "");

    bw.flush();
    bw.close();
    br.close();
  }
}