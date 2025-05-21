package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;
import nz.ac.auckland.se281.model.Colour;

public class Game {
  public static String AI_NAME = "HAL-9000";
  
  private int numRounds; // this will track the number of rounds
  private int currentRound; // this will track the current round number

  public Game() {}

  public void newGame(Difficulty difficulty, int numRounds, String[] options) {
    String namePlayer = options[0];
    System.out.println(namePlayer);
    MessageCli.WELCOME_PLAYER.printMessage(namePlayer);

    this.currentRound = 1;
    this.numRounds = numRounds;
  }

  public void play() {
   

    // Showing the rounds 
    if(currentRound > numRounds) { 
      return;
    }
      MessageCli.START_ROUND.printMessage(currentRound, numRounds);

      //Boolean validInput = false;
        while(true) {  
          MessageCli.ASK_HUMAN_INPUT.printMessage();
          String input = Utils.scanner.nextLine();
          String [] parts = input.split(" ");

          if (parts.length != 2) { 
            MessageCli.INVALID_HUMAN_INPUT.printMessage();
          continue;
          }

          Colour picked = Colour.fromInput(parts[0]);
          Colour guess = Colour.fromInput(parts[1]);
          if(picked == null || guess == null) { 
            MessageCli.INVALID_HUMAN_INPUT.printMessage();
            continue;
          }

          // Valid input received, break out of the loop
          break;
        }
        
      currentRound++;
    }
  

  public void showStats() {}
}
