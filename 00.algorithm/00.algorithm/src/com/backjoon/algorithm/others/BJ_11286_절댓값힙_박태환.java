package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BJ_11286_절댓값힙_박태환 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(in.readLine());

		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (Math.abs(o1[1]) == Math.abs(o2[1])) {
					return Integer.valueOf(o1[1]).compareTo(Integer.valueOf(o2[1]));
				} else {
					return Integer.valueOf(Math.abs(o1[1])).compareTo(Integer.valueOf(Math.abs(o2[1])));
				}
			}
		});

		for (int i = 0; i < N; i++) {
			int inputNum = Integer.parseInt(in.readLine());

			if (inputNum != 0) {
				queue.offer(new int[] { i, inputNum });
			} else {
				if (queue.size() == 0) {
					sb.append(0 + "\n");
				} else {
					sb.append(queue.poll()[1] + "\n");
				}
			}
		}
		System.out.println(sb);
	
	}

}
