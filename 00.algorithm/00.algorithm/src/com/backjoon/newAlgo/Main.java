package com.backjoon.newAlgo;

import java.util.Arrays;

// 양궁대회
public class Main {

    static int N, maxGap;
    static int[] lion, aPeach, answer;

    public static void main(String[] args) {
        int[] result = solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0});
        System.out.println(Arrays.toString(result));
    }

    static public int[] solution(int n, int[] info) {
        aPeach = info;
        lion = new int[11];
        answer = new int[11];
        N = n;
        maxGap = 0;
        dfs(0,0);
        // int arrSum = Arrays.stream(answer).sum();
        if (maxGap != 0) {
            return answer;
        }

        return new int[] {-1};
    }

    // 중복조합
    static public void dfs(int level, int start){
        if (level == N) {
            // 라이언 점수 - 어피치 점수
            System.out.println(Arrays.toString(lion));
            
            int result = calScore(aPeach,lion);
            if (result <= 0) return;
            // System.out.println(result);
            if (maxGap <= result) {
                answer = Arrays.copyOf(lion, 11);
                maxGap = result;
            }
            return;
        }
        for (int i = start; i < 11; i++) {
            lion[i] ++;
            // 두 번째 인자 주의
            dfs(level+1, i);
            lion[i]--;
        }
    }

    private static int calScore(int[] aPeach, int[] lion) {
        int aSum = 0;
        int lSum = 0;
        for (int i = 0; i < 11; i++) {
            int score = 10-i;
            if (aPeach[i] == 0 && lion[i] == 0) continue;
            if (aPeach[i] >= lion[i]) aSum += score;
            else lSum += score;
        }
        return lSum-aSum;
    }
}