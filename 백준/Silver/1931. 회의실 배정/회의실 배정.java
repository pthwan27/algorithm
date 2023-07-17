import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 회의실 배정. Comparator를 써서 ? X 정렬해놓고, 재귀로?
 * 
 * @author
 *
 */
public class Main {
	static int N;;
	static ArrayList<int[]> timesList;

	static int cnt = 1;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		timesList = new ArrayList<>();
		for (int idx = 0; idx < N; idx++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());

			int[] times = new int[] { startTime, endTime };

			timesList.add(idx, times);
		}
		sortList(timesList);
		
		int endTime = timesList.get(0)[1];
		
		for (int idx = 1; idx < timesList.size(); idx++) {		
			if(endTime > timesList.get(idx)[0]) {
				continue;
			}
			else {
				endTime = timesList.get(idx)[1];
				cnt++;
			}			
		}
		System.out.println(cnt);
	}
	
	//종료시간 오름차순으로 정렬, 만약 종료시간이 같으면 시작시간이 낮은것부터 나오도록 정렬
	public static void sortList(ArrayList<int[]> timesList) {
		Collections.sort(timesList, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.valueOf(o1[0]).compareTo(Integer.valueOf((o2[0])));
				} else {
					return Integer.valueOf(o1[1]).compareTo(Integer.valueOf((o2[1])));
				}

			}
		});
	}

}
