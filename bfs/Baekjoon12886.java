package bfs;

/*
  문제 출처 : https://www.acmicpc.net/problem/12886
  돌 그룹
  푼 날짜 : 2021-03-08
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Baekjoon12886 {
  private static String arrayToString(int[] arr) {
    return "" + arr[0] + arr[1] + arr[2];
  }

  private static int solution(int a, int b, int c) {
    Queue<int[]> q = new LinkedList<int[]>();
    Set<String> set = new HashSet<String>();
    int[] arr = new int[3];
    String arrString = ""; // string으로 변환된 배열을 저장할 변수
    
    if((a + b + c % 3) == 0) return 0; // a, b, c의 개수가 3의 배수가 아니면 같은 개수로 나눌 수 없음
    
    arr[0] = a; arr[1] = b; arr[2] = c;
    Arrays.sort(arr); // 1, 2, 3 그룹이나 3, 2, 1 그룹이나 동일하게 처리되기 때문에 sort 수행
    arrString = arrayToString(arr);
    q.offer(arr);
    set.add(arrString);
    
    // 같은 개수로 나눌 수 있는지 확인
    while(!q.isEmpty()) {
      int[] group = q.poll();

      if(group[0] == group[1] && group[1] == group[2]) return 1;
      
      int minGroup = group[0];
      for(int i = 1 ; i < 3 ; i++) {
        if(minGroup == group[i]) continue; // 두 그룹의 값이 같으면 값을 변경할 필요 없음
        int[] newArr = new int[3]; // q에 넣은 배열은 얕은 복사를 통해 저장되므로 새로운 배열을 선언하여 사용

        newArr[0] = minGroup + minGroup;
        newArr[1] = group[1]; newArr[2] = group[2];
        newArr[i] = group[i] - minGroup;

        // 이미 검사한 그룹일 경우 다시 검사 X
        Arrays.sort(newArr);
        arrString = arrayToString(newArr);
        if(set.contains(arrString)) continue;

        q.offer(newArr);
        set.add(arrString);
      }
    }
    
    return 0;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    int result = solution(a, b, c);

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}