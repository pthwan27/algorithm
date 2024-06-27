function solution(scores) {
    let target = scores[0];
    let targetSum = target[0] + target[1];
    
    scores.sort((a, b) => {
        if(a[0] === b[0]) return b[1] - a[1];
        else return a[0] - b[0];
    });
    
    console.log(scores);
    
    let maxScore = scores[scores.length - 1][1];
    let rank = 1;
    
    for(let i = scores.length - 1; i >= 0; i--){
        let [w, c] = scores[i];
        let sum = w + c;
        
        if(maxScore > c) {
            if(scores[i] === target) return -1;
            continue;
        }
        if(sum > targetSum) {
            rank++;
        }
        
        maxScore = Math.max(maxScore, c);

    }
    
    return rank;
}