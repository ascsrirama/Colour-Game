

package nz.ac.auckland.se281.engine;
import nz.ac.auckland.se281.model.Colour;
import java.util.List;

public interface AiStrategy {
  Colour chooseColour();

  Colour makeGuess(List<Colour> history);

  //void updateHistory(Colour humanPick);

}
