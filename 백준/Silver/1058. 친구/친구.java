import java.io.*;

public class Main {
	static int MAX = 99999999;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		int[][] dist = new int[N][N];

		for (int r = 0; r < N; r++) {
			String input = in.readLine();
			for (int c = 0; c < N; c++) {
				if (r == c)
					continue;

				if (input.charAt(c) == 'Y') {
					dist[r][c] = 1;
				} else {
					dist[r][c] = MAX;

				}
			}
		}

		for (int k = 0; k < N; k++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (r == c || c == k || k == r)
						continue;

					if (dist[r][c] > dist[r][k] + dist[k][c]) {
						dist[r][c] = dist[r][k] + dist[k][c];
					}
				}
			}
		}
		
		int result = -1;
		for (int r = 0; r < N; r++) {
			int cnt = 0;
			for (int c = 0; c < N; c++) {
				if(r == c) continue;
				
				if(dist[r][c] <= 2) {
					cnt++;					
				}
			}
			result = Math.max(result, cnt);
		}

		System.out.println(result);
	}
}
