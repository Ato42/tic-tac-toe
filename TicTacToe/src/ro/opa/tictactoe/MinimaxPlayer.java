package ro.opa.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class MinimaxPlayer {

	private TicTacToe ticTacToe;
	private Integer[][] matrix;

	public MinimaxPlayer(TicTacToe ticTacToe) {
		System.out.println("AI initialized");
		this.ticTacToe = ticTacToe;
		this.matrix = new Integer[ticTacToe.getGameSize()][ticTacToe.getGameSize()];
		for (int i = 0; i < ticTacToe.getGameSize(); i++) {
			for (int j = 0; j < ticTacToe.getGameSize(); j++) {
				matrix[i][j] = ticTacToe.getBoard().getMatrix()[i][j];
			}
		}
	}

	public int[] move() {
		long[] result = minimax(2, Board.PLAYER_O, Long.MIN_VALUE, Long.MAX_VALUE);
		System.out.println("Calculated move: [" + result[1] + ", " + result[2] + "]");
		return new int[] { (int) result[1], (int) result[2] };
	}

	private long[] minimax(int depth, int player, long alpha, long beta) {
		// Generate possible next moves in a list of int[2] of {row, col}.
		List<int[]> nextMoves = generateMoves();

		// mySeed is maximizing; while oppSeed is minimizing
		long score;
		int bestRow = -1;
		int bestCol = -1;

		if (nextMoves.isEmpty() || depth == 0) {
			// Gameover or depth reached, evaluate score
			score = computeScore(bestRow, bestCol);
			return new long[] { score, bestRow, bestCol };
		} else {
			for (int[] move : nextMoves) {
				// try this move for the current "player"
				matrix[move[0]][move[1]] = player;
				score = computeScore(move[0], move[1]);
				if (player == Board.PLAYER_O) { // mySeed (computer) is
												// maximizing player
					score = minimax(depth - 1, Board.PLAYER_X, alpha, beta)[0];
					if (score > alpha) {
						alpha = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				} else { // oppSeed is minimizing player
					score = minimax(depth - 1, Board.PLAYER_O, alpha, beta)[0];
					if (score < beta) {
						beta = score;
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				// undo move
				matrix[move[0]][move[1]] = Board.EMPTY;
				// cut-off
				if (alpha >= beta)
					break;
			}
			return new long[] { (player == Board.PLAYER_O) ? alpha : beta, bestRow, bestCol };
		}
	}

	private int countWinsFromPosition(int player, int pi, int pj, int winSize, int withFreeSize) {
		int winsCount = 0;
		int countH = 0;
		int countV = 0;
		int countDR = 0;
		int countDL = 0;
		for (int k = 0; k < winSize; k++) {
			/* horizontal to right */
			if (pj + winSize < ticTacToe.getGameSize() && player == matrix[pi][pj + k]) {
				countH++;
			}
			/* vertical to bottom */
			if (pi + winSize < ticTacToe.getGameSize() && player == matrix[pi + k][pj]) {
				countV++;
			}
			/* diagonal to right */
			if (pi + winSize < ticTacToe.getGameSize() && pj + winSize < ticTacToe.getGameSize() && player == matrix[pi + k][pj + k]) {
				countDR++;
			}
			/* diagonal to left */
			if (pi + winSize < ticTacToe.getGameSize() && pj + 1 - winSize >= 0 && player == matrix[pi + k][pj - k]) {
				countDL++;
			}
		}
		int countFreeEndH = 0;
		int countFreeEndV = 0;
		int countFreeEndDR = 0;
		int countFreeEndDL = 0;
		for (int k = winSize; k < winSize + withFreeSize; k++) {
			/* horizontal to right */
			if (pj + k < ticTacToe.getGameSize() && player == matrix[pi][pj + k]) {
				countFreeEndH++;
			}
			/* vertical to bottom */
			if (pi + k < ticTacToe.getGameSize() && player == matrix[pi + k][pj]) {
				countFreeEndV++;
			}
			/* diagonal to right */
			if (pi + k < ticTacToe.getGameSize() && pj + k < ticTacToe.getGameSize() && player == matrix[pi + k][pj + k]) {
				countFreeEndDR++;
			}
			/* diagonal to left */
			if (pi + k < ticTacToe.getGameSize() && pj - k >= 0 && player == matrix[pi + k][pj - k]) {
				countFreeEndDL++;
			}
		}
		int countFreeBeginH = 0;
		int countFreeBeginV = 0;
		int countFreeBeginDR = 0;
		int countFreeBeginDL = 0;
		for (int k = -1; k >= 0 - withFreeSize; k--) {
			/* horizontal to right */
			if (pj + k >= 0 && player == matrix[pi][pj + k]) {
				countFreeBeginH++;
			}
			/* vertical to bottom */
			if (pi + k >= 0 && player == matrix[pi + k][pj]) {
				countFreeBeginV++;
			}
			/* diagonal to right */
			if (pi + k >= 0 && pj + k >= 0 && player == matrix[pi + k][pj + k]) {
				countFreeBeginDR++;
			}
			/* diagonal to left */
			if (pi + k >= 0 && pj - k < ticTacToe.getGameSize() && player == matrix[pi + k][pj - k]) {
				countFreeBeginDL++;
			}
		}
		System.out.println(">> ("+countH+" == "+winSize+" && ("+countFreeEndH+" == "+withFreeSize+" || "+countFreeBeginH+" == "+withFreeSize+"))");
		if (countH == winSize && (countFreeEndH == withFreeSize || countFreeBeginH == withFreeSize)) {
			winsCount++;
		}
		if (countV == winSize && (countFreeEndV == withFreeSize || countFreeBeginV == withFreeSize)) {
			winsCount++;
		}
		if (countDR == winSize && (countFreeEndDR == withFreeSize || countFreeBeginDR == withFreeSize)) {
			winsCount++;
		}
		if (countDL == winSize && (countFreeEndDL == withFreeSize || countFreeBeginDL == withFreeSize)) {
			winsCount++;
		}
		return winsCount;
	}

	private int countWins(int player, int winSize, int withFreeSize) {
		int winsCount = 0;
		for (int i = 0; i < ticTacToe.getGameSize(); i++) {
			for (int j = 0; j < ticTacToe.getGameSize(); j++) {
				winsCount += countWinsFromPosition(player, i, j, winSize, withFreeSize);
			}
		}
		System.out.println("> winsCount: " + winsCount);
		return winsCount;
	}

	private long computeScore(int row, int col) {
		return computeScore(Board.PLAYER_O, row, col);
	}

	private long computeScore(int player, int row, int col) {
		long score = 0;
		score += countWins(player, ticTacToe.getGameWinSize(), 0) * Math.pow(10, ticTacToe.getGameWinSize() - 1);
		System.out.println("[" + row + ", " + col + "]: " + score);
		for (int i = ticTacToe.getGameWinSize() - 1; i > 0; i--) {
			score += countWins(player, i, ticTacToe.getGameWinSize() - i) * Math.pow(10, i - 1);
			//System.out.println("   {" + i + ", " + (ticTacToe.getGameWinSize() - i) + "} ");
		}
		//System.out.println("[" + row + ", " + col + "]: " + score);
		return score;
	}

	private List<int[]> generateMoves() {
		List<int[]> nextMoves = new ArrayList<int[]>();

		// Search for empty cells and add to the List
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == Board.EMPTY) {
					nextMoves.add(new int[] { i, j });
				}
			}
		}
		return nextMoves;
	}

}
