import java.io.*;
import java.util.*;

public class Main {
	static class room implements Comparable<room>{
		int startTime, endTime;
		
		public room(int start, int end) {
			this.startTime = start;
			this.endTime = end;
		}
		
		
		@Override
		public int compareTo(room o) {
			if(this.endTime == o.endTime) {
				return this.startTime - o.startTime;
			}else {				
				return this.endTime - o.endTime;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		ArrayList<room> roomList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String[] times = in.readLine().split(" ");
			roomList.add(new room(Integer.parseInt(times[0]),Integer.parseInt(times[1])));
		}
		Collections.sort(roomList);
		
		int endTime = roomList.get(0).endTime;
		int cnt = 1;
		for(int i = 1; i < N; i++) {		
			if(endTime > roomList.get(i).startTime) {
				continue;
			}else {
				endTime = roomList.get(i).endTime;
				cnt++;
			}
			
		}
		
		System.out.println(cnt);
	}

}