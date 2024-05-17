function solution(k, d) {
    let answer = 0;
    let dSquared = d * d;

    for(let a = 0; a <= d; a += k) {
        let aSquared = a * a;
        let maxB = Math.floor(Math.sqrt(dSquared - aSquared) / k) * k;  // 가능한 최대 b 값 계산
        answer += (maxB / k) + 1;  // 0부터 maxB까지 k 간격의 b 값의 개수
    }
    
    return answer;
}