package nz.ac.auckland.se281.engine;
import nz.ac.auckland.se281.model.Colour;
import java.util.List;

public class LeastUsedStrategy implements AiStrategy {
    private Colour lastPick;
    private Colour lastGuess;

    @Override
    public Colour chooseColour() {
      lastPick = Colour.getRandomColourForAi();
      return lastPick;
        
    }

    // this is the guess and it will guess what the player has chosen
    @Override
    public Colour makeGuess(List<Colour> history) {
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

    // Find the least used colour
    // int minCount = Integer.MAX_VALUE;
    // Colour leastUsedColour = Colour.RED; // Default to RED, will be updated

    // if (redCount < minCount) {
    //     minCount = redCount;
    //     leastUsedColour = Colour.RED;
    // }
    // if (greenCount < minCount) {
    //     minCount = greenCount;
    //     leastUsedColour = Colour.GREEN;
    // }
    // if (blueCount < minCount) {
    //     minCount = blueCount;
    //     leastUsedColour = Colour.BLUE;
    // }
    // if (yellowCount < minCount) {
    //     minCount = yellowCount;
    //     leastUsedColour = Colour.YELLOW;
    // }

    // return leastUsedColour;

    int minCount = Math.min(Math.min(redCount, greenCount), Math.min(blueCount, yellowCount));

    // Return the first colour (by enum order) with the minimum count
    if (redCount == minCount) return Colour.RED;
    if (greenCount == minCount) return Colour.GREEN;
    if (blueCount == minCount) return Colour.BLUE;
    return Colour.YELLOW;
    
}
}