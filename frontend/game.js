const gameBoard = document.getElementById("game-board");
const resetButton = document.getElementById("reset-button");
const winnerBox = document.getElementById("winner-box");
const turnBox = document.getElementById("turn-box");
const infoBox = document.getElementById("info-box");

let gridSize = null;
let mode = null;
let difficulty = null;
let currentBoard = [];
let currentPlayer = "x";

function boardToString(board) {
  return board.map(row => row.join(",")).join(";");
}
function stringToBoard(str) {
  if (!str) return Array.from({ length: gridSize }, () => Array(gridSize).fill(""));
  return str.split(";").map(row => row.split(","));
}
function updateUI(boardStr) {
  currentBoard = stringToBoard(boardStr);
  const cells = document.querySelectorAll(".cell");
  cells.forEach((cell, i) => {
    const row = Math.floor(i / gridSize);
    const col = i % gridSize;
    cell.textContent = currentBoard[row][col] || "";
  });
}
function showWinner(winner) {
  if (winner) {
    winnerBox.textContent = winner === "Tie" ? "It’s a Tie!" : `Winner: ${winner}`;
    winnerBox.classList.remove("hidden");
    turnBox.classList.add("hidden"); // hide turn when game ends
  }
}
function updateTurn(player) {
  turnBox.textContent = `Current Turn: ${player.toUpperCase()}`;
  turnBox.classList.remove("hidden");
}

export function startGame(size, selectedMode, selectedDifficulty) {
  gridSize = size;
  mode = selectedMode;
  difficulty = selectedDifficulty;
  currentBoard = Array.from({ length: size }, () => Array(size).fill(""));
  currentPlayer = "x";

  gameBoard.innerHTML = "";
  resetButton.classList.remove("hidden");
  winnerBox.classList.add("hidden");

  const needed = size === 3 ? 3 : 4;
  infoBox.textContent = `Get ${needed} in a row to win!`;
  infoBox.classList.remove("hidden");

  const cellSize = Math.floor(400 / size);
  gameBoard.style.gridTemplateColumns = `repeat(${size}, ${cellSize}px)`;
  gameBoard.style.gridTemplateRows = `repeat(${size}, ${cellSize}px)`;
  gameBoard.style.gap = "5px";

  for (let i = 0; i < size * size; i++) {
    const cell = document.createElement("div");
    cell.classList.add("cell");
    cell.style.width = `${cellSize}px`;
    cell.style.height = `${cellSize}px`;

    cell.addEventListener("click", () => handleCellClick(i));
    gameBoard.appendChild(cell);
  }

  updateUI(boardToString(currentBoard));
  updateTurn(currentPlayer);
}

export function resetGame() {
  gameBoard.innerHTML = "";
  resetButton.classList.add("hidden");
  winnerBox.classList.add("hidden");
  turnBox.classList.add("hidden");
  infoBox.classList.add("hidden");
  currentBoard = [];
  currentPlayer = "x";
}

async function handleCellClick(index) {
  const row = Math.floor(index / gridSize);
  const col = index % gridSize;

  if (currentBoard[row][col] !== "" || !winnerBox.classList.contains("hidden")) return;

  currentBoard[row][col] = currentPlayer;
  updateUI(boardToString(currentBoard));

  const requestBody = {
    board: boardToString(currentBoard),
    boardGrid: { x: gridSize, y: gridSize },
    playerLetter: currentPlayer,
    totalConsecutive: gridSize === 3 ? 3 : 4
  };

  try {
    let response, data;

    if (mode === "two") {
      response = await fetch("http://localhost:8080/api/play/human", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestBody)
      });
      data = await response.json();

      updateUI(data.board);
      if (data.winner) return showWinner(data.winner);

      currentPlayer = currentPlayer === "x" ? "o" : "x";
      updateTurn(currentPlayer);

    } else if (mode === "single") {
      response = await fetch("http://localhost:8080/api/play/human", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestBody)
      });
      data = await response.json();

      updateUI(data.board);
      if (data.winner) return showWinner(data.winner);

      let aiUrl;
      if (gridSize === 3) {
        aiUrl = difficulty === "unbeatable"
          ? "http://localhost:8080/api/play/unbeatable"
          : "http://localhost:8080/api/play/random";
      } else {
        aiUrl = "http://localhost:8080/api/play/random";
      }

      const aiRequest = {
        ...requestBody,
        board: data.board,
        playerLetter: currentPlayer === "x" ? "o" : "x"
      };

      updateTurn("AI"); 

      response = await fetch(aiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(aiRequest)
      });
      data = await response.json();

      updateUI(data.board);
      if (data.winner) return showWinner(data.winner);

      updateTurn(currentPlayer);
    }
  } catch (err) {
    console.error("Error:", err);
  }
}
