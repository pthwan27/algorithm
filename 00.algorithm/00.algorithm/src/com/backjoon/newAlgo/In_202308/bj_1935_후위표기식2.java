package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_1935_후위표기식2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		String inputStr = in.readLine();

		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}

		Stack<Double> numStack = new Stack<>();
		for (int i = 0; i < inputStr.length(); i++) {
			char cur = inputStr.charAt(i);

			if ('A' <= cur && 'Z' >= cur) {
				double n = arr[cur - 'A'];
				numStack.push(n);
			} else {
				double n1 = numStack.pop();
				double n2 = numStack.pop();
				double result = 0.0;

				switch (cur) {
				case '+':
					result = n2 + n1;
					break;
				case '-':
					result = n2 - n1;
					break;
				case '*':
					result = n2 * n1;
					break;
				case '/':
					result = n2 / n1;
					break;
				}
				numStack.push(result);
			}
		}
		
		System.out.printf("%.2f", numStack.pop());

	}
}
