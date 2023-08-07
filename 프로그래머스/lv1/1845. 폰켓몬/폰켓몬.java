import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] nums) {        
        int answer = 0;
        HashMap<Integer, Integer> poketmonMap = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++){
            if(poketmonMap.containsKey(nums[i])){
                poketmonMap.put(nums[i], poketmonMap.getOrDefault(nums[i],1)+1);
            }else{
                poketmonMap.put(nums[i], 1);
            }
        }
        
        int keyCount = poketmonMap.size();
        
        if((nums.length / 2) >= keyCount){
            return keyCount;
        }else{
            return nums.length/2;
        }
    }
}