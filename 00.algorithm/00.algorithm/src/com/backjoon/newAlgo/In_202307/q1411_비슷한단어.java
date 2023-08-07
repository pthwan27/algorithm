package com.backjoon.newAlgo.In_202307;

import java.io.*;
import java.util.*;

public class q1411_비슷한단어 {
	static int N;

	static String[] wordArr;
	static boolean[] isSelected;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		wordArr = new String[N];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			wordArr[i] = in.readLine();
		}

		count = 0;
		for (int a = 0; a < N; a++) {			
			for (int b = a+1; b < N; b++) {
				String cur = wordArr[a];
				
				//숫자로 바꾼다.
				//HashMap으로?
					

			}
		}
		System.out.println(count);

	}

}
