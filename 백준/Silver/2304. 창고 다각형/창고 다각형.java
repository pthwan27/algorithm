import java.io.*;
import java.util.*;

public class Main {
	static class bar {
		int x, h;

		public bar(int x, int h) {
			this.x = x;
			this.h = h;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		bar[] bars = new bar[N];

		int maxHeight = -1;

		for (int i = 0; i < N; i++) {
			String[] inputs = in.readLine().split(" ");
			int x = Integer.parseInt(inputs[0]);
			int h = Integer.parseInt(inputs[1]);

			maxHeight = Math.max(maxHeight, h);

			bars[i] = new bar(x, h);
		}
		Arrays.sort(bars, (b1, b2) -> b1.x - b2.x);

		int startX = 0;
		int endX = 0;
		for (int i = 0; i < bars.length; i++) {
			if (bars[i].h == maxHeight) {
				startX = bars[i].x;
				break;
			}
		}

		for (int i = bars.length - 1; i >= 0; i--) {
			if (bars[i].h == maxHeight) {
				endX = bars[i].x;
				break;
			}
		}

		int result = 0;
		int curHeight = bars[0].h;
		int curX = bars[0].x;
		for (int i = 1; i < startX; i++) {
			if (curX == startX) {
				break;
			}

			if (curHeight < bars[i].h) {
				result += curHeight * (bars[i].x - curX);
				curX = bars[i].x;
				curHeight = bars[i].h;
			}
		}

		curHeight = bars[bars.length - 1].h;
		curX = bars[bars.length - 1].x;
		for (int i = bars.length - 2; i >= 0; i--) {
			if (curX == endX) {
				break;
			}

			if (curHeight < bars[i].h) {
				result += curHeight * (curX - bars[i].x);
				curX = bars[i].x;
				curHeight = bars[i].h;
			}
		}
		
		result += (endX + 1 - startX) * maxHeight;

		System.out.println(result);

	}
}
