package hwalgo14_광주_5반_박태환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class refrigerator implements Comparable<refrigerator> {
	int minTemp, maxTemp;

	public refrigerator(int minTemp, int maxTemp) {
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}

	@Override
	public int compareTo(refrigerator o) {
		return o.minTemp == this.minTemp ? Integer.valueOf(this.maxTemp).compareTo(Integer.valueOf(o.maxTemp))
				: Integer.valueOf(this.minTemp).compareTo(Integer.valueOf(o.minTemp));
	}
}

public class JO_1828_냉장고_박태환 {

	static int refrCnt = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());

		Queue<refrigerator> tempQueue = new PriorityQueue<refrigerator>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			tempQueue.offer(new refrigerator(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		refrCnt = 1;
		// 처음 물질의 온도
		refrigerator temp = tempQueue.poll();
		int lowTemp = temp.minTemp;
		int highTemp = temp.maxTemp;

		while (!tempQueue.isEmpty()) {
			// 다음 물질 온도
			refrigerator nextValue = tempQueue.poll();

			// 처음 온도의 최고 온도보다 , 최저 온도가 높을 때
			if (highTemp < nextValue.minTemp) {
				refrCnt++;
				lowTemp = nextValue.minTemp;
				highTemp = nextValue.maxTemp;
				continue;
			}
			// 현재 최대값보다 다음 값의 최대값이 작으면 범위 줄이기
			if (nextValue.maxTemp < highTemp) {
				highTemp = nextValue.maxTemp;
			}	
		}
		System.out.println(refrCnt);
	}

}
