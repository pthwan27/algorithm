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
  let answer = 0;

  let [N, M] = input[0].split(" ").map(Number);

  const map = [];
  const isVisited = Array.from({ length: N }, () => new Array(M).fill(false));
  const dr = [-1, 0, 1, 0, -1, -1, 1, 1];
  const dc = [0, -1, 0, 1, -1, 1, 1, -1];

  for (let i = 1; i < N + 1; i++) {
    map.push(input[i].split(" ").map(Number));
  }

  for (let r = 0; r < N; r++) {
    for (let c = 0; c < M; c++) {
      if (isVisited[r][c]) continue;

      if (bfs(r, c)) answer++;
    }
  }

  console.log(answer);

  function bfs(r, c) {
    let isPeak = true;
    const bfsQ = [];

    bfsQ.push([r, c]);

    while (bfsQ.length) {
      let [curR, curC] = bfsQ.pop();

      isVisited[curR][curC] = true;

      for (let i = 0; i < 8; i++) {
        let nextR = curR + dr[i];
        let nextC = curC + dc[i];

        if (isIn(nextR, nextC)) {
          if (map[nextR][nextC] > map[curR][curC]) isPeak = false;

          if (isVisited[nextR][nextC]) continue;

          if (map[nextR][nextC] === map[curR][curC]) {
            isVisited[nextR][nextC] = true;
            bfsQ.push([nextR, nextC]);
          }
        }
      }
    }

    return isPeak;
  }

  function isIn(r, c) {
    return r >= 0 && r < N && c >= 0 && c < M;
  }
});
