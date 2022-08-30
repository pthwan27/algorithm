package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_4013_특이한자석_박태환 {

	static ArrayList<Integer>[] magnetList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(in.readLine());

			// 자석정보 담아두기
			magnetList = new ArrayList[4];
			for (int i = 0; i < 4; i++) {
				magnetList[i] = new ArrayList<>();
			}

			for (int i = 0; i < 4; i++) {
				ArrayList temp = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int a = 0; a < 8; a++) {
					temp.add(Integer.parseInt(st.nextToken()));
				}
				magnetList[i] = temp;
			}

			// 명령수만큼 반복
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());

				// 선택한 톱니 위치
				int magnetNum = Integer.parseInt(st.nextToken()) - 1;
				// 선택한 톱니의 회전 방향
				int dir = Integer.parseInt(st.nextToken()); // 1 시계, -1 반시계

				Queue<int[]> rotationQ = new LinkedList<int[]>();
				// 시작위치는 무조건 바꿈.
				rotationQ.offer(new int[] { magnetNum, dir });

				// 바꿔야 하는 톱니 검사
				// 왼쪽 먼저 확인
				int left = magnetList[magnetNum].get(6);
				int count = magnetNum - 1;
				int d1 = dir;
				while (count >= 0) {
					if (magnetList[count].get(2) != left) {
						d1 *= -1;
						left = magnetList[count].get(6);
						rotationQ.offer(new int[] { count, d1 });
					} else {
						break;
					}
					count--;
				}

				// 오른쪽톱니 검사
				int right = magnetList[magnetNum].get(2);
				count = magnetNum + 1;
				d1 = dir;
				while (count < 4) {
					if (magnetList[count].get(6) != right) {
						d1 *= -1;
						right = magnetList[count].get(2);
						rotationQ.offer(new int[] { count, d1 });
					} else {
						break;
					}
					count++;
				}

				// 바꿔야할 톱니들 처리
				while (!rotationQ.isEmpty()) {
					int[] temp = rotationQ.poll();
					rotation(temp[0], temp[1]);
				}
			}

			// 결과 계산
			int result = 0;
			for (int i = 0; i < 4; i++) {
				if (magnetList[i].get(0) == 1) {
					result += Math.pow(2, i);
				}
			}

			sb.append("#" + tc + " " + result).append("\n");
		}
		System.out.println(sb);
	}

	private static void rotation(int magnetNum, int dir) {
		if (dir == 1) {
			int temp = magnetList[magnetNum].get(7);
			magnetList[magnetNum].remove(7);
			magnetList[magnetNum].add(0, temp);
		} else if (dir == -1) {
			int temp = magnetList[magnetNum].get(0);
			magnetList[magnetNum].remove(0);
			magnetList[magnetNum].add(temp);
		}
	}
}
