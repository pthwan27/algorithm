package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1244_스위치켜고끄기 {	
	// N - > 스위치 개수 // switchArr -> 스위치 담은 배열
	// 스위치 상태 집어넣기,
	// C - > 학생 수 (학생 수 만큼 받아야함.)	
	// 성별과, 학생을 2차원 배열에 넣어둔다 -> 어차피 순서대로 처리하기 때문에

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] switchArr = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		// SwitchArr 집어넣기
		for (int i = 0; i < N; i++) {
			switchArr[i] = Integer.parseInt(st.nextToken());
		}

		int C = Integer.parseInt(in.readLine());
		int[][] stdArr = new int[C][2];
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < stdArr[i].length; j++) {
				stdArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 입력 끝 
		// solution
		// 남자인 경우 -> stdArr[i][0] -> 1인 경우, 자기가 받은 번호의 배수 스위치 switching
		// 여자인 경우 -> stdArr[i][0] -> 2인 경우, 좌,우 스위치를 보면서 대칭 이룰때 까지,
		// -> 이 때 , 0, length면 끝나도록 해야함.

		for (int i = 0; i < C; i++) {
			if (stdArr[i][0] == 1) {
				switchArr = manSol(switchArr, stdArr[i][1]);
			}

			if (stdArr[i][0] == 2) {
				switchArr = womanSol(switchArr, stdArr[i][1]);
			}
		}

		for (int i = 1; i <= switchArr.length; i++) {			
			System.out.print(switchArr[i-1] + " ");
			
			if(i != 0 && i % 20 == 0) {
				System.out.println();
			}
		}
	}

	private static int[] manSol(int[] switchArr, int startPosition) {
		for (int i = 0; i < switchArr.length; i++) {
			if ((i + 1) % startPosition == 0) {
				if (switchArr[i] == 1) {
					switchArr[i] = 0;
				} else {
					switchArr[i] = 1;
				}
			}
		}
		return switchArr;
	}

	private static int[] womanSol(int[] switchArr, int startPosition) {
		// 0->1, 1->0으로 바꿀 범위 정하기 위해 생성
		int changeStart = startPosition - 1;
		int changeEnd = startPosition - 1;

		for (int i = 0; i < switchArr.length; i++) {
			// switch 크기 안에서
			if (changeStart - 1 >= 0 && changeEnd + 1 < switchArr.length) {
				// 대칭을 이룰 때 하나씩 늘려줌
				if (switchArr[changeStart - 1] == switchArr[changeEnd + 1]) {
					changeStart = changeStart - 1;
					changeEnd = changeEnd + 1;
				} else {
					break;
				}
			}
		}

		for (int i = 0; i <= changeEnd - changeStart; i++) {
			if (switchArr[changeStart + i] == 0) {
				switchArr[changeStart + i] = 1;
			} else {
				switchArr[changeStart + i] = 0;
			}
		}

		return switchArr;
	}

}