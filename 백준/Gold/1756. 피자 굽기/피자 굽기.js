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
  const [D, N] = input[0].split(" ").map(Number);

  const deep = input[1].split(" ").map(Number);
  const pizza = input[2].split(" ").map(Number);

  for (let i = 0; i < deep.length - 1; i++) {
    if (deep[i + 1] > deep[i]) {
      deep[i + 1] = deep[i];
    }
  }

  let lastIdx = 0;

  for (let i = D - 1; i >= 0; i--) {
    if (deep[i] >= pizza[lastIdx]) {
      lastIdx++;
    }

    if (lastIdx === N) {
      console.log(i + 1);
      return;
    }

    if (i === 0) {
      console.log(0);
      return;
    }
  }
});
