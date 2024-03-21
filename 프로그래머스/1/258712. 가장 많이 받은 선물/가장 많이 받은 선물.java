import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
    
        int len = friends.length;
        HashMap<String, Integer> nameToNumber = new HashMap<>();
        
        for(int i = 0; i < len; i++){
            nameToNumber.put(friends[i], i);
        }
        
        int[][] giftScoreArr = new int[friends.length][friends.length];
        
        for(int i = 0; i < gifts.length; i++){
            String[] gift = gifts[i].split(" ");
            
            String sender = gift[0]; 
            String recipient = gift[1];
            
            
            giftScoreArr[nameToNumber.get(sender)][nameToNumber.get(recipient)]++;
        }
        int[] giftScore = new int[len];
        
        int[] result = new int[len];
        for(int r = 0; r < len; r++){
            int send = 0;
            int receive = 0;
            
            for(int c = 0; c < len; c++){
                send += giftScoreArr[r][c];
                receive += giftScoreArr[c][r];
            }            
            giftScore[r] = send - receive;
        }
        
        for(int r = 0; r < len; r++){
            for(int c = 0; c < len; c++){
                if(r == c) continue; 
                
                if(giftScoreArr[r][c] == giftScoreArr[c][r]){
                    if(giftScore[r] > giftScore[c]){
                        result[r]++;
                    }
                }else if(giftScoreArr[r][c] > 0 || giftScoreArr[c][r] > 0){
                    if(giftScoreArr[r][c] > giftScoreArr[c][r]){
                        result[r]++;
                    }
                }
            }
            answer = Math.max(result[r], answer);
        }
        
        return answer;
    }
}