package com.backjoon.algorithm.day0810_RotateArr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기4 {

	static int N, M, K;
	static int[][] map;
	//계산에 사용할 배열
	static int[][] calcMap;

	//r, c, s 저장한 리스트
	static ArrayList<int[]> calcArr;

	//수열 저장하기 위한 배열
	static boolean[] isSelected;
	static int[] permNums;

	// 하 , 우, 상 , 좌
	static int[] dx = new int[] { 1, 0, -1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };

	static int dir = 0;

	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 연산횟수
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 1; c <= M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		calcArr = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			int[] inputTemp = new int[3];
			for (int j = 0; j < 3; j++) {
				inputTemp[j] = Integer.parseInt(st.nextToken());
			}
			calcArr.add(inputTemp);
		}

		// 중복을 막기 위해
		isSelected = new boolean[K];

		// 순열을 저장하기 위해 생성
		permNums = new int[K];

		// 순열만들기 시작
		perm(0);

		System.out.println(result);
	}

	private static void perm(int cur) {
		if (cur == K) {
			calcMap = new int[N + 1][M + 1];
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= M; c++) {
					calcMap[r][c] = map[r][c];
				}
			}

			for (int i = 0; i < permNums.length; i++) {
				rotateArr(permNums[i]);
			}

			result = Math.min(result, calcLineSum(calcMap));
			// 순열을 가져왔을 때 -> 가져온 값으로 회전연산을 순서대로 돌리는 로직을 구현

			return;
		}

		for (int i = 0; i < K; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;
			permNums[cur] = i;

			perm(cur + 1);
			isSelected[i] = false;
		}

	}

	private static void rotateArr(int perNum) {
		// calcArr r, c, s 가 담긴 배열
		int r = calcArr.get(perNum)[0];
		int c = calcArr.get(perNum)[1];
		int s = calcArr.get(perNum)[2];

		// System.out.println(r +" " + c + " " + s);
		// 시작 위치
		int startR = r - s;
		int startC = c - s;
		int endR = r + s;
		int endC = c + s;

		// 반복 횟수 - > ex 3, 4, 2연산일 때
		// 1, 2 ~ 5 ,6 -> 5,5 크기의 정사각형
		// 2번 반복해야함 - > r+s - (r-s) / 2 =
		// 2s / 2 = s
		int rotateCount = s;
		while (rotateCount-- > 0) {

			int startNum = calcMap[startR][startC];

			// 현재 칸 위치, 다음 칸 위치
			int nowR = startR;
			int nowC = startC;

			dir = 0;
			while (dir < 4) {
				int nextR = nowR + dx[dir];
				int nextC = nowC + dy[dir];

				// 다음 값이 회전 배열 범위안에 있는지 확인
				if (nextR >= startR && nextC >= startC && nextR <= endR && nextC <= endC) {
					calcMap[nowR][nowC] = calcMap[nextR][nextC];

					nowR = nextR;
					nowC = nextC;
				} else {
					dir++;
				}
			}
			calcMap[startR][startC + 1] = startNum;

			startR++;
			startC++;
			endR--;
			endC--;
		}
	}

	private static int calcLineSum(int[][] map) {
		int Sum = Integer.MAX_VALUE;
		for (int r = 1; r < map.length; r++) {
			int lineSum = 0;
			for (int c = 1; c < map[r].length; c++) {
				lineSum += map[r][c];
			}
			Sum = Math.min(Sum, lineSum);
		}
		return Sum;
	}

	private static void printMap(int[][] map2) {
		for (int i = 0; i < map2.length; i++) {
			System.out.println(Arrays.toString(map2[i]));
		}
		System.out.println();
	}

}
