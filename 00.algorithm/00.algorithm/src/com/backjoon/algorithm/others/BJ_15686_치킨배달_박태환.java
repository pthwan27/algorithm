package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_15686_치킨배달_박태환 {

	/**
	 * 크기가 N * N N*N 은 빈 칸, 치킨집, 집 중 하나 이다. r행 c열, r/c는 1부터 시작한다. 치킨거리 - 집과 가장 가까운
	 * 치킨집 사이의 거리 이고 도시의 치킨거리는 모든 집의 치킨 거리의 합 이다.
	 * 
	 * 이 때 도시에 M개 만큼의 치킨집만 있어야 할 때, 어떤 M개를 골라야 도시의 치킨거리가 가장 낮게 나오는지 확인하고, 그 가장 낮은
	 * 도시의 치킨거리를 출력.
	 * 
	 */
	static int N; // 배열 크기
	static int M; // 있어야하는 치킨집 갯수

	static int[][] map; // 맵 정보 저장

	static ArrayList<int[]> chickenMap; // 치킨집의 위치 저장

	static int[] SelectNum; // 조합 저장 (중복 x)

	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		chickenMap = new ArrayList<>();

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 1; c <= N; c++) {
				int inputNum = Integer.parseInt(st.nextToken());

				if (inputNum == 2) {
					chickenMap.add(new int[] { r, c });
				}
				map[r][c] = inputNum;
			}
		}
//		print(chickenMap); // 입력확인 완료

		// 조합 뽑기 (치킨 집을 M개 만큼 뽑을 수 있는 경우의 수)
		SelectNum = new int[M];
		comb(0, 0); // 현재위치, 출발점
		System.out.println(result);
	}

	private static void comb(int cur, int start) {
		if (cur == M) {
//			System.out.println(Arrays.toString(SelectNum));
			// 구해 둔 조합을 돌면서, 최소거리 찾기
			result = Math.min(result, calcLen(SelectNum));
			return;
		}

		for (int idx = start; idx < chickenMap.size(); idx++) {
			SelectNum[cur] = idx;
			comb(cur + 1, idx + 1);
		}
	}

	private static int calcLen(int[] selectNum) {
		int minDistance = Integer.MAX_VALUE;
		int Sum = 0;

		for (int r = 1; r < map.length; r++) {
			for (int c = 1; c < map[r].length; c++) {
				// 선택해둔 치킨집 2개와의 거리 비교하고, 작은걸로 더하기
				if (map[r][c] == 1) {
					for (int i = 0; i < M; i++) {
						// 치킨집과의 거리 비교하면서 가장 짧은 거리 MinSum에 저장
						// System.out.println(chickenMap.get(selectNum[0])[0] + " " +
						// chickenMap.get(selectNum[0])[1]);
						int chickenR = chickenMap.get(selectNum[i])[0];
						int chickenC = chickenMap.get(selectNum[i])[1];

						int distance = Math.abs(chickenR - r) + Math.abs(chickenC - c);
						minDistance = Math.min(minDistance, distance);
					}
					Sum += minDistance;
					minDistance = Integer.MAX_VALUE;
				}
			}
		}
		return Sum;
	}

}
