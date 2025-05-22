
package nz.ac.auckland.se281.engine;
import nz.ac.auckland.se281.model.Colour;
import java.util.List;

public class AvoidLastStrategy implements AiStrategy {
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
      if (history.isEmpty() ) { 
        lastGuess = Colour.getRandomColourForAi();
        return lastGuess;
      }
      Colour previousColour = history.get(history.size() - 1);
        lastGuess = Colour.getRandomColourExcluding(previousColour);
        return lastGuess;
    }
}


