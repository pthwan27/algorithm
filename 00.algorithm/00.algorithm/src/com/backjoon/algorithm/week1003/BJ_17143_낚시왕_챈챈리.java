package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_17143_낚시왕_챈챈리 {
	static class Shark {
		int id; // 아이디
		int row;
		int col;
		int speed; // 속력
		int dir; // 이동방향
		int size; // 크기
		
		public Shark() {
		}

		public Shark(int id, int row, int col, int speed, int distance, int size) {
			super();
			this.id = id;
			this.row = row;
			this.col = col;
			this.speed = speed;
			this.dir = distance;
			this.size = size;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Shark [id=");
			builder.append(id);
			builder.append(", row=");
			builder.append(row);
			builder.append(", col=");
			builder.append(col);
			builder.append(", speed=");
			builder.append(speed);
			builder.append(", distance=");
			builder.append(dir);
			builder.append(", size=");
			builder.append(size);
			builder.append("]");
			return builder.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Shark other = (Shark) obj;
			if (id != other.id)
				return false;
			return true;
		}

	}

	private static int R; // 맵 행 크기
	private static int C; // 맵 열 크기
	private static int M; // 상어 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Shark[][] map = new Shark[R + 1][C + 1];
		LinkedList<Shark> sharks = new LinkedList<>();

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(m, row, col, speed, distance, size));
			map[row][col] = sharks.get(m);
		}

		int result = 0;
		// 열을 옮겨가며 낚시 시작
		//print(map);
		for (int c = 1; c <= C; c++) {
			// 행 확인하기
			for (int r = 1; r <= R; r++) {
				if (map[r][c] != null) {
					// !!!!!!!!!!!상어 잡음!!!!!!!!!!!!!!!
					System.out.println("잡힘: " +map[r][c]);

					// 상어 result 에 추가하기
					result += map[r][c].size;
					// 위치 null로 처리해주기
					sharks.set(map[r][c].id, null);
					map[r][c] = null;
					break;
				}
			}
			System.out.println("낚시 후");
			print(map);

			
			
			// 상어 이동
			moveSharks(map, sharks);
//			System.out.println("이동");
//			print(map);
			// 상어들 싸움
			

			map = makeNewMap(sharks);
			System.out.println("싸움");
			print(map);

		}
		System.out.println(result);
	}

	// 상어 이동시키는 함수
	public static void moveSharks(Shark[][] map, LinkedList<Shark> sharks) {
		for (int i = 0; i < sharks.size(); i++) {
			Shark shark = sharks.get(i);
			whereMove(shark);
		}
	}


	public static void whereMove(Shark shark) {
//		System.out.println("id: " + shark.id + " 움직이기 전~ : " + shark);
		int num = 0;
		int changeCount = 0;
		if(shark == null) {
			return;
		}
		switch (shark.dir) {
		case 1: // 위
			num = (R - 1) - shark.row ;
			changeCount = (shark.speed + num) / (R - 1);
			// 방향이 바뀌는지 체크
			if (changeCount == 0) { // 0이면 움직이기만 하면 됨
				shark.row = shark.row - shark.speed;
			} else if (changeCount % 2 == 1) { // 홀수이면 방향이 바뀜
				shark.dir = 2;
				shark.row = 2 + (shark.speed - ((R - 1) * changeCount - num));
			} else { // 짝수이면 방향은 그대로
				shark.row = R - 1 - (shark.speed - ((R - 1) * changeCount - num));
			}
			break;
		case 2: // 아래
			num = shark.row - 2;
			changeCount = (shark.speed + num) / (R - 1);
			// 방향이 바뀌는지 체크
			if (changeCount == 0) { // 0이면 움직이기만 하면 됨
				shark.row = shark.row + shark.speed;
			} else if (changeCount % 2 == 1) { // 홀수이면 방향이 바뀜
				shark.dir = 1;
				shark.row = R - 1 - (shark.speed - ((R - 1) * changeCount - num));
			} else { // 짝수이면 방향은 그대로
				shark.row = 2 + (shark.speed - ((R - 1) * changeCount - num));
			}
			break;
		case 3: // 오른쪽
			num = shark.col - 2;
			// 방향을 언제 바꾸는가 ?
			// 몇번 방향을 바꾸는지 보자
			changeCount = (shark.speed + num) / (C - 1);
			// 방향이 바뀌는지 체크
			if (changeCount == 0) { // 0이면 움직이기만 하면 됨
				shark.col = shark.col + shark.speed;
			} else if (changeCount % 2 == 1) { // 홀수이면 방향이 바뀜
				// 방향이 바뀌면 C-1부터 시작
				shark.dir = 4;
				shark.col = C - 1 - (shark.speed - ((C - 1) * changeCount - num));
			} else { // 짝수이면 방향은 그대로
				shark.col = 2 + (shark.speed - ((C - 1) * changeCount - num));
			}
			break;
		case 4: // 왼쪽
//			num = shark.col - (C - 1);
			num = (C - 1) -shark.col ;
			// 방향을 언제 바꾸는가 ? (C-1) * n - (col-(C-1)) 번째마다 바꾼다.
			// 몇번 방향을 바꾸는지 보자
			changeCount = (shark.speed + num) / (C - 1);
			// 방향이 바뀌는지 체크
			if (changeCount == 0) { // 0이면 움직이기만 하면 됨
				shark.col = shark.col - shark.speed;
			} else if (changeCount % 2 == 1) { // 홀수이면 방향이 바뀜
				// 방향이 바뀌면 C-1부터 시작
				shark.dir = 3;
				shark.col = 2 + (shark.speed - ((C - 1) * changeCount - num));
			} else { // 짝수이면 방향은 그대로
				shark.col = C - 1 - (shark.speed - ((C - 1) * changeCount - num));
			}
			//System.out.println(shark.col);
			break;
		}
//		System.out.println("id: " + shark.id + " 움직이기 후~ : " + shark);
	}
	
	public static Shark[][] makeNewMap(LinkedList<Shark> sharks) {
		Shark[][] newMap = new Shark[R + 1][C + 1];
		for(int i = 0; i < sharks.size(); i++) {
			if(sharks.get(i)== null) {
				continue;
			}
			Shark shark = sharks.get(i);
			int row = shark.row;
			int col = shark.col;
			if(newMap[row][col] == null) {
				newMap[row][col] = shark;				
			} else {
				Shark otherShark = newMap[row][col];
				if(shark.size > otherShark.size) {
					System.out.println("먹힘: " + otherShark);
					newMap[row][col] = shark;
					sharks.set(otherShark.id, null);
				} else {
					System.out.println("먹힘: " + shark);
					newMap[row][col] = shark;
					sharks.set(shark.id, null);
				}
			}
		}
		return newMap;
	}

	public static void print(Shark[][] map) {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (map[r][c] == null) {
					System.out.print(". ");
				} else {
					switch(map[r][c].id) {
					case 0:
						System.out.print("A ");
						break;
					case 1:
						System.out.print("B ");
						break;
					case 2:
						System.out.print("C ");
						break;
					case 3:
						System.out.print("D ");
						break;
					case 4:
						System.out.print("E ");
						break;
					case 5:
						System.out.print("F ");
						break;
					case 6:
						System.out.print("G ");
						break;
					case 7:
						System.out.print("H ");
						break;
					}
					
				}
			}
			System.out.println();
		}
		System.out.println("==========================");
	}

}