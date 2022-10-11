package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 서로 다른 무게추, 양팔저울
 * 
 * 오른쪽 무게의 총합 < 왼쪽 무게의 총합
 * 
 * 순서가 있기 때문에 순열을 먼저 구하고, 그 순열의 부분집합을 구해야함.
 * 부분집합을 구할 때 모든 추를 다 썻을 때 계산되게 함.
 * @author park
 *
 */
public class D4_3234_준환이의양팔저울_박태환 {

	static int N;
	static int[] weightArr;
	static int weightSum = 0;

	static boolean[] isSelected;
	static int[] nums;
	static int count;
	
	//임시
	static int[] temp;
	static boolean[] tempSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// TC수
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 추 개수
			N = Integer.parseInt(in.readLine());
			count = 0;

			// 추마다의 무게 저장
			weightArr = new int[N];
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				weightArr[i] = Integer.parseInt(st.nextToken());
				weightSum += weightArr[i];
			}
			
			isSelected = new boolean[N];
			nums = new int[N];
			perm(0);
			System.out.println("#" + tc + " " + count);
		}

		
	}

	//N 크기 순열을 만들기 
	private static void perm(int cur) {
		if (cur == N) {
			calc(0, 0, 0);
			return;
		}

		//순열생성
		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;
			nums[cur] = weightArr[i];
			perm(cur + 1);
			isSelected[i] = false;
		}
	}

	//부분집합을 만들어서 계산하기
	private static void calc(int cnt, int left, int right) {	
		if(right > left) return; //오른쪽이 왼쪽보다 크다면 빠져나가도록
		
		//위 조건을 통과하고 무게추를 다 계산하면 count++
		if(cnt == N) {
			count++;
			//System.out.println("left :" + left + " " + "right : " + right);
			return;			
		}
		
		//재귀 (부분집합)
		calc(cnt+1, left, right + nums[cnt]);
		calc(cnt+1, left + nums[cnt], right);
	}
	

}
