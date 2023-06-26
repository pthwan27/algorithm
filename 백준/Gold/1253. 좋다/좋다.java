import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer> inputList;
	static int trueCnt;
	static ArrayList<int[]> check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		check = new ArrayList<>();
		trueCnt = 0;

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		inputList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			inputList.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(inputList);

		for (int i = 0; i < N; i++) {
			int startPos = 0;
			int endPos = N - 1;

			while (true) {
				if (i == endPos) {
					endPos--;
				} else if (i == startPos) {
					startPos++;
				}

				if (startPos == endPos) {
					break;
				}

				if (inputList.get(i) == inputList.get(startPos) + inputList.get(endPos)) {
					trueCnt++;
					break;
				} else if (inputList.get(i) > inputList.get(startPos) + inputList.get(endPos)) {
					startPos++;
				} else if (inputList.get(i) < inputList.get(startPos) + inputList.get(endPos)) {
					endPos--;
				}
			}

		}

		System.out.println(trueCnt);
	}
}
