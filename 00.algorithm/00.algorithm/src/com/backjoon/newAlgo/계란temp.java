package com.backjoon.newAlgo;

import java.io.*;
import java.util.*;

public class 계란temp {
	static class cow implements Comparable<cow> {
		int start, end;

		public cow(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(cow o) {
			if (o.start == this.start) {
				return this.end - o.end;
			} else {
				return this.start - o.start;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		ArrayList<cow> cowList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String[] nums = in.readLine().split(" ");
			cowList.add(new cow(Integer.parseInt(nums[0]), Integer.parseInt(nums[1])));
		}

		Collections.sort(cowList);

		int time = cowList.get(0).start + cowList.get(0).end;

		for (int i = 1; i < N; i++) {
			if (time < cowList.get(i).start) {
				time += cowList.get(i).start - time + cowList.get(i).end;
			} else {
				time += cowList.get(i).end;
			}
		}
		System.out.println(time);
	}
}