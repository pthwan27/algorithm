function solution(targets) {
    var answer = 1;
    
    targets.sort((a,b) => {
        if(a[1] == b[1]){
            return a[0] - b[0];
        }else{
            return a[1] - b[1];
        }
    });
    console.log(targets);
    
    var end = targets[0][1];
    
    for(var i = 0; i < targets.length; i++){
        if(end > targets[i][0]){
            continue;
        }else{
            answer++;
            end = targets[i][1];
        }
    }
    
    var end = targets[0][1];
    return answer;
}