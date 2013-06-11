//each gamestate is a node in the tree that also contains the current configuration
public class GameState {
	
	GameState parent = null;
	GameState child1 = null;
	GameState child2 = null;
	GameState child3 = null;
	GameState child4 = null;
	
	int[][] configuration;
	int xIndexOfBlank;
	int yIndexOfBlank;
	/* index as follows (y,x):  00,01,02
								 		 10,11,12
								       20,21,22
	*/
		
	//initialize puzzle to goal state 
	public GameState() {
		configuration = new int[][]{{0, 1, 2},{3, 4, 5}, {6, 7, 8}};
		xIndexOfBlank = 0;
		yIndexOfBlank = 0;
	}
	
	//initialize puzzle to specific state
	public GameState(int[][] conf) {
		configuration = conf;
		int[] coordinates = findBlank(conf);
		xIndexOfBlank = coordinates[1];
		yIndexOfBlank = coordinates[0];
	}
	
	//need to find where the blank is if initialized to a specific state
	public int[] findBlank(int[][] conf) {
		int[] coordinates = new int[2];
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (conf[row][column] == 0) {
					coordinates[0] = row;
					coordinates[1] = column;
					return coordinates;
				}
			}
		}
		return null;
	}
				
	// THE NEXT FOUR METHODS ARE USED FOR RANDOMIZING A GIVEN STATE, 
	// AND FOR CONSTRUCTING THE SUCCESSORS OF A GIVEN STATE      
	
	public int moveBlankUp() {
		//if blank not already in top row
		if (yIndexOfBlank != 0) {
			int temp = configuration[yIndexOfBlank - 1][xIndexOfBlank]; 
			configuration[yIndexOfBlank][xIndexOfBlank] = temp;
			configuration[yIndexOfBlank - 1][xIndexOfBlank] = 0;
			yIndexOfBlank--;
			return 1; //if sucessful
		}
		return 0; //if unsucessful
	}
	
	public int moveBlankDown() {
		//if blank not already in bottom row
		if (yIndexOfBlank != 2) {
			int temp = configuration[yIndexOfBlank + 1][xIndexOfBlank]; 
			configuration[yIndexOfBlank][xIndexOfBlank] = temp;
			configuration[yIndexOfBlank + 1][xIndexOfBlank] = 0;
			yIndexOfBlank++;
			return 1;
		}
		return 0;
	}	
	
	public int moveBlankLeft() {
		//if blank not already in left column
		if (xIndexOfBlank != 0) {
			int temp = configuration[yIndexOfBlank][xIndexOfBlank - 1]; 
			configuration[yIndexOfBlank][xIndexOfBlank] = temp;
			configuration[yIndexOfBlank][xIndexOfBlank - 1] = 0;
			xIndexOfBlank--;
			return 1;
		}
		return 0;
	}
	
	public int moveBlankRight() {
		//if blank not already in right column
		if (xIndexOfBlank != 2) {
			int temp = configuration[yIndexOfBlank][xIndexOfBlank + 1]; 
			configuration[yIndexOfBlank][xIndexOfBlank] = temp;
			configuration[yIndexOfBlank][xIndexOfBlank + 1] = 0;
			xIndexOfBlank++;
			return 1;
		}
		return 0;
	}
	
	// PUZZLE RANDOMIZER
	
	//randomize this state with specified number of moves from goal state
	public void randomize(int numberOfMoves) {
		for (int i = 0; i < numberOfMoves; i++) {
			int whichMove = (int)((Math.random() * 4) + 1); //get random number between 0 and 5
//			System.out.println("int rand = " + whichMove);
			switch (whichMove) {
				case 1: moveBlankLeft();
//					System.out.println("move left");
					break;
				case 2: moveBlankRight();
//					System.out.println("move right");
					break;
				case 3: moveBlankUp();
//					System.out.println("move up");
					break;
				case 4: moveBlankDown();
//					System.out.println("move down");
			}
//			System.out.println("intermediate state");
//			printState();
		}
	}
	
	//print the current puzzle configuration to the terminal
	public void printState() {
		for (int row = 0; row < configuration.length; row++) {
			for (int column = 0; column < configuration[0].length; column++) {
				int number = configuration[row][column];
				if (number == 0)
					System.out.print("  ");
				else
					System.out.print(number + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	//use this method when building succesor states of a given state; make a new instance of GameState(), 
	//and initialize if with its parent's configuration, then make changes to it: new GameState(parent.getConfig())
	public int[][] getConfig() {
		int[][] newConfig = new int[3][3];
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				newConfig[row][column] = configuration[row][column];
			}
		}
		return newConfig;
	}
	
}
		
