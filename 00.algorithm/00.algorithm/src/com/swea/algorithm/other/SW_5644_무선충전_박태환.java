package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Charger {
	int x;
	int y;
	int c;
	int p;

	public Charger(int x, int y, int c, int p) {
		super();
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}

public class SW_5644_무선충전_박태환 {

	static int M;

	static ArrayList<Charger> chargerList;

	static Queue<Integer> aRouteQue;
	static Queue<Integer> bRouteQue;

	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());
			// BC의 갯수
			int A = Integer.parseInt(st.nextToken());

			int[][] map = new int[11][11];
			aRouteQue = new LinkedList<Integer>();
			bRouteQue = new LinkedList<Integer>();

			st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens()) {
				aRouteQue.offer(Integer.parseInt(st.nextToken()));
			}

			st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens()) {
				bRouteQue.offer(Integer.parseInt(st.nextToken()));
			}

			chargerList = new ArrayList<>();
			// BC의 위치, 범위, 충전량

			// 충전기 정보 담기
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				chargerList.add(new Charger(x, y, c, p));
			}

			Move();

			System.out.println("#" + tc + " " + result);
			result = 0;
		}

	}

	private static void Move() {
		// 한칸씩 이동하면서 충전 가능여부 확인
		// A 는 0,0 B 는 9,9
		// 맨 처음에도 충전가능한지 확인해야함.

		// 사용자가 지도 밖으로 나가는 일은 없음.
		int goAr = 1;
		int goAc = 1;

		int goBr = 10;
		int goBc = 10;

		chargePossibleCheck(goAr, goAc, goBr, goBc);

		for (int i = 0; i < M; i++) {
			switch (aRouteQue.poll()) {
			case 0:
				break;
			case 1:
				goAr--;
				break;

			case 2:
				goAc++;
				break;

			case 3:
				goAr++;
				break;

			case 4:
				goAc--;
				break;
			}

			switch (bRouteQue.poll()) {
			case 0:
				break;
			case 1:
				goBr--;
				break;

			case 2:
				goBc++;
				break;

			case 3:
				goBr++;
				break;

			case 4:
				goBc--;
				break;
			}
			chargePossibleCheck(goAr, goAc, goBr, goBc);
		}

	}

	private static void chargePossibleCheck(int goAr, int goAc, int goBr, int goBc) {
		// goA, goB와 충전기들과의 거리를 구해서 C안에 있는지 확인하고
		// 충전기의 위치 더해주기
		List<Integer> Achargelist = new ArrayList<>();
		List<Integer> Bchargelist = new ArrayList<>();

		for (int i = 0, size = chargerList.size(); i < size; i++) {
			// A가 충전기 거리안에 있을 때
			if (Math.abs(chargerList.get(i).y - goAr) + Math.abs(chargerList.get(i).x - goAc) <= chargerList.get(i).c) {
				Achargelist.add(i);
			}
			// B가 충전기 거리안에 있을 때
			if (Math.abs(chargerList.get(i).y - goBr) + Math.abs(chargerList.get(i).x - goBc) <= chargerList.get(i).c) {
				Bchargelist.add(i);
			}
		}
		
		int max = 0;
		int temp = 0;
		// A,B 둘 다 접속 가능한 충전기가 1개 이상이라면
		if (Achargelist.size() > 0 && Bchargelist.size() > 0) {
			//완탐을 돌리면서 가장 큰 충전양의 조합 구하기
			for (int i : Achargelist) {
				for (int j : Bchargelist) {
					temp = 0;
					// 같은 충전기면 한개만 더하기 == 같은 충전기에 위치한다면
					if (i == j) {
						temp = chargerList.get(i).p;
					}
					// 같은 충전기가 아니면 각각 더하기
					else {
						temp += chargerList.get(i).p;
						temp += chargerList.get(j).p;
					}
					max = Math.max(max, temp);
				}
			}
		}
		// A가 접속가능한 BC가 1개 이상일 때
		else if (Achargelist.size() > 0) {
			for (int i : Achargelist) {
				if (max < chargerList.get(i).p) {
					max = chargerList.get(i).p;
				}
			}
		}

		// B가 접속가능한 BC가 1개 이상일 때
		else if (Bchargelist.size() > 0) {
			for (int i : Bchargelist) {
				if (max < chargerList.get(i).p) {
					max = chargerList.get(i).p;
				}
			}
		}
		// System.out.println("Max : " + max);
		result += max;
		// System.out.println("result : " + result);
	}

}
