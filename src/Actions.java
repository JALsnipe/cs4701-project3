import java.util.ArrayList;
import java.util.Date;

/**
 * Actions.java
 * Contains various game actions.
 * @author jal2238
 *
 */

public class Actions {

	/**
	 * copy creates a deep copy of a 2D char array.
	 * @param input
	 * @return char[][]
	 */
	public static char[][] copy(char[][] input) {
		char[][] copy = new char[input.length][];

		for (int i = 0; i < copy.length; i++) {
			char[] member = new char[input[i].length];
			System.arraycopy(input[i], 0, member, 0, input[i].length);
			copy[i] = member;
		}

		return copy;
	}

	/**
	 * generateChildren generates children from a parent node.
	 * @param state
	 * @param player
	 * @return ArrayList<State> of child states
	 */
	public static ArrayList<State> generateChildren(State state, char player) {

		ArrayList<State> list = new ArrayList<State>();

		char[][] board = copy(state.getData());

		for(int i = 0; i < GomokuTester.BOARD_SIZE; i++) {
			for(int j = 0; j < GomokuTester.BOARD_SIZE; j++) {

				char newBoard[][] = new char[GomokuTester.BOARD_SIZE][GomokuTester.BOARD_SIZE];

				newBoard = copy(board);

				if(board[i][j] == '.') {
					State newState = new State();
					newBoard[i][j] = player;
					newState.setData(newBoard);
					list.add(newState);
				}
			}
		}
		return list;
	}

	/**
	 * count counts the number of a specific character in a row, in a specified direction.
	 * This function builds on a similar count function examined in the textbook
	 * "Programming: Introduction to Programming Using JAVA" by David J. Eck.
	 * @param board
	 * @param player
	 * @param row
	 * @param col
	 * @param dirX
	 * @param dirY
	 * @return int count, the number of pieces counted
	 */
	public static int count(char[][] board, char player, int row, int col, int dirX, int dirY) {

		// Horizontal (1,0)
		// Vertical (0,1)
		// Diagonal 1 (1,1)
		// Diagonal 2 (1,-1)

		int count = 0;

		int r, c = 0;

		r = row + dirX;
		c = col + dirY;

		while ( r >= 0 && r < GomokuTester.BOARD_SIZE && c >= 0 && c < GomokuTester.BOARD_SIZE && board[r][c] == player ) {

			count++;
			r += dirX;
			c += dirY;
		}

		r = row - dirX;
		c = col - dirY;

		while ( r >= 0 && r < GomokuTester.BOARD_SIZE && c >= 0 && c < GomokuTester.BOARD_SIZE && board[r][c] == player ) {

			count++;
			r -= dirX;
			c -= dirY;
		}

		return count;
	}

	/**
	 * checkWinnerX uses count to check if the most recently placed X piece resulted
	 * in a winning move.
	 * @param board
	 * @param row
	 * @param col
	 * @return true or false
	 */
	public static boolean checkWinnerX(char[][] board, int row, int col) {

		if (count(board, 'X', row, col, 1, 0) == GomokuTester.CHAIN_LENGTH)
			return true;
		if (count(board, 'X', row, col, 0, 1) == GomokuTester.CHAIN_LENGTH)
			return true;
		if (count(board, 'X', row, col, 1, -1) == GomokuTester.CHAIN_LENGTH)
			return true;
		if (count(board, 'X', row, col, 1, 1) == GomokuTester.CHAIN_LENGTH)
			return true;

		// If no win condition found:
		return false;

	}

	/**
	 * checkWinnerO uses count to check if the most recently placed O piece resulted
	 * in a winning move.
	 * @param board
	 * @param row
	 * @param col
	 * @return true or false
	 */
	public static boolean checkWinnerO(char[][] board, int row, int col) {

		if (count(board, 'O', row, col, 1, 0) == GomokuTester.CHAIN_LENGTH)
			return true;
		if (count(board, 'O', row, col, 0, 1) == GomokuTester.CHAIN_LENGTH)
			return true;
		if (count(board, 'O', row, col, 1, -1) == GomokuTester.CHAIN_LENGTH)
			return true;
		if (count(board, 'O', row, col, 1, 1) == GomokuTester.CHAIN_LENGTH)
			return true;

		// If no win condition found:
		return false;

	}

	/**
	 * minMaxDecision is the driver for my Minmax algorithm.
	 * @param state
	 * @param player
	 * @param depth
	 * @return Game state, the best calculated move for a specified player 
	 */
	public static State minMaxDecision(State state, char player, int depth) {

		// System.nanoTime() was giving me errors, so I used new Date().getTime().
		double startTime = new Date().getTime();
		double endTime = startTime + (GomokuTester.TIME_LIMIT * 1000);

		player = swapPlayer(player);

		State newState = maxValue(state, player, depth, startTime, endTime);

		return newState;
	}

	/**
	 * maxValue recursive call
	 * @param state
	 * @param player
	 * @param depth
	 * @param startTime
	 * @param endTime
	 * @return Game state
	 */
	public static State maxValue(State state, char player, int depth, double startTime, double endTime) {

		// for first call, pass 0 as depth

		// Swap player for minmax to work correctly
		player = swapPlayer(player);

		// I impose an artificial depth limit of 5 as a precaution.
		if(depth == 0 || new Date().getTime() > (endTime - 10)) {
			double score = heuristic(state, player);
			state.setHeuristic(score);
			return state; // return calculated score
		}

		depth--;

		double v = Double.MAX_VALUE * -1; // negative infinity

		ArrayList<State> list = generateChildren(state, player);

		State bestState = null;

		for(int i = 0; i < list.size(); i++) {
			State min = minValue(list.get(i), player, depth, startTime, endTime);

			if(min.getHeuristic() > v) {
				v = min.getHeuristic();
				list.get(i).setHeuristic(v);
				bestState = list.get(i);

			}

			// Alpha-beta implementation.
			if(v >= state.getBeta()) {
				return bestState;
			}

			if(v > state.getAlpha()) {
				bestState.setAlpha(v);
			} else {
				bestState.setAlpha(state.getAlpha());
			}

		}

		return bestState;
	}

	/**
	 * minValue recursive call
	 * @param state
	 * @param player
	 * @param depth
	 * @param startTime
	 * @param endTime
	 * @return Game state
	 */
	public static State minValue(State state, char player, int depth, double startTime, double endTime) {

		player = swapPlayer(player);

		if(depth == 0 || new Date().getTime() > endTime - 100) {
			double score = heuristic(state, player);
			state.setHeuristic(score);
			return state; // return calculated score
		}
		depth--;

		double v = Double.MAX_VALUE;

		ArrayList<State> list = generateChildren(state, player);

		State bestState = null;

		for(int i = 0; i < list.size(); i++) {
			State max = maxValue(list.get(i), player, depth, startTime, endTime);
			if(max.getHeuristic() < v) {
				v = max.getHeuristic();
				list.get(i).setHeuristic(v);
				bestState = list.get(i);
			}

			// Alpha-beta implementation.
			if(v <= state.getAlpha()) {
				return bestState;
			}

			if(v < state.getBeta()) {
				bestState.setBeta(v);
			} else {
				
				bestState.setBeta(state.getBeta());
			}
		}
		
		return bestState;
	}

	/**
	 * swapPlayer takes in a player character and returns the opposite player.
	 * Used with my minmax algoprithm inplementation.
	 * @param player
	 * @return char player
	 */
	public static char swapPlayer(char player) {
		if(player == 'X') {
			player = 'O';
		} else {
			player = 'X';
		}

		return player;
	}

	/**
	 * Attempts to assign a greater value to longer chains of a player's pieces.
	 * Not working as desired.
	 * @param state
	 * @param player
	 * @return
	 */
	public static double heuristic(State state, char player) {

		double maximum = 0;

		for(int i = 0; i < GomokuTester.BOARD_SIZE; i++) {
			for(int j = 0; j < GomokuTester.BOARD_SIZE; j++) {
				maximum = heuristicCount(state.getData(), player, i, j, 1, 0); //horizontal
				int count2 = heuristicCount(state.getData(), player, i, j, 0, 1); //vertical
				if(count2 > maximum) {
					maximum = count2; 
				}
				
				int count3 = heuristicCount(state.getData(), player, i, j, 1, 1); //diag1
				if (count3 > maximum) {
					maximum = count3; // keep greater value
				}
				
				int count4 = heuristicCount(state.getData(), player, i, j, 1, -1); //diag2
				if (count4 > maximum) {
					maximum = count4; // keep greater value
				}

			}
		}
		return maximum;
	}

	/**
	 * A modified count function to use with my heuristic.
	 * @param board
	 * @param player
	 * @param row
	 * @param col
	 * @param dirX
	 * @param dirY
	 * @return
	 */
	public static int heuristicCount(char[][] board, char player, int row, int col, int dirX, int dirY) {

		int ct = 1;  // Count is one, since we're exploring chains of at least one piece

		int r, c = 0;

		r = row + dirX;
		c = col + dirY;
		while ( r >= 0 && r < GomokuTester.BOARD_SIZE && c >= 0 && c < GomokuTester.BOARD_SIZE && board[r][c] == player ) {
			ct++;
			r += dirX;
			c += dirY;
		}

		r = row - dirX;
		c = col - dirY;
		while ( r >= 0 && r < GomokuTester.BOARD_SIZE && c >= 0 && c < GomokuTester.BOARD_SIZE && board[r][c] == player ) {
			ct++;
			r -= dirX;
			c -= dirY;
		}

		return ct;
	}
}