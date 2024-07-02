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
  const N = Number(input[0]);

  const minCost = new Array(N).fill(Infinity);

  const map = [];
  for (let i = 0; i < N; i++) {
    map.push(input[i + 1].split(" ").map(Number));
  }

  for (let i = 0; i < N; i++) {
    let isVisited = new Array(N).fill(false);

    isVisited[i] = true;
    dfs(i, i, 0, 0, isVisited);
  }

  function dfs(start, cur, cost, deep, visited) {
    if (deep === N - 1) {
      if (map[cur][start]) {
        minCost[start] = Math.min(minCost[start], cost + map[cur][start]);
      }

      return;
    }

    let next = map[cur];

    next.forEach((e, idx) => {
      if (!visited[idx] && e !== 0) {
        visited[idx] = true;
        dfs(start, idx, cost + e, deep + 1, [...visited]);
        visited[idx] = false;
      }
    });

    return;
  }

  console.log(Math.min(...minCost));
});
