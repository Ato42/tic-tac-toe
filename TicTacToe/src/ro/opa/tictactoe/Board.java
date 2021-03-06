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

public class Board {
	
	private Integer[][] matrix;
	
	public static final int EMPTY = 0;
	
	public static final int PLAYER_X = 1;
	
	public static final int PLAYER_O = 2;
	
	public Board(Integer size) {
		matrix = new Integer[size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				matrix[i][j] = Board.EMPTY;
			}
		}
	}

	public Integer[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Integer[][] matrix) {
		this.matrix = matrix;
	}
	
	public static int getOtherPlayer(int player) {
		int otherPlayer = 0;
		if (player == Board.PLAYER_O) {
			otherPlayer = Board.PLAYER_X;
		} else if (player == Board.PLAYER_X) {
			otherPlayer = Board.PLAYER_O;
		}
		return otherPlayer;
	}
	
	public static String getLetter(int player) {
		String letter = "";
		if (player == Board.PLAYER_O) {
			letter = TicTacToe.LETTER_PLAYER_O;
		} else if (player == Board.PLAYER_X) {
			letter = TicTacToe.LETTER_PLAYER_X;
		}
		return letter;
	}

}
