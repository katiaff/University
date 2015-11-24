package roulette;

public class Bet {

	public final static String RED = "Red";
	public final static String BLACK = "Black";
	public final static String EVEN = "Even";
	public final static String ODD = "Odd";
	public final static String FAILED = "Failed";
	public final static String PASSED = "Passed";
	public final static String COLUMN = "Column";
	public final static String CORNER_BET = "Corner";
	public final static String STRAIGHT_UP = "Straight up";

	private String type;
	private double money;
	private Integer number;
	private Integer column;

	public String getType() {
		return type;
	}

	public Bet(String type, double money, Integer number, Integer column) {
		this.type = type;
		this.money = money;
		this.number = number;
		this.column = column;
	}

	public double getMoney() {
		return money;
	}

	public int getColumn() {
		return column;
	}

	public int getNumber() {
		return number;
	}

}
