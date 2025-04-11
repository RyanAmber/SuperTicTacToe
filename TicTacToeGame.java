package SuperTicTacToe;

import java.util.*;

public class TicTacToeGame {
	public static int getMove(TicTacToeBoard b, int corner, int player, Scanner s, String team, String[][] wins,
			int timer) {
		int answer = -1;
		if (player == 1) {
			System.out.println("Where would you like to move?");
			while (!b.isValid(corner, answer))
				answer = s.nextInt();
		} else if (player == 2) {
			while (!b.isValid(corner, answer))
				answer = (int) (Math.random() * 9 + 1);
			try {
				Thread.sleep(timer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (player == 3) {
			int score = -1000;
			int max = score;
			List<Integer> possible = new ArrayList<Integer>();
			for (int i = 1; i <= 9; i++) {
				if (b.isValid(corner, i)) {
					score = b.score(wins, corner, i, 3, team);
					if (score > max) {
						possible = new ArrayList<Integer>();
						possible.add(i);
						max = score;
					} else if (score == max) {
						possible.add(i);
					}
				}
			}
			if (possible.size() == 0) {
				return -1;
			}
			answer = possible.get((int) (Math.random() * possible.size()));
			try {
				Thread.sleep(timer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return answer;
	}

	public static int getCorner(TicTacToeBoard b, String[][] wins, int player, Scanner s) {
		int answer = -1;
		List<Integer> valid = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (wins[i][j].equals(" ") && !full(b.getGrid()[i][j]))
					valid.add(i * 3 + j + 1);
			}
		}
		System.out.println("Valid Boxes:" + valid);
		if (valid.size() == 0) {
			return -1;
		}
		if (player == 1) {
			System.out.println("Which corner would you like to move to?");
			while (!valid.contains(answer)) {
				answer = s.nextInt();
			}
		} else if (player == 2||player==3) {
			while (!valid.contains(answer)) {
				answer = (int) (Math.random() * 9 + 1);
			}
		}else if(player==3) {
			for (int i=1;i<=9;i++) {
				//if (valid.contains(i))
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] wins = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				wins[i][j] = " ";
			}
		}
		Scanner s = new Scanner(System.in);
		int games = 1;
		boolean dev = false;
		System.out.println("Are you a developer? No[1], Yes[2]");
		if (s.nextInt() == 2) {
			dev = true;
		}
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		if (dev) {
			System.out.println("How many games should be played?");
			games = s.nextInt();
		}
		System.out.println("Who is player 1: Human[1], Easy[2], Hard[3]");
		int p1 = s.nextInt();
		System.out.println("Who is player 2: Human[1], Easy[2], Hard[3]");
		int p2 = s.nextInt();
		System.out.println("Grid is");
		System.out.println("1 2 3\n4 5 6\n7 8 9");
		System.out.println("This is for both individual boards and the full grid");
		for (int i = 0; i < games; i++) {
			TicTacToeBoard b = new TicTacToeBoard();
			int corner = 5;
			int player = 1;
			for (int k = 0; k < 3; k++) {
				for (int j = 0; j < 3; j++) {
					wins[k][j] = " ";
				}
			}
			while (winner(wins).equals(" ")) {
				b.print(wins);
				System.out.println("Board:" + corner);
				if (!wins[(corner - 1) / 3][(corner - 1) % 3].equals(" ")) {
					corner = getCorner(b, wins, player == 1 ? p1 : p2, s);
					if (corner == -1) {
						break;
					}
				}
				int move = getMove(b, corner, player == 1 ? p1 : p2, s, player == 1 ? "X" : "O", wins, dev ? 0 : 1000);
				b.makeMove(corner, move, player == 1 ? "X" : "O");
				if (b.winner(corner) != " " || full(b.getGrid()[(corner - 1) / 3][(corner - 1) % 3])) {
					if (full(b.getGrid()[(corner - 1) / 3][(corner - 1) % 3])) {
						wins[(corner - 1) / 3][(corner - 1) % 3] = "C";
					} else {
						wins[(corner - 1) / 3][(corner - 1) % 3] = b.winner(corner);
					}
					// System.out.println("FOUND IT");
				}
				corner = move;

				player = player == 1 ? 2 : 1;
			}
			b.print(wins);
			if (winner(wins) == "X") {
				total1++;
				System.out.println("X wins");
			} else if (winner(wins) == "O") {
				total2++;
				System.out.println("O wins");
			} else {
				total3++;
				System.out.println("Tie Game");
			}
		}
		if (dev) {
			System.out.println("Player 1 wins:" + total1);
			System.out.println("Player 2 wins:" + total2);
			System.out.println("Ties:" + total3);
		}
		s.close();
	}

	public static boolean full(String[][] grid) {
		for (String[] i : grid) {
			for (String j : i) {
				if (j.equals(" ")) {
					return false;
				}
			}
		}
		return true;
	}

	public static String winner(String[][] grid) {
		if (grid[0][0] != " " && grid[0][0].equals(grid[0][1]) && grid[0][0].equals(grid[0][2])) {
			return grid[0][0];
		} else if (grid[1][0] != " " && grid[1][0].equals(grid[1][1]) && grid[1][0].equals(grid[1][2])) {
			return grid[1][0];
		} else if (grid[2][0] != " " && grid[2][0].equals(grid[2][1]) && grid[2][0].equals(grid[2][2])) {
			return grid[2][0];
		} else if (grid[0][0] != " " && grid[0][0].equals(grid[1][0]) && grid[1][0].equals(grid[2][0])) {
			return grid[0][0];
		} else if (grid[0][1] != " " && grid[0][1].equals(grid[1][1]) && grid[1][1].equals(grid[2][1])) {
			return grid[0][1];
		} else if (grid[0][2] != " " && grid[0][2].equals(grid[1][2]) && grid[1][2].equals(grid[2][2])) {
			return grid[0][2];
		} else if (grid[1][1] != " " && grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2])) {
			return grid[1][1];
		} else if (grid[1][1] != " " && grid[0][2].equals(grid[1][1]) && grid[1][1].equals(grid[2][0])) {
			return grid[1][1];
		} else {
			return " ";
		}
	}

}
