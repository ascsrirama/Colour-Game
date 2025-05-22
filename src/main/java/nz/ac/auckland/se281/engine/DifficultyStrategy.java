package nz.ac.auckland.se281.engine;

public interface DifficultyStrategy {
  AiStrategy setStrategy(int round, int lastAiScore, String currentStrategy);
}
