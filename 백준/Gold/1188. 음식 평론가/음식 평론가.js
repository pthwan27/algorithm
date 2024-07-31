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
  let [N, M] = input[0].split(" ").map(Number);

  let gcdNum = gcd(N, M);

  console.log(M - gcdNum);
});

function gcd(a, b) {
  if (a % b === 0) return b;

  return gcd(b, a % b);
}
