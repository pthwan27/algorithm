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
  const [N, M] = input[0].split(" ").map(Number);

  const colorChk = new Array(M).fill(false);
  let answer = 0;

  for (let i = 1; i < 1 + N; i++) {
    let box = input[i].split(" ").map(Number);

    let idx = -1;
    let cnt = 0;

    box.forEach((e, i) => {
      if (e > 0) {
        idx = i;
        cnt++;
      }
    });

    if (cnt < 1) continue;

    if (cnt === 1) {
      if (!colorChk[idx]) colorChk[idx] = true;
      else answer++;
    } else {
      answer++;
    }
  }

  console.log(answer - 1 === -1 ? 0 : answer - 1);
});
