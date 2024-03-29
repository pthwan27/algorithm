package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_1974_스도쿠검증 {
	public static void main(String[] args) throws Exception, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        int c = 0;
        for (c = 1; c <= T; c++) {
         
            int [][] arr = new int[9][9];
             
            int i = 0; int j =0;
            for(i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine());
                for(j = 0; j < 9; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean chk = true;
            int result = 1;
            int[] chkarr = new int[9];
             
            //가로 확인
            for(i = 0; i < arr.length; i++) 
            {
                for(j = 0; j <arr[i].length; j++) 
                {
                    chkarr[j] = arr[i][j];
                }
                chk = isDuplicate(chkarr);
                if(chk == false) {
                    result = 0;
                }
                 
            }
             
            //세로 확인
            for(i = 0; i < arr.length; i++) 
            {
                for(j = 0; j <arr[i].length; j++) 
                {
                    chkarr[j] = arr[j][i];
                }
                chk = isDuplicate(chkarr);
                if(chk == false) {
                    result = 0;
                }
                 
            }           
            chkarr = new int[9];
            for(i = 0; i <3; i++) 
            {
                for(j = 0; j < 3; j++) {
                    for(int k = 0; k < 3; k++) {
                        chkarr[j*3+k] = arr[(i*3)+j][(i*3)+k];
                    }
                }
                 
                chk = isDuplicate(chkarr);
                if(chk == false) {
                    result = 0;
                }
                 
            }
            System.out.println("#" + c + " " + result );
        }
 
    }
 
    private static boolean isDuplicate(int[] chkarr) {
        boolean chk = true;
        for(int i = 0; i< chkarr.length; i++) {
            for(int j = 0; j< chkarr.length; j++) {
                if(i!=j) 
                {
                    if(chkarr[i] == chkarr[j]) {
                        chk = false;
                    }
                 
                }
            }
        }
        return chk;
    }

}
