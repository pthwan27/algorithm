package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D3_1289_원재의메모리복구하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// TC 갯수  -> T
		int T = Integer.parseInt(in.readLine());
		
		//메모리 정보 담을 배열
		int [] arr = null;
		for (int tc = 1; tc <= T; tc++) {
			String str = in.readLine();
			arr = new int[str.length()];			
			
			//메모리 담기
			for(int i = 0; i < arr.length; i++) {
				arr[i] = str.charAt(i) - '0';				
			}
			
			int count = 0;
			
			//왼쪽부터 오른쪽 다 바뀌기 때문에 , for문 돌면서 확인
			for(int i = 0; i < arr.length; i++) {
				if(arr[i] == 1) {
					for(int j = i; j< arr.length; j++) {
						if(arr[j] == 0) {
							arr[j] = 1;
						}else {
							arr[j] = 0;
						}						
					}					
					count++;
				}
			}			
			System.out.printf("#%d %d%n", tc, count);
		}			
	}
}
