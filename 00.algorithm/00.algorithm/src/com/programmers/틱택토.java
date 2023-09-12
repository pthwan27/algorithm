package com.programmers;

import java.io.*;
import java.util.*;

class 틱택토 {
    // o는 x보다 작을 수 없음, o는 x+1까지만 가능
    
    //x, o 가 같을 때 o가 승리할 수 없음
    //x, o 다를 때 x가 승리할 수 없음
    public int solution(String[] board) {
        int answer = -1;
        
        int oCnt = 0;
        int xCnt = 0;
        
        char[][] map = new char[3][3];
        
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[r].length(); c++){
                map[r][c] = board[r].charAt(c);
                
                if(map[r][c] == 'O') oCnt++;
                else if(map[r][c] == 'X') xCnt++;
                
            }
        }
        int[] result = isPossible(map);
        
        answer = 1;
        
        if(oCnt >= xCnt && oCnt - 1 <= xCnt){
            if(oCnt == xCnt){
                if(result[0] > 0) answer = 0;
            }            
            
            else{
                if(result[1] > 0) answer = 0;
            }
        }else{
            answer = 0;
        }
        
        return answer;
    }
    public static int[] isPossible(char[][] map){
        int oWinCnt = 0;
        int xWinCnt = 0;
        
        //가로
        for(int r = 0; r < 3; r++){
            if(map[r][0] != '.' && map[r][0] == map[r][1] && map[r][1] == map[r][2]){
                if(map[r][0] == 'O') oWinCnt++;
                else if(map[r][0] == 'X') xWinCnt++;
            }
        }
        
        //세로
        for(int c = 0; c < 3; c++){
            if(map[0][c] != '.' && map[0][c] == map[1][c] && map[1][c] == map[2][c]){
                if(map[0][c] == 'O') oWinCnt++;
                else if(map[0][c] == 'X') xWinCnt++;
            }
        }        
        
        //대각선
        if(map[0][0] != '.' && map[0][0] == map[1][1] && map[1][1] == map[2][2]){
            if(map[0][0] == 'O') oWinCnt++;
            else if(map[0][0] == 'X') xWinCnt++;
        }        
        
        if(map[0][2] != '.' && map[0][2] == map[1][1] && map[1][1] == map[2][0]){
            if(map[0][2] == 'O') oWinCnt++;
            else if(map[0][2] == 'X') xWinCnt++;
        }
        
        return new int[]{oWinCnt, xWinCnt};       
    }
}