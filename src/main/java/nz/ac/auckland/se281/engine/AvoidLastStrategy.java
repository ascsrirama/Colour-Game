
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


/*Avoid Last Colour Strategy
This strategy selects the AI’s own colour randomly. However, for the guessed colour, it randomly picks a colour excluding the one the human chose in the previous round.
The idea is that the human is unlikely to choose the same colour two times in a row.

For example, if in the first round the player chose RED and guessed GREEN, then in the second round, the AI will guess randomly among GREEN, BLUE, and YELLOW, but not RED.

You should use the method Colour.getRandomColourExcluding(Colour exclude) (already implemented for you) to help with this. */