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

  if (N % 2 === 0 || N % 5 === 0) {
    console.log(-1);
    return;
  }

  let mod = 1 % N;
  let answer = 1;

  while (mod !== 0) {
    mod = (mod * 10 + 1) % N;
    answer++;
  }

  console.log(answer);
});
