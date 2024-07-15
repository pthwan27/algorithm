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
  const long_gear =
    input[0].length >= input[1].length
      ? input[0].split("").map(Number)
      : input[1].split("").map(Number);
  const short_gear =
    input[0].length < input[1].length
      ? input[0].split("").map(Number)
      : input[1].split("").map(Number);

  let minLen = long_gear.length + short_gear.length;

  let check = true;
  let len = long_gear.length + short_gear.length;

  for (let sIdx = 0; sIdx < short_gear.length; sIdx++) {
    len = long_gear.length + short_gear.length;
    check = true;

    for (let gIdx = sIdx; gIdx >= 0; gIdx--) {
      if (short_gear[short_gear.length - 1 - (sIdx - gIdx)] === 2 && long_gear[gIdx] === 2) {
        check = false;
        break;
      } else {
        len--;
      }
    }

    if (check) minLen = Math.min(len, minLen);

    len = long_gear.length + short_gear.length;
    check = true;

    for (let gIdx = sIdx; gIdx >= 0; gIdx--) {
      if (long_gear[long_gear.length - 1 - (sIdx - gIdx)] === 2 && short_gear[gIdx] === 2) {
        check = false;
        break;
      } else {
        len--;
      }
    }

    if (check) minLen = Math.min(len, minLen);
  }

  for (let lIdx = 0; lIdx <= long_gear.length - short_gear.length; lIdx++) {
    check = true;

    for (let gIdx = 0; gIdx < short_gear.length; gIdx++) {
      if (long_gear[lIdx + gIdx] === 2 && short_gear[gIdx] === 2) {
        check = false;
        break;
      }
    }

    if (check) {
      minLen = Math.min(minLen, long_gear.length);
    }
  }
  console.log(minLen);
});
