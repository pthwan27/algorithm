function solution(storey) {
    let answer = 0;
    let storeySplitArr = new String(storey).split('').map(e => Number(e));

    for(let i = storeySplitArr.length - 1; i >= 0; i--){
        if(storeySplitArr[i] > 5){
            answer += 10 - storeySplitArr[i];
            
            if(i === 0) answer++;
            
            storeySplitArr[i - 1]++;
        }else if(storeySplitArr[i] == 5 && storeySplitArr[i - 1] >= 5){
            answer += 10 - storeySplitArr[i];
            storeySplitArr[i - 1]++;
        }else {
            answer += storeySplitArr[i];
            storeySplitArr[i] = 0;
        }
    }
    return answer;
}