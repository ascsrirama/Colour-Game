package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.model.Colour;



public class Game {

  private AiStrategy aiStrategy;

  public static String AI_NAME = "HAL-9000";
  
  private int numRounds; // this will track the number of rounds
  private int currentRound; // this will track the current round number
  private String namePlayer; // store the player's name
  private int playerScore = 0; // track the player's score
  private int aiScore = 0; // track the AI's score

  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    this.namePlayer = options[0];
    System.out.println(namePlayer);
    //MessageCli.WELCOME_PLAYER.printMessage(namePlayer);
    this.currentRound = 1;
    this.numRounds = numRounds;

    //choose strategy 
    switch (difficulty) {
      case EASY: 
        aiStrategy = new RandomStrategy();
        break;
      case MEDIUM:
        // TODO: Implement MEDIUM strategy
        break;
      case HARD:
        // TODO: Implement HARD strategy
        break;
    }
    MessageCli.WELCOME_PLAYER.printMessage(namePlayer);
  }
// PLAY STARTS HERE ==========================
  public void play() {
   

    // Showing the rounds 
    if(currentRound > numRounds) { 
      return;
    }
      MessageCli.START_ROUND.printMessage(currentRound, numRounds);


      //Power colour thing 
      if (currentRound % 3 == 0) {
        Colour powerColour = Colour.getRandomColourForPowerColour();
        MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour);
      }

      //Boolean validInput = false;
      Colour picked = null;
      Colour guess = null;
      while(true) {  
        MessageCli.ASK_HUMAN_INPUT.printMessage();
        String input = Utils.scanner.nextLine();
        String [] parts = input.split(" ");

        if (parts.length != 2) { 
          MessageCli.INVALID_HUMAN_INPUT.printMessage();
          continue;
        }

        picked = Colour.fromInput(parts[0]);
        guess = Colour.fromInput(parts[1]);
        if(picked == null || guess == null) { 
          MessageCli.INVALID_HUMAN_INPUT.printMessage();
          continue;
        } 

        // Valid input received, break out of the loop
        break;
      }
      // Now we have a valid input, we can proceed with the game logic
      MessageCli.PRINT_INFO_MOVE.printMessage(namePlayer, picked, guess);

      // AI's turn
      Colour aiPicked = aiStrategy.chooseColour();
      Colour aiGuess = aiStrategy.makeGuess();

      MessageCli.PRINT_INFO_MOVE.printMessage(AI_NAME, aiPicked, aiGuess);
      aiStrategy.updateHistory(picked);





      // Let us check for the power Round
      Colour powerColour = null;
      Boolean isPowerRound = false;

      if (currentRound % 3 == 0) {
        powerColour = Colour.getRandomColourForPowerColour();
        isPowerRound = true;
        MessageCli.PRINT_POWER_COLOUR.printMessage(powerColour);
      }

      // Player Score Tracking 
      int playerRoundScore = 0;
      if (guess == aiPicked) {
        playerRoundScore++;
        if (isPowerRound && picked == powerColour) {
          playerRoundScore += 2;
        }
      }
      playerScore += playerRoundScore;
      
      //AI score tracking
      int aiRoundScore = 0;
      if(aiGuess == picked) { 
        aiRoundScore++;
        if(isPowerRound && aiGuess == powerColour) { 
          aiRoundScore += 2;
        }
      }
      aiScore += aiRoundScore;
      // We need to print the outcome of the round 
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(namePlayer, playerRoundScore);
      MessageCli.PRINT_OUTCOME_ROUND.printMessage(AI_NAME, aiRoundScore);

      currentRound++;

    }
// PLAY ENDS HERE ==========================

  public void showStats() {}
}
