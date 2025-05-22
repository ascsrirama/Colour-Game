package nz.ac.auckland.se281.engine;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.model.Colour;

public class Game {

  public static String AI_NAME = "HAL-9000";

  private int numRounds; // this will track the number of rounds
  private int currentRound; // this will track the current round number
  private String namePlayer; // store the player's name
  private int playerScore = 0; // track the player's score
  private int aiScore = 0; // track the AI's score
  private List<Colour> playerHistory = new ArrayList<>(); // track player's picked colours
  private Difficulty gameDifficulty; // track the difficulty level
  // private String currentStrategy;
  private int lastAiRoundScore; // track the last AI round score

  
  boolean gameRunning = false;

  private AiStrategy aiStrategy;
  private DifficultyStrategy difficultyStrategy;

  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.namePlayer = options[0];
    System.out.println(namePlayer);

    this.currentRound = 1;
    this.numRounds = numRounds;
    this.gameDifficulty = difficulty;

    this.difficultyStrategy = StrategyFactory.createStrategy(difficulty);
    this.aiStrategy = difficultyStrategy.setStrategy(currentRound, lastAiRoundScore, "none");

    this.playerHistory.clear();
    this.playerScore = 0;
    this.aiScore = 0;
    this.lastAiRoundScore = 0;

    // Set the strategy based on the difficulty
    setStrategy(this.difficultyStrategy);

    MessageCli.WELCOME_PLAYER.printMessage(namePlayer);
    this.gameRunning = true;
  }

  // This method assigns the correponding strategy
  private void setStrategy(DifficultyStrategy s) {
    s.setStrategy(
        currentRound,
        lastAiRoundScore,
        aiStrategy == null ? "none" : aiStrategy.getClass().getSimpleName());
  }

  // ======================== PLAY STARTS HERE ==========================
  public void play() {
    if (!gameRunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }

    aiStrategy =
        difficultyStrategy.setStrategy(
            currentRound,
            lastAiRoundScore,
            aiStrategy == null ? "none" : aiStrategy.getClass().getSimpleName());

    // Showing the rounds
    if (currentRound > numRounds) {
      return;
    }
    MessageCli.START_ROUND.printMessage(currentRound, numRounds);

    Colour picked = null;
    Colour guess = null;
    while (true) {
      MessageCli.ASK_HUMAN_INPUT.printMessage();
      String input = Utils.scanner.nextLine();
      String[] parts = input.split(" ");

      if (parts.length != 2) {
        MessageCli.INVALID_HUMAN_INPUT.printMessage();
        continue;
      }
      picked = Colour.fromInput(parts[0]);
      guess = Colour.fromInput(parts[1]);
      if (picked == null || guess == null) {
        MessageCli.INVALID_HUMAN_INPUT.printMessage();
        continue;
      }
      break;
    }
    // Let us check for the power Round
    Colour powerColour = null;
    Boolean isPowerRound = false;
    if (currentRound % 3 == 0) {
      powerColour = Colour.getRandomColourForPowerColour();
      isPowerRound = true;
      MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour);
    }
    // Now we have a valid input, we can proceed with the game logic
    MessageCli.PRINT_INFO_MOVE.printMessage(namePlayer, picked, guess);

    // AI's turn
    Colour aiPicked = aiStrategy.chooseColour();
    Colour aiGuess = aiStrategy.makeGuess(playerHistory);

    // Add player history
    playerHistory.add(picked);

    MessageCli.PRINT_INFO_MOVE.printMessage(AI_NAME, aiPicked, aiGuess);

    // Player Score Tracking
    int playerRoundScore = 0;
    if (guess == aiPicked) {
      playerRoundScore += 1;
      if (isPowerRound && guess == powerColour) {
        playerRoundScore += 2;
      }
    }
    playerScore += playerRoundScore;

    // AI score tracking
    int aiRoundScore = 0;
    if (aiGuess == picked) {
      aiRoundScore += 1;
      if (isPowerRound && aiGuess == powerColour) {
        aiRoundScore += 2;
      }
    }
    aiScore += aiRoundScore;
    // We need to print the outcome of the round
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(namePlayer, playerRoundScore);
    MessageCli.PRINT_OUTCOME_ROUND.printMessage(AI_NAME, aiRoundScore);

    lastAiRoundScore = aiRoundScore;

    currentRound++;
    if (currentRound > numRounds) {
      showStats();
      MessageCli.PRINT_END_GAME.printMessage();
      if (playerScore > aiScore) {
        MessageCli.PRINT_WINNER_GAME.printMessage(namePlayer);

      } else if (aiScore > playerScore) {
        MessageCli.PRINT_WINNER_GAME.printMessage(AI_NAME);
      } else {
        MessageCli.PRINT_TIE_GAME.printMessage();
      }
      gameRunning = false;
    }
  }

  // PLAY ENDS HERE ==========================

  public void showStats() {
    if (!gameRunning) {
      MessageCli.GAME_NOT_STARTED.printMessage();
      return;
    }
    MessageCli.PRINT_PLAYER_POINTS.printMessage(namePlayer, playerScore);
    MessageCli.PRINT_PLAYER_POINTS.printMessage(AI_NAME, aiScore);
  }
}
