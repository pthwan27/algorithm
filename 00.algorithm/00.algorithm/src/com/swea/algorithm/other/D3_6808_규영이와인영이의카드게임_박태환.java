package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_6808_규영이와인영이의카드게임_박태환 {

	static int[] GyuCard;
	static int[] InCard;

	static boolean[] isSelected;

	static int GyuWinCnt;
	static int InWinCnt;

	static int result[];
	static int Cnt;
	
	
	/*
	 * 1~18까지의 카드. 규영이는 주어진 순서대로 낸다 -> 입력값으로 주어짐
	 * 
	 * 높은 수가 적힌 카드를 낸 사람은 두 카드의 합만큼 적수 획득 낮은 수가 적힌 카드를 낸 사람은 점수 얻을 수 없다.
	 *
	 * 
	 * 규영이는 주어진 순서대로 낸다.
	 * 
	 * 규영이가 이기는 경우, 지는경우가 총 몇가지인지 구하기
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(in.readLine());
		
		//Tc 만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			GyuCard = new int[9];
			InCard = new int[9];

			GyuWinCnt = 0;
			InWinCnt = 0;

			st = new StringTokenizer(in.readLine());
			boolean[] SelectedCard = new boolean[19];

			for (int i = 0; i < 9; i++) {				
				GyuCard[i] = Integer.parseInt(st.nextToken());
				SelectedCard[GyuCard[i]] = true;
			}

			int cnt = 0;
			
			for (int i = 1; i <= 18; i++) {
				if (SelectedCard[i] == false) {
					InCard[cnt++] = i;
				}
			}

			//바뀐 카드들을 저장하는 배열
			result = new int[9];
			
			//중복 막기 위해(중복순열 X)
			isSelected = new boolean[9];

			//재귀 시작
			InCardChange(0);

			System.out.println("#" + tc + " " + GyuWinCnt + " " + InWinCnt);
		}

	}

	// 인영이의 카드 순서를 바꿔가면서 계산할 수 있도록
	private static void InCardChange(int c) {
		// 재귀 종료 조건
		if (c == 9) {		
			int GyuScore = 0;
			int InScore = 0;

			// 스코어 계산, result -> 인영이의 바뀐 카드
			for (int i = 0; i < 9; i++) {
				if (GyuCard[i] > result[i]) {
					GyuScore += (GyuCard[i] + result[i]);
				} else if (GyuCard[i] < result[i]) {
					InScore += (GyuCard[i] + result[i]);
				}
			}
			
			//승리 수 계산
			if (GyuScore > InScore) {
				GyuWinCnt++;
			} else if(GyuScore < InScore) {
				InWinCnt++;
			}
			return;
		}
		
		// 카드 중복이 안되게 순열 생성
		for (int i = 0; i < 9; i++) {
			if (isSelected[i] == false) {
				result[c] = InCard[i];
				isSelected[i] = true;
				InCardChange(c + 1);
				isSelected[i] = false;
			}
		}
	}
}
