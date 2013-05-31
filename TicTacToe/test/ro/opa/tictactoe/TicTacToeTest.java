/**
Copyright (c) 2013, Mihai Cirneala (mihai.cirneala@gmail.com, http://m.opa.ro)
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * The names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
	
	public static ActionEvent move(TicTacToe ttt, String player, int posX, int posY) {
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
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 2));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 2, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 1, 1));
		assertTrue(ttt.isWin());
		
		/* diagonal to left */
		ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 2));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 1, 1));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 0));
		assertTrue(ttt.isWin());
		
		/* horizontal win */
		ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 1));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 2));
		assertTrue(ttt.isWin());
		
		/* vertical win */
		ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 1));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 1, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 1));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 0));
		assertTrue(ttt.isWin());
	}
	
	@Test
	public void test5x3WinChecks() {
		int SIZE = 5;
		int WIN_SIZE = 3;
		
		TicTacToe ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 4));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 3));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 3, 3));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 2, 2));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 4, 2));
		assertTrue(ttt.isWin());
	}

}
