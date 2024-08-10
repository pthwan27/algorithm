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
  const lnk = input[0].split(" ");

  const L = BigInt(lnk[0]);
  let [N, K] = [Number(lnk[1]), Number(lnk[2])];

  const lampArr = input[1].split(" ").map(BigInt);
  const lampQ = [];

  const visited = new Set();

  for (let i = 0; i < N; ++i) {
    lampQ.push([lampArr[i], BigInt(0)]);
    visited.add(lampArr[i]);
  }

  // BFS 시작
  let front = 0;
  const dr = [-1, 1];
  while (lampQ.length > front) {
    const [cur, dist] = lampQ[front++];

    // 현재 위치의 어두운 정도 (가장 가까운 가로등까지의 거리)를 출력
    console.log(dist.toString());
    K--;

    // K개의 위치를 모두 출력했으면 프로그램 종료
    if (K === 0) {
      return;
    }

    // 현재 위치의 왼쪽과 오른쪽 이웃을 탐색
    for (let i = 0; i < 2; i++) {
      const next = cur + BigInt(dr[i]);

      // 이웃 위치가 도로 범위 내에 있고 아직 방문하지 않았다면 큐에 추가
      if (isPossible(next, L) && !visited.has(next)) {
        lampQ.push([next, dist + BigInt(1)]);
        visited.add(next);
      }
    }
  }

  function isPossible(cur, max) {
    return cur >= 0 && cur <= max;
  }
});
