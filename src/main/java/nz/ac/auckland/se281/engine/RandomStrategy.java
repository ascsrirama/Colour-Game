package nz.ac.auckland.se281.engine;
import nz.ac.auckland.se281.model.Colour;

public class RandomStrategy implements AiStrategy {
    private Colour lastPick;
    private Colour lastGuess;

    @Override
    public Colour chooseColour() {
        lastPick = Colour.getRandomColourForAi();
        return lastPick;
    }

    @Override
    public Colour makeGuess(java.util.List<Colour> history) {
      
        lastGuess = Colour.getRandomColourForAi();
        return lastGuess;
    }

    public void updateHistory(Colour humanPick) {
        // Nothing to remember in this strategy
    }
  
}
