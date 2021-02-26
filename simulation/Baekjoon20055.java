package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/20055
  컨베이어 벨트 위의 로봇
  푼 날짜 : 2021-02-26
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 풀이참조 : https://jellyinghead.tistory.com/69
public class Baekjoon20055 {
  private static int n, k;
  private static List<Integer> belt;
  private static List<Boolean> robot;

  private static void rotateBelt() {
    // 벨트 회전
    int size = belt.size();
    int lastBelt = belt.remove(size - 1);
    belt.add(0, lastBelt);
    
    // 로봇 회전
    robot.remove(n - 1);
    robot.add(0, false);
    if(robot.get(n - 1)) robot.set(n - 1, false);
  }

  private static void moveRobot() {
    for(int i = n - 2 ; i >= 0 ; i--) {
      if(!robot.get(i)) continue;
      if(belt.get(i + 1) > 0 && !robot.get(i + 1)) {
        robot.set(i, false);
        robot.set(i + 1, true);
        belt.set(i + 1, belt.get(i + 1) - 1);
        if(belt.get(i + 1) == 0) k--;
        if(robot.get(n - 1)) robot.set(n - 1, false);
      }
    }
  }

  private static void newRobot() {
    if(belt.get(0) > 0 && !robot.get(0)) {
      robot.set(0, true);
      belt.set(0, belt.get(0) - 1);
      if(belt.get(0) == 0) k--;
    }
  }

  private static int solution() {
    int answer = 0;

    while(k > 0) {
      answer++;
      // 벨트 회전
      rotateBelt();
      // 로봇 이동
      moveRobot();
      // 새 로봇 올리기
      newRobot();
    }
    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    belt = new ArrayList<Integer>();
    robot = new ArrayList<Boolean>();

    st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < 2 * n ; i++) {
      belt.add(Integer.parseInt(st.nextToken()));
    }
    
    for(int i = 0 ; i < n ; i++) {
      robot.add(false);
    }

    int count = solution();

    bw.write(count + "");

    bw.flush();
    bw.close();
    br.close();
  }
}