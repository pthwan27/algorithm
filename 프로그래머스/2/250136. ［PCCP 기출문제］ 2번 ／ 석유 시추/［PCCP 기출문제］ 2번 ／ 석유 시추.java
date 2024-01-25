import java.util.*;

class Solution {
    int rSize, cSize;
    boolean[][] isSelected;
    int[][] oilmap;
    public int solution(int[][] land) {
        int answer = 0;
        
        rSize = land.length;
        cSize = land[0].length;
        oilmap = new int[rSize][cSize];
        isSelected = new boolean[rSize][cSize];
        
        int oilIdCnt = 1;
        HashMap<Integer, Integer> savedOil = new HashMap<>();
        
        int getOil = 0;
        for(int c = 0; c < cSize; c++){
            for(int r = 0; r < rSize; r++){
                if(!isSelected[r][c] && land[r][c] == 1){
                    isSelected[r][c] = true;
                    getOil = bfs(land, r, c, oilIdCnt);
                    savedOil.put(oilIdCnt++, getOil);
                }
            }
        }
        
        for(int c = 0; c < cSize; c++){
            Set<Integer> idSet = new HashSet<>();
            int sum = 0;
            for(int r = 0; r < rSize; r++){
                if(land[r][c] == 1){
                    idSet.add(oilmap[r][c]);
                }
            }
            
            for(int id : idSet){
                sum += savedOil.get(id);
            }
            answer = Math.max(sum, answer);
            sum = 0;
        }
        
        return answer;
    }
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};
    
    public int bfs(int[][] map, int r, int c, int oilIdCnt){
        Queue<int[]> bfsQ = new LinkedList<>(); 
        bfsQ.offer(new int[]{r,c});
        oilmap[r][c] = oilIdCnt;
        
        int count = 1;
        while(!bfsQ.isEmpty()){
            int[] next = bfsQ.poll();
            for(int i = 0; i < 4; i++){
                int nextR = next[0] + dx[i];
                int nextC = next[1] + dy[i];
                
                if(isIn(nextR, nextC) && !isSelected[nextR][nextC] && map[nextR][nextC] == 1){
                    count++;
                    oilmap[nextR][nextC] = oilIdCnt;
                    bfsQ.offer(new int[]{nextR, nextC});
                    isSelected[nextR][nextC] = true;
                }
            }
        }
        return count;
    }
    
    public boolean isIn(int r, int c){
        return r >= 0 && r < rSize && c >= 0 && c < cSize;
    }
}