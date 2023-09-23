package com.programmers;

import java.io.*;
import java.util.*;

class 부대복귀 {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < roads.length; i++){
            int start = roads[i][0];
            int end = roads[i][1];
            
            graph[start].add(end);
            graph[end].add(start);        
        }
        
        int[] dist = dijkstra(n, destination, graph);
        
        for(int i = 0; i < sources.length; i++){
            if(dist[sources[i]] == Integer.MAX_VALUE){
                answer[i] = -1;
            }else{
                answer[i] = dist[sources[i]];
            }
        }
        
        return answer;
    }
    
    public static int[] dijkstra(int n, int destination, ArrayList<Integer>[] graph){
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Integer> q = new LinkedList<>();
        
        dist[destination] = 0;
        q.add(destination);
        
        while(!q.isEmpty()){
            int s = q.poll();
            
            for(int i = 0; i < graph[s].size(); i++){
                int next = graph[s].get(i);
                
                if(dist[next] == Integer.MAX_VALUE){
                    dist[next] = dist[s] + 1;
                    q.add(next);
                }            
            }
        }
        
        return dist;        
    }
}