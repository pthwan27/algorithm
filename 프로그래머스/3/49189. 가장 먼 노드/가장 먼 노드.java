import java.util.*; 
class Solution {    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edge.length; i++){
            int start = edge[i][0];
            int end = edge[i][1];
            
            graph[start].add(end);
            graph[end].add(start);
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        Queue<int[]> bfsQ = new LinkedList<>();
        
        bfsQ.offer(new int[] {1, 0});
        
        while(!bfsQ.isEmpty()){
            int[] cur = bfsQ.poll();
            int start = cur[0];
            int cnt = cur[1];
            
            for(int e : graph[start]){
                int nextCnt = cnt+1;
                
                if(dist[e] > nextCnt){
                    dist[e] = nextCnt;
                    bfsQ.offer(new int[]{e, nextCnt});
                }
            }
        }
        
        Arrays.sort(dist);
        int i = dist.length - 2;
        int max = dist[i];
      
        while(i > 0 && max == dist[i]){
            answer++;
            i--;
        }
        return answer;
    }
}