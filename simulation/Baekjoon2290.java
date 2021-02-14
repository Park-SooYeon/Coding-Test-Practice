package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/2290
  LCD Test
  푼 날짜 : 2021-02-14
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon2290 {

  private static String drawNumber(int s, String n) {
    StringBuilder sb = new StringBuilder();
    int length = n.length();
    int width = s + 2;
    int height = 2 * s + 3;
    int middle = height / 2;
    String[] str = new String[height]; // 줄별로 출력할 문자열을 저장할 배열
    
    for(int j = 0 ; j < length ; j++) {
      char nowChar = n.charAt(j);
      for(int i = 0 ; i < height ; i++) {
        if(str[i] == null) str[i] = "";
        if(i == 0) {
          // 첫째줄
          switch(nowChar) {
            case '1':
            case '4':
              for(int k = 0 ; k < width ; k++) str[i] += " ";
              break;
            default:
              str[i] += " ";
              for(int k = 0 ; k < s ; k++) str[i] += "-";
              str[i] += " ";
          }
        } else if(i > 0 && i < middle) {
          // 첫째줄과 중간줄 사이
          switch(nowChar) {
            case '1':
            case '2':
            case '3':
            case '7':
              for(int k = 0 ; k < width - 1 ; k++) str[i] += " ";
              str[i] += "|";
              break;
            case '5':
            case '6':
              str[i] += "|";
              for(int k = 0 ; k < width - 1 ; k++) str[i] += " ";
              break;
            default:
              str[i] += "|";
              for(int k = 0 ; k < s ; k++) str[i] += " ";
              str[i] += "|";
          }
        } else if(i == middle) {
          // 중간줄
          switch(nowChar) {
            case '1':
            case '7':
            case '0':
              for(int k = 0 ; k < width ; k++) str[i] += " ";
              break;
            default:
              str[i] += " ";
              for(int k = 0 ; k < s ; k++) str[i] += "-";
              str[i] += " ";
          }
        } else if(i > middle && i < height - 1) {
          // 중간줄과 마지막줄 사이
          switch(nowChar) {
            case '1':
            case '3':
            case '4':
            case '5':
            case '7':
            case '9':
              for(int k = 0 ; k < width - 1 ; k++) str[i] += " ";
              str[i] += "|";
              break;
            case '2':
              str[i] += "|";
              for(int k = 0 ; k < width - 1 ; k++) str[i] += " ";
              break;
            default:
              str[i] += "|";
              for(int k = 0 ; k < s ; k++) str[i] += " ";
              str[i] += "|";
          }
        } else {
          // 마지막줄
          switch(nowChar) {
            case '1':
            case '4':
            case '7':
              for(int k = 0 ; k < width ; k++) str[i] += " ";
              break;
            default:
              str[i] += " ";
              for(int k = 0 ; k < s ; k++) str[i] += "-";
              str[i] += " ";
          }
        }
        str[i] += " ";
      }
    }

    for(int i = 0 ; i < height ; i++) {
      sb.append(str[i] + "\n");
    }

    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int s = Integer.parseInt(st.nextToken());
    String n = st.nextToken();

    String result = drawNumber(s, n);

    bw.write(result);

    bw.flush();
    bw.close();
    br.close();
  }
}