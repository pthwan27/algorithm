import java.util.*;
import java.io.*;

public class Main {

	static int N, r, c;

	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		r = Integer.parseInt(inputs[1]);
		c = Integer.parseInt(inputs[2]);

		int size = (int) Math.pow(2, N);

		find(r, c, size);

		System.out.println(result);
	}

	static void find(int r, int c, int size) {
		if (size == 1) {
			return;
		}

		int divSize = size / 2;

		if (r < divSize && c < divSize) {
			find(r, c, divSize);
		} else if (r < divSize && c >= divSize) {
			result += Math.pow(divSize, 2) * 1;
			find(r, c - divSize, divSize);
		} else if (r >= divSize && c < divSize) {
			result += Math.pow(divSize, 2) * 2;
			find(r - divSize, c, divSize);
		} else if (r >= divSize && c >= divSize) {
			result += Math.pow(divSize, 2) * 3;
			find(r - divSize, c - divSize, divSize);
		}
	}
}
