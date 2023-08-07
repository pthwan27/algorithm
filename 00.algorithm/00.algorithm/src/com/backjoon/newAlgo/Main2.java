package com.backjoon.newAlgo;

import java.io.*;
import java.util.*;

public class Main2 {
	static class report implements Comparable<report> {
		int d, t;

		public report(int d, int t) {
			this.d = d;
			this.t = t;
		}

		public int compareTo(report o) {
			if (o.t == this.t) {
				return this.d-o.d;
			} else {
				return o.t - this.t;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		report[] reportArr = new report[N];

		for (int i = 0; i < N; i++) {
			String inputNums[] = in.readLine().split(" ");
			reportArr[i] = new report(Integer.parseInt(inputNums[0]), Integer.parseInt(inputNums[1]));
		}
		Arrays.sort(reportArr);
		
		int period = reportArr[0].t;
		
		for(int i = 0; i < N; i++) {
			
		}

	}
}
