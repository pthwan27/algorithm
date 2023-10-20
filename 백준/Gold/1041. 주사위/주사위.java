import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		int[] dice = new int[6];
		String[] inputs = in.readLine().split(" ");

		int minOfDice = Integer.MAX_VALUE;
		int maxOfDice = Integer.MIN_VALUE;
		int sumOfDice = 0;
		for (int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(inputs[i]);
			minOfDice = Math.min(minOfDice, dice[i]);
			maxOfDice = Math.max(maxOfDice, dice[i]);
			sumOfDice += dice[i];
		}
		if(N == 1) {
			System.out.println(sumOfDice - maxOfDice);
			return;
		}

		ArrayList<Integer> sumOf2 = new ArrayList<>();
		ArrayList<Integer> sumOf3 = new ArrayList<>();

		for (int a = 0; a < 6; a++) {
			for (int b = 0; b < 6; b++) {
				if (a == b)
					continue;

				if (a == 0 || a == 5) {
					if (b == 5 || b == 0)
						continue;
					else
						sumOf2.add(dice[a] + dice[b]);
				} else if (a == 1 || a == 4) {
					if (b == 4 || b == 1)
						continue;
					else
						sumOf2.add(dice[a] + dice[b]);
				} else if (a == 2 || a == 3) {
					if (b == 3 || b == 2)
						continue;
					else
						sumOf2.add(dice[a] + dice[b]);
				}
			}
		}

		int[] set1 = new int[] { 0, 5 };
		int[] set2 = new int[] { 1, 4 };
		int[] set3 = new int[] { 2, 3 };

		for (int a = 0; a < 2; a++) {
			for (int b = 0; b < 2; b++) {
				for (int c = 0; c < 2; c++) {
					sumOf3.add(dice[set1[a]] + dice[set2[b]] + dice[set3[c]]);
					sumOf3.add(dice[set2[a]] + dice[set1[b]] + dice[set3[c]]);
					sumOf3.add(dice[set3[a]] + dice[set1[b]] + dice[set2[c]]);
				}
			}
		}

		Collections.sort(sumOf2);
		Collections.sort(sumOf3);

		long result = sumOf3.get(0) * 4;
		result += sumOf2.get(0) * (((N - 2) * 4) + ((N - 1) * 4));
		result += minOfDice * (Math.pow(N - 2, 2) + (Math.pow(N - 2, 2) + (N - 2)) * 4);

		System.out.println(result);
	}

}
