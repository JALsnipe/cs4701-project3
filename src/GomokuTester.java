import java.util.Scanner;


public class GomokuTester {
	
	static int BOARD_SIZE;
	static int CHAIN_LENGTH;
	static int TIME_LIMIT;
	static char[][] board;
	static char player;
	
	public static boolean isValidMove(char[][] board, int[] move) {
		
		if(move[0] > GomokuTester.BOARD_SIZE || move[1] > GomokuTester.BOARD_SIZE) {
			return false;
		}
		
		if(board[move[0]][move[1]] == '.') {
			return true;
		}
		
		return false;

	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Gomoku!");
		
		System.out.println("Please enter the game board dimension n:");
		BOARD_SIZE = in.nextInt();
		
		board = new char[BOARD_SIZE][BOARD_SIZE];
		
		System.out.println("Please enter the winning chain length m:");
		CHAIN_LENGTH = in.nextInt();
		
		System.out.println("Please enter the move selection time limit s:");
		TIME_LIMIT = in.nextInt();
		
		System.out.println("Please enter x or o:");
		player = in.next().charAt(0);
		
		System.out.println(player);
		
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
				char[][] newBoard = copy(board);
				newBoard[move[0]][move[1]] = player;
				newState.setData(newBoard);
				
				// algorithm goes here
				
			}
			
			break;
			
			
//			State newState = new State();
//			newState.setData(copy(state.getData()));
			
			
		}
		
		//System.out.println("size: " + BOARD_SIZE + " chain: " + CHAIN_LENGTH + " time: " +TIME_LIMIT);

	}

}
