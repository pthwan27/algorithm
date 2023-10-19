
import java.io.*;
import java.util.*;

public class Main {

	static int N;

	static class lecture {
		int num, start, end;

		public lecture(int n, int s, int e) {
			num = n;
			start = s;
			end = e;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		ArrayList<lecture> lectureList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String[] inputs = in.readLine().split(" ");
			int n = Integer.parseInt(inputs[0]);
			int s = Integer.parseInt(inputs[1]);
			int e = Integer.parseInt(inputs[2]);

			lectureList.add(new lecture(n, s, e));
		}

		Collections.sort(lectureList, (lecture l1, lecture l2) -> l1.start - l2.start);

		int result = Integer.MAX_VALUE;

		PriorityQueue<Integer> pQue = new PriorityQueue<>();
		pQue.offer(lectureList.get(0).end);

		for (int i = 1; i < N; i++) {
			if (pQue.peek() <= lectureList.get(i).start) {
				pQue.poll();
				pQue.offer(lectureList.get(i).end);
			} else {
				pQue.offer(lectureList.get(i).end);
			}
		}
		
		result = Math.min(pQue.size(), result);
		System.out.println(result);
	}

}
