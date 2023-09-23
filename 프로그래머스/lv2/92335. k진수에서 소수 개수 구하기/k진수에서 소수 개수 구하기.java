import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int count = 0;
        
//         boolean[] isPrime = new boolean[Integer.MAX_VALUE];
        
//         for(int i = 2; i * i < Integer.MAX_VALUE; i++){
//             if(!isPrime[i]){
//                 for(int j = i*i; j < Integer.MAX_VALUE; j+=i){
//                     isPrime[j] = true;
//                 }
//             }
//         }
        
        String changeStr = Integer.toString(n, k);
        
        String[] splitStr = changeStr.split("0");
        
        for(String data: splitStr){
            if(data.equals("")) continue;
            long num=Long.parseLong(data);                       
            if(isPrime(num)){
                count++;
            }
        }
        
        System.out.println(count);
        
        return count;
    }
    public static boolean isPrime(Long num){
        if(num < 2){
            return false;
        }
        
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }
        
        return true;
    }
}