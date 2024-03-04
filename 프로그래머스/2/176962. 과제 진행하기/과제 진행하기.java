import java.util.*;

class plan{
    String name;
    int startTime;
    int playTime;
    
    public plan(String n, int st, int pt){
        name = n; 
        startTime = st;
        playTime = pt;
    }
}
class remainPlan{
    String rpName;
    int rpTime;
    
    public remainPlan(String rpn, int rt){
        rpName = rpn; 
        rpTime = rt;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[0];
        
        ArrayList<String> answerList = new ArrayList<>();
        
        PriorityQueue<plan> plansQue = new PriorityQueue<>((o1, o2) -> o1.startTime - o2.startTime);
        Stack<remainPlan> remainPlanStk = new Stack<>(); 
        
        for(int i = 0; i < plans.length; i++){
            plansQue.add(new plan(plans[i][0], calcMin(plans[i][1]), Integer.parseInt(plans[i][2])));
        }
        
            
        while(!plansQue.isEmpty()){
            plan curPlan = plansQue.poll();
            
            if(plansQue.peek() == null){
                answerList.add(curPlan.name);
                break;
            }
            
            //다음 과제 시작시간까지 끝낼 수 있을 때
            if(curPlan.startTime + curPlan.playTime <= plansQue.peek().startTime){
                int spareTime = plansQue.peek().startTime - (curPlan.startTime + curPlan.playTime);
                answerList.add(curPlan.name);
                
                while(!remainPlanStk.isEmpty() && spareTime > 0){                    
                    if(spareTime >= remainPlanStk.peek().rpTime){
                        spareTime -= remainPlanStk.peek().rpTime;
                        answerList.add(remainPlanStk.peek().rpName);
                        remainPlanStk.pop();
                    }else{
                        remainPlanStk.peek().rpTime -= spareTime;
                        spareTime = 0;
                    }                    
                }              
            }
            //다음 과제 시작시간까지 못끝낼 때
            else{
                int remainTime = curPlan.playTime - (plansQue.peek().startTime - curPlan.startTime);
                
                remainPlanStk.push(new remainPlan(curPlan.name, remainTime));
            }
        }
        while(!remainPlanStk.isEmpty()){
            answerList.add(remainPlanStk.pop().rpName);
        }
        
        return answerList.toArray(new String[0]);
    }
    
    public int calcMin(String start){
        String[] startTime = start.split(":");
        
        return Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
    }
}