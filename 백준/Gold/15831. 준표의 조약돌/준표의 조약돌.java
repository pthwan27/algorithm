import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		char[] stoneArr = in.readLine().toCharArray();

		int start = 0, end = 0;
		int wStoneCnt = 0, bStoneCnt = 0;

		int len = 0;
		int maxLen = 0;
		while (end < n) {
			//검은색돌을 줄여야할 때
			if (bStoneCnt > b) {
				if (stoneArr[start] == 'B') {
					bStoneCnt--;
				} else if (stoneArr[start] == 'W') {
					wStoneCnt--;
				}
				len--;
				start++;
			}
			//나아가도 될때 (검은색돌 조건에 만족한 경우)
			else {
				if (stoneArr[end] == 'B') {
					bStoneCnt++;
				} else if (stoneArr[end] == 'W') {
					wStoneCnt++;
				}
				len++;
				end++;
			}

			if (wStoneCnt >= w && bStoneCnt <= b) {
				maxLen = Math.max(maxLen, len);
			}
		}

		System.out.println(maxLen);

	}
}
