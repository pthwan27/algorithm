function solution(numbers, target) {
    let answer = 0;
    
    dfs(0, 0);
    
    return answer;
    
    function dfs(idx, sum){
        // console.log(`idx = ${idx} , sum = ${sum}`);
        
        if(idx > numbers.length - 1){
            if(sum == target) answer++;
            
            return;
        }
        
        dfs(idx + 1, sum + numbers[idx]);
        dfs(idx + 1, sum - numbers[idx]);        
    }
}