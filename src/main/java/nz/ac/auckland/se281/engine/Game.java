package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;
import nz.ac.auckland.se281.cli.MessageCli;
import nz.ac.auckland.se281.cli.Utils;

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
    MessageCli.START_ROUND.printMessage(currentRound, numRounds);
  }

  public void play() {
    MessageCli.ASK_HUMAN_INPUT.printMessage();
    //String input = Utils.scanner.nextLine();
    if(currentRound <= numRounds) { 
      MessageCli.START_ROUND.printMessage(currentRound, numRounds);
      currentRound++;
    } else { 

    }

  }

  public void showStats() {}
}
