import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int i = 0; i < T; i++) {
			String[] inputs = in.readLine().split(" ");

			int[] start = new int[2];
			int[] end = new int[2];

			start[0] = Integer.parseInt(inputs[0]);
			start[1] = Integer.parseInt(inputs[1]);
			end[0] = Integer.parseInt(inputs[2]);
			end[1] = Integer.parseInt(inputs[3]);

			int n = Integer.parseInt(in.readLine());

			int sCnt = 0;
			int eCnt = 0;

			for (int j = 0; j < n; j++) {
				inputs = in.readLine().split(" ");

				int x = Integer.parseInt(inputs[0]);
				int y = Integer.parseInt(inputs[1]);
				int r = Integer.parseInt(inputs[2]);

				if (Math.hypot(start[0] - x, start[1] - y) < r) {
					sCnt++;
				}
				if (Math.hypot(end[0] - x, end[1] - y) < r) {
					eCnt++;
				}

				if (Math.hypot(start[0] - x, start[1] - y) < r && Math.hypot(end[0] - x, end[1] - y) < r) {
					sCnt--;
					eCnt--;
				}

			}
			sb.append(sCnt + eCnt + "\n");
		}
		System.out.println(sb);
	}

}
