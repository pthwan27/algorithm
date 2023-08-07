import java.io.*;
import java.util.*; 

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        String[] todayArr = today.split("\\.");
        int todayDay = changeToDay(todayArr);
        
        HashMap<String, Integer> termMap = new HashMap<>();
        for(String term : terms){
            String[] termArr = term.split(" ");
            String termName = termArr[0];
            int termExp = todayDay - Integer.parseInt(termArr[1]) * 28;
            
            termMap.put(termName, termExp);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < privacies.length; i++){
            String temp = privacies[i];
            
            String[] privacy = temp.split(" ");
            String[] privacyDays = privacy[0].split("\\.");
            int privacyDay = changeToDay(privacyDays);
            int privacyTermDay = termMap.get(privacy[1]);
            
            if(privacyDay <= privacyTermDay){
                result.add(i+1);                
            }
        }
        
        int [] answer = new int[result.size()];
        
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
    public static int changeToDay(String[] YMDArr){
        //년 * 12개월 * 28일 + 월 * 28 + 일수 => 일수
        int total = 0;
        for(int i = 0; i < YMDArr.length; i++){
            if(i == 0){
                total += Integer.parseInt(YMDArr[i]) * 12 * 28;
            }else if (i == 1){                
                total += Integer.parseInt(YMDArr[i]) * 28;
            }else{
                total += Integer.parseInt(YMDArr[i]);
            }
        }
        return total;
    }
}