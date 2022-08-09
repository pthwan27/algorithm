package com.backjoon.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1158_요세푸스문제 {

	// 요세푸스 문제
	// 1~ N 까지의 사람이 원을 이루고 있다
	// ex 1 2 3 4 5 6 7
	// 이 때 K번째 사람을 제거한다.
	// ex 1 2 3 4 5 6 7
	// 1 2 4 5 6 7 -> 3 제거, 3부터 다시 3번째 사람
	// 1 2 4 5 7 - > 6 제거

	//
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int K = sc.nextInt();

		Queue<Integer> numsQ = new LinkedList<Integer>();

		for (int i = 1; i <= N; i++) {
			numsQ.offer(i);
		}
		
		int cnt = 1;
		sb.append("<");
		while (true) {
			if (numsQ.size() == 1) {
				sb.append(numsQ.poll());
				break;
			}

			if (cnt % K == 0) {
				sb.append(numsQ.poll() + ", ");
				cnt++;
			} else {
				numsQ.offer(numsQ.poll());
				cnt++;
			}
		}
		sb.append(">");

		System.out.println(sb);
	}
}
