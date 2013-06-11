//for testing the methods used by the puzzle randomizer
import java.util.Scanner;
public class TestBed {
	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		
		GameState state = new GameState();
				
		System.out.println("Initial state:");
		state.printState();
		
		state.randomize(20);		
		System.out.println("Initial state after being rendomized with 20 moves:");
		state.printState();

		System.out.print("Please enter which way to move the blank (u, d, l, r - quit to end): ");
		String move = input.next();
		System.out.println();
		
		while (!move.equals("quit")) {
		
			if (move.equals("r")) {
				System.out.println("move right");
				state.moveBlankRight();
				state.printState();
			}
		
			if (move.equals("d")) {						
				System.out.println("move down");
				state.moveBlankDown();
				state.printState();
			}
			
			if (move.equals("l")) {		
				System.out.println("move left");
				state.moveBlankLeft();
				state.printState();
			}
			
			if (move.equals("u")) {			
				System.out.println("move up");
				state.moveBlankUp();
				state.printState();
			}	
			
			move = input.next();
		}
	}
}
