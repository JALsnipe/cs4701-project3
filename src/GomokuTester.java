import java.util.Arrays;
import java.util.Scanner;


public class GomokuTester {
	
	static int BOARD_SIZE;
	static int CHAIN_LENGTH;
	static int TIME_LIMIT;
	static char[][] board;
	//static char player;
	
	static char p1char;
	static char p2char;
	
//	static char p1first;
	
	static char humanVsHuman;
	
	public static boolean isValidMove(char[][] board, int[] move) {
		
		if(move[0] > GomokuTester.BOARD_SIZE || move[1] > GomokuTester.BOARD_SIZE) {
			return false;
		}
		
		if(board[move[0]][move[1]] == '.') {
			return true;
		}
		
		return false;

	}
	
	public static void printBoard(char[][] board) {
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void pXHumanMove() {
		//		while(pXmoveBool == true) {
		printBoard(board);
		Scanner in = new Scanner(System.in);
		int[] Xmove = new int[2];
		System.out.println("Player X, please enter an x value");
		Xmove[0] = in.nextInt();
		System.out.println("Player X, please enter an y value");
		Xmove[1] = in.nextInt();

		System.out.println("Move: " + Arrays.toString(Xmove));

		System.out.println("isValidMove(board, PXmove)? " + isValidMove(board, Xmove));

		// check if valid
		// if yes, placeTile, move to next player
		// if not, continue;
		// player first, then computer
		if(!isValidMove(board, Xmove)) {
			pXHumanMove();
		} else {
			System.out.println("In else, inputted valid move.");
//			State newState = new State();
//			char[][] newBoard = Actions.copy(board);
			board[Xmove[0]][Xmove[1]] = 'X';
//			printBoard(board);
//			newState.setData(newBoard);
			if(Actions.checkWinnerX(board, Xmove[0], Xmove[1]) == true) {
				System.out.println("Player X wins!");
				System.exit(0);
			}
		}
		//			pXmoveBool = false;
		//		}
	}
	
	public static void pOHumanMove() {
		//		while(pXmoveBool == true) {
		printBoard(board);
		Scanner in = new Scanner(System.in);
		int[] POmove = new int[2];
		System.out.println("Player O, please enter an x value");
		POmove[0] = in.nextInt();
		System.out.println("Player O, please enter an y value");
		POmove[1] = in.nextInt();

		System.out.println("Move: " + Arrays.toString(POmove));

		System.out.println("isValidMove(board, PXmove)? " + isValidMove(board, POmove));

		// check if valid
		// if yes, placeTile, move to next player
		// if not, continue;
		// player first, then computer
		if(!isValidMove(board, POmove)) {
			pOHumanMove();
		} else {
			System.out.println("In else, inputted valid move.");
//			State newState = new State();
//			char[][] newBoard = Actions.copy(board);
			board[POmove[0]][POmove[1]] = 'O';
//			printBoard(board);
//			newState.setData(newBoard);
			if(Actions.checkWinnerX(board, POmove[0], POmove[1]) == true) {
				System.out.println("Player X wins!");
				System.exit(0);
			}
		}
		//			pXmoveBool = false;
		//		}
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Gomoku!");
		
		System.out.println("Please enter the game board dimension n:");
		BOARD_SIZE = in.nextInt();
		
		board = new char[BOARD_SIZE][BOARD_SIZE];
		//initialize array
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = '.';
			}
		}
		
		System.out.println("Please enter the winning chain length m:");
		CHAIN_LENGTH = in.nextInt();
		
		System.out.println("Please enter the move selection time limit s:");
		TIME_LIMIT = in.nextInt();
		
		System.out.println("Please select your game mode:");
		System.out.println("1. Human (X) vs Human (O)");
		System.out.println("2. Human (X) vs. Computer (O)");
		System.out.println("3. Computer (X) vs. Human (O)");
		System.out.println("4. Randomly-moving player (X) vs. Computer (O)");
		System.out.println("5. Computer (X) vs. Computer (O)");
		
		int choice = in.nextInt();
		
		if(choice == 1) {
			
			while(true) {
				
				pXHumanMove();
				pOHumanMove();
				
			}
		}
		
		else if(choice == 2) {
			while(true) {
				
				pXHumanMove();
				pOComputerMove();
			}
			
		}
		
		//x always go first.  ask the user if the human player is x, or if the computer player is x
		
//		System.out.println("Please player 1 character (x or o):");
//		p1char = in.next().charAt(0);
//		System.out.println("Player 1 is " + p1char + ".");
//		
//		System.out.println("Is player 1 going first? (y or n)");
//		p1first = in.next().charAt(0);
		
//		System.out.println("");
		
//		System.out.println("here");
//		
//		System.out.println("count and check winner test");
//		board[1][1] = 'x';
//		board[2][2] = 'x';
//		board[3][3] = 'x';
//		board[4][4] = 'x';
//		
//		System.out.println(Actions.checkWinnerX(board, 4, 4));
//		System.out.println(Actions.checkWinnerO(board, 4, 4));
		
		// break when you win
		while(true) {
			// ask user for input
			int[] move = new int[2];
			System.out.println("Please enter an x value");
			move[0] = in.nextInt();
			System.out.println("Please enter an y value");
			move[1] = in.nextInt();
			
			// check if valid
			// if yes, placeTile, move to next player
			// if not, continue;
			// player first, then computer
			if(!isValidMove(board, move)) {
				continue;
			} else {
				State newState = new State();
//				char[][] newBoard = copy(board);
//				newBoard[move[0]][move[1]] = player;
//				newState.setData(newBoard);
				
				// algorithm goes here
				
			}
			
			break;
			
			
//			State newState = new State();
//			newState.setData(copy(state.getData()));
			
			
		}
		
		//System.out.println("size: " + BOARD_SIZE + " chain: " + CHAIN_LENGTH + " time: " +TIME_LIMIT);

	}

	private static void pOComputerMove() {
		// TODO Auto-generated method stub
		
		State newState = new State();
		char[][] newBoard = Actions.copy(board);
//		newBoard[move[0]][move[1]] = player;
		newState.setData(newBoard);
		
		System.out.println("in pOComputerMove");
		
		State temp = Actions.minMaxDecision(newState, 'O', 3);
		System.out.println("temp state printed:");
		printBoard(temp.data);
		board = temp.getData();
		System.out.println("score: " + temp.getHeuristic());
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				if(Actions.checkWinnerO(board, i, j) == true) {

					System.out.println("Player O wins!");
					System.exit(0);
				}
			}
		}		
	}

}
