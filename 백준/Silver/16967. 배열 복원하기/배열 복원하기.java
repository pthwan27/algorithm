import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		int H = Integer.parseInt(inputs[0]);
		int W = Integer.parseInt(inputs[1]);
		int X = Integer.parseInt(inputs[2]);
		int Y = Integer.parseInt(inputs[3]);

		int[][] map = new int[H + X][W + Y];
		int[][] answer = new int[H + X][W + Y];

		for (int r = 0; r < H + X; r++) {
			inputs = in.readLine().split(" ");
			for (int c = 0; c < W + Y; c++) {
				map[r][c] = answer[r][c] = Integer.parseInt(inputs[c]);
			}
		}

		int bH = H + X;
		int bW = W + Y;

		for (int r = 0; r < H + X; r++) {
			for (int c = 0; c < W + Y; c++) {
				//두 배열에 모두 포함
				if (r >= 0 && r < H && c >= 0 && c < W && r >= X && r < bH && c >= Y && c < bW) {
					answer[r][c] = map[r][c] - answer[r - X][c - Y];
				}

			}
		}

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				sb.append(answer[r][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
