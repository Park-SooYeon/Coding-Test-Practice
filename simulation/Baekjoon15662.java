package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/15662
  톱니바퀴 (2)
  푼 날짜 : 2021-01-25
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 풀이 참조 : https://m.blog.naver.com/PostView.nhn?blogId=coolbino&logNo=221380760793&proxyReferer=https:%2F%2Fwww.google.com%2F
public class Baekjoon15662 {
  private static int S_POLE = 1;
  private static int GEAR_TEETH = 8;
  private static int LEFT_TEETH_INDEX = 6;
  private static int RIGHT_TEETH_INDEX = 2;
  private static int TOP_TEETH_INDEX = 0;
  private static int t = 0;
  private static ArrayList<LinkedList<Integer>> gears = new ArrayList<>();

  private static void left(int gearNumber, boolean rotateDirection) {
    if(gearNumber < 1) return; // 첫번째 톱니는 왼쪽에 접합한 톱니가 없음
    // 현재 선택된 톱니와 이후에 있는 톱니 정보 비교
    int currentGearLeftTeeth = gears.get(gearNumber).get(RIGHT_TEETH_INDEX);
    int nextGearLeftTeeth = gears.get(gearNumber + 1).get(LEFT_TEETH_INDEX);
    
    // 톱니가 다르면 현재의 톱니가 반대 방향으로 회전
    if(currentGearLeftTeeth != nextGearLeftTeeth) {
      left(gearNumber - 1, !rotateDirection); // 이전 톱니 회전 확인
      turn(gearNumber, rotateDirection); // 현재 톱니 회전
    }
  }

  private static void right(int gearNumber, boolean rotateDirection) {
    if(gearNumber > t) return; // 마지막 톱니는 오른쪽에 접합한 톱니가 없음
    // 현재 선택된 톱니와 이전에 있는 톱니 정보 비교
    int currentGearRightTeeth = gears.get(gearNumber).get(LEFT_TEETH_INDEX);
    int prevGearLeftTeeth = gears.get(gearNumber - 1).get(RIGHT_TEETH_INDEX);
    
    // 톱니가 다르면 이전의 톱니가 반대 방향으로 회전
    if(currentGearRightTeeth != prevGearLeftTeeth) {
      right(gearNumber + 1, !rotateDirection); // 이후 톱니 회전 확인
      turn(gearNumber, rotateDirection); // 현재 톱니 회전
    }
  }

  private static void turn(int gearNumber, boolean rotateDirection) {
    LinkedList<Integer> currentGear = gears.get(gearNumber);

    if(rotateDirection) { // 시계방향 회전
      currentGear.addFirst(currentGear.pollLast());
    } else { // 반시계방향 회전
      currentGear.addLast(currentGear.pollFirst());
    }
  }

  private static void rotateGear(int gearNumber, boolean rotateDirection) {
    // 왼쪽 톱니바퀴 회전 여부
    left(gearNumber - 1, !rotateDirection);
    // 오른쪽 톱니바퀴 회전 여부
    right(gearNumber + 1, !rotateDirection);
    // 현재 톱니 회전
    turn(gearNumber, rotateDirection);
  }

  private static int getTopTeethCount() {
    int count = 0;

    for(int i = 1 ; i <= t ; i++) {
      LinkedList<Integer> currentGear = gears.get(i);
      if(currentGear.get(TOP_TEETH_INDEX) == S_POLE) count++;
    }

    return count;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    t = Integer.parseInt(br.readLine());
    gears.add(new LinkedList<Integer>()); // 0번쨰 인덱스 셋팅

    for(int i = 1 ; i <= t ; i++) {
      String input = br.readLine();
      gears.add(new LinkedList<Integer>());
      for(int j = 0 ; j < GEAR_TEETH ; j++) gears.get(i).add(input.charAt(j) - '0');
    }

    int k = Integer.parseInt(br.readLine());

    for(int i = 0 ; i < k ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int gearNumber = Integer.parseInt(st.nextToken()); // arr의 index 번호 사용을 위해 톱니 번호가 0 ~ t-1인 것을 고려
      boolean rotateDirection = Integer.parseInt(st.nextToken()) == 1 ? true : false; // 1(true)면 시계방향, -1(false)면 반시계방향으로 회전
      rotateGear(gearNumber, rotateDirection);
    }

    int count = getTopTeethCount();

    bw.write(count + "");

    bw.flush();
    bw.close();
    br.close();
  }
}