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
  let plus = true;
  let minus = true;

  if (N === 100) {
    console.log(0);
    return;
  }

  if (input[2]) {
    input[2].split(" ").map((e) => {
      if (e === "+") plus = false;
      else if (e === "-") minus = false;
      else buttons[e] = false;
    });
  }

  let targetCh = N;
  let targetChArr = targetCh.toString().split("").map(Number);

  let chPlus = 100;
  let chPlusCnt = Math.min(Math.abs(targetCh - 100), targetChArr.length + Math.abs(targetCh - 100));
  let minPlusCnt = chPlusCnt;

  let chMinus = 100;
  let chMinusCnt = Math.min(
    Math.abs(targetCh - 100),
    targetChArr.length + Math.abs(targetCh - 100)
  );
  let minMinusCnt = chMinusCnt;

  while (plus && chPlus < targetCh * 2 - 100) {
    chPlus++;
    chPlusCnt++;

    let ch = chPlus.toString().split("").map(Number);

    if (chPlus === targetCh) minPlusCnt = Math.min(chPlusCnt, minPlusCnt);

    if (isAble(ch, buttons)) {
      chPlusCnt = Math.min(ch.length + Math.abs(targetCh - chPlus), chPlusCnt);
      minPlusCnt = Math.min(chPlusCnt, minPlusCnt);
    }
  }

  while (minus && chMinus > 0) {
    chMinus--;
    chMinusCnt++;

    let ch = chMinus.toString().split("").map(Number);

    if (chMinus === targetCh) minMinusCnt = Math.min(chMinusCnt, minMinusCnt);

    if (isAble(ch, buttons)) {
      chMinusCnt = Math.min(ch.length + Math.abs(targetCh - chMinus), chMinusCnt);
      minMinusCnt = Math.min(chMinusCnt, minMinusCnt);
    }
  }

  console.log(Math.min(minPlusCnt, minMinusCnt));
});
function isAble(ch, buttons) {
  for (let i = 0; i < ch.length; i++) {
    if (!buttons[ch[i]]) {
      return false;
    }
  }

  return true;
}
