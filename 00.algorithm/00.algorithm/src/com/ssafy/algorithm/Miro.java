package com.ssafy.algorithm;

import java.util.Scanner;

public class Miro {
	//상우하좌
	static int[] dx = {-1,0,1,0};
	static int[] dy = { 0,1,0,-1};
	
	static int Jumpers; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int startX;
			int startY;
			//미로 만들기
			int [][] miro = new int[N][N];
			
			//시작 위치
			startX = sc.nextInt()-1;
			startY = sc.nextInt()-1;
			
			//점퍼갯수
			Jumpers = sc.nextInt();
			
			//점퍼 위치 심기
			int x = 0;
			int y = 0;
			for(int i = 0; i < Jumpers; i++) {
				x = sc.nextInt()-1;
				y = sc.nextInt()-1;
				miro[x][y] = 1;
			}
			
			int MoveCount = sc.nextInt();
			int direction = 0;
			int distance = 0;
			
			
			int [] result = {-1,-1};
			
			for(int i = 0; i < MoveCount; i++) {
				direction = sc.nextInt();
				distance = sc.nextInt();
				
				//상,우,하,좌 -> 1,2,3,4
				result = Move(direction, distance, startX, startY , miro);
				startX = result[0];
				startY = result[1];
				
				if(startX == -1 && startY == -1) {
					break;
				}
			}			
			System.out.printf("#%d %d %d%n", tc, startX+1, startY+1);
		}
	}
	private static int[] Move(int direction, int distance, int startX, int startY ,int [][] Miro) {
		
		//결과값을 전달해주기 위해
		int [] result; 
		
		//이동칸수만큼 확인하기 위해 이동칸수 만큼 반복
		for(int i =0; i < distance; i++) {
			int mx = dx[direction-1];
			int my = dy[direction-1];			
			
			//배열 범위 밖으로 나갔을 때
			if((startX+mx < 0 || startX+mx >= Miro.length)||(startY+my < 0 || startY+my >= Miro.length) ) {
				startX = -1;
				startY = -1;
				break;
			}
			//점퍼 탔을 때
			else if(Miro[startX+mx][startY+my] == 1) {
				startX = -1;
				startY = -1;
				break;
			}		
			
			//정상적으로 움직였을 때
			startX += mx;
			startY += my;
					
		}
		//결과값 반환
		return result = new int[]{startX,startY}; 
	}

}
