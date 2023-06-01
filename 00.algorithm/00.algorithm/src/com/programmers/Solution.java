package com.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class Solution {
	static HashMap<String, Integer> menuMap;

	static int[] maxArr;

	public String[] solution(String[] orders, int[] course) {
		// map -> key : 메뉴 구성 , value : 갯수 
		menuMap = new HashMap<>();
		maxArr = new int[course.length];

		ArrayList<String> resultList = new ArrayList<>();

		for (int i = 0; i < orders.length; i++) {
			char[] charArr = orders[i].toCharArray();

			//알파벳순서로 정렬시키기
			Arrays.sort(charArr);

			orders[i] = String.valueOf(charArr);

			int maxIdx = 0;
			for (int menuLen : course) {
				comb(0, 0, "", orders[i], menuLen, maxIdx++);

			}
		}

		for (String key : menuMap.keySet()) {
			for (int i = 0; i < course.length; i++) {
				if (course[i] == key.length() && maxArr[i] > 1 && menuMap.get(key) == maxArr[i]) {
					resultList.add((key));
				}
			}
		}

		//길이정렬
		Collections.sort(resultList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {

				return o1.length() - o2.length();
			}
		});

		//알파벳순 정렬
		Collections.sort(resultList);

		System.out.println(resultList);

		String[] answer = resultList.toArray(new String[0]);
		return answer;
	}

	//메뉴 조합 구하기
	private void comb(int cur, int start, String newMenu, String order, int menuLen, int idx) {
		if (cur == menuLen) {
			if (menuMap.containsKey(newMenu)) {
				menuMap.put(newMenu, menuMap.get(newMenu) + 1);
			} else {
				menuMap.put(newMenu, 1);
			}

			maxArr[idx] = Math.max(maxArr[idx], menuMap.get(newMenu));
			return;
		}

		for (int i = start; i < order.length(); i++) {
			comb(cur + 1, i + 1, newMenu + order.charAt(i), order, menuLen, idx);
		}

	}
}