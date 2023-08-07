
import java.io.*;
import java.util.*;

public class Main {
	static int N;

	static int[] numArr;

	static int[] result;

	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		numArr = new int[3];
		for (int i = 0; i < 3; i++) {
			numArr[i] = i;
		}

		result = new int[N];
		perm(0);

		System.out.println(answer);
	}

	private static void perm(int cnt) {
		if (cnt == N) {
			int num =  ArrayToNumber(result);
			
			if ((int)Math.log10(num)+1 == N) {
				if (num / 3 >= 1 && num % 3 == 0) {
					answer++;
				}
			}
			return;
		}

		for (int i = 0; i < 3; i++) {
			result[cnt] = numArr[i];
			perm(cnt + 1);
		}

	}

	private static int ArrayToNumber(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]);
		}
		String numStr = sb.toString();
		return Integer.parseInt(numStr);
	}
}