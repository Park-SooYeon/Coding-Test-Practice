package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/1759
  암호 만들기
  푼 날짜 : 2021-01-10
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Baekjoon1759 {
  static int l, c;
  static String[] inputChar;
  static StringBuilder sb = new StringBuilder();

  private static void makePW(int index, int length, String pw) {
    if(length == l) {
      // 모음 개수 체크
      String reg = "a|e|i|o|u";
      int vowelsCount = 0;
      Pattern pat = Pattern.compile(reg);
      Matcher match = pat.matcher(pw);
      
      while(match.find()) {
        vowelsCount++;
      }
      // 모음이 1개 이상, 자음이 2개 이상인 것만 암호 가능
      if(vowelsCount >= 1 && length - vowelsCount >= 2) sb.append(pw + "\n");
    }

    for(int i = index; i < c ; i++) {
      makePW(i + 1, length + 1, pw + inputChar[i]);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    l = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    inputChar = new String[c];

    st = new StringTokenizer(br.readLine());
    // while (st.hasMoreTokens()) 를 사용해서 for문을 돌려줄수도 있다.
    for(int i = 0 ; i < c ; i++) {
      inputChar[i] = st.nextToken();
    }
    Arrays.sort(inputChar);

    makePW(0, 0, "");

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}