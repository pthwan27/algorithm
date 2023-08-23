
import java.io.*;
import java.util.*;

public class Main {

	static class robot {
		int r, c, dir, num;

		public robot(int r, int c, int dir, int num) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.num = num;
		}
	}

	static int A, B, N, M;
	static int[][] map;
	static robot[] robotArr;

	static String result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		A = Integer.parseInt(inputs[0]);
		B = Integer.parseInt(inputs[1]);

		inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		map = new int[B + 1][A + 1];

		robotArr = new robot[N + 1];

		for (int i = 1; i <= N; i++) {
			inputs = in.readLine().split(" ");

			int c = Integer.parseInt(inputs[0]);
			int r = Integer.parseInt(inputs[1]);

			char dir = inputs[2].charAt(0);

			map[B - r + 1][c] = i;
			robotArr[i] = new robot(B - r + 1, c, changeDir(dir), i);
		}

		result = "OK";
		// 명령
		for (int i = 0; i < M; i++) {
			inputs = in.readLine().split(" ");

			int robot = Integer.parseInt(inputs[0]);
			char order = inputs[1].charAt(0);
			int orderCnt = Integer.parseInt(inputs[2]);

			if (!movingRobot(robot, order, orderCnt)) {
				break;
			}
		}

		System.out.println(result);
	}

	//N W S E;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	private static boolean movingRobot(int robot, char order, int orderCnt) {
		robot curRobot = robotArr[robot];
		int r = curRobot.r;
		int c = curRobot.c;
		int robotNum = curRobot.num;
		int dir = curRobot.dir;

		for (int i = 0; i < orderCnt; i++) {
			switch (order) {
			case 'L':
				dir++;
				if (dir > 3) {
					dir = 0;
				}
				break;

			case 'R':
				dir--;
				if (dir < 0) {
					dir = 3;
				}
				break;

			case 'F':
				curRobot = robotArr[robot];
				r = curRobot.r;
				c = curRobot.c;
				robotNum = curRobot.num;

				int nextR = r + dr[dir];
				int nextC = c + dc[dir];
				//벽에 충돌
				if (checkCrashIntoTheWall(nextR, nextC)) {
					result = "Robot " + robotNum + " crashes into the wall";
					return false;
				}

				//다른 로봇과 충돌
				int nextRobotNum = checkCrashIntoRobot(nextR, nextC, robotNum);
				if (nextRobotNum != robotNum) {
					result = "Robot " + robotNum + " crashes into robot " + nextRobotNum;
					return false;
				}

				map[nextR][nextC] = map[r][c];
				map[r][c] = 0;

				robotArr[robotNum].r = nextR;
				robotArr[robotNum].c = nextC;

				break;
			}
		}

		robotArr[robotNum].dir = dir;

		return true;

	}

	private static boolean checkCrashIntoTheWall(int nr, int nc) {
		return nr < 1 || nr > B || nc < 1 || nc > A;
	}

	private static int checkCrashIntoRobot(int nr, int nc, int curRobotNum) {

		if (map[nr][nc] != 0 && map[nr][nc] != curRobotNum) {
			return map[nr][nc];
		} else {
			return curRobotNum;
		}
	}

	private static int changeDir(char dir) {
		switch (dir) {
		case 'N':
			return 0;
		case 'W':
			return 1;
		case 'S':
			return 2;
		case 'E':
			return 3;
		}
		return -1;
	}
}
