
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);

		int[] arr = new int[N];

		inputs = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(inputs[i]);
		}

		int left = 0;
		int right = 0;

		int cnt = 0;

		int dist = Integer.MAX_VALUE;
		while (left <= right && right <= arr.length) {
			if (cnt < K) {
				// 더 이상 라이언이 포함된 집합을 찾을 수 없음.
				if (right == arr.length) {
					break;
				}

				if (arr[right] == 1) {
					cnt++;
				}
				right++;
			} else {
				dist = Math.min(dist, right - left);
				if (arr[left] == 1) {
					cnt--;
				}
				left++;
			}
		}

		if (dist == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dist);
		}

	}

}
