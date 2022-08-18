package com.swea.algorithm.week0816;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class D5_1247_최적경로_박태환 {

	//고객 좌표들 저장
	static ArrayList<Point> customerAdr;
	//결과
	static int result;
	
	// 회사 좌표, 집 좌표
	static Point officeAdr;
	static Point HomeAdr;
	
	//순열정보 저장
	static int[] nums;
	//순열 중복 체크
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());	
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			customerAdr = new ArrayList<>();
			result = Integer.MAX_VALUE;
			
			officeAdr = new Point();
			officeAdr.x = Integer.parseInt(st.nextToken());
			officeAdr.y = Integer.parseInt(st.nextToken());
			
			HomeAdr = new Point();
			HomeAdr.x = Integer.parseInt(st.nextToken());
			HomeAdr.y = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {				
				customerAdr.add(new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			
			nums = new int[customerAdr.size()];
			isSelected = new boolean[customerAdr.size()];
			
			AdrPermutation(0);
			
			System.out.println("#" + tc + " " + result);
		}
	}

	private static void AdrPermutation(int cur) {
		if(cur == customerAdr.size()) {
//			System.out.println(Arrays.toString(nums));
// 			계산하기		
			result = Math.min(result, calcDistance(nums));
			
			return;
		}
		
		for(int i = 0; i < customerAdr.size(); i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			nums[cur] = i;
			AdrPermutation(cur+1);
			isSelected[i] = false;
		}
		
		
	}

	private static int calcDistance(int[] AdrPermList) {
		int curX = officeAdr.x;
		int curY = officeAdr.y;
		
		int sum = Math.abs(curX - customerAdr.get(AdrPermList[0]).x) + Math.abs(curY - customerAdr.get(AdrPermList[0]).y);
		
		curX = customerAdr.get(AdrPermList[0]).x;
		curY = customerAdr.get(AdrPermList[0]).y;
		
		for(int i = 1; i < AdrPermList.length; i++) {
			sum += Math.abs(curX - customerAdr.get(AdrPermList[i]).x) + Math.abs(curY - customerAdr.get(AdrPermList[i]).y);
			curX = customerAdr.get(AdrPermList[i]).x;
			curY = customerAdr.get(AdrPermList[i]).y;			
		}
		
		sum += Math.abs(curX - HomeAdr.x) + Math.abs(curY - HomeAdr.y);		
	
		return sum;
	}

}
