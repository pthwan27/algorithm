package com.programmers;

import java.util.*;
import java.io.*;

public class 기지국설치 {
	public static void main(String[] args) {
		int answer = solution(16, new int[] {9}, 2);
	}

	public static int solution(int n, int[] stations, int w) {
		int answer = 0;
		
		Queue<Integer> stationQ = new LinkedList<>();
		for(int i = 0; i < stations.length; i++) {
			stationQ.offer(stations[i]);
		}
		
		int curCnt = 0;
		
		//범위 curCnt-w ~ curCnt + w
		while(curCnt < n) {
			//설치된 기지국 안에 있으면
			if(!stationQ.isEmpty() && curCnt >= stationQ.peek() - w) {
				curCnt = stationQ.poll() + w + 1;
			}else {
				curCnt += w*2+1;
				answer++;
			}
		}

		// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
		System.out.println(answer);

		return answer;
	}
}
