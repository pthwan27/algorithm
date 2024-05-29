function solution(arrayA, arrayB) {
    let answer = 0;

    for(let i = 1; i <= arrayA[0]; i++){
        if(isDivisibleAll(arrayA, i) && isNotDivisibleAll(arrayB, i)){
            answer = Math.max(answer, i);
        }
    }
    
    for(let i = 1; i <= arrayB[0]; i++){
        if(isDivisibleAll(arrayB, i) && isNotDivisibleAll(arrayA, i)){
            answer = Math.max(answer, i);
        }
    }
    return answer;
}
function isDivisibleAll(arr, num) {
    for(let i = 0; i < arr.length; i++){
        if(arr[i] % num !== 0) return false;
    }
    return true;
}
function isNotDivisibleAll(arr, num) {
    for(let i = 0; i < arr.length; i++){
        if(arr[i] % num === 0) return false;
    }
    return true;
}