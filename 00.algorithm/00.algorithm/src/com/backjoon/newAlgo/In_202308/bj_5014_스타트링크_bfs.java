package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_5014_스타트링크_bfs {

	static int F, S, G, U, D;

	static boolean isPossible;
	static int minResult = Integer.MAX_VALUE;

	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		F = Integer.parseInt(inputs[0]);
		S = Integer.parseInt(inputs[1]);
		G = Integer.parseInt(inputs[2]);
		U = Integer.parseInt(inputs[3]);
		D = Integer.parseInt(inputs[4]);

		isSelected = new boolean[F + 1];
		bfs();

		System.out.println(isPossible ? minResult : "use the stairs");
	}

	public static void bfs() {
		Queue<int[]> bfsQ = new LinkedList<>();

		bfsQ.offer(new int[] { 0, S });
		isSelected[S] = true;

		while (!bfsQ.isEmpty()) {
			int[] cur = bfsQ.poll();

			if (cur[1] == G) {
				minResult = Math.min(minResult, cur[0]);
				isPossible = true;
			}

			int nextUpFloor = cur[1] + U;
			int nextDownFloor = cur[1] - D;

			if (nextUpFloor <= F && !isSelected[nextUpFloor]) {
				isSelected[nextUpFloor] = true;
				bfsQ.offer(new int[] { cur[0] + 1, nextUpFloor });
			}

			if (nextDownFloor >= 1 && !isSelected[nextDownFloor]) {
				isSelected[nextDownFloor] = true;
				bfsQ.offer(new int[] { cur[0] + 1, nextDownFloor });
			}

		}

	}
}
