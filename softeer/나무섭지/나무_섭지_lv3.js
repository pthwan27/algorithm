const readline = require("readline");
const rl = readline.createInterface({
  // input: process.stdin,
  input: require("fs").createReadStream("input.txt"),
  output: process.stdout,
});

class Heap {
  constructor() {
    this.heap = [];
  }
  size() {
    return this.heap.length;
  }

  push(e) {
    this.heap.push(e);

    this.bubbleUp();
  }
  pop() {
    if (this.heap.length < 0) return null;
    if (this.heap.length === 1) return this.heap.pop();

    let popItem = this.heap[0];

    this.heap[0] = this.heap.pop();

    this.bubbleDown();

    return popItem;
  }

  bubbleUp() {
    let curIdx = this.heap.length - 1;
    let parentIdx = Math.floor((curIdx - 1) / 2);

    while (this.heap[parentIdx] && this.heap[parentIdx][2] > this.heap[curIdx][2]) {
      this.swap(parentIdx, curIdx);

      curIdx = parentIdx;
      parentIdx = Math.floor((curIdx - 1) / 2);
    }
  }

  bubbleDown() {
    let curIdx = 0;
    let leftIdx = curIdx * 2 + 1;
    let rightIdx = curIdx * 2 + 2;

    while (
      (this.heap[leftIdx] && this.heap[curIdx][2] > this.heap[leftIdx][2]) ||
      (this.heap[rightIdx] && this.heap[curIdx][2] > this.heap[rightIdx][2])
    ) {
      let smallerIdx = leftIdx;

      if (this.heap[rightIdx] && this.heap[smallerIdx][2] > this.heap[rightIdx][2])
        smallerIdx = rightIdx;

      this.swap(smallerIdx, curIdx);

      curIdx = smallerIdx;
      leftIdx = curIdx * 2 + 1;
      rightIdx = curIdx * 2 + 2;
    }
  }

  swap(idx1, idx2) {
    [this.heap[idx1], this.heap[idx2]] = [this.heap[idx2], this.heap[idx1]];
  }
}

const input = [];

rl.on("line", (e) => {
  input.push(e);
});

rl.on("close", () => {
  const dr = [-1, 0, 1, 0];
  const dc = [0, -1, 0, 1];

  const [R, C] = input[0].split(" ").map(Number);
  let N,
    D,
    G = [];
  const map = new Array(R);

  for (let i = 0; i < R; i++) {
    map[i] = input[i + 1].split("");
  }

  for (let r = 0; r < R; r++) {
    for (let c = 0; c < C; c++) {
      if (map[r][c] === "N") N = [r, c];
      else if (map[r][c] === "D") D = [r, c];
      else if (map[r][c] === "G") G.push([r, c]);
    }
  }

  let ghostResult = Infinity;

  for (let i = 0; i < G.length; i++) {
    ghostResult = Math.min(ghostResult, ghostLen(G[i][0], G[i][1]));
  }

  let result = bfs(N[0], N[1]);

  if (ghostResult > result) console.log("Yes");
  else console.log("No");

  function ghostLen(r, c) {
    return Math.abs(D[0] - r) + Math.abs(D[1] - c);
  }

  function bfs(r, c) {
    const bfsQ = new Heap();
    bfsQ.push([r, c, 0]);

    const isVisited = Array.from(Array(R), () => new Array(C).fill(false));
    isVisited[r][c] = true;

    while (bfsQ.size()) {
      let [r, c, cnt] = bfsQ.pop();

      if (map[r][c] === "D") return cnt;

      for (let i = 0; i < 4; i++) {
        let nextR = r + dr[i];
        let nextC = c + dc[i];
        let nextCnt = cnt + 1;

        if (isIn(nextR, nextC) && !isVisited[nextR][nextC] && map[nextR][nextC] !== "#") {
          bfsQ.push([nextR, nextC, nextCnt]);
          isVisited[nextR][nextC] = true;
        }
      }
    }
  }

  function isIn(r, c) {
    return r >= 0 && r < R && c >= 0 && c < C;
  }
});
