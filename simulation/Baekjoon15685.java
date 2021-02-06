package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/15685
  드래곤 커브
  푼 날짜 : 2021-02-06
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 풀이 참조 : https://dublin-java.tistory.com/34
public class Baekjoon15685 {
  private static int MAP_SIZE = 101;
  private static int[] X = {1, 0, -1, 0}; // 우, 상, 좌, 하
  private static int[] Y = {0, -1, 0, 1};

  private static boolean[][] map = new boolean[MAP_SIZE][MAP_SIZE];
  
  // 이전 세대의 드래곤 커브의 방향 정보를 가져옴
  private static List<Integer> getDirections(int d, int g) {
    List<Integer> directions = new ArrayList<>();
    directions.add(d);

    // 주어진 세대만큼 반복
    while(g > 0) {
      int size = directions.size() - 1;
      // 이전 세대의 드래곤 커브의 방향을 기준으로 새로 그릴 드래곤 커브의 방향 추가
      for(int i = size ; i >= 0 ; i--) {
        int direction = (directions.get(i) + 1) % 4;
        directions.add(direction);
      }
      g--;
    }

    return directions;
  }

  // map에 드래곤 커브 그리기
  private static void drawCurveMap(int x, int y, List<Integer> directions) {
    int nextX = x;
    int nextY = y;
    
    map[x][y] = true;

    for(int direction : directions) {
      nextX = nextX + X[direction];
      nextY = nextY + Y[direction];

      map[nextX][nextY] = true;
    }
  }

  // 그려진 사각형의 개수 구하기
  private static int getNumberOfSquares() {
    int count = 0;

    // 완전탐색을 통해 0 ~ 99까지 돌면서 사각형의 개수 구하기
    for(int x = 0 ; x < MAP_SIZE - 1 ; x++) {
      for(int y = 0 ; y < MAP_SIZE - 1 ; y++) {
        if(map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1]) count++;
      }
    }

    return count;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    for(int i = 0 ; i < n ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken()); // 시작 방향
      int g = Integer.parseInt(st.nextToken()); // 세대

      List<Integer> directions = getDirections(d, g);
      drawCurveMap(x, y, directions);
    }

    int count = getNumberOfSquares();

    bw.write(count + "");

    bw.flush();
    bw.close();
    br.close();
  }
}