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

  const commands = [];

  for (let i = 0; i < N; i++) {
    const command = input[i + 1].split(" ");

    if (i === N - 1) {
      end = Number(command[2]);
    }

    commands.push(command);
  }

  let answer = "";

  const stk = [];
  stk.push(["", 0]);

  commands.forEach(([cmd, a, time], idx) => {
    if (cmd === "type") {
      answer += a;
      stk.push([answer, time]);
    } else {
      let i = idx;

      changeTime = time - Number(a) - 1;

      while (i >= 0 && changeTime < stk[i][1]) {
        i--;
      }

      if (i < 0) {
        answer = "";
      } else {
        answer = stk[i][0];
      }
      stk.push([answer, time]);
    }
  });

  console.log(answer);
});
