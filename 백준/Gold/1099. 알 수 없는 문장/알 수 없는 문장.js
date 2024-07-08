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
  const sentense = input[0];

  const N = Number(input[1]);

  const words = [];

  for (let i = 0; i < N; i++) {
    words.push(input[i + 2]);
  }

  const dp = new Array(51).fill(Infinity);

  for (let s = 0; s < sentense.length + 1; s++) {
    let sliceSentense = sentense.slice(0, s);

    for (let si = 0; si < sliceSentense.length; si++) {
      let checkSentense = sliceSentense.slice(si, s);

      for (let wi = 0; wi < words.length; wi++) {
        let checkWord = words[wi];

        if (!isSame(checkSentense, checkWord)) continue;

        let cnt = 0;

        for (let i = 0; i < checkWord.length; i++) {
          if (checkSentense[i] !== checkWord[i]) cnt++;
        }

        if (si === 0) {
          dp[s] = Math.min(dp[s], cnt);
        } else {
          dp[s] = Math.min(dp[s], dp[si] + cnt);
        }
      }
    }
  }

  console.log(dp[sentense.length] === Infinity ? -1 : dp[sentense.length]);
});

function isSame(w1, w2) {
  let sortedWord1 = w1.split("").sort().join("");
  let sortedWord2 = w2.split("").sort().join("");

  return sortedWord1 === sortedWord2;
}
