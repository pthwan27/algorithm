function solution(array, commands) {
    var answer = [];
    
    for(let i = 0; i < commands.length; i++){
        let filterArr = array.filter((e, idx) => {
            return idx >= (commands[i][0] - 1) && idx < commands[i][1];
        });
        filterArr.sort((a,b) => a - b);
        
        answer.push(filterArr[commands[i][2] - 1]);
    }
    return answer;
}