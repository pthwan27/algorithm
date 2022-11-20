package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_8458_원점으로집합_박태환 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			boolean chk = true;
			int maxLen = -1;
			int div = -1;

			// 입력받으면서 최고길이 구하기
			// 길이 -> 맨해튼거리로 계산
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				int len = Math.abs(x) + Math.abs(y);

				if (i == 0) {
					maxLen = len;
					div = len % 2;
				}

				else {
					if (div != len % 2) {
						chk = false;
					}
					maxLen = Math.max(len, maxLen);
				}
			}

			//최고길이를 가지고 언제도착하는지 계산
			int i = 0;
			if (!chk) {
				i = -1;

			} else {
				while (maxLen > 0) {
					i++;
					maxLen -= i;
				}
				maxLen *= -1;

				if (maxLen % 2 == 1) {
					if (i % 2 == 0) {
						i += 1;
					} else {
						i += 2;
					}
				}
			}

			sb.append("#" + tc + " " + i + "\n");
		}
		System.out.println(sb);
	}

}
