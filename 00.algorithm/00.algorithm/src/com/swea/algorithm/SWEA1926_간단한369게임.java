package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA1926_간단한369게임 {

	   public static void main(String[] args) throws Exception, IOException {
	         
	        //문제 정리 : 
	        //1번 규칙 369가 들어간 수는 말하지 않는다
	        //2번 369가 들어간 수 만큼 박수
	        //3번 3,6,9 는 '-'로 표시, 33 -> '--'     
	         
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	         
	        //N까지 369!
	        int N = Integer.parseInt(br.readLine());
	         
	        //for문 변수들
	        int i = 0;  
	        int j = 0;
	         
	        //전체 숫자에 369가 들어가있는지 확인하기 위한 문자열
	        String nums = null;     
	        //각 자리수마다 확인하기 위해
	        String temp = "";       
	        // 369가 들어가는지 확인하기 위해
	        boolean check = false;      
	        // 369갯수 체크
	        int count = 0; 
	         
	         
	        //1~N까지 검사
	        for (i = 1; i <= N; i++) 
	        {               
	            nums = String.valueOf(i);
	 
	            //숫자들을 자릿수별로 하나씩 검사
	            for(j = 0; j < nums.length(); j++) 
	            {
	                temp = String.valueOf(nums.charAt(j));
	                 
	                //처음엔 3으로 나눴을 때 나머지가 0인 경우만 추가하였음 -> 문제발생
	                //10 이면 1~9는 정상적으로 되지만 2자리가  됐을 때 
	                //1, 0 을 검사하는 과정에서 0이 나머지가 무조건 0이라 10이 369로 카운터 됌
	                //그래서 369가 들어간 숫자는 모두 3의 배수이기 때문에 3의 배수 조건 추가
	                //check -> 369인지 확인, count -> 3,6,9의 개수 확인
	                if(Integer.parseInt(temp) / 3 >= 1 && Integer.parseInt(temp) % 3 == 0 ) 
	                {
	                    check = true;
	                    count++;
	                }
	            }       
	            //369아닐 땐 그냥 숫자 나오도록
	            if(check == false) 
	            {
	                System.out.print(i + " ");              
	            }
	             
	            else
	            {
	                //3,6,9의 개수만큼 '-'출력
	                for(j = 0; j <count; j++) 
	                {
	                    System.out.print("-");
	                }
	                System.out.print(" ");
	                check = false;
	                count = 0;
	            }                       
	        }   
	         
	    }
	 
	}