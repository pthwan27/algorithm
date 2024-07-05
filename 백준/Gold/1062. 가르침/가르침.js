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
  let answer = 0;

  const [N, K] = input[0].split(" ").map(Number);
  if (K < 5) {
    console.log(0);
    return;
  }

  const words = [];
  for (let i = 1; i < N + 1; i++) {
    words.push(input[i]);
  }

  const alpha = Array.from({ length: 26 }, (_, i) => String.fromCharCode(i + 97));
  const excludedAlpha = new Set(["a", "n", "t", "i", "c"]);
  const filteredAlpha = alpha.filter((e) => !excludedAlpha.has(e));

  comb("", filteredAlpha, K - 5);

  console.log(answer);

  function comb(prefix, filteredAlpha, size) {
    if (size === 0) {
      answer = Math.max(answer, count(prefix));
      return;
    }

    for (let i = 0; i < filteredAlpha.length; i++) {
      comb(prefix + filteredAlpha[i], filteredAlpha.slice(i + 1), size - 1);
    }
  }

  function count(prefix) {
    let cnt = words.length;
    let isEducatedWord = prefix + "antic";

    let isEducatedAlpha = new Array(26).fill(false);

    for (let alpha of isEducatedWord) {
      isEducatedAlpha[alpha.charCodeAt() - 97] = true;
    }

    words.forEach((word) => {
      for (let i = 4; i < word.length; i++) {
        if (!isEducatedAlpha[word[i].charCodeAt() - 97]) {
          cnt--;
          break;
        }
      }
    });

    return cnt;
  }
});
