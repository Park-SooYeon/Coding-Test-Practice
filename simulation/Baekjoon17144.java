package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/17144
  미세먼지 안녕!
  푼 날짜 : 2021-03-22
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon17144 {
  private static final int[] dx = {-1, 0, 1, 0};
  private static final int[] dy = {0, 1, 0, -1};

  private static void setAirCleaner(int[][] temp, List<int[]> airCleaner) {
    for(int i = 0 ; i < airCleaner.size() ; i++) {
      int x = airCleaner.get(i)[0];
      int y = airCleaner.get(i)[1];

      temp[x][y] = -1;
    }
  }

  private static int[][] spreadDust(int[][] a, List<int[]> airCleaner) {
    int R = a.length;
    int C = a[0].length;
    int[][] temp = new int[R][C];

    // 확산된 먼지가 기존 먼지 확산에 영향을 미치지 않음
    for(int i = 0 ; i < R ; i++) {
      for(int j = 0 ; j < C ; j++) {
        if(a[i][j] == 0) continue;
        if(a[i][j] == -1) {
          temp[i][j] = -1;
          continue;
        }

        temp[i][j] += a[i][j];

        int spreadDustAmount = a[i][j] / 5;
        for(int k = 0 ; k < 4 ; k++) {
          int nextX = i + dx[k];
          int nextY = j + dy[k];
          
          if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) continue;
          if(a[nextX][nextY] == -1) continue;
          
          temp[nextX][nextY] += spreadDustAmount;
          temp[i][j] -= spreadDustAmount;
        }
      }
    }

    return temp;
  }

  private static void operateAirCleaner(int[][] a, List<int[]> airCleaner) {
    int R = a.length;
    int C = a[0].length;

    // 위쪽 공기 순환
    int bottom = airCleaner.get(0)[0];
    int left = airCleaner.get(0)[1];
    int top = 0;
    int right = C - 1;

    for (int i = bottom; i > top; i--)  a[i][left] = a[i - 1][left];
    for (int i = left; i < right; i++)  a[top][i] = a[top][i + 1];
    for (int i = top; i < bottom; i++)  a[i][right] = a[i + 1][right];
    for (int i = right; i > left; i--)  a[bottom][i] = a[bottom][i - 1];
    a[bottom][1] = 0; // 공기청정기에서 나오는 바람은 먼지가 없는 바람임
    
    
    // 아래쪽 공기 순환
    top = airCleaner.get(1)[0];
    left = airCleaner.get(1)[1];
    bottom = R - 1;
    right = C - 1;

    for (int i = top; i < bottom; i++)  a[i][left] = a[i + 1][left];
    for (int i = left; i < right; i++)  a[bottom][i] = a[bottom][i + 1];
    for (int i = bottom; i > top; i--)  a[i][right] = a[i - 1][right];
    for (int i = right; i > left; i--)  a[top][i] = a[top][i - 1];
    a[top][1] = 0;
    
    // 공기청정기 위치를 다시 세팅
    setAirCleaner(a, airCleaner);
  }

  private static int getTotalDustAmount(int[][] a) {
    int R = a.length;
    int C = a[0].length;
    int totalDustAmount = 0;

    for(int i = 0 ; i < R ; i++) {
      for(int j = 0 ; j < C ; j++) {
        if(a[i][j] == -1) continue;
        totalDustAmount += a[i][j];
      }
    }

    return totalDustAmount;
  }

  private static int solution(int T, int[][] a, List<int[]> airCleaner) {
    int answer = 0;

    for(int i = 0 ; i < T ; i++) {
      // 미세먼지 확산
      a = spreadDust(a, airCleaner);

      // 공기청정기 작동
      operateAirCleaner(a, airCleaner);
    }

    answer = getTotalDustAmount(a);

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());
    int[][] a = new int[R][C];
    List<int[]> airCleaner = new ArrayList<int[]>(); // 공기청정기의 위치를 저장할 변수

    for(int i = 0 ; i < R ; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < C ; j++) {
        int inputInfo = Integer.parseInt(st.nextToken());
        if(inputInfo == -1) airCleaner.add(new int[]{i, j});
        a[i][j] = inputInfo;
      }
    }

    int answer = solution(T, a, airCleaner);

    bw.write(answer + "");

    bw.flush();
    bw.close();
    br.close();
  }
}