
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(in.readLine());
			ArrayList<String> numList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				numList.add(in.readLine());
			}

			// String타입의 List, Array에 Sort를 하면 기존 숫자정렬과는 다른 결과
			// 숫자 정렬은 1, 9, 10, 44, 34, 4 => 1, 4, 9, 10, 34, 44
			// 문자열 정렬은 다 가나 다라 마 => 가나 다 다라 마 처럼 정렬되듯이
			// 1, 9, 10, 44, 34, 4 => 1 10 34 4 44 9
		
			Collections.sort(numList);
			boolean check = true;

			for (int a = 0; a < n-1; a++) {
				if (numList.get(a+1).startsWith(numList.get(a))) {
					sb.append("NO").append("\n");
					check = !check;
					break;

				}

			}
			if (check) {
				sb.append("YES").append("\n");
			}
		}
		System.out.println(sb);

	}
}
