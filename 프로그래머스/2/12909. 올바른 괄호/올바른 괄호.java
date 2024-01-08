import java.util.*;

class Solution {
    boolean solution(String s) {  
        boolean answer = false;
        
        if(s.length() <= 1 || s.charAt(0) == ')' || s.charAt(s.length()-1) == '('){
            return false;
        }
        
        Stack<Character> st = new Stack<>();
        st.push(s.charAt(0));
        for(int i = 1; i < s.length(); i++){
            char next = s.charAt(i);
            
            
            if(next == ')'){
                if(st.size() == 0){
                    return false;
                }
                
                if(st.peek() == '('){
                    st.pop();
                }
            }else{
                st.push(next);
            }
        }
        if(st.size() == 0){
            answer = true;
        }
                
        
        return answer;
    }
}