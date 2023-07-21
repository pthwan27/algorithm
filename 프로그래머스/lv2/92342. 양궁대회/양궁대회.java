class Solution {   
    static int[] ryan = new int[11];
    static int[] res = {};
    static int max = Integer.MIN_VALUE;
   
	public static int[] solution(int n, int[] info) {
		dfs(0, n, info);

		if (max == -1) {
			res = new int[1];
			res[0] = -1;
		}
		return res;
	}
    
	public static void dfs(int depth, int n, int[] info) {
		if (depth == n) {
			int diff = calcDiff(info);
			if (max <= diff) {
				max = diff;
				res = ryan.clone();
			}
			return;
		}

		for (int i = 0; i < info.length && ryan[i] <= info[i]; i++) {
			ryan[i] += 1;
			dfs(depth + 1, n, info);
			ryan[i] -= 1;
		}
	}
    
    
	private static int calcDiff(int[] info) {
		int apScore = 0;
		int ryScore = 0;

		for (int i = 0; i < ryan.length; i++) {
			if (info[i] == 0 && ryan[i] == 0) {
				continue;
			}

			if (info[i] >= ryan[i]) {
				apScore += (10 - i);
			} else {
				ryScore += (10 - i);
			}
		}

		int diff = ryScore - apScore;
		if (diff <= 0) {
			return -1;
		}

		return diff;
	}
}