import java.io.*;
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> answerMap = new HashMap<String, Integer>();
        for(int i = 0; i < participant.length; i++){
            if(answerMap.containsKey(participant[i])){
                answerMap.put(participant[i], answerMap.getOrDefault(participant[i],1)+1);
            }
            else{
                answerMap.put(participant[i],1);    
            }            
        }
        
        for(int i = 0; i < completion.length; i++){
            if(answerMap.containsKey(completion[i])){
                if(answerMap.get(completion[i]) > 1){
                    answerMap.put(completion[i], answerMap.getOrDefault(completion[i], 0)-1);
                }else{
                    answerMap.remove(completion[i]);
                }
            }
        }
        
        String answer = "";
        for(Map.Entry<String, Integer> entry : answerMap.entrySet()){
            answer = entry.getKey();
        }
        
        return answer;
    }
}