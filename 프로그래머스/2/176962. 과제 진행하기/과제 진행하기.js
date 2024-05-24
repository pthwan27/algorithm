function solution(plans) {
    let answer = [];
    
    const changedPlans = plans.map(([name,playTime,durTime], i) => {
        let [hh, mm] = playTime.split(':').map(Number);
        
        return [name, hh * 60 + mm,Number(durTime)];
    });
    
    changedPlans.sort((a, b) => a[1] - b[1]);
    
    let remainTimePlan = [];
    
    for(let i = 0; i < changedPlans.length - 1; i++){
        let curPlan = changedPlans[i];
        let nextPlan = changedPlans[i+1]
        
        if(curPlan[1] + curPlan[2] <= nextPlan[1]) {
            answer.push(curPlan[0]);
            
            let remainTime = nextPlan[1] - (curPlan[1] + curPlan[2]);
            
            while(remainTime > 0 && remainTimePlan.length){
                let [remainPlanName, remainPlanTime] = remainTimePlan.pop();
                
                if(remainPlanTime <= remainTime) {
                    remainTime -= remainPlanTime;
                    answer.push(remainPlanName);
                }else {
                    remainTimePlan.push([remainPlanName, remainPlanTime - remainTime]);
                    remainTime = 0;
                    break;
                }  
            }
        } else {
            remainTimePlan.push([curPlan[0],curPlan[1] + curPlan[2] - nextPlan[1]]);
        }
    }
    answer.push(changedPlans[changedPlans.length - 1][0]);
    
    while(remainTimePlan.length){
        answer.push(remainTimePlan.pop()[0]);
    }
    
    return answer;
}