package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 
 * @author 박태환
 * 배열에 높이를 담고, 결과로 높이 차이만 출력하면 되기때문에 
 * 1. 오름차순 정렬으로 정렬하고 ,   
 * 2. 덤프 횟수 만큼
 * 3. 가장 큰것 -> 가장 작은것으로 하나 옮김 (arrlength -> 0자리로)
 */
public class D3_1208_Flatten {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		
		for(int tc = 1; tc <= 10; tc++) {
			
			// N -> 덤프횟수 , 높이가 담긴 가로길이는 항상 100
			int N = Integer.parseInt(in.readLine());
			int[] dumpArr = new int[100];
			
			StringTokenizer st = new StringTokenizer(in.readLine());			
			
			for(int index = 0; index < dumpArr.length; index++) {
				dumpArr[index] = Integer.parseInt(st.nextToken());
			}
			//입력 끝.

			//오름차순 정렬
			Arrays.sort(dumpArr);
					
			while(dumpArr[dumpArr.length-1] - dumpArr[0] > 1 && N > 0) {			
				dumpArr[dumpArr.length-1] -= 1; 
				dumpArr[0] += 1;				
				
				//오름차순 정렬
				Arrays.sort(dumpArr);
				N--;
			}
			
			System.out.println("#" + tc + " " + (dumpArr[dumpArr.length-1] - dumpArr[0]));
			
		}
	}
}
