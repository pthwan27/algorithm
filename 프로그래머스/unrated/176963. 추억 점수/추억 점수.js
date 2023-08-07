function solution(name, yearning, photo) {
    const resultObj = name.reduce((acc, cur, idx) => {
        acc[cur] = yearning[idx];
        return acc;
    }, new Object);
    
    
    var answer = [];
    for(const arr of photo){
        let sum = 0;
        for(const name of arr){
            if(resultObj[name]){
                sum += resultObj[name];
            }
        }
        answer.push(sum);
    }
    return answer;
}