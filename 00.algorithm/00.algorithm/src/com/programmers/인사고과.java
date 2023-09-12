package com.programmers;

import java.io.*;
import java.util.*;

class 인사고과 {
	public static void main(String[] args) {
		int result = solution(new int[][] {{2,2},{1,4},{3,2},{3,2},{2,1}});
		
		System.out.println(result);
	}
    public static int solution(int[][] scores) {
        int answer = 0;
        
        ArrayList<Info> infoList = new ArrayList<>();
        
        for(int i = 0; i < scores.length; i++){
            Info info = new Info(scores[i][0], scores[i][1], i+1);
            infoList.add(info);
        }
        
        Collections.sort(infoList);
        
        int rank = 1;
        int count = 1;
        
        int beforeTotal = infoList.get(0).total;
        
        for(int i = 1; i < infoList.size(); i++){
            int curTotal = infoList.get(i).total;
            
            System.out.println(infoList.get(i).num + " " + beforeTotal + " " + curTotal);
            
            if(beforeTotal == curTotal){
                count++;
            }else if(beforeTotal > curTotal){
                beforeTotal = curTotal;
                rank = count + 1;
            }
            

            if(infoList.get(i).num == 1){
                return rank;
            }
            
        }

        
        return rank;
    
    }
}

class Info implements Comparable<Info> { 
    int x, y, total, num;
    
    public Info(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.total = x+y;
        this.num = num;
    }
    
    @Override
    public int compareTo(Info o){
        return o.total - this.total;
    }
}