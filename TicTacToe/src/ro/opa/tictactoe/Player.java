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

public abstract class Player {

	protected Integer[][] matrix;
	protected Integer gameSize;
	protected Integer gameWinSize;

	public Player(Integer gameSize, Integer gameWinSize, Integer[][] matrix) {
		this.gameSize = gameSize;
		this.gameWinSize = gameWinSize;
		this.matrix = matrix;
	}
	
	public Player(Integer gameSize, Integer gameWinSize) {
		this.gameSize = gameSize;
		this.gameWinSize = gameWinSize;
	}
	
	public Player(TicTacToe ticTacToe) {
		System.out.println("AI initialized");
		this.gameSize = ticTacToe.getGameSize();
		this.gameWinSize = ticTacToe.getGameWinSize();
		this.matrix = new Integer[gameSize][gameSize];
		for (int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				matrix[i][j] = ticTacToe.getBoard().getMatrix()[i][j];
			}
		}
	}
	
	public abstract int[] move();

	public Integer[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Integer[][] matrix) {
		this.matrix = matrix;
	}

	public Integer getGameSize() {
		return gameSize;
	}

	public void setGameSize(Integer gameSize) {
		this.gameSize = gameSize;
	}

	public Integer getGameWinSize() {
		return gameWinSize;
	}

	public void setGameWinSize(Integer gameWinSize) {
		this.gameWinSize = gameWinSize;
	}

}
