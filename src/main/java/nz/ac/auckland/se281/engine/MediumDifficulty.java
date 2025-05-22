package nz.ac.auckland.se281.engine;

public class MediumDifficulty implements DifficultyStrategy {

  @Override
  public AiStrategy setStrategy(int round, int lastAiScore, String currentStrategy) {
    // set the strategy to medium difficulty
    return new AvoidLastStrategy();
  }

  // Add any additional methods or properties specific to medium difficulty if needed
}
