package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;

public class StrategyFactory {

  public static DifficultyStrategy createStrategy(Difficulty difficulty) {
    // choose strategy
    switch (difficulty) {
      case EASY:

        //RandomStrategy randomStrategy = new RandomStrategy();
        //Difficulty easyDifficulty = new easyDifficulty(); 
        return new easyDifficulty();
      case MEDIUM:
        // AvoidLastStrategy avoidLastStrategy = new AvoidLastStrategy();
        // return avoidLastStrategy;
        //Difficulty easyDifficulty = new easyDifficulty(); 
        return new mediumDifficulty();
      case HARD:
        // LeastUsedStrategy leastUsedStrategy = new LeastUsedStrategy();
        // return leastUsedStrategy;
        //Difficulty hardDifficulty = new hardDifficulty(); 
        return new hardDifficulty();
      default:
        throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
    }
  }

  // public static AiStrategy getHardStrategy(int round, int lastAiScore, String currentStrategy) {
  //   if (round == 1 || round == 2) {
  //     return new RandomStrategy();
  //   } else if (round >= 4) {
  //     if (lastAiScore == 0) {
  //       if ("Least Used".equals(currentStrategy)) {
  //         return new AvoidLastStrategy();
  //       } else {
  //         return new LeastUsedStrategy();
  //       }
  //     } else {
  //       return new LeastUsedStrategy();
  //     }
  //   }
  //   return new LeastUsedStrategy(); // Default for round 3
  // }

  public static String getStrategyName(AiStrategy strategy) {
    if (strategy instanceof RandomStrategy) {
      return "Random";
    } else if (strategy instanceof AvoidLastStrategy) {
      return "Avoid Last";
    } else if (strategy instanceof LeastUsedStrategy) {
      return "Least Used";
    }
    return "Unknown";
  }

}
