package bruteForce.recursion;

/*
  문제 출처 : https://www.acmicpc.net/problem/15661
  링크와 스타트
  푼 날짜 : 2021-02-05
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon15661 {
  private static int n;
  private static int[][] arr;
  private static boolean[] visited; // true면 스타트팀, false면 링크팀
  private static int min = Integer.MAX_VALUE;

  private static int getTotal(List<Integer> team) {
    int sum = 0;
    int length = team.size();
    if(length == 1) return 0; // 팀원이 혼자일 경우에는 팀 능력치가 0이다.

    for(int i = 0 ; i < length ; i++) {
      for(int j = i + 1 ; j < length ; j++) {
        int firstPerson = team.get(i);
        int secondPerson = team.get(j);
        sum += arr[firstPerson][secondPerson] + arr[secondPerson][firstPerson];
      }
    }

    return sum;
  }
  private static void calculateDiff() {
    List<Integer> startTeam = new ArrayList<>();
    List<Integer> linkTeam = new ArrayList<>();

    for(int i = 0 ; i < n ; i++) {
      if(visited[i]) startTeam.add(i);
      else linkTeam.add(i);
    }
    
    // 각 팀에서 2명의 팀원을 선택해서 능력치의 합을 구하는 경우의 수 구하기 (조합)
    int startTeamScore = getTotal(startTeam);
    int linkTeamScore = getTotal(linkTeam);

    int diff = Math.abs(startTeamScore - linkTeamScore);

    if(diff == 0) {
      min = 0;
      return;
    }

    min = Math.min(diff, min);
  }

  // n명의 사람 중, 스타트팀인 사람을 num명 선택하는 경우의 수 구하기 (조합)
  private static void combination(int index, int count, int num) {
    if(count == num) {
      // 절반의 팀원이 선택되면 스타트 팀과 링크 팀의 능력치 차이 계산
      calculateDiff();
      return;
    }

    for(int i = index ; i < n ; i++) {
      if(visited[i]) continue; // 이미 선택된 팀원이면 건너뜀
      
      visited[i] = true;
      combination(i + 1, count + 1, num);
      visited[i] = false;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Integer.parseInt(br.readLine());
    arr = new int[n][n];
    visited = new boolean[n];
    
    for(int i = 0 ; i < n ; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0 ; j < n ; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 1 ; i <= (n / 2) ; i++) {
      // 스타트 팀의 팀원이 1 ~ n/2 명 선택되었을 때의 팀 능력치 차이를 계산
      // 팀이 1 / 2, 3, 4와 2, 3, 4 / 1로 나누었을 때 팀 능력치 차이는 동일하므로 절반을 선택한 경우까지만 구한다.
      combination(0, 0, i);
    }

    bw.write(min + "");

    bw.flush();
    bw.close();
    br.close();
  }
}