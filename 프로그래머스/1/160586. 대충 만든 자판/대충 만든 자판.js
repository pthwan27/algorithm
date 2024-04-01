function solution(keymap, targets) {    
    let answer = [targets.length];
    
    let alpha = new Array(26);
    alpha.fill(999);
    
    keymap.forEach((e1) => {
        Array.from(e1).forEach((e2, i) => {
            let idx = e2.charCodeAt() - 65;
            
            alpha[idx] = Math.min(alpha[idx], i+1);
        });
    })
    
    targets.forEach((e1, idx) => {
        let sum = 0;
        for(let i = 0; i < e1.length; i++){
            let idx = e1.charAt(i).charCodeAt() - 65;
            if(alpha[idx] == 999){
                sum = -1;
                break;
            }else{
                sum += alpha[idx];
            }
        }
        answer[idx] = sum;
    });
    return answer;
}