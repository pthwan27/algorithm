const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const originMap = [];

rl.on("line", (e) => {
  originMap.push(e.split(""));
});

rl.on("close", () => {
  let result = 0;

  const arr = [];

  function makeComb(idx, depth) {
    if (idx === 7) {
      const newArr = changeRC(arr);

      if (sCntCheck(newArr) && isConnected(newArr)) result++;

      return;
    }

    if (depth === 25) return;

    arr[idx] = depth;
    makeComb(idx + 1, depth + 1);
    makeComb(idx, depth + 1);
  }

  makeComb(0, 0);

  console.log(result);
});

function sCntCheck(arr) {
  let cnt = 0;

  arr.forEach(([r, c]) => {
    if (originMap[r][c] === "S") cnt++;
  });

  return cnt >= 4 ? true : false;
}

function isConnected(arr) {
  const isSelected = new Array(7).fill(false);
  isSelected[0] = true;

  const bfsQ = [];
  bfsQ.push(arr[0]);

  let len = 7;

  const dr = [-1, 0, 1, 0];
  const dc = [0, 1, 0, -1];

  while (bfsQ.length) {
    let [r, c] = bfsQ.pop();
    len--;

    for (let i = 0; i < 4; i++) {
      let nextR = r + dr[i];
      let nextC = c + dc[i];

      if (nextR >= 0 && nextR < 5 && nextC >= 0 && nextC < 5) {
        arr.forEach(([ar, ac], idx) => {
          if (nextR === ar && nextC === ac && !isSelected[idx]) {
            isSelected[idx] = true;
            bfsQ.push(arr[idx]);
          }
        });
      }
    }
  }

  if (len === 0) return true;
  else return false;
}

function changeRC(arr) {
  const newArr = arr.map((e) => {
    let r = Math.floor(e / 5);
    let c = e % 5;

    return [r, c];
  });

  return newArr;
}
