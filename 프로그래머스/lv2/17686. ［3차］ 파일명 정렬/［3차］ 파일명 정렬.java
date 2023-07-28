import java.util.*;
import java.io.*;
class file implements Comparable<file>{
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
        // compareToIgnoreCase 대소문자 관계없이 비교하기
        int headCompareNum = this.Head.compareToIgnoreCase(o.Head);

        if(headCompareNum == 0) {
            return Integer.parseInt(this.Number) - Integer.parseInt(o.Number);
        }

        return headCompareNum;
    }
}

class Solution {
    public String[] solution(String[] files) {
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