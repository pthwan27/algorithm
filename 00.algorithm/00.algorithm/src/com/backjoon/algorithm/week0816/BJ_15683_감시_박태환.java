package com.backjoon.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV {
	// 위치, 어떤 cctv인지 (1~5번)
	int x;
	int y;
	int type;

	public CCTV(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}

public class BJ_15683_감시_박태환 {

	static int N, M;

	static int[][] map;

	static ArrayList<CCTV> cctvList;

	// 결과
	static int minResult = Integer.MAX_VALUE;

	// 0은 빈칸, 6은 벽, 1~5는 cctv
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cctvList = new ArrayList<>();

		// cctvList에 , cctv 번호, 좌표 저장
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < M; c++) {
				String temp = st.nextToken();

				map[r][c] = Integer.parseInt(temp);

				if (map[r][c] >= 1 && map[r][c] <= 5) {
					// cctv에 r,c, 몇번 cctv인지 저장
					cctvList.add(new CCTV(r, c, map[r][c]));
				}
			}
		}

		dfs(0, map, cctvList);
		System.out.println(minResult);
	}

	private static void dfs(int cur, int[][] map, ArrayList<CCTV> List) {
		// 사이즈 같아지면(cctv마지막 까지 왔을 때) 값을 비교해서 출력해야함
		if (cur == List.size()) {
			minResult = Math.min(minResult, zeroCnt(map));
//			print(map);
//			System.out.println(zeroCnt(map));
			return;
		}

		// 현 cctv의 좌표, 몇번cctv인지 가져옴
		int r = List.get(cur).x;
		int c = List.get(cur).y;
		int cctvType = List.get(cur).type;

		int[][] changedMap;

		// cctv 번호에 따라 다르게 수행
		switch (cctvType) {
		// 4번 한 방향씩 4번 확인
		case 1:
			// 바뀐 맵을 저장해서, 그 맵을 돌린다. -> 그래야 모든 경우가 나오기 때문에.
			changedMap = copyMap(map); // copyMap은 깊은복사를 위해 만듦.
			// 오른쪽부터 -> 아래까지 4방향
			checkRight(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkLeft(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkUp(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkDown(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			break;
		// 2번 2방향씩 2번만 확인
		case 2:
			changedMap = copyMap(map);
			checkRight(changedMap, r, c);
			checkLeft(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkUp(changedMap, r, c);
			checkDown(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			break;

		// 3번 2방향씩 , 4번 확인
		case 3:
			changedMap = copyMap(map);
			checkUp(changedMap, r, c);
			checkRight(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkRight(changedMap, r, c);
			checkDown(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkLeft(changedMap, r, c);
			checkDown(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkLeft(changedMap, r, c);
			checkUp(changedMap, r, c);
			dfs(cur + 1, changedMap, List);
			break;

		// 4번 3방향씩, 4번확인
		case 4:
			changedMap = copyMap(map);
			checkUp(changedMap, r, c);
			checkRight(changedMap, r, c);
			checkDown(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkRight(changedMap, r, c);
			checkDown(changedMap, r, c);
			checkLeft(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkDown(changedMap, r, c);
			checkLeft(changedMap, r, c);
			checkUp(changedMap, r, c);
			dfs(cur + 1, changedMap, List);

			changedMap = copyMap(map);
			checkLeft(changedMap, r, c);
			checkUp(changedMap, r, c);
			checkRight(changedMap, r, c);
			dfs(cur + 1, changedMap, List);
			break;

		// 한번만 4방향 1번
		case 5:
			changedMap = copyMap(map);
			checkUp(changedMap, r, c);
			checkRight(changedMap, r, c);
			checkLeft(changedMap, r, c);
			checkDown(changedMap, r, c);
			dfs(cur + 1, changedMap, List);
			break;
		}
	}

	private static void checkRight(int[][] backupMap, int r, int c) {
		for (int i = c + 1; i < M; i++) {
			// 벽일 땐 빠져나가고
			if (backupMap[r][i] == 6)
				break;

			// 0이 아닐 때(다른cctv, #일 때)
			if (backupMap[r][i] != 0)
				continue;

			backupMap[r][i] = 9;
		}
	}

	private static void checkLeft(int[][] backupMap, int r, int c) {
		for (int i = c - 1; i >= 0; i--) {
			// 벽일 땐 빠져나가고
			if (backupMap[r][i] == 6)
				break;

			// 0이 아닐 때(다른cctv, #일 때)
			if (backupMap[r][i] != 0)
				continue;

			backupMap[r][i] = 9;
		}
	}

	private static void checkUp(int[][] backupMap, int r, int c) {
		for (int i = r - 1; i >= 0; i--) {
			// 벽일 땐 빠져나가고
			if (backupMap[i][c] == 6)
				break;

			// 0이 아닐 때(다른cctv, #일 때)
			if (backupMap[i][c] != 0)
				continue;

			backupMap[i][c] = 9;
		}
	}

	private static void checkDown(int[][] backupMap, int r, int c) {
		for (int i = r + 1; i < N; i++) {
			// 벽일 땐 빠져나가고
			if (backupMap[i][c] == 6)
				break;

			// 0이 아닐 때(다른cctv, #일 때)
			if (backupMap[i][c] != 0)
				continue;

			backupMap[i][c] = 9;
		}
	}

	// 0 카운트 함수
	private static int zeroCnt(int[][] map) {
		int zeroCnt = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 0) {
					zeroCnt++;
				}
			}
		}
		return zeroCnt;

	}

	// 깊은 복사
	private static int[][] copyMap(int[][] map) {
		int[][] tmpMap = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		return tmpMap;
	}

	private static void print(int[][] map2) {
		for (int i = 0; i < map2.length; i++) {
			for (int j = 0; j < map2[i].length; j++) {
				System.out.print(map2[i][j] + " ");
			}
			System.out.println();
		}

	}
}
