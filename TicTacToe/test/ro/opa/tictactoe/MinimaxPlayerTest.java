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

import org.junit.Ignore;
import org.junit.Test;
import static ro.opa.tictactoe.TicTacToe.LETTER_PLAYER_X;
import static ro.opa.tictactoe.TicTacToe.LETTER_PLAYER_O;
import static ro.opa.tictactoe.TicTacToeTest.move;

public class MinimaxPlayerTest {
	
	@Test
	@Ignore
	public void test3x3WinChecks() {
		int SIZE = 3;
		int WIN_SIZE = 3;

		TicTacToe ttt = new TicTacToe(SIZE, WIN_SIZE);
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 0, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 2, 2));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_O, 1, 0));
		assertFalse(ttt.isWin());
		ttt.toggledGameActionPerformed(move(ttt, LETTER_PLAYER_X, 1, 1));
		assertFalse(ttt.isWin());
		
		MinimaxPlayer mmPlayer = new MinimaxPlayer(ttt);
		int[] move = mmPlayer.move();
		boolean win = ttt.checkWins(move(ttt, LETTER_PLAYER_O, move[0], move[1]), Board.PLAYER_O);
		assertTrue(win);
	}

	@Test
	public void testCountWinsFromPosition() {
		MinimaxPlayer mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,0,0}, {2,1,0}, {2,0,1}} );
		/*
		 */
		int count = mmPlayer.countWinsFromPosition(Board.PLAYER_O, 2, 0, 3, 0);
		//System.out.println("COUNT: " + count);
		assertEquals(count, 1);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,0,0}, {2,1,0}, {0,0,1}} );
		count = mmPlayer.countWinsFromPosition(Board.PLAYER_O, 0, 0, 1, 2);
		//System.out.println("COUNT: " + count);
		assertEquals(count, 1);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,1,0}, {0,1,2}, {0,2,0}} );
		count = mmPlayer.countWinsFromPosition(Board.PLAYER_O, 2, 1, 3, 0);
		//System.out.println("COUNT: " + count);
		assertEquals(count, 0);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{ 2, 1, 2 }, {2, 1, 0 }, {1, 1, 0 }} );
		count = mmPlayer.countWinsFromPosition(Board.PLAYER_X, 2, 1, 3, 0);
		//System.out.println("COUNT: " + count);
		assertEquals(count, 1);
	}
	@Test
	public void testComputeScore() {
		// test win recognition
		MinimaxPlayer mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,1,0}, {2,1,0}, {2,0,0}} );
		long scoreWin = mmPlayer.computeScore(Board.PLAYER_O, 2, 0);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,1,0}, {0,1,2}, {0,2,0}} );
		long scoreLoseBlock = mmPlayer.computeScore(Board.PLAYER_O, 2, 1);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,1,0}, {0,1,2}, {0,0,2}} );
		long scoreLose = mmPlayer.computeScore(Board.PLAYER_O, 2, 2);
		//System.out.println("scoreWin: " + scoreWin + " > scoreLoseBlock: " + scoreLoseBlock + " > scoreLose: " + scoreLose);
		
		// test block recognition
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,1,0}, {0,1,2}, {0,2,0}} );
		long scoreMustBlock = mmPlayer.computeScore(Board.PLAYER_O, 2, 1);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,1,0}, {0,1,2}, {2,0,0}} );
		long scoreToWin = mmPlayer.computeScore(Board.PLAYER_O, 2, 0);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{2,1,0}, {0,1,2}, {0,0,2}} );
		scoreToWin = mmPlayer.computeScore(Board.PLAYER_O, 2, 2);
		//System.out.println("scoreMustBlock: " + scoreMustBlock + " > scoreToWin: " + scoreToWin + " = scoreToWin: " + scoreToWin);

		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{1,2,1}, {0,2,0}, {1,2,0}} );
		scoreMustBlock = mmPlayer.computeScore(Board.PLAYER_O, 2, 1);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{1,2,1}, {0,2,2}, {1,0,0}} );
		scoreToWin = mmPlayer.computeScore(Board.PLAYER_O, 1, 2);
		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{1,2,1}, {2,2,0}, {1,0,0}} );
		scoreLose = mmPlayer.computeScore(Board.PLAYER_O, 1, 0);
		//System.out.println("scoreMustBlock: " + scoreMustBlock + " > scoreToWin: " + scoreToWin + " > scoreLose: " + scoreLose);

		mmPlayer = new MinimaxPlayer(3, 3, new Integer[][] {{ 2, 1, 2 }, {2, 1, 0 }, {1, 1, 0 }} );
		scoreLose = mmPlayer.computeScore(Board.PLAYER_O, 2, 1);
		//System.out.println("scoreLose: " + scoreLose);
	}
	
}
