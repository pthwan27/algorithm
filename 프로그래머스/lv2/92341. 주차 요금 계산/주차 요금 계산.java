import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int baseMin = fees[0];
        int baseFee = fees[1];
        int perMin = fees[2];
        int perFee = fees[3];
        
        HashMap<String, Integer> recordMap = new HashMap<>();
        HashMap<String, Integer> minRecordMap = new HashMap<>();
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for(String record : records){
            String[] recordArr = record.split(" ");
            String[] time = recordArr[0].split(":");
            
            String carNum = recordArr[1]; 
            int min = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            
            if (recordMap.containsKey(carNum)) {
				int diff = min - recordMap.get(carNum);
				if (minRecordMap.containsKey(carNum)) {
					minRecordMap.put(carNum, minRecordMap.get(carNum) + diff);
					recordMap.remove(carNum);
				}else {
					minRecordMap.put(carNum, diff);
					recordMap.remove(carNum);					
				}
			} else {
				recordMap.put(carNum, min);
			}
        }
        Iterator<String> recordIter = recordMap.keySet().iterator();
        while(recordIter.hasNext()){
            String carNum = recordIter.next();
            int diff = (23*60+59) - recordMap.get(carNum);
             if(minRecordMap.containsKey(carNum)){
                    minRecordMap.put(carNum, minRecordMap.get(carNum) + diff);
             }else{
                 minRecordMap.put(carNum, diff);
             }
        }
		parkingCar[] pC = new parkingCar[minRecordMap.size()];

		Iterator<String> minRecordIter = minRecordMap.keySet().iterator();
		int i = 0;
		while (minRecordIter.hasNext()) {
			String nextKey = minRecordIter.next();
			int carNum = Integer.parseInt(nextKey);
			int min = minRecordMap.get(nextKey);
			if (min < baseMin) {
				pC[i] = new parkingCar(carNum, baseFee);
			} else {
				int diff = min - baseMin;
				pC[i] = new parkingCar(carNum, (int) (baseFee + (Math.ceil((double) diff / perMin) * perFee)));
			}
			i++;
		}

		Arrays.sort(pC);
		
		int[] answer = new int[pC.length];
		for(i = 0; i < pC.length; i++) {
			answer[i] = pC[i].fee;
		}
		return answer;
    }
}
class parkingCar implements Comparable<parkingCar> {
    int num, fee;
    public parkingCar(int num, int fee){
        this.num = num;
        this.fee = fee;
    }
    @Override
    public int compareTo(parkingCar o){
        return this.num - o.num;
    }
}