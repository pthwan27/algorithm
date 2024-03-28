function solution(numbers, target) {
    const memo = new Map();

    function dfs(idx, sum) {
        if (idx === numbers.length) {
            return sum === target ? 1 : 0;
        }
        
        const key = `${idx},${sum}`;
        if (memo.has(key)) {
            return memo.get(key);
        }

        const add = dfs(idx + 1, sum + numbers[idx]);
        const subtract = dfs(idx + 1, sum - numbers[idx]);

        // 각각의 경우에 대해 결과를 메모이제이션
        memo.set(`${idx + 1},${sum + numbers[idx]}`, add);
        memo.set(`${idx + 1},${sum - numbers[idx]}`, subtract);

        // 현재 상태의 결과를 반환합니다. 
        // => (4,4)에서 (5,3) (5,5) 2가지의 경우가 있는데
        // 여기서 타겟넘버는 1을 반환, 아니면 0를 반환하는데 
        // 이 2개를 더해서 윗 단계 (4,4)에 넘겨준다
        return add + subtract;
    }

    return dfs(0, 0);
}
