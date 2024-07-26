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
  let [N, K] = input[0].split(" ").map(Number);

  let purchasedBottleCnt = 0;

  if (N <= K) {
    console.log(0);
    return;
  }

  while (true) {
    let cnt = 0;
    let copyN = N;
    while (copyN != 0) {
      if (copyN % 2 === 1) cnt++;

      copyN = Math.floor(copyN / 2);
    }

    if (cnt <= K) break;

    N += 1;
    purchasedBottleCnt += 1;
  }

  console.log(purchasedBottleCnt);
});
