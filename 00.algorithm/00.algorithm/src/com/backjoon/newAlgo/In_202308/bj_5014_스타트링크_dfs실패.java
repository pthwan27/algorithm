package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_5014_스타트링크_dfs실패 {

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
		dfs(0, S);
		
		
		System.out.println(isPossible ? minResult : "use the stairs");
	}

	public static void dfs(int count, int floor) {
		if (floor < 1 || floor > F)
			return;
		
		if (isSelected[floor])
			return;

		if (floor == G) {
			minResult = Math.min(count, minResult);
			isPossible = true;
		}

		isSelected[floor] = true;

		int nextUpFloor = floor + U;
		int nextDownFloor = floor - D;

		if (nextUpFloor <= F) 
			dfs(count + 1, nextUpFloor);

		if (nextDownFloor >= 1)
			dfs(count + 1, nextDownFloor);

		isSelected[floor] = false;
	}
}
