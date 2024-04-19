function solution(sequence, k) {
  let answer = [];

  let s = 0;
  let e = 0;
  let sum = sequence[0];
  let shortestLength = sequence.length;

  while (s < sequence.length && e < sequence.length && s <= e) {
    if (sum === k && shortestLength > e - s) {
      answer = [s, e];
      shortestLength = e - s;
    }

    if (sum <= k) {
      e++;
      sum += sequence[e];
    } else {
      sum -= sequence[s];
      s++;
    }
  }
  return answer;
}
