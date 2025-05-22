package nz.ac.auckland.se281.engine;

public class hardDifficulty implements DifficultyStrategy {
  private AiStrategy strategy;

  @Override
public AiStrategy setStrategy(int round, int lastAiScore, String currentStrategy) {
  if (round == 1 || round == 2) {
    return new RandomStrategy();
  } else if (round == 3) {
    return new LeastUsedStrategy();
  } else {
    if (lastAiScore == 0) {
      if (currentStrategy.equals("LeastUsedStrategy")) {
        return new AvoidLastStrategy();
      } else {
        return new LeastUsedStrategy();
      }
    } else {
      // Keep current strategy
      if (currentStrategy.equals("LeastUsedStrategy")) {
        return new LeastUsedStrategy();
      } else if (currentStrategy.equals("AvoidLastStrategy")) {
        return new AvoidLastStrategy();
      } else {
        return new LeastUsedStrategy();
      }
    }
  }
}
}



