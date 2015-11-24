package roulette;

import java.util.ArrayList;

public class BetManager {

	public static final int NUMBER_OF_BETS = 5;

	private double prize;
	private String winnerBets = "";
	private double totalMoney;

	private ArrayList<Bet> bets;
	private int[] redNumbers = { 3, 9, 12, 18, 21, 27, 30, 36, 5, 14, 23, 32,
			1, 7, 16, 19, 25, 34 };
	private int[] column1 = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36 };
	private int[] column2 = { 2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35 };
	private int[] column3 = { 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };
	private int[] acceptableCornerNumbers = { 2, 5, 8, 11, 14, 17, 20, 23, 26,
			29, 32, 35, 1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34 };

	public void add(Bet bet) {

		if (isAcceptedValue(bet.getMoney())) {
			totalMoney -= bet.getMoney();
			bets.add(bet);
		}

	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public boolean isAcceptedValue(double money) {
		return (money == 5 || money == 10 || money == 50 || money == 100
				|| money == 500 || money == 1000)
				&& (money <= totalMoney);
	}

	public BetManager(double totalMoney) {
		bets = new ArrayList<Bet>();
		// createNumbers();
		this.totalMoney = totalMoney;
	}

	public void calculatePrize(int rouletteNumber) {
		double prize = 0;
		for (int i = 0; i < bets.size(); i++) {
			Bet bet = bets.get(i);
			if ((bet.getType() == Bet.RED && isRed(rouletteNumber))
					|| (bet.getType() == Bet.BLACK && !isRed(rouletteNumber))) {
				prize += prizeRedBlack(bet);
				prize += bet.getMoney();
				winnerBets += bet.getType() + " ";
			}
			if ((bet.getType() == Bet.EVEN && isEven(rouletteNumber))
					|| (bet.getType() == Bet.ODD && !isEven(rouletteNumber))) {
				prize += prizeEvenOdd(bet);
				prize += bet.getMoney();
				winnerBets += bet.getType() + " ";
			}

			if ((bet.getType() == Bet.FAILED && isFailed(rouletteNumber))
					|| (bet.getType() == Bet.PASSED && !isFailed(rouletteNumber))) {
				prize += prizeFailedPassed(bet);
				prize += bet.getMoney();
				winnerBets += bet.getType() + " ";

			}

			if (bet.getType() == Bet.COLUMN
					&& isInColumn(bet.getColumn(), rouletteNumber)) {
				prize += prizeColumn(bet);
				prize += bet.getMoney();
				winnerBets += bet.getType() + " ";
			}
			if (bet.getType() == Bet.CORNER_BET
					&& isCornerBet(bet.getNumber(), rouletteNumber)) {
				prize += prizeCornerbet(bet);
			}
			if (bet.getType() == Bet.STRAIGHT_UP
					&& isStraightUp(bet.getNumber(), rouletteNumber)) {
				prize += prizeStraightUp(bet);
			}

		}
		this.prize = prize;
	}

	private boolean isCornerBet(int number, int rouletteNumber) {
		return rouletteNumber == number || rouletteNumber == number + 1
				|| rouletteNumber == number + 3 || rouletteNumber == number + 4;
	}

	public String getCornerBet(int number) {
		int[] numbers = { number, number + 1, number + 3, number + 4 };
		String aux = "[";
		for (int i = 0; i < (numbers.length) - 1; i++) {
			aux += numbers[i] + ", ";
		}
		aux += numbers[numbers.length - 1] + "]";

		return aux;
	}

	private boolean isInColumn(int column, int rouletteNumber) {
		boolean result = false;
		if (column == 1) {
			for (int i = 0; i < column1.length; i++) {
				if (column1[i] == rouletteNumber) {
					result = true;
				}
			}
		}
		if (column == 2) {
			for (int i = 0; i < column2.length; i++) {
				if (column2[i] == rouletteNumber) {
					result = true;
				}
			}
		}
		if (column == 3) {
			for (int i = 0; i < column3.length; i++) {
				if (column3[i] == rouletteNumber) {
					result = true;
				}
			}
		}
		return result;
	}

	private double prizeStraightUp(Bet bet) {
		int number = bet.getNumber();
		double money = bet.getMoney();
		if (number != 0) {
			return money * 35;
		} else {
			if (money >= 10) {
				return money / 2;
			} else {
				return 0;
			}
		}
	}

	private double prizeCornerbet(Bet bet) {
		return bet.getMoney() * 8;
	}

	private double prizeColumn(Bet bet) {
		return bet.getMoney() * 2;
	}

	private double prizeFailedPassed(Bet bet) {
		return bet.getMoney();
	}

	private double prizeEvenOdd(Bet bet) {
		return bet.getMoney();
	}

	private double prizeRedBlack(Bet bet) {
		return bet.getMoney();
	}

	public boolean isRed(int x) {
		for (int i = 0; i < redNumbers.length; i++) {
			if (x == redNumbers[i]) {
				return true;
			}
		}
		return false;
	}

	public boolean isEven(int x) {

		return x % 2 == 0;
	}

	public boolean isFailed(int x) {

		return (x >= 1 && x <= 18);
	}

	private boolean isStraightUp(int x, int rouletteNumber) {

		return x == rouletteNumber;

	}

	public boolean isOKBets() {
		return !bets.isEmpty() && bets.size() <= NUMBER_OF_BETS;
	}

	public boolean noMoreBets() {
		return bets.size() >= NUMBER_OF_BETS;
	}

	public String getWinnerBets() {
		if (winnerBets.isEmpty()) {
			return "None";
		}
		return winnerBets.trim();
	}

	public double getPrize() {
		return prize;
	}

	public boolean isAcceptableCornerNumber(int number) {
		for (int i = 0; i < acceptableCornerNumbers.length; i++) {
			if (number == acceptableCornerNumbers[i]) {
				return true;
			}
		}
		return false;
	}

}
