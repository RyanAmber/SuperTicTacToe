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
		} else if (player == 3 || player == 4) {
			int score = -10000;
			int max = score;
			List<Integer> possible = new ArrayList<Integer>();
			for (int i = 1; i <= 9; i++) {
				if (b.isValid(corner, i)) {
					score = b.score(wins, corner, i, player, team);
					if (timer == 0)
						System.out.print(score + " ");
					if (score > max) {
						possible = new ArrayList<Integer>();
						possible.add(i);
						max = score;
					} else if (score == max) {
						possible.add(i);
					}
				} else if (timer == 0) {
					System.out.print("   ");
				}
				if (i % 3 == 0 && timer == 0) {
					System.out.println();
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
		} else if (player == 5) {
			int max = Integer.MIN_VALUE;
			List<Integer> possible = new ArrayList<>();

			for (int i = 1; i <= 9; i++) {
			    if (!b.isValid(corner, i)) continue;

			    TicTacToeBoard save1 = new TicTacToeBoard(b);
			    save1.makeMove(corner, i, team);
			    
			    int score1 = save1.score(wins, corner, i, 4, team);

			    int worstCaseScore = Integer.MIN_VALUE;

			    for (int j = 1; j <= 9; j++) {
			        if (!save1.isValid(i, j)) continue;

			        TicTacToeBoard save2 = new TicTacToeBoard(save1);
			        String opponent = team.equals("X") ? "O" : "X";
			        save2.makeMove(i, j, opponent);

			        int score2 = save2.score(wins, i, j, 4, opponent);

			        int bestResponseScore = Integer.MIN_VALUE;

			        for (int k = 1; k <= 9; k++) {
			            if (!save2.isValid(j, k)) continue;

			            TicTacToeBoard save3 = new TicTacToeBoard(save2);
			            save3.makeMove(j, k, team);

			            int score3 = save3.score(wins, j, k, 4, team);

			            int combinedScore = score1 - score2 + score3;
			            System.out.printf("Team:%s  Path: %d → %d → %d | Scores: %d + %d + %d = %d\n",
			                    team, i, j, k, score1, score2, score3, combinedScore);

			            bestResponseScore = Math.max(bestResponseScore, combinedScore);
			        }

			        worstCaseScore = Math.max(worstCaseScore, bestResponseScore);
			    }

			    if (worstCaseScore > max) {
			        max = worstCaseScore;
			        possible.clear();
			        possible.add(i);
			    } else if (worstCaseScore == max) {
			        possible.add(i);
			    }
			}
			if (possible.size() > 0) {
			    answer = possible.get((int) (Math.random() * possible.size()));
			} else {
			    return -1;
			}


		}
		return answer;
	}

	public static int getCorner(TicTacToeBoard b, String[][] wins, int player, Scanner s, String team) {
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
		} else if (player == 2) {
			while (!valid.contains(answer)) {
				answer = (int) (Math.random() * 9 + 1);
			}
		} else if (player == 3 || player == 4||player==5) {
			int topScore = -5000;
			List<Integer> options = new ArrayList<Integer>();
			for (int i = 1; i <= 9; i++) {
				if (valid.contains(i)) {
					int attempt = b.cornerScore(i, player, team, wins);
					System.out.print(attempt + " " + i + " ");
					if (attempt > topScore) {
						options.clear();
						options.add(i);
						topScore = attempt;
					} else if (attempt == topScore) {
						options.add(i);
					}
				}
			}

			answer = options.get((int) (Math.random() * options.size()));
		}
		return answer;
	}

	public static void main(String[] args) {
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
		int firstScore1 = 0;
		int firstScore2 = 0;
		int firstTotal1 = 0;
		int firstTotal2 = 0;
		int score1 = 0;
		int score2 = 0;
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		if (dev) {
			System.out.println("How many games should be played?");
			games = s.nextInt();
		}
		System.out.println("Who is player 1: Human[1], Easy[2], Hard[3], Extreme[4]");
		int p1 = s.nextInt();
		System.out.println("Who is player 2: Human[1], Easy[2], Hard[3], Extreme[4]");
		int p2 = s.nextInt();
		System.out.println("Grid is");
		System.out.println("1 2 3\n4 5 6\n7 8 9");
		System.out.println("This is for both individual boards and the full grid");
		for (int round = 0; round < 2 && (dev || round == 0); round++) {
			for (int i = 0; i < (games / 2 == 0 ? 1 : games / 2); i++) {
				TicTacToeBoard b = new TicTacToeBoard();
				int corner = 5;
				int player = 1 + round;
				for (int k = 0; k < 3; k++) {
					for (int j = 0; j < 3; j++) {
						wins[k][j] = " ";
					}
				}
				while (winner(wins).equals(" ")) {
					b.print(wins);
					System.out.println("Board:" + corner);
					if (!wins[(corner - 1) / 3][(corner - 1) % 3].equals(" ")) {
						corner = getCorner(b, wins, player == 1 ? p1 : p2, s, player == 1 ? "X" : "O");
						if (corner == -1) {
							break;
						}
						System.out.println("Chosen Board:" + corner);
					}
					int move = getMove(b, corner, player == 1 ? p1 : p2, s, player == 1 ? "X" : "O", wins,
							dev ? 0 : 1000);
					b.makeMove(corner, move, player == 1 ? "X" : "O");
					if (b.winner(corner) != " " || full(b.getGrid()[(corner - 1) / 3][(corner - 1) % 3])) {
						if (b.winner(corner) == " " && full(b.getGrid()[(corner - 1) / 3][(corner - 1) % 3])) {
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
				if (dev)
					for (String[] row : wins) {
						for (String str : row) {
							if (str == "X") {
								score1++;
							} else if (str == "O") {
								score2++;
							}
						}
					}
			}
			if (dev && round == 0) {
				firstScore1 = score1;
				firstScore2 = score2;
				firstTotal1 = total1;
				firstTotal2 = total2;
			}
		}
		if (dev) {
			System.out.println("Player 1 wins:" + total1+ " type"+p1);
			System.out.println("Player 2 wins:" + total2+" type"+p2);
			System.out.println("Ties:" + total3);
			System.out.println("Score 1:" + (score1 + (total1 * 5)));
			System.out.println("Average Score 1:" + ((double) (score1 + (total1 * 5)) / games));
			System.out.println("Score 2:" + (score2 + (total2 * 5)));
			System.out.println("Average Score 2:" + ((double) (score2 + (total1 * 5)) / games));
			if (score1 + (total1 * 5) > score2 + (total2 * 5)) {
				System.out.println("Player 1 better");
			} else if (score1 + (total1 * 5) < score2 + (total2 * 5)) {
				System.out.println("Player 2 better");
			} else {
				System.out.println("TIE");
			}
			System.out.println();
			System.out.println("When player 1 had a lead");
			System.out.println("Score 1:" + (firstScore1 + (firstTotal1 * 5)));
			System.out.println("Average Score 1:" + (2 * (double) firstScore1 / games));
			System.out.println("Score 2:" + (firstScore2 + (firstTotal2 * 5)));
			System.out.println("Average Score 2:" + (2 * (double) firstScore2 / games));
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