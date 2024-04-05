function solution(scores) {
    let target = scores[0];
    
    scores.sort((a, b) => {
        if(a[0] == b[0]) return a[1] - b[1];
        else return b[0] - a[0];
    });
    
    let maxPeer = -1;
    let rank = 1;
    for(let score of scores){
        let total = score[0] + score[1];
        
        if(maxPeer > score[1]){
            if(score == target) return -1;
            continue;
        }
        
        if(total > target[0] + target[1]){
            rank++;
        }
        
        maxPeer = Math.max(maxPeer, score[1]);     
    }
    
    return rank;
}