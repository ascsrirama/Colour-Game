package nz.ac.auckland.se281.engine;

public class easyDifficulty implements DifficultyStrategy {


  private AiStrategy strategy;


	@Override
	public AiStrategy setStrategy(int round, int lastAiScore, String currentStrategy) {
    // set the strategy to easy difficulty
    return new RandomStrategy(); 

  
  }

}
