package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구간 합 구하기 5
 * @author 박태환
 *
 * 수의 개수 N, 합을 구해야하는 횟수 M
 * N만큼의 배열만들고,
 * 합을 구해야하는 범위를 받아 합을 출력.
 *
 */
public class BJ_11659_구간합구하기4 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//N만큼의 배열 ( 합의 범위가 1부터 시작하기 때문에 한 칸 늘려서 만듦 )
		int [] numArr = new int[N+1];
		
		int startPoint = 0;
		int endPoint = 0;
		
		st = new StringTokenizer(br.readLine());
		
		//배열에 값 입력
		for(int idx = 1; idx < numArr.length; idx++) {
			numArr[idx] = Integer.parseInt(st.nextToken());
		}
		//입력 끝.		
		
		/*시간초과 오류 발생*/
		//M만큼 반복하며 tc 수행
//		for(int cnt = 0; cnt < M; cnt++) {
//			st = new StringTokenizer(br.readLine());
//			//합을 구할 시작지점 ,  끝지점
//			startPoint = Integer.parseInt(st.nextToken());
//			endPoint = Integer.parseInt(st.nextToken());
//			
//			//결과 저장할 곳
//			int Sum = 0;
//			
//			for(int i = startPoint; i <= endPoint; i++) {
//				Sum += numArr[i];
//			}
//			System.out.println(Sum);
//		}		
		
		/*숫자배열들의 합 구해놓고, EndPoint까지 구한 값 - StartPoint까지 구한 값으로 출력*/
		int [] SumArr = new int[N+1];
		SumArr[1] = numArr[1];
		for(int i = 2; i < numArr.length; i++) {
			SumArr[i] = SumArr[i-1] + numArr[i]; 
		}
		
		for(int cnt = 0; cnt < M; cnt++) {
			st = new StringTokenizer(br.readLine());
			//합을 구할 시작지점 ,  끝지점
			startPoint = Integer.parseInt(st.nextToken());
			endPoint = Integer.parseInt(st.nextToken());
			
			/*2번째 시간초과 오류 발생*
			 -> StringBuilder 사용 
			*/
			//System.out.println(SumArr[endPoint] - SumArr[startPoint-1]);
			sb.append(SumArr[endPoint] - SumArr[startPoint-1] + "\n");	
		}
		System.out.println(sb);
	}
}
