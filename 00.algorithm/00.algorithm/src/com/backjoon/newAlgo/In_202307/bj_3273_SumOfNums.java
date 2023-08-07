package com.backjoon.newAlgo.In_202307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_3273_SumOfNums
{
    static int N, X;
    static int[] nArr;

    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        nArr = new int[N];

        st = new StringTokenizer(in.readLine());

        for(int i = 0; i < N; i++){
            nArr[i]= Integer.parseInt(st.nextToken());
        }

        X = Integer.parseInt(in.readLine());

        Arrays.sort(nArr);

        int start = 0;
        int end = nArr.length-1;

        count = 0;
        while(start!=end){
            if(nArr[start] + nArr[end] == X){
                count++;
                end--;
            }else if(nArr[start] + nArr[end] < X){
                start++;
            }else if(nArr[start] + nArr[end] > X){
                end--;
            }
        }

        System.out.println(count);
    }
}
