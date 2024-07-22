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
  let [W, H, f, c, x1, y1, x2, y2] = input[0].split(" ").map(BigInt);

  let paintW = BigInt(x2 - x1);
  let paintH = BigInt(y2 - y1);

  let answer = W * H;

  let dbWidth = W - f < f ? W - f : f;

  if (dbWidth <= x1) {
    answer -= paintW * paintH * (c + 1n);
  } else if (dbWidth < x2) {
    answer -= paintH * (dbWidth - x1) * (c + 1n) * 2n;

    answer -= paintH * (x2 - dbWidth) * (c + 1n);
  } else {
    answer -= paintH * paintW * (c + 1n) * 2n;
  }

  console.log(answer.toString());
});
