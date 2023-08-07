package com.backjoon.newAlgo.In_202307;

import java.io.*;
import java.util.*;

public class q2258_정육점 {

	static int N, M;
	static meat[] meatArr;

	static class meat implements Comparable<meat> {
		int weight, price;

		public meat(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}

		@Override
		public int compareTo(meat o) {
			if(this.price == o.price) {
				return o.weight - this.weight;
			}
			return this.price - o.price;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		meatArr = new meat[N];
		for (int i = 0; i < N; i++) {
			String[] input = in.readLine().split(" ");
			meatArr[i] = new meat(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}

		Arrays.sort(meatArr);
		
		int prevPrice = 0;
		int totalWeight = 0;
		int totalPrice = 0;
		int minPrice = Integer.MAX_VALUE;
		
		for(meat curMeat : meatArr) {
			totalWeight += curMeat.weight;
			
			if(prevPrice != curMeat.price) {
				prevPrice = totalPrice = curMeat.price;
			}
			//자기보다 싼 가격의 고기만 무료라 
			//동일한 가격이 나왔을때는 계속 더해줘야함
			else {
				totalPrice += curMeat.price;
			}
			
			if(totalWeight >= M) {
				minPrice = Math.min(minPrice, totalPrice);
			}
		}
		
		if(totalWeight < M) {
			System.out.println(-1);
		}else {			
			System.out.println(minPrice);
		}
	}

}
