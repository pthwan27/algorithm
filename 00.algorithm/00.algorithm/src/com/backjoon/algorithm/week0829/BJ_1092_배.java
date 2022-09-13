package com.backjoon.algorithm.week0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1092_배 {

	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> cranelist = new ArrayList<>();
		ArrayList<Integer> boxlist = new ArrayList<>();

		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			cranelist.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(cranelist, Collections.reverseOrder());

		M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < M; i++) {
			boxlist.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(boxlist, Collections.reverseOrder());

		int result = 0;
		// 박스를 다 못 옮기는 경우
		if (cranelist.get(0) < boxlist.get(0)) {
			result = -1;
		} else {

			int size = boxlist.size();
			int minBox = boxlist.get(size-1);

			while (size > 0) {
				int boxCnt = 0;
				for (int i = 0; i < cranelist.size() && size > 0;) {
					if (cranelist.get(i) >= boxlist.get(boxCnt)) {
						i++;
						boxlist.remove(boxCnt);
						size--;
					}
					else if (cranelist.get(i) >= minBox && cranelist.get(i) < boxlist.get(boxCnt)) {
						boxCnt++;						
					}
					else {
						break;
					}
					
				}
				result++;
			}
		}

		System.out.println(result);
	}

}
