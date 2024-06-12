function solution(data, col, row_begin, row_end) {
    let answer = [];
    
    data.sort((a,b) => {
        if(a[col-1] === b[col-1]) return b[0] - a[0];
        else return a[col-1] - b[col-1];
    })
    
    for(let i = row_begin - 1; i <= row_end - 1; i++){
        let rest = data[i].reduce((total, cur) => {
            total += cur % (i + 1);
            return total;
        },0)
        
        answer.push(rest);
    }
    
    return answer.reduce((result, cur) => {
        return result ^= cur;
    },0);
}