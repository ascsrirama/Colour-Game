package nz.ac.auckland.se281.engine;

import java.util.List;
import nz.ac.auckland.se281.model.Colour;

public class AvoidLastStrategy implements AiStrategy {
  private Colour lastPick;
  private Colour lastGuess;

  @Override
  public Colour chooseColour() {
    lastPick = Colour.getRandomColourForAi();
    return lastPick;
  }

  // this will guess what the player has picked and it will also avoid the last guess
  @Override
  public Colour makeGuess(List<Colour> history) {

    if (history.isEmpty()) {
      // If there is no history, pick a random colour
      lastGuess = Colour.getRandomColourForAi();
      return lastGuess;
    }
    Colour previousColour = history.get(history.size() - 1);
    // This will pick avoid the last guess
    lastGuess = Colour.getRandomColourExcluding(previousColour);
    return lastGuess;
  }
}
