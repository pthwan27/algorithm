import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");
		int width = Integer.parseInt(inputs[0]);
		int height = Integer.parseInt(inputs[1]);

		int N = Integer.parseInt(in.readLine());
		ArrayList<int[]> shopList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");

			shopList.add(new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) });
		}

		inputs = in.readLine().split(" ");
		int[] dong = new int[] { Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) };

		int result = 0;

		for (int i = 0; i < N; i++) {
			int dir = 0;
			int dist = 0;

			switch (dong[0]) {
			case 1:
				dir = shopList.get(i)[0];
				dist = shopList.get(i)[1];
				if (dir == 1) {
					result += Math.abs(dist - dong[1]);
				} else if (dir == 2) {
					result += Math.min(height + dong[1] + dist, height + (width * 2) - dong[1] - dist);
				} else if (dir == 3) {
					result += dong[1] + dist;
				} else {
					result += width - dong[1] + dist;
				}

				break;
			case 2:
				dir = shopList.get(i)[0];
				dist = shopList.get(i)[1];
				if (dir == 1) {
					result += Math.min(height + dong[1] + dist, height + (width * 2) - dong[1] - dist);
				} else if (dir == 2) {
					result += Math.abs(dist - dong[1]);
				} else if (dir == 3) {
					result += dong[1] + (height - dist);
				} else {
					result += width - dong[1] + (height - dist);
				}

				break;
			case 3:
				dir = shopList.get(i)[0];
				dist = shopList.get(i)[1];
				if (dir == 1) {
					result += dong[1] + dist;
				} else if (dir == 2) {
					result += height - dong[1] + dist;
				} else if (dir == 3) {
					result += Math.abs(dist - dong[1]);
				} else {
					result += Math.min(width + dong[1] + dist, width + (height * 2) - dong[1] - dist);
				}
				break;
			case 4:
				dir = shopList.get(i)[0];
				dist = shopList.get(i)[1];
				if (dir == 1) {
					result += dong[1] + width - dist;
				} else if (dir == 2) {
					result += height - dong[1] + width - dist;
				} else if (dir == 3) {
					result += Math.min(width + dong[1] + dist, width + (height * 2) - dong[1] - dist);
				} else {
					result += Math.abs(dist - dong[1]);
				}
				break;
			}

		}
		System.out.println(result);
	}

}
