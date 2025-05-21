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
    public Colour makeGuess() {
        lastGuess = Colour.getRandomColourExcluding(lastPick);
        return lastGuess;
    }

    @Override
    public void updateHistory(Colour humanPick) {
        // No history to update in this strategy
    }

  
}
