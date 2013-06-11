//the state-space search tree
public class StateSpace {

	GameState root = null; //root
	GameState[] array = new GameState[2000000];
	boolean goalStateFound = false; //true when goal state is found, signals to stop building the tree
	GameState goal = new GameState();
	
	//initialize with specified inital state
	public StateSpace(GameState initialState) {
		root = initialState;
	}
	
	//this methods constructs the successor states, and builds the tree
	public void buildTree() {
		int indexOfNodeToExpand = 0;
		int indexOfLastElement = 0;
		array[0] = root;
		
		while (!goalStateFound) {
			
			//expand each node; here, expand means construct the successor states
			
			//the current node to expand
			GameState current = array[indexOfNodeToExpand];
			
			//the children of the current node
			GameState child1 = new GameState(current.getConfig());
			GameState child2 = new GameState(current.getConfig());
			GameState child3 = new GameState(current.getConfig());
			GameState child4 = new GameState(current.getConfig());
				
			//left most child
			int change1 = child1.moveBlankUp();
			if (change1 == 1) {                   //if this move changed the configuration
				current.child1 = child1;           //assign current's child1 reference to this child node
//				p("child1 = ");
//				child1.printState();
				child1.parent = current;           //assign this child node's parent reference back to current
//				p("child1.parent = ");
//				(child1.parent).printState();
				array[indexOfLastElement + 1] = child1; //add to array
				indexOfLastElement++;              //increment last filled position in array
			}
			else {
				current.child1 = null;
			}

			//check if child1 is the goal state
			if (child1 != null && equal(child1, goal))
				break;
			
			//left inner child
			int change2 = child2.moveBlankDown();
			if (change2 == 1) {                   //if this move changed the configuration
				current.child2 = child2;
//				p("child2 = ");
//				child2.printState();
				child2.parent = current;
//				p("child2.parent = ");
//				(child2.parent).printState();
				array[indexOfLastElement + 1] = child2; //add to array
				indexOfLastElement++;
			}
			else {
				current.child2 = null;
			}

			//check if goal state
			if (child2 != null && equal(child2, goal))
				break;
			
			//right inner child
			int change3 = child3.moveBlankLeft();
			if (change3 == 1) {                       //if this move changed the configuration
				current.child3 = child3;
//				p("child3 = ");
//				child3.printState();
				child3.parent = current;
//				p("child3.parent = ");
//				(child3.parent).printState();
				array[indexOfLastElement + 1] = child3; //add to array
				indexOfLastElement++;
			}
			else {
				current.child3 = null;
			}

			//check if goal state
			if (child3 != null && equal(child3, goal))
				break;
				
			//right most child
			int change4 = child4.moveBlankRight();
			if (change4 == 1) {                        //if this move changed the configuration
				current.child4 = child4;
//				p("child4 = ");
//				child4.printState();
				child4.parent = current;
//				p("child4.parent = ");
//				(child4.parent).printState();
				array[indexOfLastElement + 1] = child4; //add to array
				indexOfLastElement++;
			}
			else {
				current.child4 = null;
			}

			//check if goal state
			if (child4 != null && equal(child4, goal))
				break;
				

			
			//increment index to expand the next node in array	
			indexOfNodeToExpand++;
						
			//as many as four new nodes could be added on each iteration of the while loop,
			//so if indexOfLaseElement >= array.length - 5 (not ==, because that exact number
			//might never be hit), call doubleArray() to copy elements
			//in array to a new array, twice as large
			if (indexOfLastElement >= array.length - 5)
				doubleArray();
				
		}
	}
	
	public void doubleArray() {
		GameState[] newArray = new GameState[2*array.length];
		
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		
		array = newArray; // now array points to the new array, twice as long
	}
	
	public boolean equal(GameState a, GameState b) {
		int[][] aConfig = a.getConfig();
		int[][] bConfig = b.getConfig();
		
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (aConfig[row][column] != bConfig[row][column])
					return false;
			}
		}
		return true;
	}
	
	//level order traversal
	public void breadthFirstSearch() {
		p("Solving with breadth-first search...");
		java.util.LinkedList<GameState> queue = new java.util.LinkedList<GameState>();
		if (root != null)
			queue.add(root);
			
		while (!queue.isEmpty()) {
			GameState next = queue.remove();
//			next.printState();
			if (equal(next, goal)) { 
				printPathToRoot(next);
			}
			if (next.child1 != null)
				queue.add(next.child1);
			if (next.child2 != null)
				queue.add(next.child2);
			if (next.child3 != null)
				queue.add(next.child3);
			if (next.child4 != null)
				queue.add(next.child4);
		}
	}
	
	public void depthFirstSearch() {
		if (root != null)
			p("Solving with depth-first search...");
			preOrderSearch(root);
	}
	
	//depth-first traversal
	public void preOrderSearch(GameState root) {
//		root.printState();
		if (root.child1 != null) {
			if (equal(root.child1, goal)) {
				printPathToRoot(root.child1);
				System.exit(0);
			}
			preOrderSearch(root.child1);
		}
		if (root.child2 != null) {
			if (equal(root.child2, goal)) {
				printPathToRoot(root.child2);
				System.exit(0);
			}
			preOrderSearch(root.child2);
		}
		if (root.child3 != null) {
			if (equal(root.child3, goal)) {
				printPathToRoot(root.child3);
				System.exit(0);
			}
			preOrderSearch(root.child3);
		}
		if (root.child4 != null) {
			if (equal(root.child4, goal)) {
				printPathToRoot(root.child4);
				System.exit(0);
			}
			preOrderSearch(root.child4);
		}
	}		
	
	public void printPathToRoot(GameState actualGoalState) {
		//follow the parent links from the actualGoalState back up to starting point
		p("This is the path from the goal state up the tree to the root:");
		GameState next = actualGoalState;
		while (next != null) {	
			next.printState();
			next = next.parent;
		}
	}
	
	//for use while debugging
	//prints a String
	public void p(String s) {
		System.out.println(s);
	}
	
	//for use while debugging
	//prints contents of an array
	public void printArray() {
		for (int i = 0; i < array.length; i++) {
			System.out.println(i + " " + array[i]);
		}		
	}
}
	
