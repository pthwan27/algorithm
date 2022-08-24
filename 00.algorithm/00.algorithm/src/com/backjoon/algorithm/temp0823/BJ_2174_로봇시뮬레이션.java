package com.backjoon.algorithm.temp0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Robot {
	int c, r;
	char dir;

	public Robot(int r, int c, char dir) {
		super();
		this.c = c;
		this.r = r;
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "Robot [r=" + r + ",c=" + c + ", dir=" + dir + "]";
	}

}

class Command {
	int target, repeatNum;
	char command;

	public Command(int target, char command, int repeatNum) {
		super();
		this.target = target;
		this.command = command;
		this.repeatNum = repeatNum;
	}

	@Override
	public String toString() {
		return "Command [target=" + target + ", repeatNum=" + repeatNum + ", command=" + command + "]";
	}

}

public class BJ_2174_로봇시뮬레이션 {

	static int[][] robotMap;

	static ArrayList<Robot> robotList;
	static ArrayList<Command> CommandList;

	static int A;
	static int B;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		// A - > c , B -> r
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		robotMap = new int[B + 1][A + 1];

		// N -> 로봇들의 개수, N만큼 좌표 주어짐
		// M -> 명령 개수 -> 명령을 내리는 로봇 번호, 명령의 종류 , 명령의 반복 회수
		// 명령의 종류 : L -> 왼쪽으로 90도 회전, R 오른쪽으로 90도 회전, F 앞으로 한칸 움직인다.
		
		st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		robotList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = B + 1 - Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			Robot robot1 = new Robot(r, c, dir);

			robotList.add(robot1);

			robotMap[r][c] = i + 1;
		}

		CommandList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int target = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);
			int repeatNum = Integer.parseInt(st.nextToken());
			Command command1 = new Command(target, command, repeatNum);

			CommandList.add(command1);
		}
		// 입력 끝

		simulation();
	}

	// 상, 우, 하, 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	private static void simulation() {
		boolean outCheck = true; //게임이 끝나면 -> 오류 발생시 나가도록

		// 반복 명령의 갯수만큼 반복하고
		for (int a = 0, size = CommandList.size(); a < size && outCheck; a++) {
			
			// 어떤 로봇을 타켓으로 할지, 어떤 명령어를 실행할지 가져오기
			int target = CommandList.get(a).target;
			char command = CommandList.get(a).command;
			
			// findDir -> N,E,W,S 를 숫자로 바꿔줌
			int dir = findDir(robotList.get(target-1).dir);

			// 로봇의 위치를 가져오고
			int goR = robotList.get(target-1).r;
			int goC = robotList.get(target-1).c;


			// 명령의 반복 횟수만큼 명령을 수행한다, outCheck를 통해 오류 메시지가 발생하면 이후 검사 X
			for (int b = CommandList.get(a).repeatNum; b > 0 && outCheck; b--) {
				// 명령어에 따라 다르게 수행할 수 있도록 switch문 작성
				switch (command) {
				case 'L':
					if (dir == 0) {
						dir = 3;
					} else {
						dir--;
					}

					break;
				case 'R':
					if (dir == 3) {
						dir = 0;
					} else {
						dir++;
					}
					break;
				case 'F':
					goR = goR + dr[dir];
					goC = goC + dc[dir];

					// 벗어날 경우
					if (!isIn(goR, goC, dir)) {
						System.out.println("Robot " + target + " crashes into the wall");
						outCheck = false;
						break;
					}
					
					// 부딪힐 경우
					if (robotMap[goR][goC] != 0) {
						System.out.println("Robot " + target + " crashes into robot " + robotMap[goR][goC]);
						outCheck = false;
						break;
					}


					break;
				}
			}
			// 무사히 통과했다면 , target의 정보 -> robot의 정보를 바꿔준다!
			if (outCheck) {
				char temp = ' ';
				switch (dir) {
				case 0:
					temp = 'N';
					break;

				case 1:
					temp = 'E';
					break;

				case 2:
					temp = 'S';
					break;

				case 3:
					temp = 'W';
					break;
				}
				
				//맵에 저장해둔 로봇의 위치를 바꿔준다.
				robotMap[robotList.get(target-1).r][robotList.get(target-1).c] = 0;
				robotList.set(target - 1, new Robot(goR, goC, temp));
				robotMap[goR][goC] = target;
			}
		}

		//오류 발생하지 않고 통과했다면
		if (outCheck) {
			System.out.println("OK");
		}
	}

	// N E W S - > 숫자로 바꿔줌
	private static int findDir(char dir) {
		int temp = 0;
		switch (dir) {
		case 'N':
			temp = 0;
			break;

		case 'E':
			temp = 1;
			break;

		case 'S':
			temp = 2;
			break;

		case 'W':
			temp = 3;
			break;
		}
		return temp;
	}

	// 명령어가 F일 때 범위 나가는지 확인
	private static boolean isIn(int goR, int goC, int dir) {
		if (goR >= 1 && goC >= 1 && goR <= B && goC <= A) {
			return true;
		}
		return false;
	}
}
