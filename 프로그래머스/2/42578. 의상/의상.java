import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, ArrayList<String>> clothesMap = new HashMap<>();
        
        for(int i = 0; i < clothes.length; i++){
            String clothesName = clothes[i][0];
            String clothesKind = clothes[i][1];
            
            clothesMap.putIfAbsent(clothesKind, new ArrayList<String>());
            
            clothesMap.get(clothesKind).add(clothesName);
        }
        for(String clothesKind : clothesMap.keySet()){
            answer *= clothesMap.get(clothesKind).size() + 1;
        }
        return answer - 1;
    }
}