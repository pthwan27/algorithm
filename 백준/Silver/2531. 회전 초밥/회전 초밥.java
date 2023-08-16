
import java.io.*;
import java.util.*;

public class Main {

	static int N, d, k, c;
	static int[] belt;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		d = Integer.parseInt(inputs[1]);
		k = Integer.parseInt(inputs[2]);
		c = Integer.parseInt(inputs[3]);

		belt = new int[N];
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(in.readLine());
		}

		int[] arr = new int[N + k - 1];
		for (int i = 0; i < arr.length; i++) {
			if (i >= 0 && i < k - 1) {
				arr[i] = belt[N - k + (i + 1)];
			} else {
				arr[i] = belt[i - (k - 1)];
			}
		}

		int maxResult = Integer.MIN_VALUE;
		HashMap<Integer, Integer> cntMap = new HashMap<>();

		cntMap.put(arr[0], 1);
		

		if (cntMap.containsKey(arr[1])) {
			cntMap.put(arr[1], cntMap.get(arr[1]) + 1);
		} else {
			cntMap.put(arr[1], 1);
		}

		int start = 0;
		int end = 1;

		while (true) {
			if (end - start == k-1) {
				if (cntMap.containsKey(c)) {
					cntMap.put(c, cntMap.get(c)+ 1);
				} else {
					cntMap.put(c, 1);
				}
				
				maxResult = Math.max(maxResult, cntMap.size());
				
				if (cntMap.get(c) == 1) {
					cntMap.remove(c);
				} else {
					cntMap.put(c , cntMap.get(c) - 1);
				}
			}

			if (end - start < k-1) {
				end++;
				if(end >= arr.length) break;
				
				if (cntMap.containsKey(arr[end])) {
					cntMap.put(arr[end], cntMap.get(arr[end]) + 1);
				} else {
					cntMap.put(arr[end], 1);
				}
			} else {
				if (cntMap.get(arr[start]) == 1) {
					cntMap.remove(arr[start]);
				} else {
					cntMap.put(arr[start], cntMap.get(arr[start]) - 1);
				}

				start++;
			}

		}
		System.out.println(maxResult);

	}
}
