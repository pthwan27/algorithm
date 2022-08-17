package com.swea.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
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

public class SW_5644_무선충전 {

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
			
			System.out.println(result);
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
		while (!(bRouteQue.isEmpty() && aRouteQue.isEmpty())) {
			chargePossibleCheck(goAr, goAc, goBr, goBc);

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

			// System.out.println("AR " + goAr + " AC " + goAc + " BR " + goBr + " BC " +
			// goBc);
		}

	}

	private static void chargePossibleCheck(int goAr, int goAc, int goBr, int goBc) {
		// goA, goB와 충전기들과의 거리를 구해서 C안에 있는지 확인하고
		// 충전량 더해주기
		// 두 명다 들어가있는지 확인해야 하기 떄문에 boolean으로 확인하기
		// 두개 충전기에 들어가 있는지도 확인해야함 -> 어떻게?..
		// chargeList 크기의 충전기를 만들기

		boolean[] AchargeCheck = new boolean[chargerList.size()];
		boolean[] BchargeCheck = new boolean[chargerList.size()];
		int AchargeCnt = 0;
		int BchargeCnt = 0;

		for (int i = 0, size = chargerList.size(); i < size; i++) {
			// A가 첫번째 충전기 거리안에 있을 때
			if (Math.abs(chargerList.get(i).y - goAr) + Math.abs(chargerList.get(i).x - goAc) <= chargerList.get(i).c) {
				AchargeCheck[i] = true;
				System.out.println("A =" + goAr + " " + goAc);
				AchargeCnt++;
				// 충전기의 충전량 ++ 해줘야함
			}

			if (Math.abs(chargerList.get(i).y - goBr) + Math.abs(chargerList.get(i).x - goBc) <= chargerList.get(i).c) {
				BchargeCheck[i] = true;
				System.out.println("B =" + goBr + " " + goBc);
				BchargeCnt++;
				// 충전기의 충전량 ++ 해줘야함
			}
		}

		for (int i = 0; i < chargerList.size(); i++) {
			// 두개가 같은 곳에 있을 경우
			if (AchargeCheck[i] && BchargeCheck[i] && goAr == goBr) {
				result += chargerList.get(i).p;
			} else if (AchargeCheck[i] && BchargeCheck[i] && AchargeCnt != BchargeCnt) {
				for (int j = 0; j < 3; j++) {
					if (AchargeCheck[i] || BchargeCheck[i]) {
						result += chargerList.get(i).p;
					}
				}
			} else if (AchargeCheck[i]) {
				result += chargerList.get(i).p;
			} else if (BchargeCheck[i]) {
				result += chargerList.get(i).p;
			}
		}
	}

}
