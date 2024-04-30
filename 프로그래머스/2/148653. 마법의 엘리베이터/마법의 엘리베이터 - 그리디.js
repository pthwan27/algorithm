function solution(storey) {
  let answer = 0;

  const storeyArr = String(storey)
    .split("")
    .map((e) => e * 1);

  for (let i = storeyArr.length - 1; i >= 0; i--) {
    if (storeyArr[i] > 5) {
      answer += 10 - storeyArr[i];

      if (i === 0) answer++;

      storeyArr[i - 1]++;
    } else if (storeyArr[i] === 5 && storeyArr[i - 1] >= 5) {
      answer += 5;
      storeyArr[i - 1]++;
    } else {
      answer += storeyArr[i];
    }
  }

  return answer;
}
