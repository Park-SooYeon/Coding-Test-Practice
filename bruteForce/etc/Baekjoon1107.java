package bruteForce.etc;

/*
  문제 출처 : https://www.acmicpc.net/problem/1107
  리모컨
  푼 날짜 : 2021-01-11
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Baekjoon1107 {
  private static final int MAX_CHANNEL = 1000000;
  private static int initChannel = 100;
  private static List<Integer> notWorkingButton = new ArrayList<>();

  private static boolean isAvailable(int i) {
    if(notWorkingButton.size() == 0) return true;

    String reg = "";

    for(int button : notWorkingButton) {
      reg += button + "|";
    }

    reg = reg.substring(0, reg.length() - 1); // 마지막 | 제거

    Pattern pat = Pattern.compile(reg);
    Matcher match = pat.matcher(i + "");

    if(match.find()) return false;
    return true;
  }

  private static int getMinClickCount(int n) {
    int minClickCount = Math.abs(n - initChannel);

    if(n == initChannel) return 0;

    for(int i = 0 ; i < MAX_CHANNEL; i++) {
      // 버튼으로 만들 수 있는 채널인지 체크
      if(!isAvailable(i)) continue; // 만들 수 없는 경우
      // 만들 수 있는 경우
      int channelLength = (i + "").length();
      int diff = Math.abs(n - i);
      minClickCount = Math.min(diff + channelLength, minClickCount);
    }

    return minClickCount;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());
    
    if(m != 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      while(st.hasMoreTokens()) notWorkingButton.add(Integer.parseInt(st.nextToken()));
    }

    int clickCount = getMinClickCount(n);

    bw.write(clickCount + "");

    bw.flush();
    bw.close();
    br.close();
  }
}