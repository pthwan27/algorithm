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
  let N = BigInt(input[0]);

  let answer = 0;

  while (N > 1n) {
    N /= 2n;
    answer++;
  }

  console.log(8 + answer + 2);
});
