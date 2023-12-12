import java.util.*;

class Number implements Comparable<Number>{
    int num;
    
    public Number(int n){
        num = n;
    }
    
    @Override
    public int compareTo(Number n1){
        String s1 = String.valueOf(this.num);
        String s2 = String.valueOf(n1.num);
        
        return (s2+s1).compareTo(s1+s2);
    }
}
class Solution {
    public String solution(int[] numbers) {
        Number[] numArr = new Number[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            numArr[i] = new Number(numbers[i]);
        }
        
        Arrays.sort(numArr);
        
        StringBuilder sb = new StringBuilder();
        for(Number N : numArr){
            sb.append(String.valueOf(N.num));
        }
        
        return (sb.charAt(0) == '0' || sb.length() == 0) ? "0" : sb.toString();
    }
}