const readline = require("readline");
const rl = readline.createInterface({
  // input: process.stdin,
  input: require("fs").createReadStream("input.txt"),
  output: process.stdout,
});

const input = [];
rl.on("line", (e) => {
  input.push(e);
});

rl.on("close", () => {
  const strengths = input[0].split(" ").map(Number);

  // [[0,1], [0,2], [0,3], [1,2], [1,3], [2,3]]
  const matches = makeMatchComb(4);
  const results = makeWinComb(6);

  let totalProb = 0;

  results.forEach((result) => {
    let scores = [0, 0, 0, 0];
    let prob = 1;

    for (let i = 0; i < result.length; i++) {
      let teamA = matches[i][0];
      let teamB = matches[i][1];

      total = strengths[teamA] + strengths[teamB];

      if (result[i] === 0) {
        scores[teamA] += 3;

        prob *= (4 * strengths[teamA]) / (5 * total);
      } else if (result[i] === 1) {
        scores[teamA] += 1;
        scores[teamB] += 1;

        prob *= total / (5 * total);
      } else {
        scores[teamB] += 3;

        prob *= (4 * strengths[teamB]) / (5 * total);
      }
    }

    if (scores.filter((score) => score > scores[0]).length < 2) {
      totalProb += prob;
    }
  });

  console.log((totalProb * 100).toFixed(3));
});

function makeMatchComb(cnt) {
  let result = [];

  function comb(cur, idx) {
    if (cur.length === 2) {
      result.push([...cur]);
      return;
    }

    for (let i = idx; i < cnt; i++) {
      cur.push(i);
      comb(cur, i + 1);
      cur.pop();
    }
  }

  comb([], 0);

  return result;
}
function makeWinComb(cnt) {
  let result = [];

  function comb(cur) {
    if (cur.length === cnt) {
      result.push([...cur]);
      return;
    }

    for (let i = 0; i < 3; i++) {
      cur.push(i);
      comb(cur);
      cur.pop();
    }
  }

  comb([]);

  return result;
}
