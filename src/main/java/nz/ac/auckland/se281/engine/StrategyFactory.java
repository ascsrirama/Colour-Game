package nz.ac.auckland.se281.engine;

import nz.ac.auckland.se281.Main.Difficulty;

//THIS WILL SET THE STRATEGY FOR THE AI BASED ON THE DIFFICULTY	

public class StrategyFactory {

  public static DifficultyStrategy createStrategy(Difficulty difficulty) {
    // choose strategy
    switch (difficulty) {
      case EASY:

        // RandomStrategy randomStrategy = new RandomStrategy();
        // Difficulty easyDifficulty = new easyDifficulty();

        return new EasyDifficulty();
      case MEDIUM:
        // AvoidLastStrategy avoidLastStrategy = new AvoidLastStrategy();
        // return avoidLastStrategy;
        // Difficulty easyDifficulty = new easyDifficulty();
        return new MediumDifficulty();
      case HARD:
        // LeastUsedStrategy leastUsedStrategy = new LeastUsedStrategy();
        // return leastUsedStrategy;
        // Difficulty hardDifficulty = new hardDifficulty();
        return new HardDifficulty();
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


  // This will be used to get the strategy name for the current strategy for tracking purpose
  public static String getStrategyName(AiStrategy strategy) {
    // Checking the type based on instances	
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
