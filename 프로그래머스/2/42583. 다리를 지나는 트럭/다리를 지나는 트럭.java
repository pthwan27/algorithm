import java.util.*;

class Car{
    int w, st;
    public Car(int w,int st){
        this.w = w;
        this.st = st;
    }
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
		int time = 1;

		Queue<Car> carQ = new LinkedList<>();

		for (int i = 0; i < truck_weights.length;) {
			while (weight < truck_weights[i]) {
				if (carQ.size() >= bridge_length) {
					Car pollCar = carQ.poll();
					weight += pollCar.w;
				}else {
					carQ.offer(new Car(0, time));
					time++;
				}
				
			}
			
			if (weight >= truck_weights[i]) {
				carQ.offer(new Car(truck_weights[i], time));
				weight -= truck_weights[i];
				i++;
			}
			
			time++;
		}
		while (!carQ.isEmpty()) {
			Car pollCar = carQ.poll();
            answer = pollCar.st;
        }
        
        return answer + bridge_length;
    }
}