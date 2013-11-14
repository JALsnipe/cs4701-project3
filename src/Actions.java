import java.util.ArrayList;


public class Actions {
	
	public static char[][] copy(char[][] input) {
		char[][] copy = new char[input.length][];
		 
		for (int i = 0; i < copy.length; i++) {
			char[] member = new char[input[i].length];
			System.arraycopy(input[i], 0, member, 0, input[i].length);
			copy[i] = member;
			}
		
		return copy;
		}
	
	public static ArrayList<State> generateChildren(State state, char player) {
		
		ArrayList<State> list = new ArrayList<State>();
		
		System.out.println("in generateChildren");
		
		char[][] board = copy(state.getData());
		
		for(int i = 0; i < GomokuTester.BOARD_SIZE; i++) {
			for(int j = 0; j < GomokuTester.BOARD_SIZE; j++) {
				
				char newBoard[][] = new char[GomokuTester.BOARD_SIZE][GomokuTester.BOARD_SIZE];
				
				newBoard = copy(board);
				
				if(board[i][j] == '.') {
					System.out.println("in generate if");
					State newState = new State();
					newBoard[i][j] = player;
					newState.setData(newBoard);
					list.add(newState);
					GomokuTester.printBoard(newState.getData());
				}
			}
		}
		return list;
	}

	//	public int count(State state, char player, int row, int col, int dirX, int dirY) {
	public static int count(char[][] board, char player, int row, int col, int dirX, int dirY) {

		//        		  dirX    dirY    Why?
		//                ----    ----    --------------------------------
		//horizontal direction    1       0       Only x changes.
		//vertical direction      0       1       Only y changes.
		//first diagonal          1       1       Both x and y change.
		//second diagonal         1      -1       Change in opposing directions.

		// Counts the number of the specified player's pieces starting at
		// square (row,col) and extending along the direction specified by
		// (dirX,dirY).  It is assumed that the player has a piece at
		// (row,col).  This method looks at the squares (row + dirX, col+dirY),
		// (row + 2*dirX, col + 2*dirY), ... until it hits a square that is
		// off the board or is not occupied by one of the players pieces.
		// It counts the squares that are occupied by the player's pieces.
		// Furthermore, it sets (win_r1,win_c1) to mark last position where
		// it saw one of the player's pieces.  Then, it looks in the
		// opposite direction, at squares (row - dirX, col-dirY),
		// (row - 2*dirX, col - 2*dirY), ... and does the same thing.
		// Except, this time it sets (win_r2,win_c2) to mark the last piece.
		// Note:  The values of dirX and dirY must be 0, 1, or -1.  At least
		// one of them must be non-zero.

		//		char[][] board = state.getData();

		int ct = 1;  // Number of pieces in a row belonging to the player.

		int r, c = 0;    // A row and column to be examined

		r = row + dirX;  // Look at square in specified direction.
		c = col + dirY;
		while ( r >= 0 && r < GomokuTester.BOARD_SIZE && c >= 0 && c < GomokuTester.BOARD_SIZE && board[r][c] == player ) {
			// Square is on the board and contains one of the players's pieces.
			System.out.println("in while1");
			ct++;
			r += dirX;  // Go on to next square in this direction.
			c += dirY;
		}

		r = row - dirX;  // Look in the opposite direction.
		c = col - dirY;
		while ( r >= 0 && r < GomokuTester.BOARD_SIZE && c >= 0 && c < GomokuTester.BOARD_SIZE && board[r][c] == player ) {
			// Square is on the board and contains one of the players's pieces.
			System.out.println("in while2");
			ct++;
			r -= dirX;   // Go on to next square in this direction.
			c -= dirY;
		}

		return ct;

	}  // end count()

	//	public boolean checkWinnerX(State state, int row, int col) {
	public static boolean checkWinnerX(char[][] board, int row, int col) {
		// This is called just after a piece has been played on the
		// square in the specified row and column.  It determines
		// whether that was a winning move by counting the number
		// of squares in a line in each of the four possible
		// directions from (row,col).  If there are 5 squares (or more)
		// in a row in any direction, then the game is won.

		//		char[][] board = state.getData();

		//count(State state, char player, int row, int col, int dirX, int dirY)
		//count(char[][] board, char player, int row, int col, int dirX, int dirY) {

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

	}  // end checkWinnerX()

	//	public boolean checkWinnerO(State state, int row, int col) {
	public static boolean checkWinnerO(char[][] board, int row, int col) {
		// This is called just after a piece has been played on the
		// square in the specified row and column.  It determines
		// whether that was a winning move by counting the number
		// of squares in a line in each of the four possible
		// directions from (row,col).  If there are 5 squares (or more)
		// in a row in any direction, then the game is won.

		//			char[][] board = state.getData();

		//count(State state, char player, int row, int col, int dirX, int dirY)
		//count(char[][] board, char player, int row, int col, int dirX, int dirY) {

		if (count(board, 'O', row, col, 1, 0) == 5)
			return true;
		if (count(board, 'O', row, col, 0, 1) == 5)
			return true;
		if (count(board, 'O', row, col, 1, -1) == 5)
			return true;
		if (count(board, 'O', row, col, 1, 1) == 5)
			return true;

		// If no win condition found:
		return false;

	}  // end checkWinnerX()
	
	public static State minMaxDecision(State state, char player, int depth) {
		
		// idfs
		//if time is almost up, return node
		// for now, make a depth limit
		
		player = swapPlayer(player);
		
		State newState = maxValue(state, player, depth);
		
		return newState;
	}
	
	public static State maxValue(State state, char player, int depth) {
		
		// for first call, pass 0 as depth
		
		player = swapPlayer(player);
		
//		int depth = 0;
		
		if(depth == 0) {
			System.out.println("in depth == 5");
			System.out.println(depth);
			double score = heuristic(state, player);
			state.setHeuristic(score);
			return state; // return calulated score
		}
		
		depth--;
		
		double v = Double.MIN_VALUE;
		
		System.out.println("in maxValue before gen children");
		
		ArrayList<State> list = generateChildren(state, player);
		
		State bestState = null;
		
		for(int i = 0; i < list.size(); i++) {
			State min = minValue(list.get(i), player, depth);
			if(min.getHeuristic() > v) {
				GomokuTester.printBoard(min.getData());
				v = min.getHeuristic();
				list.get(i).setHeuristic(v);
				bestState = list.get(i);
			}
		}
		
		//bestState.setHeuristic(v);
		
		return bestState;
		
	}

	public static State minValue(State state, char player, int depth) {
		
		player = swapPlayer(player);
		
		if(depth == 0) {
			double score = heuristic(state, player);
			state.setHeuristic(score);
			return state; // return calulated score
		}
		depth--;
		
		double v = Double.MAX_VALUE;
		
		ArrayList<State> list = generateChildren(state, player);
		
		State bestState = null;
		
		for(int i = 0; i < list.size(); i++) {
			State max = maxValue(list.get(i), player, depth);
			if(max.getHeuristic() < v) {
				v = max.getHeuristic();
				//bestState = max;
				list.get(i).setHeuristic(v);
				bestState = list.get(i);
			}
		}
		
		//bestState.setHeuristic(v);
		
		return bestState;
		
	}
	
	public static char swapPlayer(char player) {
		if(player == 'X') {
			player = 'O';
		} else {
			player = 'X';
		}
		
		return player;
	}
	
	public static double heuristic(State state, char player) {
		
//		count(char[][] board, char player, int row, int col, int dirX, int dirY) {

			//        		  dirX    dirY    Why?
			//                ----    ----    --------------------------------
			//horizontal direction    1       0       Only x changes.
			//vertical direction      0       1       Only y changes.
			//first diagonal          1       1       Both x and y change.
			//second diagonal         1      -1       Change in opposing directions.
		
		double maximum = 0;
		
		for(int i = 0; i < GomokuTester.BOARD_SIZE; i++) {
			for(int j = 0; j < GomokuTester.BOARD_SIZE; j++) {
				maximum = count(state.getData(), player, i, j, 1, 0); //horizontal
				int count2 = count(state.getData(), player, i, j, 0, 1); //vertical
				if(count2 > maximum) {
					maximum = count2;
				}
				int count3 = count(state.getData(), player, i, j, 1, 1); //diag1
				if (count3 > maximum) {
					maximum = count3;
				}
				int count4 = count(state.getData(), player, i, j, 1, -1); //diag2
				if (count4 > maximum) {
					maximum = count4;
				}
				
			}
		}
		return maximum;
	}
	
}
