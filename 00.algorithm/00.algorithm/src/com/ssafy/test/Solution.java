package com.ssafy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) throws ParseException {
        int[] answer;
        
        HashMap<String, Integer> termsMap = new HashMap<>();             
        for(int i = 0; i < terms.length; i++) {
        	String[] temp = terms[i].split(" ");
        	termsMap.put(temp[0], Integer.parseInt(temp[1]));
        }
        
        ArrayList<Integer> resultList = new ArrayList<>();        
        for(int i = 0; i < privacies.length; i++) {
        	String[] privacy = privacies[i].split(" ");
        	
        	String pdate = privacy[0];
        	int addMonth = termsMap.get(privacy[1]);
        	
        	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        	Date date = format.parse(pdate);
        	
        	Calendar cal = Calendar.getInstance();
            cal.setTime(date);
        	cal.add(Calendar.MONTH, addMonth);

        	Date todayDate = format.parse(today);
        	int compare = todayDate.compareTo(cal.getTime());
        	

        	if(compare >= 0) {
        		resultList.add(i+1);
        	}           
        }
        answer = new int[resultList.size()];
        for(int i = 0; i < resultList.size(); i++) {
        	answer[i] = resultList.get(i);
        }
        
        return answer;
    }
}