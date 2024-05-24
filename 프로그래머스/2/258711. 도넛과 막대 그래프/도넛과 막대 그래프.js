function solution(edges) {    
    const pointMap = new Map();
    const graph = new Array(1000001).fill().map(()=> []);
    
    edges.map(e => {
        let start = e[0];
        let end = e[1];
        
        if(!pointMap.has(start)) {
            pointMap.set(start, [0, 1]);
        }else {
            pointMap.get(start)[1]++;
        }
        
        if(!pointMap.has(end)) {
            pointMap.set(end, [1, 0]);
        }else {
            pointMap.get(end)[0]++;
        }
        
        graph[start].push(end);
    });
    
    let generatedNum = [];
    
    for(const [key, value] of pointMap) {
        if(value[0] === 0 && value[1] >= 2) {
            generatedNum[0] = key;
            generatedNum[1] = value[1];
            
            graph[generatedNum[0]].forEach(e => {
                pointMap.get(e)[0]--;
            })
            break;
        }
    }
    
    let donut = 0;
    let stick = [0, 0];
    let eight = 0;
    for(const [key, value] of pointMap) {
        if(value[0] === 0 && value[1] === 0) {
            stick[0]++;
            stick[1]++;
        }
        
        if(value[0] === 0 && value[1] === 1) stick[0]++;
        
        if(value[0] === 1 && value[1] === 0) stick[1]++;
        
        if(value[0] === 2 && value[1] === 2) eight++;
    }
    stick = Math.min(stick[0], stick[1]);
    
    return [generatedNum[0], generatedNum[1] - eight - stick, stick, eight];
}