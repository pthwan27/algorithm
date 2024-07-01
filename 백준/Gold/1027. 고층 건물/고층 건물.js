const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const input = [];

rl.on("line", (e) => {
  input.push(e);
});

rl.on("close", () => {
  const N = Number(input[0]);

  const buildings = input[1].split(" ").map(Number);
  let answer = 0;

  for (let i = 0; i < N; i++) {
    let start = i;
    let left = start - 1;
    let right = start + 1;

    let maxDegrees = -Infinity;
    let maxCount = 0;

    while (isIn(left)) {
      let y = buildings[left] - buildings[start];
      let x = start - left;

      let degree = Math.atan(y / x) * (180 / Math.PI);

      if (maxDegrees < degree) {
        maxDegrees = degree;
        maxCount++;
      }
      left--;
    }

    maxDegrees = -Infinity;

    while (isIn(right)) {
      let y = buildings[right] - buildings[start];
      let x = right - start;

      let degree = Math.atan(y / x) * (180 / Math.PI);

      if (maxDegrees < degree) {
        maxDegrees = degree;
        maxCount++;
      }
      right++;
    }

    answer = Math.max(answer, maxCount);
  }
  console.log(answer);

  function isIn(idx) {
    return idx >= 0 && idx < N;
  }
});
