package com.programmers;

import java.util.*;
import java.io.*;

/* 
\\d: 숫자와 일치
\\D: 숫자가 아닌 것과 일치
\\s: 공백 문자와 일치
\\S: 공백이 아닌 문자와 일치
\\w: 단어 문자(알파벳, 숫자, 밑줄)와 일치
\\W: 단어 문자가 아닌 것과 일치

`+` 는 바로 앞의 문자나 패턴이 하나 이상 반복될 수 있음
*/
public class 파일명정렬 {

	static class file implements Comparable<file>{
		String Head;
		String Number;
		String Tail;
		
		public file(String file) {
			//img12.png에서 split("\\D+")를 사용하면 결과는 ["", "12", ""]
			String number = file.split("\\D+")[1]; 
	        int numberIndex = file.indexOf(number);
	        int numberEndIndex = numberIndex + number.length(); 
	        
	        this.Head = file.substring(0, numberIndex);
	        this.Number = file.substring(numberIndex, numberEndIndex);
	        this.Tail = file.substring(numberEndIndex);
		}
		public int compareTo(file o) {
			
			int headCompareNum = this.Head.compareToIgnoreCase(o.Head);
			
			if(headCompareNum == 0) {
				return Integer.parseInt(this.Number) - Integer.parseInt(o.Number);
			}
			
			return headCompareNum;
		}
	}
	
	public static void main(String[] args) {
		String[] answer = solution(
				new String[] { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" });
		
		
	}

	public static String[] solution(String[] files) {
		ArrayList<file> fileList = new ArrayList<>();
		
		for(int i = 0; i < files.length; i++) {
			fileList.add(new file(files[i]));
		}
		Collections.sort(fileList);
		
		String[] answer = new String[fileList.size()];
		
		for(int i = 0; i < answer.length; i++) {
			file f = fileList.get(i);
			answer[i] = f.Head + f.Number + f.Tail;
		}
		return answer;
	}
}
