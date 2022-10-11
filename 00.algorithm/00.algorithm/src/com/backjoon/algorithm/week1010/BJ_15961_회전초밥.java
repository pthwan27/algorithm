package com.backjoon.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15961_회전초밥 {
	static int N, D, K, C;

	static int res, cnt;

	static int[] eat, dish;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시 수
		D = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		C = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		dish = new int[N];

		eat = new int[D + 1];

		for (int i = 0; i < N; i++) {
			dish[i] = Integer.parseInt(in.readLine());
		}

		//0번째 초밥부터 먹기
		for (int i = 0; i < K; i++) {
			if (eat[dish[i]] == 0) {
				res++;
			}
			eat[dish[i]]++;
		}
		// 쿠폰에 적힌 초밥이 새로운 종류라면
		cnt = res;
		res = eat[C] == 0 ? cnt + 1 : cnt;
		// 이 상태에서 추가로 하나씩 먹어보자    
		belt();

		System.out.println(res);
	}

	//접시를 밀면서 먹는 느낌
	private static void belt() {
		//시작
		int start = K;

		//회전 초밥돌면서 검사
		while (true) {
			//이전에 먹었던 것--
			eat[dish[(start - K) % N]]--;

			if (eat[dish[(start - K) % N]] == 0) {
				cnt--;
			}

			if (eat[dish[start % N]] == 0) {
				cnt++;
			}

			eat[dish[start % N]]++;

			//얼마나 다양한 종류를 먹었나
			//쿠폰에 적힌 초밥이 안먹은 종류라면 +1
			res = Math.max(res, (eat[C] == 0) ? cnt + 1 : cnt);

			//다음초밥
			start++;

			//다돌면 break
			if (start == (N - 1) + K) {
				break;
			}
		}

	}
}