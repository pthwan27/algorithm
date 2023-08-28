import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static ArrayList<Long> decNumList;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		decNumList = new ArrayList<>();

		if (N < 10) {
			System.out.println(N);
		} else if (N > 1022) {
			System.out.println(-1);
		} else {
			for (long i = 0; i <= 9; i++) {
				decNumList.add(i);
			}

			for (long i = 1; i <= 9; i++) {
				dfs(i);
			}

			Collections.sort(decNumList);

			System.out.println(decNumList.get(N));

		}
	}

	private static void dfs(long cur) {
		if (cur > 987654321) {
			return;
		}

		for (int i = 0; i < cur % 10; i++) {
			long nextNum = Long.parseLong(String.valueOf(cur) + String.valueOf(i));
			decNumList.add(nextNum);
			dfs(nextNum);
		}
	}

}
