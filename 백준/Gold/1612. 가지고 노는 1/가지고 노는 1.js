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
  const N = input[0];

  let one = 1;
  let result = 1;
  if (N % 5 === 0 || N % 2 === 0) {
    console.log(-1);
  } else {
    while (one % N != 0) {
      result++;
      one = 10 * (one % N) + 1;
    }
    console.log(result);
  }
});
