package com.backjoon.newAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_16198_chargingEnergy {
    static ArrayList<Integer> energyList;
    static int N;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(in.readLine());
        energyList = new ArrayList<Integer>();
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            energyList.add(Integer.parseInt(st.nextToken()));
        }
        dfs(energyList, 0);
        System.out.println(max);
    }

    private static void dfs(ArrayList<Integer> list, int sum){
        if(list.size() <= 2){
            max = Math.max(max, sum);
            return;
        }

        for(int i = 1; i < list.size()-1; i++){
            int removedNum = list.get(i);
            int tempSum = list.get(i-1) * list.get(i+1);

            list.remove(i);
            dfs(list,sum + tempSum);
            list.add(i, removedNum);
        }
    }
}