package simulation;

/*
  문제 출처 : https://www.acmicpc.net/problem/1917
  정육면체 전개도
  푼 날짜 : 2021-02-19
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 풀이 참조 : https://ssongkr.github.io/2020/04/07/bj-1917.html
public class Baekjoon1917 {
  private static int[][][] cubes = { // 정육면체 생성이 가능한 모든 타일의 종류
		{ {0, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 0} }, 
		{ {0, 0, 0, 1}, {1, 1, 1, 1}, {0, 1, 0, 0} }, 
		{ {0, 0, 0, 1}, {1, 1, 1, 1}, {0, 0, 1, 0} }, 
		{ {0, 0, 0, 1}, {1, 1, 1, 1}, {0, 0, 0, 1} },
		{ {0, 0, 1, 0}, {1, 1, 1, 1}, {0, 1, 0, 0} },
		{ {0, 0, 1, 0}, {1, 1, 1, 1}, {0, 0, 1, 0} },
		{ {0, 0, 1, 1}, {1, 1, 1, 0}, {1, 0, 0, 0} },
		{ {0, 0, 1, 1}, {1, 1, 1, 0}, {0, 1, 0, 0} },
		{ {0, 0, 1, 1}, {1, 1, 1, 0}, {0, 0, 1, 0} },
		{ {0, 0, 1, 1, 1}, {1, 1, 1, 0, 0} },
		{ {0, 0, 1, 1}, {0, 1, 1, 0}, {1, 1, 0, 0} },
	};

  private static int[][] arr;
  private static int n = 6;

  private static boolean inside(int row, int col) {
    return row >= 0 && col >= 0 && row < n && col < n;
  }

  private static boolean check(int[][] cube, int sr, int sc) {
    int R = cube.length;
		int C = cube[0].length;
    
		for(int i = 0 ; i < R ; i++) {
			for(int j = 0 ; j < C ; j++) {
        // 타일이 범위를 벗어날 경우 false 반환
				if(!inside(sr+i, sc+j)) return false;
        // 주어진 타일과 정육면체 타일이 불일치 할 경우 false 반환
				if(cube[i][j] != arr[sr + i][sc + j]) return false;
			}
		}
		
		return true;
  }

  private static boolean isSame(int[][] cube) {
    // 좌표값을 변경해가며 주어진 타일과 정육면체 타일이 일치하는지 검사
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if(check(cube, i, j)) return true;
			}
		}
		return false;
  }

  private static void rotate() {
		int[][] temp = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				temp[j][n - 1 - i] = arr[i][j];
			}
		}
		arr = temp;
  }

  private static void reverse() {
		int[][] temp = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				temp[i][n - 1 - j] = arr[i][j];
			}
		}
		arr = temp;
  }

  private static String checkCube() {
    for(int j = 0 ; j < 2 ; j++) {
			for(int i = 0 ; i < 4 ; i++) {
				for(int[][] cube: cubes) {
          // 정육면체 타일 중, 일치하는 타일이 있는지 검사
					if(isSame(cube)) return "yes";
				}
        // 회전 후 검사
				rotate();
			}
      // 반전 후 검사
			reverse();
		}
		return "no";
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    for(int t = 0 ; t < 3 ; t++) {
      arr = new int[n][n];
      
      for(int i = 0 ; i < n ; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int j = 0 ; j < n ; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      String isCube = checkCube();
      sb.append(isCube + "\n");
    }

    bw.write(sb.toString());

    bw.flush();
    bw.close();
    br.close();
  }
}