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

  const items = [];
  items.push([0, 0]);

  for (let i = 1; i < N + 1; i++) {
    let [V, C, K] = input[i].split(" ").map(Number);

    let squareCnt = 1;
    while (K >= squareCnt) {
      K -= squareCnt;

      items.push([V * squareCnt, C * squareCnt]);

      squareCnt = squareCnt << 1;
    }

    if (K > 0) {
      items.push([V * K, C * K]);
    }
  }

  const dp = Array.from({ length: items.length }, () => new Array(M + 1).fill(0));

  for (let item_idx = 1; item_idx < items.length; item_idx++) {
    let [w, v] = items[item_idx];

    for (let kg = 1; kg <= M; kg++) {
      if (kg >= w) {
        dp[item_idx][kg] = Math.max(dp[item_idx - 1][kg], dp[item_idx - 1][kg - w] + v);
      } else {
        dp[item_idx][kg] = dp[item_idx - 1][kg];
      }
    }
  }

  console.log(dp[items.length - 1][M]);
});
