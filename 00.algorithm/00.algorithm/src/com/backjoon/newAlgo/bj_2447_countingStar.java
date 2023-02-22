package com.backjoon.newAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_2447_countingStar {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                countingStar(r, c);
            }
            sb.append("\n");

        }
        System.out.println(sb);
    }

    private static void countingStar(int r, int c) {
        if(r < 3 && c < 3){
            if(r % 3 == 1 && c % 3 ==1){
                sb.append(" ");
            }else{
                sb.append("*");
            }
        }else{
            if(r % 3 == 1 && c % 3 == 1){
                sb.append(" ");
            }else{
                countingStar(r/3, c/3);
            }
        }
    }
}
//
//      while (N  < 1){
//        for (int j = 0; j < 3; j++) {
//            if(N / 3 == 1 && j / 3 == 1) {
//                System.out.print(" ");
//            } else if (N % 3 == 1 && j % 3 == 1) {
//                System.out.print(" ");
//            } else {
//                System.out.print("*");
//            }
//        }
//        N = N/3;
//    }
//
//
//
//    //27 9 3 1
//        for (int i = 0; i < 3; i++) {
//        for (int j = 0; j < 3; j++) {
//            if(i / 3 == 1 && j / 3 == 1) {
//                System.out.print(" ");
//            } else if (i % 3 == 1 && j % 3 == 1) {
//                System.out.print(" ");
//            } else {
//                System.out.print("*");
//            }
//        }
//        System.out.println();
//    }
//
//
//
//        System.out.println("-------------");
//        //27 9 3 1
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (i % 9 == 1 && j % 9 == 1) {
//                    System.out.print(" ");
//                } else if (i % 3 == 1 && j % 3 == 1) {
//                    System.out.print(" ");
//                } else if (i / 3 == 1 && j / 3 == 1) {
//                    System.ouZt.print(" ");
//                } else {
//                    System.out.print("*");
//                }
//            }
//            System.out.println();
//        }
