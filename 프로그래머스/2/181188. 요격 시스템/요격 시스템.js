function solution(targets) {
  targets.sort(([s1, e1], [s2, e2]) => {
    if (e1 === e2) return s2 - s1;
    else return e1 - e2;
  });
  let answer = 1;
  let e = targets[0][1];

  for (let i = 1; i < targets.length; i++) {
    const nextTarget = targets[i];

    if (e > nextTarget[0] && e <= nextTarget[1]) continue;
    else {
      answer++;
      e = nextTarget[1];
    }
  }

  return answer;
}
