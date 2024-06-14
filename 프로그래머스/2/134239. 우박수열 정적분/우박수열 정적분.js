function solution(k, ranges) {
    let answer = [];
    
    let graph = [];
    
    let idx = 0;
    graph.push(k);
    
    while(k !== 1) {
        k = k % 2 === 1 ? k * 3 + 1 : k / 2;
        
        graph.push(k);
    }
    let n = graph.length - 1;
    
    ranges.forEach(range => {
        let s = range[0];
        let e = n + range[1];
        
        if(s > e) answer.push(-1);
        else if(s === e) answer.push(0);
        else {
            let total = 0;
            for(let i = s; i < e; i++){
                total += Math.abs((graph[i] - graph[i+1]) / 2);
                
                if(graph[i] < graph[i+1]) total += Math.abs(graph[i]);
                else total += Math.abs(graph[i+1]);
            }
            answer.push(total);
        }
    });
    
    return answer;
}