function solution(topping) {
    let answer = 0;
    
    let leftMap = new Map();
    let rightMap = new Map();
    
    leftMap.set(topping[0], 1);
    for(let i = 1; i < topping.length; i++){
        if(rightMap.has(topping[i])) {
            rightMap.set(topping[i], rightMap.get(topping[i]) + 1);
        }else{
            rightMap.set(topping[i], 1);
        }
    }
    if(leftMap.size === rightMap.size) answer++;
    
    for(let i = 1; i < topping.length; i++){        
        if(leftMap.has(topping[i])) {
            leftMap.set(topping[i], leftMap.get(topping[i]) + 1);
        }else{
            leftMap.set(topping[i], 1);
        }
        
        if(rightMap.get(topping[i]) === 1) {
            rightMap.delete(topping[i]);
        }else{
            rightMap.set(topping[i], rightMap.get(topping[i]) - 1);
        }
        if(leftMap.size === rightMap.size) answer++;    
    }
    
    return answer;
}