import java.util.*;

class Solution {
    static boolean[][] isSelected;
    static char[][] map;
    
    static int rSize, cSize;
    
    public int[] solution(String[] maps) {
        rSize = maps.length;
        cSize = maps[0].length();
        
        isSelected = new boolean[rSize][cSize];
        
        map = new char[rSize][cSize];
        
        for(int r = 0; r < rSize; r++){
            for(int c = 0; c < cSize; c++){
                map[r][c] = maps[r].charAt(c);
            }
        }
        
        ArrayList<Integer> resultList = new ArrayList<>();
        for(int r = 0; r < rSize; r++){
            for(int c = 0; c < cSize; c++){
                if(map[r][c] != 'X' && !isSelected[r][c]){
                    resultList.add(bfs(r,c));
                }
            }
        }
        
        if(resultList.size() < 1){
            return new int[]{-1};
        }else{
            Collections.sort(resultList);
            int[] answer = new int[resultList.size()];

            for(int i = 0; i < resultList.size(); i++){
                answer[i] = resultList.get(i);
            }
            return answer;
        }
        
    }
    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, -1, 0, 1};
    
    public int bfs(int r, int c){
        Queue<int[]> bfsQ = new LinkedList<>();
        isSelected[r][c] = true;
        bfsQ.offer(new int[]{r,c});
        int count = map[r][c] - '0';
        
        while(!bfsQ.isEmpty()){
            int[] cur = bfsQ.poll();
            
            for(int i = 0; i < 4; i++){
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];
                
                if(nextR >= 0 && nextR < rSize && nextC >= 0 && nextC < cSize && !isSelected[nextR][nextC]){
                    if(map[nextR][nextC] != 'X'){
                        isSelected[nextR][nextC] = true;
                        bfsQ.offer(new int[]{nextR,nextC});
                        count += map[nextR][nextC] - '0';
                    }
               }
            }
        }      
        
        return count;
    } 
}