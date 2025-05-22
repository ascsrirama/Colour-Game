package nz.ac.auckland.se281.engine;

import java.util.List;
import nz.ac.auckland.se281.model.Colour;

public class LeastUsedStrategy implements AiStrategy {
  private Colour lastPick;

  @Override
  public Colour chooseColour() {
    lastPick = Colour.getRandomColourForAi();
    return lastPick;
  }

  // this is the guess and it will guess what the player has chosen
  @Override
  public Colour makeGuess(List<Colour> history) {
    // This will keep track of the number of times each colour has been used
    // and return the least used colour
    int redCount = 0;
    int greenCount = 0;
    int blueCount = 0;
    int yellowCount = 0;

    for (Colour colour : history) {
      switch (colour) {
        case RED:
          redCount++;
          break;
        case GREEN:
          greenCount++;
          break;
        case BLUE:
          blueCount++;
          break;
        case YELLOW:
          yellowCount++;
          break;
      }
    }

    int minCount = Math.min(Math.min(redCount, greenCount), Math.min(blueCount, yellowCount));

    // Return the first colour (by enum order) with the minimum count
    if (redCount == minCount) return Colour.RED;
    if (greenCount == minCount) return Colour.GREEN;
    if (blueCount == minCount) return Colour.BLUE;
    return Colour.YELLOW;
  }
}
