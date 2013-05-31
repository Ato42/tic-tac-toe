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
	private int movesCount;
	private Integer size;
	private Integer winSize;

	public TicTacToe(Integer size, Integer winSize) {
		this.size = size;
		this.winSize = winSize;
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
		System.out.println(buttonsMatrix.size() + " -- " + buttonsMatrix.get(0).size());
		

		/* Make The Window Visible */
		window.setVisible(true);

	}

	public void actionPerformed(ActionEvent a) {
		String letter = movesCount % 2 == 0 ? "X" : "O";
		renderMove(a, letter);
		boolean win = checkWins(a, letter);
		if (win == true) {
			renderEndGame(a);
			JOptionPane.showMessageDialog(null, letter + " WINS!");
		} else if (movesCount+1 == size*size && win == false) {
			JOptionPane.showMessageDialog(null, "Tie Game!");
		}
		movesCount++;
	}
	
	private void renderMove(ActionEvent a, String letter) {
		/* Display X's or O's on the buttons */
		for (ArrayList<JButton> cols : buttonsMatrix) {
			for (JButton button : cols) {
				if (a.getSource() == button) {
					button.setText(letter);
					button.setEnabled(false);
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

	private boolean checkWinsFromPosition(ActionEvent a, String letter, int pi, int pj) {
		boolean win = false;
		/* horizontal to right */
		if (pj + winSize <= size) { 
			int count = 0;
			System.out.print("H: ");
			for (int k = 0; k < winSize; k++) {
				System.out.print("["+pi+", "+(pj+k)+"] ");
				if (letter.equals(buttonsMatrix.get(pi).get(pj+k).getText())) {
					count++;
				}
			}
			System.out.print(" :"+count);
			if (count == winSize) {
				System.out.print(" WIN");
				win = true;
				for (int k = 0; k < winSize; k++) {
					buttonsMatrix.get(pi).get(pj+k).setBackground(Color.RED);
				}
			}
			System.out.println();
		}
		/* vertical to bottom */
		if (pi + winSize <= size) { 
			int count = 0;
			System.out.print("V: ");
			for (int k = 0; k < winSize; k++) {
				System.out.print("["+(pi+k)+", "+pj+"] ");
				if (letter.equals(buttonsMatrix.get(pi+k).get(pj).getText())) {
					count++;
				}
			}
			if (count == winSize) {
				System.out.print(" WIN");
				win = true;
				for (int k = 0; k < winSize; k++) {
					buttonsMatrix.get(pi+k).get(pj).setBackground(Color.RED);
				}
			}
			System.out.println();
		}
		/* diagonal to right */
		if (pi + winSize <= size && pj + winSize <= size) { 
			int count = 0;
			System.out.print("DR: ");
			for (int k = 0; k < winSize; k++) {
				System.out.print("["+(pi+k)+", "+(pj+k)+"] ");
				if (letter.equals(buttonsMatrix.get(pi+k).get(pj+k).getText())) {
					count++;
				}
			}
			if (count == winSize) {
				System.out.print(" WIN");
				win = true;
				for (int k = 0; k < winSize; k++) {
					buttonsMatrix.get(pi+k).get(pj+k).setBackground(Color.RED);
				}
			}
			System.out.println();
		}
		/* diagonal to left */
		if (pi + winSize <=size && pj + 1 - winSize >= 0) { 
			int count = 0;
			System.out.print("DL: ");
			for (int k = 0; k < winSize; k++) {
				System.out.print("["+(pi+k)+", "+(pj-k)+"] ");
				if (letter.equals(buttonsMatrix.get(pi+k).get(pj-k).getText())) {
					count++;
				}
			}
			if (count == winSize) {
				System.out.print(" WIN");
				win = true;
				for (int k = 0; k < winSize; k++) {
					buttonsMatrix.get(pi+k).get(pj-k).setBackground(Color.RED);
				}
			}
			System.out.println();
		}
		return win;
		
	}
	private boolean checkWins(ActionEvent a, String letter) {
		boolean win = false;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (checkWinsFromPosition(a, letter, i, j)) {
					win = true;
					break;
				}
			}
		}
		return win;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TicTacToe ttt = new TicTacToe(3, 3);

	}

}
