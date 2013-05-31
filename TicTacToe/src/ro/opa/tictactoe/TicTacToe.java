package ro.opa.tictactoe;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe implements ActionListener {

	/* Instance Variables */
	private JFrame window = new JFrame("Tic-Tac-Toe");
	private ArrayList<ArrayList<JButton>> buttonsMatrix = new ArrayList<ArrayList<JButton>>();
	private Board board;
	private int movesCount;
	private Integer gameSize;
	private Integer gameWinSize;
	public static String LETTER_PLAYER_X = "X";
	public static String LETTER_PLAYER_O = "O";
	private Boolean win;
	private Boolean visible = false;
	private Boolean computer = false;

	public TicTacToe(Integer size, Integer winSize) {
		this.gameSize = size;
		this.gameWinSize = winSize;
		board = new Board(size);
		/* Create Window */
		window.setSize(50*size, 50*size);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridLayout(size, size));

		for (int i=0; i<size; i++) {
			ArrayList<JButton> row = new ArrayList<JButton>();
			for (int j=0; j<size; j++) {
				JButton button = new JButton("");
				window.add(button);
				button.addActionListener(this);
				row.add(button);
			}
			buttonsMatrix.add(row);
		}
		
	}
	
	/* Make The Window Visible */
	public void show() {
		window.setVisible(true);
		visible = true;
	}

	public void actionPerformed(ActionEvent a) {
		
		//int player = movesCount % 2 == 0 ? Board.PLAYER_X : Board.PLAYER_O;
		//String letter = movesCount % 2 == 0 ? LETTER_PLAYER_X : LETTER_PLAYER_O;
		renderMove(a, LETTER_PLAYER_X);
		win = checkWins(a, Board.PLAYER_X);
		renderCheckWins(a);
		movesCount++;
		if (win) {
			return;
		}
		
		// computer's turn
		
		MinimaxPlayer mmPlayer = new MinimaxPlayer(this);
		int[] move = mmPlayer.move();
		JButton button = buttonsMatrix.get(move[0]).get(move[1]);
		button.setText(LETTER_PLAYER_O);
		button.setEnabled(false);
		board.getMatrix()[move[0]][move[1]] = Board.PLAYER_O;
		win = checkWins(a, Board.PLAYER_O);
		renderCheckWins(a);
		movesCount++;
	}

	public void toggleActionPerformed(ActionEvent a) {
		String letter = movesCount % 2 == 0 ? LETTER_PLAYER_X : LETTER_PLAYER_O;
		renderMove(a, letter);
		int player = movesCount % 2 == 0 ? Board.PLAYER_X : Board.PLAYER_O;
		win = checkWins(a, player);
		if (win == true) {
			renderEndGame(a);
			if (visible) {
				JOptionPane.showMessageDialog(null, letter + " WINS!");
			}
		} else if (movesCount+1 == gameSize*gameSize && win == false) {
			if (visible) {
				JOptionPane.showMessageDialog(null, "Tie Game!");
			}
		}
		movesCount++;
	}
	
	private void renderCheckWins(ActionEvent a) {
		String letter = movesCount % 2 == 0 ? LETTER_PLAYER_X : LETTER_PLAYER_O;
		if (win == true) {
			renderEndGame(a);
			if (visible) {
				JOptionPane.showMessageDialog(null, letter + " WINS!");
			}
		} else if (movesCount+1 == gameSize*gameSize && win == false) {
			if (visible) {
				JOptionPane.showMessageDialog(null, "Tie Game!");
			}
		}
	}
	private void renderMove(ActionEvent a, String letter) {
		/* Display X's or O's on the buttons */
		for (int i=0; i < buttonsMatrix.size(); i++) {
			for (int j=0; j < buttonsMatrix.get(i).size(); j++) {
				JButton button = buttonsMatrix.get(i).get(j);
				if (a.getSource() == button) {
					button.setText(letter);
					button.setEnabled(false);
					if (LETTER_PLAYER_X.equals(letter)) {
						board.getMatrix()[i][j] = Board.PLAYER_X;
					} else {
						board.getMatrix()[i][j] = Board.PLAYER_O;
					}
				}
			}
		}
	}
	
	private void renderEndGame(ActionEvent a) {
		/* Display X's or O's on the buttons */
		for (ArrayList<JButton> cols : buttonsMatrix) {
			for (JButton button : cols) {
				button.setEnabled(false);
			}
		}
	}

	private boolean checkWinsFromPosition(ActionEvent a, int player, int pi, int pj) {
		return checkWinsFromPosition(a, player, pi, pj, gameWinSize, true, 0);
	}
	
	private boolean checkWinsFromPosition(ActionEvent a, int player, int pi, int pj, int winSize, boolean renderWin, int withFreeSize) {
		boolean win = false;
		int countH = 0;
		int countV = 0;
		int countDR = 0;
		int countDL = 0;
		for (int k = 0; k < winSize; k++) {
			/* horizontal to right */
			if (pj + winSize <= gameSize && player == board.getMatrix()[pi][pj+k]) {
				countH++;
			}
			/* vertical to bottom */
			if (pi + winSize <= gameSize && player == board.getMatrix()[pi+k][pj]) {
				countV++;
			}
			/* diagonal to right */
			if (pi + winSize <= gameSize && pj + winSize <= gameSize && player == board.getMatrix()[pi+k][pj+k]) {
				countDR++;
			}
			/* diagonal to left */
			if (pi + winSize <=gameSize && pj + 1 - winSize >= 0 && player == board.getMatrix()[pi+k][pj-k]) {
				countDL++;
			}
		}
		if (countH == winSize || countV == winSize || countDR == winSize || countDL == winSize) {
			win = true;
			if (renderWin) {
				for (int k = 0; k < winSize; k++) {
					if (countH == winSize) {
						buttonsMatrix.get(pi).get(pj+k).setBackground(Color.RED);
					}
					if (countV == winSize) {
						buttonsMatrix.get(pi+k).get(pj).setBackground(Color.RED);
					}
					if (countDR == winSize) {
						buttonsMatrix.get(pi+k).get(pj+k).setBackground(Color.RED);
					}
					if (countDL == winSize) {
						buttonsMatrix.get(pi+k).get(pj-k).setBackground(Color.RED);
					}
				}
			}
		}
		return win;
	}
	
	private boolean checkWins(ActionEvent a, int player) {
		boolean win = false;
		for (int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				if (checkWinsFromPosition(a, player, i, j)) {
					win = true;
					break;
				}
			}
		}
		return win;
	}
	
	public ArrayList<ArrayList<JButton>> getButtonsMatrix() {
		return buttonsMatrix;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Integer getGameSize() {
		return gameSize;
	}

	public Integer getGameWinSize() {
		return gameWinSize;
	}

	public Boolean isWin() {
		return win;
	}

	public Boolean getComputer() {
		return computer;
	}

	public void setComputer(Boolean computer) {
		this.computer = computer;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TicTacToe ttt = new TicTacToe(3, 3);
		ttt.setComputer(true);
		ttt.show();

	}

}
