package com.jungol.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class refrigerator2{
	int minTemp,maxTemp;
	public refrigerator2( int minTemp, int maxTemp) {
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
}

public class jo_1828_냉장고 {

	static int refrCnt = 0;
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());

		Queue<refrigerator2> tempQueue = new PriorityQueue<refrigerator2>(new Comparator<refrigerator2>() {
			@Override
			public int compare(refrigerator2 o1, refrigerator2 o2) {
				if (o1.minTemp == o2.minTemp) {
					return Integer.valueOf(o2.maxTemp).compareTo(Integer.valueOf(o1.maxTemp));
				}
				return Integer.valueOf(o1.minTemp).compareTo(Integer.valueOf(o2.minTemp));
			}
		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			tempQueue.offer(new refrigerator2(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		refrCnt = 0;
		// 처음 물질의 온도
		int lowTemp = Integer.MIN_VALUE;
		int highTemp = Integer.MIN_VALUE;

		while (!tempQueue.isEmpty()) {
			// 다음 물질 온도
			refrigerator2 refriger = tempQueue.poll();
			
			if(refriger.minTemp > highTemp) {
				refrCnt++;
				lowTemp = refriger.minTemp;
				highTemp = refriger.maxTemp;
				continue;
			}
			
			if(refriger.maxTemp < highTemp) {
				highTemp = refriger.maxTemp;
			}
		}	
		
		System.out.println(refrCnt);
	}

}
