
import java.io.*;
import java.util.*;

public class Main {
	static class girlGroup {
		String groupName;
		int groupNum;
		ArrayList<String> member;

		public girlGroup(String groupName, int groupNum, ArrayList<String> member) {
			this.groupName = groupName;
			this.groupNum = groupNum;
			this.member = member;
		}
		
		
	}

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] input = in.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		ArrayList<girlGroup> girlGroupList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String inputGroupName = in.readLine();
			int inputGroupNum = Integer.parseInt(in.readLine());
			ArrayList<String> inputMember = new ArrayList<>();
			
			
			for (int a = 0; a < inputGroupNum; a++) {
				inputMember.add(in.readLine());
			}

			Collections.sort(inputMember);
			
			girlGroupList.add(new girlGroup(inputGroupName, inputGroupNum, inputMember));
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			String quiz = in.readLine();
			int type = Integer.parseInt(in.readLine());

			if (type == 0) {
				for (int a = 0; a < N; a++) {
					if(girlGroupList.get(a).groupName.equals(quiz)) {
						for(int b = 0; b < girlGroupList.get(a).groupNum; b++) {
							sb.append(girlGroupList.get(a).member.get(b)).append("\n");
						}
					}
				}
			}else {
				for (int a = 0; a < N; a++) {
					if(girlGroupList.get(a).member.contains(quiz)) {
						sb.append(girlGroupList.get(a).groupName).append("\n");
					}	
				}
			}
		}
		System.out.println(sb.toString());
	}
}
