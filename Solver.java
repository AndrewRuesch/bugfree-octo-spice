public class Solver {
	public static void main(String[] args) {
		
//		GameState initialState = new GameState(new int[][]{{3,1,2},{4,0,5},{6,7,8}});
		GameState initialState = new GameState();   
		System.out.print("Initial state\n");
		initialState.printState();
		initialState.randomize(20);
		System.out.println("Initial state randomized with 20 moves:");
		initialState.printState();
		StateSpace s = new StateSpace(initialState);
		s.buildTree();
		s.breadthFirstSearch();
//		s.depthFirstSearch();

		
	}
}

