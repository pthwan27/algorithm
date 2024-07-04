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
  let pw = input[0].split("").map(Number);
  let k = BigInt(input[1]) - 1n;

  pw.map((e, idx) => {
    if (e === 6 || e === 7) pw[idx] -= 5;
  });

  let i = pw.length;
  while (i--) {
    if (pw[i] === 1 || pw[i] === 2) {
      if (k & 1n) pw[i] += 5;
      k >>= 1n;
    }
  }

  console.log(k ? "-1" : pw.join(""));
});
