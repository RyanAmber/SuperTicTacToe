//package SuperTicTacToe;

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
			// System.out.println("TOP ROW");
			return grid[i][j][0][0];
		} else if (grid[i][j][1][0] != " " && grid[i][j][1][0].equals(grid[i][j][1][1])
				&& grid[i][j][1][0].equals(grid[i][j][1][2])) {
			// System.out.println("MIDDLE ROW");
			return grid[i][j][1][0];
		} else if (grid[i][j][2][0] != " " && grid[i][j][2][0].equals(grid[i][j][2][1])
				&& grid[i][j][2][0].equals(grid[i][j][2][2])) {
			// System.out.println("BOTTOM ROW");
			return grid[i][j][2][0];
		} else if (grid[i][j][0][0] != " " && grid[i][j][0][0].equals(grid[i][j][1][0])
				&& grid[i][j][0][0].equals(grid[i][j][2][0])) {
			// System.out.println("LEFT COLUMN");
			return grid[i][j][0][0];
		} else if (grid[i][j][0][1] != " " && grid[i][j][0][1].equals(grid[i][j][1][1])
				&& grid[i][j][0][1].equals(grid[i][j][2][1])) {
			// System.out.println("MIDDLE COLUMN");
			return grid[i][j][0][1];
		} else if (grid[i][j][0][2] != " " && grid[i][j][0][2].equals(grid[i][j][1][2])
				&& grid[i][j][0][2].equals(grid[i][j][2][2])) {
			// System.out.println("RIGHT COLUMN");
			return grid[i][j][0][2];
		} else if (grid[i][j][0][0] != " " && grid[i][j][0][0].equals(grid[i][j][1][1])
				&& grid[i][j][0][0].equals(grid[i][j][2][2])) {
			// System.out.println("MAIN DIAGONAL");
			return grid[i][j][0][0];
		} else if (grid[i][j][0][2] != " " && grid[i][j][0][2].equals(grid[i][j][1][1])
				&& grid[i][j][0][2].equals(grid[i][j][2][0])) {
			// System.out.println("OTHER DIAGONAL");
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

	private int check(String[] arr, String team, int type) {
		if (sum(arr, team) == 2) {
			if (type == 1)
				return 10;
			else if (type == 2)
				return -10;
			else if (type == 4)
				return 100000000;
			else
				return 20;
		} else if (sum(arr, team) == -2) {
			return -18;
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

	private int cornerCheck(int i, int j, TicTacToeBoard test, String team, int type) {
		int score = 0;
		String[] arr = { test.getGrid()[i][j][0][0], test.getGrid()[i][j][1][0], test.getGrid()[i][j][2][0] };
		score += check(arr, team, type);
		arr[0] = test.getGrid()[i][j][0][1];
		arr[1] = test.getGrid()[i][j][1][1];
		arr[2] = test.getGrid()[i][j][2][1];
		score += check(arr, team, type);
		arr[0] = test.getGrid()[i][j][0][2];
		arr[1] = test.getGrid()[i][j][1][2];
		arr[2] = test.getGrid()[i][j][2][2];
		score += check(arr, team, type);
		arr[0] = test.getGrid()[i][j][0][0];
		arr[1] = test.getGrid()[i][j][1][1];
		arr[2] = test.getGrid()[i][j][2][2];
		score += check(arr, team, type);
		arr[0] = test.getGrid()[i][j][0][2];
		arr[1] = test.getGrid()[i][j][1][1];
		arr[2] = test.getGrid()[i][j][2][0];
		score += check(arr, team, type);
		arr[0] = test.getGrid()[i][j][0][0];
		arr[1] = test.getGrid()[i][j][0][1];
		arr[2] = test.getGrid()[i][j][0][2];
		score += check(arr, team, type);
		arr[0] = test.getGrid()[i][j][1][0];
		arr[1] = test.getGrid()[i][j][1][1];
		arr[2] = test.getGrid()[i][j][1][2];
		score += check(arr, team, type);
		arr[0] = test.getGrid()[i][j][2][0];
		arr[1] = test.getGrid()[i][j][2][1];
		arr[2] = test.getGrid()[i][j][2][2];
		score += check(arr, team, type);
		return score;
	}

	private int boardCheck(String[][] wins, String team, int type) {
		int score = 0;
		String[] arr = { wins[0][0], wins[1][0], wins[2][0] };
		score += check(arr, team, type);
		arr[0] = wins[0][1];
		arr[1] = wins[1][1];
		arr[2] = wins[2][1];
		score += check(arr, team, type);
		arr[0] = wins[0][2];
		arr[1] = wins[1][2];
		arr[2] = wins[2][2];
		score += check(arr, team, type);
		arr[0] = wins[0][0];
		arr[1] = wins[1][1];
		arr[2] = wins[2][2];
		score += check(arr, team, type);
		arr[0] = wins[0][2];
		arr[1] = wins[1][1];
		arr[2] = wins[2][0];
		score += check(arr, team, type);
		arr[0] = wins[0][0];
		arr[1] = wins[0][1];
		arr[2] = wins[0][2];
		score += check(arr, team, type);
		arr[0] = wins[1][0];
		arr[1] = wins[1][1];
		arr[2] = wins[1][2];
		score += check(arr, team, type);
		arr[0] = wins[2][0];
		arr[1] = wins[2][1];
		arr[2] = wins[2][2];
		score += check(arr, team, type);
		return score;
	}
	private int calcWorth(String[][] wins, int corner,String team) {
		int worth=0;
		if (corner==5) {
			worth=6;
			for (int board=1;board<10;board++) {
				if (board!=5&&wins[(board-1)/3][(board-1)%3].equals(team)&&wins[(10-board-1)/3][(10-board-1)%3].equals(team)) {
					worth+=2;
				}else if(board!=5&&wins[(board-1)/3][(board-1)%3].equals(team)&&wins[(10-board-1)/3][(10-board-1)%3].equals(" ")) {
					worth++;
				}
			}
		}else if(corner%2==1) {
			worth=4;
			worth+=sum(wins[corner/3],team);
			if (corner>5) {
				
			}
		}else {
			worth=2;
		}
		return worth;
	}

	public int score(String[][] wins, int corner, int move, int player, String team) {
		TicTacToeBoard test = new TicTacToeBoard(this);
		int score = 0;
		String[][] newwins = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newwins[i][j] = wins[i][j];
			}
		}
		test.makeMove(corner, move, team);
		if (player == 3) {
			if (move == 5) {
				score += 3;
			} else if (move % 2 == 1) {
				score++;
			}
			corner--;
			int i = corner / 3;
			int j = corner % 3;
			int worth = 1;
			if (corner + 1 == 5) {
				worth = 3;
			} else if (corner % 2 == 0) {
				worth = 2;
			}
			if (test.winner(corner + 1).equals(team)) {
				score += 50 * worth;
				newwins[i][j] = team;
			}
			score += cornerCheck(i, j, test, team, 1) * worth;
			worth = 2;
			if (move == 5) {
				worth = 4;
			} else if (move % 2 == 1) {
				worth = 3;
			}
			score += cornerCheck((move - 1) / 3, (move - 1) % 3, test, team, 2) * worth;
			if (!wins[(move - 1) / 3][(move - 1) % 3].equals(" ")) {
				score -= 25;
			}
		} else if (player == 4) {
			if (move == 5&&corner!=5) {
				score += 3;
			} else if (move % 2 == 1&&move!=5) {
				score++;
			}
			corner--;
			int i = corner / 3;
			int j = corner % 3;
			int worth = 2;
			if (corner + 1 == 5) {
				worth = calcWorth(wins,corner+1,team);
			} else if (corner % 2 == 0) {
				worth = 3;
			}
			if (test.winner(corner + 1).equals(team)) {
				score += 50 * worth;
				newwins[i][j] = team;
			}
			score += cornerCheck(i, j, test, team, 1) * worth;
			worth = 2;
			if (move == 5) {
				worth = calcWorth(wins,move,team);
			} else if (move % 2 == 1) {
				worth = 3;
			}
			score += cornerCheck((move - 1) / 3, (move - 1) % 3, test, team, 2) * worth;
			if (!wins[(move - 1) / 3][(move - 1) % 3].equals(" ")) {
				score -= 75;
			}
			score += 10 * boardCheck(wins, team, 1);
		}
		//System.out.println(score);
		return score;
	}

	public int cornerScore(int corner, int player, String team, String[][] wins) {
		int score=0;
		if( player ==7){
			score=Integer.MIN_VALUE;
			for (int i=1;i<10;i++){
				if (!isValid(corner, i)) {
					continue;
				}
				score=Math.max(score,score(wins,corner,i,4,team));
			}
		}
		score += cornerCheck((corner - 1) / 3, (corner - 1) % 3, this, team, 3);
		if (corner == 5) {
			score += 2;
		} else if (corner % 2 == 1) {
			score++;
		}
		String[][] newWins = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newWins[i][j] = wins[i][j];
			}
		}
		if (cornerCheck((corner-1)/3, (corner - 1) % 3,this,team,4)>10000)
		newWins[(corner - 1) / 3][(corner - 1) % 3] = team;
		if (TicTacToeGame.winner(newWins).equals(team)) {
			score += 200;
			System.out.println("Winning Chance!");
		}
		if (player == 4)
			score += 10 * boardCheck(newWins, team, 1);
		newWins[(corner - 1) / 3][(corner - 1) % 3] = team.equals("X") ? "O" : "X";
		if (TicTacToeGame.winner(newWins).equals(team.equals("X") ? "O" : "X")) {
			score -= 100;
		}
		return score;
	}
}