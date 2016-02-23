package lab4.coins;

public class FakeCoins {
	private Coins bag;
	private int leftStart;
	private int leftEnd;
	private int rightStart;
	private int rightEnd;

	public FakeCoins(Coins bag) {
		this.bag = bag;
		this.leftStart = 0;
		this.leftEnd= bag.getNumberOfCoins()/2;
		this.rightStart = bag.getNumberOfCoins()/2;
		this.rightEnd= bag.getNumberOfCoins();
	}

	public int findFake() {
		boolean heavier = false;
		heavier = assertHeavier(heavier);

		if (heavier) {

		}

		else {
			ScalePosition balance = bag.weigh(leftStart,leftEnd,
					rightStart, rightEnd);
			if(balance == ScalePosition.LEFT){
				balanceLighter(leftStart, leftEnd);
			}
			else{
				balanceLighter(rightStart, rightEnd);
			}
		}
		return 0;
	}

	private void balanceLighter(int start, int end) {
		// TODO Auto-generated method stub
		
	}

	private boolean assertHeavier(boolean heavier) {
		for (int i = 0; i < bag.getNumberOfCoins(); i++) {
			ScalePosition balance = bag.weigh(i, i, i + 1, i + 1);
			if (balance == ScalePosition.RIGHT) {
				heavier = true;
				break;
			}
		}
		return heavier;
	}

}
