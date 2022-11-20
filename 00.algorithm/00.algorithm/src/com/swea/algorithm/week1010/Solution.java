package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());

			int[] trees = new int[N];

			int maxLen = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				maxLen = Math.max(maxLen, trees[i]);
			}

			for (int i = 0; i < N; i++) {
				trees[i] = Math.abs(trees[i] - maxLen);
			}

			int w3d = 0;
			int w2d = 0;
			int w1d = 0;
			
			for (int i = 0; i < N; i++) {
				w3d += trees[i] / 3;

				if (trees[i] % 3 == 1) {
					w1d++;
				} 
				
				if (trees[i] % 3 == 2) {
					w2d++;
				}
			}

			int min = Math.min(w1d, w2d);
			
			w1d -= min;
			w2d -= min;
			w3d += min;
			
			if(w1d > 0) {
				w1d = w1d*2-1;
			}
			
			System.out.println(w1d + (w2d*2) + (w3d*2));
			
		}
	}

}
