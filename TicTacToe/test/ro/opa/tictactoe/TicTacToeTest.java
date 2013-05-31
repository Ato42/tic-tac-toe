package ro.opa.tictactoe;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Test;
import static ro.opa.tictactoe.TicTacToe.LETTER_PLAYER_X;
import static ro.opa.tictactoe.TicTacToe.LETTER_PLAYER_O;

public class TicTacToeTest {
	
	private ActionEvent move(TicTacToe ttt, String player, int posX, int posY) {
		ActionEvent actionEvent = mock(ActionEvent.class);
		JButton theButton = ttt.getButtonsMatrix().get(posX).get(posY);
		theButton.setText(player);
		when(actionEvent.getSource()).thenReturn(theButton);
		return actionEvent;
	}

	@Test
	public void test3x3WinChecks() {
		int SIZE = 3;
		int WIN_SIZE = 3;

		/* diagonal to right */
		TicTacToe ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 2));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 2, 0));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 1, 1));
		assertTrue(ttt.isWin());
		
		/* diagonal to left */
		ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 2));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 1, 1));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 0));
		assertTrue(ttt.isWin());
		
		/* horizontal win */
		ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 0));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 1));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 2));
		assertTrue(ttt.isWin());
		
		/* vertical win */
		ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 1, 0));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 1));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 0));
		assertTrue(ttt.isWin());
	}
	
	@Test
	public void test5x3WinChecks() {
		int SIZE = 5;
		int WIN_SIZE = 3;
		
		TicTacToe ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 4));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 3));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 3, 3));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_O, 2, 2));
		assertFalse(ttt.isWin());
		ttt.toggleActionPerformed(move(ttt, LETTER_PLAYER_X, 4, 2));
		assertTrue(ttt.isWin());
	}

}
