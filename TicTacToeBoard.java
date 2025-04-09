package SuperTicTacToe;

public class TicTacToeBoard {
	private String[][][][] grid;

	public TicTacToeBoard() {
		grid = new String[3][3][3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				for (int k = 0; k < 3; k++)
					for (int l = 0; l < 3; l++)
						grid[i][j][k][l] = " ";
	}

	public TicTacToeBoard(TicTacToeBoard b) {
		grid = new String[3][3][3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				for (int k = 0; k < 3; k++)
					for (int l = 0; l < 3; l++)
						grid[i][j][k][l] = b.grid[i][j][k][l];
	}

	public String[][][][] getGrid() {
		return grid;
	}

	public boolean isValid(int corner, int move) {
		if (move < 1 || move > 9) {
			return false;
		}
		move--;
		int row = move / 3;
		int column = move % 3;
		corner--;
		if (grid[corner / 3][corner % 3][row][column] != " ") {
			return false;
		}
		return true;
	}

	public void makeMove(int corner, int move, String team) {
		move--;
		corner--;
		int row = move / 3;
		int column = move % 3;
		grid[corner / 3][corner % 3][row][column] = team;
	}

	public void print(String[][] wins) {
		System.out.println("BOARD");
		for (int row = 0; row < 3; row++) {
			for (int box = 0; box < 3; box++) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						System.out.print("[" + grid[row][i][box][j] + "]");
					}
					System.out.print("   ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("WINS");
		for (String[] str : wins) {
			for (String strs : str) {
				System.out.print("[" + strs + "]");
			}
			System.out.println();
		}
	}

	public String winner(int corner) {
		corner--;
		int i = corner / 3;
		int j = corner % 3;
		if (grid[i][j][0][0] != " " && grid[i][j][0][0].equals(grid[i][j][0][1])
				&& grid[i][j][0][0].equals(grid[i][j][0][2])) {
			return grid[i][j][0][0];
		} else if (grid[i][j][1][0] != " " && grid[i][j][1][0].equals(grid[i][j][1][1])
				&& grid[i][j][1][0].equals(grid[i][j][1][2])) {
			return grid[i][j][1][0];
		} else if (grid[i][j][2][0] != " " && grid[i][j][2][0].equals(grid[i][j][2][1])
				&& grid[i][j][2][0].equals(grid[i][j][2][2])) {
			return grid[i][j][2][0];
		} else if (grid[i][j][0][0] != " " && grid[i][j][0][0].equals(grid[i][j][1][0])
				&& grid[i][j][1][0].equals(grid[i][j][2][0])) {
			return grid[i][j][0][0];
		} else if (grid[i][j][0][1] != " " && grid[i][j][0][1].equals(grid[i][j][1][1])
				&& grid[i][j][1][1].equals(grid[i][j][2][1])) {
			return grid[i][j][0][1];
		} else if (grid[i][j][0][2] != " " && grid[i][j][0][2].equals(grid[i][j][1][2])
				&& grid[i][j][1][2].equals(grid[i][j][2][2])) {
			return grid[i][j][0][2];
		} else if (grid[i][j][0][0] != " " && grid[i][j][0][0].equals(grid[i][j][1][1])
				&& grid[i][j][1][1].equals(grid[i][j][2][2])) {
			return grid[i][j][0][0];
		} else if (grid[i][j][0][2] != " " && grid[i][j][0][2].equals(grid[i][j][1][1])
				&& grid[i][j][1][1].equals(grid[i][j][2][0])) {
			return grid[i][j][0][2];
		} else {
			return " ";
		}
	}

	public boolean full(int corner) {
		corner--;
		for (String[] i : grid[corner / 3][corner % 3]) {
			for (String s : i) {
				if (s.equals(" ")) {
					return false;
				}
			}
		}
		return true;
	}

	private int check(String[] arr, String team) {
		if (sum(arr, team) == 2) {
			return 10;
		} else if (sum(arr, team) == -2) {
			return -15;
		}
		return 0;
	}

	private int sum(String[] arr, String team) {
		int total = 0;
		for (String i : arr) {
			if (i.equals(team)) {
				total++;
			} else if (i.equals(team.equals("X") ? "O" : "X")) {
				total--;
			}
		}
		return total;
	}

	public int score(String[][] wins, int corner, int move, int player, String team) {
		TicTacToeBoard test = new TicTacToeBoard(this);
		int score = 0;
		test.makeMove(corner, move, team);
		if (player == 3) {
			corner--;
			int i = corner / 3;
			int j = corner % 3;
			if (test.winner(corner+1).equals(team)) {
				score += 50;
				wins[i][j]=team;
			}
			String[] arr = { test.getGrid()[i][j][0][0], test.getGrid()[i][j][1][0],test.getGrid()[i][j][2][0] };
			score += check(arr, team);
			arr[0] = test.getGrid()[i][j][0][1];
			arr[1] = test.getGrid()[i][j][1][1];
			arr[2] = test.getGrid()[i][j][2][1];
			score += check(arr, team);
			arr[0] = test.getGrid()[i][j][0][2];
			arr[1] = test.getGrid()[i][j][1][2];
			arr[2] = test.getGrid()[i][j][2][2];
			score += check(arr, team);
			arr[0] = test.getGrid()[i][j][0][0];
			arr[1] = test.getGrid()[i][j][1][1];
			arr[2] = test.getGrid()[i][j][2][2];
			score += check(arr, team);
			arr[0] = test.getGrid()[i][j][0][2];
			arr[1] = test.getGrid()[i][j][1][1];
			arr[2] = test.getGrid()[i][j][2][0];
			score += check(arr, team);
			arr[0] = test.getGrid()[i][j][0][0];
			arr[1] = test.getGrid()[i][j][0][1];
			arr[2] = test.getGrid()[i][j][0][2];
			score += check(arr, team);
			arr[0] = test.getGrid()[i][j][1][0];
			arr[1] = test.getGrid()[i][j][1][1];
			arr[2] = test.getGrid()[i][j][1][2];
			score += check(arr, team);
			arr[0] = test.getGrid()[i][j][2][0];
			arr[1] = test.getGrid()[i][j][2][1];
			arr[2] = test.getGrid()[i][j][2][2];
			score += check(arr, team);
			arr[0] = wins[0][0];
			arr[1] = wins[1][0];
			arr[2] = wins[2][0];
			score += check(arr, team) * 10;
			arr[0] = wins[0][1];
			arr[1] = wins[1][1];
			arr[2] = wins[2][1];
			score += check(arr, team) * 10;
			arr[0] = wins[0][2];
			arr[1] = wins[1][2];
			arr[2] = wins[2][2];
			score += check(arr, team) * 10;
			arr[0] = wins[0][0];
			arr[1] = wins[1][1];
			arr[2] = wins[2][2];
			score += check(arr, team) * 10;
			arr[0] = wins[0][2];
			arr[1] = wins[1][1];
			arr[2] = wins[2][0];
			score += check(arr, team) * 10;
		}
		System.out.println(score);
		return score;
	}
}
