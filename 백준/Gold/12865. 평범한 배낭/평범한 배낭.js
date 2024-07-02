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
  const [N, K] = input[0].split(" ").map(Number);

  const items = [];

  for (let i = 1; i < input.length; i++) {
    items.push(input[i].split(" ").map(Number));
  }
  const dp = Array.from({ length: K + 1 }, () => new Array(N + 1).fill(0));

  for (let item_idx = 1; item_idx < N + 1; item_idx++) {
    const [item_w, item_v] = items[item_idx - 1];

    for (let bag_w = 1; bag_w < K + 1; bag_w++) {
      if (bag_w >= item_w) {
        dp[bag_w][item_idx] = Math.max(
          dp[bag_w][item_idx - 1],
          dp[bag_w - item_w][item_idx - 1] + item_v
        );
      } else {
        dp[bag_w][item_idx] = dp[bag_w][item_idx - 1];
      }
    }
  }
  console.log(dp[K][N]);
});
