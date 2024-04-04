function solution(citations) {
    var answer = citations.length;
    
    citations.sort((a,b) => b - a);
    let len = citations.length;
    
    for(let i = 1; i <= len; i++){
        if(citations[i-1] < i){
            answer = i-1; 
            break;
        }
    }
    return answer;
}