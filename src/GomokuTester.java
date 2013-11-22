import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * GomokuTester.java
 * Program tester.  Also Includes some game functions.
 * @author jal2238
 *
 */
public class GomokuTester {
	
	// Constant Global Variables
	static int BOARD_SIZE; // Master board size.
	static int CHAIN_LENGTH; // Winning chain length.
	static int TIME_LIMIT; // Move time limit, in seconds.
	static char[][] board; // Board 2D char array.
	
	/**
	 * isvalidMove checks if a tile placement on the game board is a valid move.
	 * @param board
	 * @param move
	 * @return true or false
	 */
	public static boolean isValidMove(char[][] board, int[] move) {
		
		// Checks if the move is in bounds.
		if(move[0] > GomokuTester.BOARD_SIZE - 1 || move[1] > GomokuTester.BOARD_SIZE - 1) {
			return false;
		}
		
		// Checks if the tile is being placed on an empty space.
		if(board[move[0]][move[1]] == '.') {
			return true;
		}
		
		return false;

	}
	
	/**
	 * printBoard pretty-prints the game board to System.out.
	 * @param board
	 */
	public static void printBoard(char[][] board) {
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * pXHumanMove is called when a human player is placing a tile as player X.
	 */
	public static void pXHumanMove() {
		printBoard(board);
		Scanner in = new Scanner(System.in);
		int[] Xmove = new int[2];
		System.out.println("Player X, please enter a y (row) value");
		Xmove[0] = in.nextInt();
		System.out.println("Player X, please enter an x (column) value");
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
			// In else, valid move.
			board[Xmove[0]][Xmove[1]] = 'X';
			
			if(Actions.checkWinnerX(board, Xmove[0], Xmove[1]) == true) {
				System.out.println("Player X wins!");
				printBoard(board);
				System.exit(0);
			}
		}
	}
	
	/**
	 * pOHumanMove is called when a human player is placing a tile as player O.
	 */
	public static void pOHumanMove() {
		printBoard(board);
		Scanner in = new Scanner(System.in);
		int[] POmove = new int[2];
		System.out.println("Player O, please enter an y (row) value");
		POmove[0] = in.nextInt();
		System.out.println("Player O, please enter an x (column) value");
		POmove[1] = in.nextInt();

		// check if valid
		// if yes, placeTile, move to next player
		// if not, continue;
		// player first, then computer
		if(!isValidMove(board, POmove)) {
			pOHumanMove();
		} else {
			// In else, valid move.
			board[POmove[0]][POmove[1]] = 'O';
			
			if(Actions.checkWinnerX(board, POmove[0], POmove[1]) == true) {
				System.out.println("Player X wins!");
				printBoard(board);
				System.exit(0);
			}
		}
	}

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Gomoku!");
		
		System.out.println("Please enter the game board dimension n:");
		BOARD_SIZE = in.nextInt();
		
		board = new char[BOARD_SIZE][BOARD_SIZE];
		//initialize board
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
		
		if(choice == 1) { // Human (X) vs Human (O)
			
			while(true) {
				
				pXHumanMove();
				pOHumanMove();
				
			}
		}
		
		else if(choice == 2) { // Human (X) vs. Computer (O)
			while(true) {
				
				pXHumanMove();
				pOComputerMove();
			}
			
		}
		
		else if(choice == 3) { // Computer (X) vs. Human (O)
			while(true) {
				pXComputerMove();
				pOHumanMove();
			}
		}
		
		else if(choice == 4) { // Randomly-moving player (X) vs. Computer (O)
			while(true) {
				randomComputerMoveX();
				pOComputerMove();
			}
		}
		
		else if(choice == 5) { // Computer (X) vs. Computer (O)
			while(true) {
				pXComputerMove();
				pOComputerMove();
			}
		}
	}

	/**
	 * pOComputerMove is called when a computer player is placing a tile as player O.
	 */
	private static void pOComputerMove() {
		
		State newState = new State();
		char[][] newBoard = Actions.copy(board);
		newState.setData(newBoard);
		newState.setAlpha(Double.MAX_VALUE);
		newState.setBeta(Double.MIN_VALUE);
		
		State temp = Actions.minMaxDecision(newState, 'O', 3);
		printBoard(temp.data);
		board = temp.getData();
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				if(Actions.checkWinnerO(board, i, j) == true) {

					System.out.println("Player O wins!");
					printBoard(board);
					System.exit(0);
				}
			}
		}		
	}
	
	/**
	 * pXComputerMove is called when a computer player is placing a tile as player X.
	 */
	private static void pXComputerMove() {
		
		State newState = new State();
		char[][] newBoard = Actions.copy(board);
		newState.setData(newBoard);
		newState.setAlpha(Double.MAX_VALUE);
		newState.setBeta(Double.MAX_VALUE * -1);
		
		
		State temp = Actions.minMaxDecision(newState, 'X', 3);
		printBoard(temp.data);
		board = temp.getData();
		
		for(int i = 0; i < BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				if(Actions.checkWinnerO(board, i, j) == true) {

					System.out.println("Player X wins!");
					printBoard(board);
					System.exit(0);
				}
			}
		}		
	}
	
	/**
	 * randomComputerMoveX generates a random valid move for player X.
	 */
	private static void randomComputerMoveX() {
				
		Random generator = new Random();
		
		int x = generator.nextInt(BOARD_SIZE);
		int y = generator.nextInt(BOARD_SIZE);
		System.out.println("Random Computer Move:");
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		
		int[] compMove = new int[2];
		compMove[0] = y;
		compMove[1] = x;
		
		if(!isValidMove(board, compMove)) {
			randomComputerMoveX();
		} else {
			// In else, valid move
			board[compMove[0]][compMove[1]] = 'X';
			if(Actions.checkWinnerX(board, compMove[0], compMove[1]) == true) {
				System.out.println("Player X wins!");
				printBoard(board);
				System.exit(0);
			}
		}
	}
}