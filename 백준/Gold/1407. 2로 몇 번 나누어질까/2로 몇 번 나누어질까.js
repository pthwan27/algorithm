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
  const [start, end] = input[0].split(" ").map(BigInt);

  answer = calc(end) - calc(start - 1n);

  function calc(num) {
    let result = 0n;

    let cnt = 0n;
    let div = 1n;

    while (num > 0n) {
      if (num & 1n) {
        cnt = (num + 1n) / 2n;
      } else {
        cnt = num / 2n;
      }
      result += cnt * div;

      num -= cnt;
      div *= 2n;
    }

    return result;
  }

  console.log(answer.toString());
});
