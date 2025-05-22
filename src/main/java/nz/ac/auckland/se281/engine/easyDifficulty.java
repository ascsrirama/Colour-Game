package nz.ac.auckland.se281.engine;

public class EasyDifficulty implements DifficultyStrategy {

  @Override
  public AiStrategy setStrategy(int round, int lastAiScore, String currentStrategy) {
    // set the strategy to easy difficulty
    return new RandomStrategy();
  }
}
