import java.util.*;
import java.io.*;

public class Main {

	static int[] win = new int[6];
	static int[] draw = new int[6];
	static int[] lose = new int[6];

	// 팀 2개가 경기할 수 있는 경우의 수 
	// ex A 0 0 0 0 0 1 1 1 1 2 2 2 3 3 4
	//    B 1 2 3 4 5 2 3 4 5 3 4 5 4 5 5
	static int[] matchTeamA = new int[15];
	static int[] matchTeamB = new int[15];

	static boolean isOk;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				matchTeamA[cnt] = i;
				matchTeamB[cnt] = j;
				cnt++;
			}
		}
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			isOk = false;

			int winCnt = 0;
			int drawCnt = 0;
			int loseCnt = 0;

			for (int a = 0; a < 6; a++) {
				win[a] = Integer.parseInt(st.nextToken());
				draw[a] = Integer.parseInt(st.nextToken());
				lose[a] = Integer.parseInt(st.nextToken());

				winCnt += win[a];
				drawCnt += draw[a];
				loseCnt += lose[a];
			}

			if (winCnt + drawCnt + loseCnt != 30) {
				isOk = false;
			} else {
				solve(0);
			}
			sb.append(isOk ? "1" : "0").append(" "); 
		}
		System.out.println(sb.toString());
	}

	private static void solve(int idx) {
		if (isOk)
			return;

		if (idx == 15) {
			isOk = true;
			return;
		}

		int teamA = matchTeamA[idx];
		int teamB = matchTeamB[idx];

		//위에서 경기할 팀을 가져온 걸로 경기해보기

		//A팀이 이겼을 때
		if (win[teamA] > 0 && lose[teamB] > 0) {
			win[teamA]--;
			lose[teamB]--;

			solve(idx + 1);

			win[teamA]++;
			lose[teamB]++;
		}

		//B팀이 이겼을 때
		if (win[teamB] > 0 && lose[teamA] > 0) {
			win[teamB]--;
			lose[teamA]--;

			solve(idx + 1);

			win[teamB]++;
			lose[teamA]++;
		}

		//비겼을 때
		if (draw[teamA] > 0 && draw[teamB] > 0) {
			draw[teamA]--;
			draw[teamB]--;

			solve(idx + 1);
			
			draw[teamA]++;
			draw[teamB]++;
		}
	}

}
