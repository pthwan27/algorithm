package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임_연습 {
	static int[] GyuCard;
	static int[] InCard;

	static boolean[] isSelected;

	static int[] changedCard;

	static int GyuWinCnt = 0;
	static int InWinCnt = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			boolean[] cardCheck = new boolean[19];
			Arrays.fill(cardCheck, true);

			GyuCard = new int[9];
			InCard = new int[9];

			st = new StringTokenizer(in.readLine());

			for (int i = 0; i < 9; i++) {
				GyuCard[i] = Integer.parseInt(st.nextToken());
				cardCheck[GyuCard[i]] = false;
			}
			int cnt = 0;
			for (int i = 1; i < cardCheck.length; i++) {
				if (cardCheck[i]) {
					InCard[cnt++] = i;
				}
			}

			isSelected = new boolean[9];
			changedCard = new int[9];
			cardChange(0);

			System.out.printf("#%d %d %d\n", tc, GyuWinCnt, InWinCnt);

			GyuWinCnt = 0;
			InWinCnt = 0;
		}
	}

	private static void cardChange(int n) {
		if (n == 9) {
			WinCheck();
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (isSelected[i]) {
				continue;
			}
			isSelected[i] = true;
			changedCard[i] = InCard[n];
			cardChange(n + 1);
			isSelected[i] = false;
		}
	}

	private static void WinCheck() {
		int GyuScore = 0;
		int InScore = 0;

		for (int i = 0; i < 9; i++) {
			if (GyuCard[i] > changedCard[i]) {
				GyuScore += (GyuCard[i] + changedCard[i]);
			} else if (GyuCard[i] < changedCard[i]) {
				InScore += (GyuCard[i] + changedCard[i]);
			}
		}

		if (GyuScore > InScore) {
			GyuWinCnt++;
		} else if (GyuScore < InScore) {
			InWinCnt++;
		}
	}
}
