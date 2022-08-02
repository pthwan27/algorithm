package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 박태환
 *
 * 달팽이 숫자. N * N 크기의 배열
 */
public class D2_1954_달팽이숫자 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int [][] arr = new int[N][N];
			int count = 0;
			
			
			/*0,0 / 0,1 / 0,2 / 0,3 / 0,4 / 1,4 / 2,4 / 3,4 / 4,4 / 4,4-1 / 4,4-2 /4,4-3 / 4,4-4 / 4-1,0 / 4-2,0 / 4-3,0
			 *   
			 */
//			0  1  2  3 
//			11 12 13 4
//			10 15 14 5
//			9  8  7  6
				  
			while(count > N*N) {
				int temp = N;
				int a = 0; 
				int b = 0;
				
				if(count % 4 == 0) {
					
				} 
				arr[a][b] = count++;				
			}
		}

	}

}
