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

        memo.set(key, add + subtract);
        return add + subtract;
    }

    return dfs(0, 0);
}
