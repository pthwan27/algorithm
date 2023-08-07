package com.backjoon.newAlgo.In_202307;

import java.util.*;
import java.io.*;

public class q6987_월드컵_실패 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = 6;

		ArrayList<Integer> winList = new ArrayList<>();
		ArrayList<Integer> loseList = new ArrayList<>();
		

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			winList.clear();
			loseList.clear();
			
			int count = 0;

			for (int a = 0; a < 6; a++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());

				count += win + draw + lose;

				if (win != 0) {
					if (loseList.contains(win)) {
						loseList.remove(loseList.indexOf(win));
					} else {
						winList.add(win);
					}
				}

				if (lose != 0) {
					if (winList.contains(lose)) {
						winList.remove(winList.indexOf(lose));
					} else {
						loseList.add(lose);
					}
				}
			}
			if (count == 30 && winList.size() == 0 && loseList.size() == 0) {
				sb.append("1").append(" ");
			}else {
				sb.append("0").append(" ");
			}
		}
		System.out.println(sb.toString());
	}

}
