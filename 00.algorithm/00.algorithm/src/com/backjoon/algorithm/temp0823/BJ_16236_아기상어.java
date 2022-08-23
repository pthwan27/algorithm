package com.backjoon.algorithm.temp0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BabyShark {
	int r, c, size;

	public BabyShark(int r, int c, int size) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
	}

	@Override
	public String toString() {
		return "BabyShark [r=" + r + ", c=" + c + ", size=" + size + "]";
	}
}

class Fish {
	int r, c, size;

	public Fish(int r, int c, int size) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Fish [r=" + r + ", c=" + c + ", size=" + size + "]";
	}
}

public class BJ_16236_아기상어 {

	static ArrayList<Fish> fishList = new ArrayList<>();
	static BabyShark babyShark;

	static int[][] map;

	static int N;

	static int eatFishCount;

	// 0 빈칸, 1~6 물고기의 크기, 9 아기상어 위치
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		// 아기상어 정보
		babyShark = new BabyShark(0, 0, 0);

		// 맵 입력
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				// 맵에 값 입력하면서, 아기상어, 물고기 정보 담아두기
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 9) {
					babyShark.r = r;
					babyShark.c = c;
					babyShark.size = 2;
				}

				if (map[r][c] > 0 && map[r][c] < 7) {
					Fish fish = new Fish(r, c, map[r][c]);
					fishList.add(fish);
				}
			}
			// 입력확인
			// System.out.println(Arrays.toString(map[r]));
		}
		// 입력 끝

		eatFishCount = 0;
		Simulation();
	}

	private static void Simulation() {
		// 잡아먹을 물고기가 있을 때 까지 반복하고
		// 시간을 하나 씩 늘려준다.
		int count = 0;

		while (isPossible()) {
			// 1마리 이상일 때는 거리가 가장 가까운 곳을 찾는다.
			// 거리를 찾는다 ( 잡아먹을 수 있는 물고기 까지의 )
			int[] temp = findFish(babyShark.r, babyShark.c, 1);
			count += temp[2];

			System.out.println(Arrays.toString(temp));

			eatFishCount++;

			if (babyShark.size == eatFishCount) {
				babyShark.size++;
				eatFishCount = 0;
			}

			for (int i = 0; i < fishList.size(); i++) {
				if (temp[0] == fishList.get(i).r && temp[1] == fishList.get(i).c) {
					map[temp[0]][temp[1]] = 9;
					map[babyShark.r][babyShark.c] = 0;
					babyShark.r = temp[0];
					babyShark.c = temp[1];
					fishList.remove(i);
				}
			}

			for (int i = 0; i < map.length; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();

		}

		System.out.println(count);

	}

	// 상, 좌, 우, 하
	static int[] dr = new int[] { -1, 0, 0, 1 };
	static int[] dc = new int[] { 0, -1, 1, 0 };

	private static int[] findFish(int r, int c, int count) {
		Queue<int[]> babysharkQ = new LinkedList<int[]>();
		boolean[][] isSelected = new boolean[N][N];
		isSelected[r][c] = true;
		babysharkQ.offer(new int[] { r, c, count });

		ArrayList<int[]> list = new ArrayList<>();

		int mR = 0, mC = 0, mCnt = Integer.MAX_VALUE;
		boolean outcheck = false;

		while (!babysharkQ.isEmpty() && !outcheck) {
			int[] pollArr = babysharkQ.poll();
			isSelected[pollArr[0]][pollArr[1]] = true;

			for (int i = 0; i < 4 && !outcheck; i++) {
				int goR = pollArr[0] + dr[i];
				int goC = pollArr[1] + dc[i];

				// 범위 안에 있을 때
				if (isIn(goR, goC) && isSelected[goR][goC] == false) {
					// 빈 칸이거나, 아기상어와 물고기의 크기가 같은 경우
					if (map[goR][goC] == 0 || map[goR][goC] == babyShark.size) {
						babysharkQ.offer(new int[] { goR, goC, pollArr[2] + 1 });
					}
					// 다음 칸이 아기상어 사이즈보다 클 경우
					else if (map[goR][goC] > babyShark.size) {
						continue;
					}
					// 1,2,3,4,5,6 인 물고기 중에, 아기상어보다 작은 크기일 경우
					else if (map[goR][goC] < babyShark.size) {

						mR = goR;
						mC = goC;
						mCnt = pollArr[2];
						list.add(new int[] { mR, mC, mCnt });

						break;

					}
//					System.out.println("goR : " + goR + " / " + "goC : " + goC + "/ count" + pollArr[2]);
				}
			}
		}

		mR = list.get(0)[0];
		mC = list.get(0)[1];
		mCnt = list.get(0)[2];
		for (int i = 0; i < list.size(); i++) {
//			// 거리가 짧은 거로
//			if (mCnt > list.get(i)[2]) {
//				mR = list.get(i)[0];
//				mC = list.get(i)[1];
//				mCnt = list.get(i)[2];				
//			}
//			
//			// 더 높은곳에 있으면
//			if (mR > list.get(i)[0]) {
//				mR = list.get(i)[0];
//				mC = list.get(i)[1];
//				mCnt = list.get(i)[2];				
//			}		
//			
//			//높이가 같을 때 
//			if(mR == list.get(i)[0]) {
//				//왼쪽에 있는걸로
//				if(mC > list.get(i)[1]) {
//					mR = list.get(i)[0];
//					mC = list.get(i)[1];
//					mCnt = list.get(i)[2];
//				}
//			}
			

		}

		return new int[] { mR, mC, mCnt };
	}

	// 잡아먹을 물고기가 있는 지 확인
	private static boolean isPossible() {
		for (int i = 0; i < fishList.size(); i++) {
			if (babyShark.size > fishList.get(i).size) {
				return true;
			}
		}
		return false;
	}

	// N*N 범위를 나가는지 확인
	private static boolean isIn(int goR, int goC) {
		if (goR >= 0 && goC >= 0 && goR < N && goC < N) {
			return true;
		}
		return false;
	}
}
