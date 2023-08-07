import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int k;

	static int[] belt;
	static boolean[] isRobot;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		belt = new int[2 * n];

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < belt.length; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		isRobot = new boolean[n];
		System.out.println(solution());
	}

	private static int solution() {
		int cnt = 1;
		while (true) {
			//1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			int temp = belt[belt.length - 1];

			for (int i = belt.length - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = temp;

			//1-2. 로봇도 함께 이동
			for (int i = isRobot.length - 1; i > 0; i--) {
				isRobot[i] = isRobot[i - 1];
			}
			isRobot[0] = false;
			isRobot[n - 1] = false;

			//2 . 로봇 이동 가능하다면 이동시키기
			for (int i = isRobot.length - 1; i > 0; i--) {
				//컨베이너 현재 칸에 로봇이 없고 내구도가 1 이상, 이전 칸에 로봇이 있을 경우
				if (isRobot[i - 1] && !isRobot[i] && belt[i] > 0) {
					isRobot[i] = true;
					isRobot[i - 1] = false;
					belt[i]--;
				}
			}

			//3. 로봇을 0자리에 올릴 수 있다면 0자리에 올린다
			if (belt[0] > 0) {
				isRobot[0] = true;
				belt[0]--;
			}

			//4. 내구도 검사 zeroCnt			
			int zeroCnt = 0;
			for(int i = 0; i < belt.length; i++) {
				if(belt[i] == 0) {
					zeroCnt++;
				}
				if(zeroCnt >= k) {
					return cnt;
				}
			}
			
			cnt++;
		}
	}
}
