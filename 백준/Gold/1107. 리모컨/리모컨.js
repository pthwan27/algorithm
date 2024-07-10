const readline = require("readline");

const rl = readline.createInterface({
  input : process.stdin,
  output: process.stdout,
});

const input = [];
rl.on("line", (e) => {
  input.push(e);
});

rl.on("close", () => {
  let N = Number(input[0]);
  let M = Number(input[1]);

  const buttons = new Array(10).fill(true);

  if (N === 100) {
    console.log(0);
    return;
  }

  if (M) {
    input[2].split(" ").map((e) => {
      buttons[e] = false;
    });
  }

  let targetCh = N;
  let targetChArr = targetCh.toString().split("").map(Number);

  let cnt = Math.min(Math.abs(targetCh - 100), targetChArr.length + Math.abs(targetCh - 100));
  let minCnt = cnt;

  for (let i = 0; i < 1000000; i++) {
    let ch = i.toString().split("").map(Number);

    if (isAble(ch, buttons)) {
      minCnt = Math.min(ch.length + Math.abs(targetCh - i), minCnt);
    }
  }

  console.log(minCnt);
});

function isAble(ch, buttons) {
  for (let i = 0; i < ch.length; i++) {
    if (!buttons[ch[i]]) {
      return false;
    }
  }

  return true;
}
