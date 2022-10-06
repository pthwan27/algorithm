package com.backjoon.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9205_맥주마시면서걸어가기 {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N;

	static Pos startPos;
	static Pos endPos;

	static ArrayList<Pos> posList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			posList = new ArrayList<>();

			// pos(각 지점의 위치 저장 -> 시작, 편의점 , 도착점)
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				posList.add(new Pos(x, y));
			}

			//시작, 도착 위치 세팅
			startPos = posList.get(0);
			endPos = posList.get(posList.size() - 1);

			System.out.println(bfs() ? "happy" : "sad");
		}

	}

	private static boolean bfs() {
		boolean[] isVisited = new boolean[posList.size()];

		Queue<Pos> bfsQ = new ArrayDeque<>();
		bfsQ.offer(startPos);

		while (!bfsQ.isEmpty()) {
			Pos nowPos = bfsQ.poll();

			int nx = nowPos.x;
			int ny = nowPos.y;

			//도착지를 갈 수 있따면 빠져나감
			if (isInRange(nx, ny, endPos.x, endPos.y)) {
				return true;
			}

			//다음 갈 수 있는 편의점 검색
			for (int i = 1; i < N + 2; i++) {
				if (isInRange(nx, ny, posList.get(i).x, posList.get(i).y) && !isVisited[i]) {
					isVisited[i] = true;
					bfsQ.add(new Pos(posList.get(i).x, posList.get(i).y));
				}
			}
		}
		return false;

	}
	//맥주 (20캔 내에 갈 수 있는 곳인지 확인)
	private static boolean isInRange(int nx, int ny, int x, int y) {
		if (Math.abs(nx - x) + Math.abs(ny - y) <= 1000)
			return true;
		else
			return false;
	}

}
