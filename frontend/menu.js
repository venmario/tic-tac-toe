import { startGame, resetGame } from "./game.js";

let mode = null;
let gridSize = null;
let difficulty = null;

const singlePlayerBtn = document.getElementById("single-player-button");
const twoPlayerBtn = document.getElementById("two-player-button");
const gridOptions = document.getElementById("grid-options");
const difficultyOptions = document.getElementById("difficulty-options");
const resetButton = document.getElementById("reset-button");

function setActive(selector, clickedBtn) {
  document.querySelectorAll(selector).forEach(b => b.classList.remove("active"));
  if (clickedBtn) clickedBtn.classList.add("active");
}
function clearActive(selector) {
  document.querySelectorAll(selector).forEach(b => b.classList.remove("active"));
}

singlePlayerBtn.addEventListener("click", () => {
  mode = "single";
  setActive("#game-menu > button", singlePlayerBtn);
  gridOptions.classList.remove("hidden");
  if (gridSize === 3) difficultyOptions.classList.remove("hidden");
});

twoPlayerBtn.addEventListener("click", () => {
  mode = "two";
  setActive("#game-menu > button", twoPlayerBtn);
  gridOptions.classList.remove("hidden");
  difficultyOptions.classList.add("hidden");
  difficulty = null;
  clearActive(".difficulty-button");
});

document.querySelectorAll(".grid-button").forEach(btn => {
  btn.addEventListener("click", () => {
    gridSize = parseInt(btn.dataset.size, 10);
    setActive(".grid-button", btn);

    if (mode === "single" && gridSize === 3) {
      difficultyOptions.classList.remove("hidden");
    } else {
      difficultyOptions.classList.add("hidden");
      difficulty = null;
      clearActive(".difficulty-button");
      startGame(gridSize, mode, difficulty);
    }
  });
});

document.querySelectorAll(".difficulty-button").forEach(btn => {
  btn.addEventListener("click", () => {
    difficulty = btn.dataset.level;
    setActive(".difficulty-button", btn);
    if (gridSize) startGame(gridSize, mode, difficulty);
  });
});

resetButton.addEventListener("click", () => {
  resetGame();
  mode = null;
  gridSize = null;
  difficulty = null;
  clearActive("#game-menu > button");
  clearActive(".grid-button");
  clearActive(".difficulty-button");
  gridOptions.classList.add("hidden");
  difficultyOptions.classList.add("hidden");
});
