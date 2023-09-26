package com.backjoon.newAlgo.In_202309;

import java.io.*;
import java.util.*;

public class bj_1091_카드섞기 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		int[] P = new int[N];
		int[] S = new int[N];

		int[] originshuffleCardList = new int[N];
		int[] shuffleCardList = new int[N];

		String[] pStr = in.readLine().split(" ");
		String[] sStr = in.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(pStr[i]);
			S[i] = Integer.parseInt(sStr[i]);

			//각 카드에 맞는 플레이어 설정
			shuffleCardList[i] = originshuffleCardList[i] = i % 3;
		}

		boolean isFinished = true;

		int result = 0;
		while (isFinished) {

			isFinished = false;

			for (int i = 0; i < N; i++) {
				//카드 주인이 아니면
				if (shuffleCardList[i] != P[i]) {
					//카드 서플 
					shuffleCardList = shuffleCards(shuffleCardList, S);
					result++;
					isFinished = true;
					break;
				}
			}

			//맨 처음 카드와 바뀐 카드를 비교해서 그대로면 -1 출력
			if (isFinished && check(originshuffleCardList, shuffleCardList)) {
				System.out.println(-1);
				return;
			}
		}

		System.out.println(result);
	}

	public static int[] shuffleCards(int[] shuffleCardList, int[] s) {
		int[] newshuffleCardList = new int[N];
		for (int i = 0; i < N; i++) {
			newshuffleCardList[i] = shuffleCardList[s[i]];
		}
		return newshuffleCardList;
	}

	public static boolean check(int[] originshuffleCardList, int[] shuffleCardList) {
		for (int i = 0; i < N; i++) {
			if (originshuffleCardList[i] != shuffleCardList[i]) {
				return false;
			}
		}
		return true;
	}
}
