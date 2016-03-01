package lab4.coins;

import static lab4.coins.ScalePosition.*;

public class FakeCoins {
	private Coins bag;

	public FakeCoins(Coins bag) {
		this.bag = bag;
	}

	public int findFake() {
		return findFakeRecursive(0, bag.getNumberOfCoins() - 1);
	}

	private int findFakeRecursive(int start, int end) {
		int distance = end - start;
		// base case: 3 coins
		if (distance == 2) {
			return getFakePos3(start, end);
		}
		// special case : 4 coins
		else if (distance == 3) {
			// take unbalanced side (2 coins) + 1 from the balanced side
			return getFakePos4(start, end);

		}
		// special case : 5 coins
		else if (distance == 4) {
			int fakePos = getFakePos4(start, end-1);
			return fakePos != -1? fakePos : end;
		}
		// general case
		else {
			int center = distance / 2;
			if (distance % 2 == 0) {
				ScalePosition balanceGeneral = bag.weigh(start, center, center,
						end);
				if (balanceGeneral == LEFT) {
					return findFakeRecursive(start, center);
				}
				if (balanceGeneral == RIGHT) {
					return findFakeRecursive(center, end);
				}
			} else {
				ScalePosition balanceGeneral = bag.weigh(start, center,
						center + 1, end);
				if (balanceGeneral == LEFT) {
					return findFakeRecursive(start, center);
				}
				if (balanceGeneral == RIGHT) {
					return findFakeRecursive(center+1, end);
				}
			}
		}

		return -1;
	}

	private int getFakePos3(int start, int end) {
		ScalePosition balanceLeft = bag.weigh(start, start, start + 1,
				start + 1);
		ScalePosition balanceRight = bag.weigh(start + 1, start + 1, end, end);

		// if there is a change, we found a fake coin
		if (balanceRight != balanceLeft) {

			// if balance is different on either side of i
			if ((balanceRight == LEFT && balanceLeft == RIGHT)
					|| (balanceRight == RIGHT && balanceLeft == LEFT)) {
				return start + 1;
			}

			// if balance is different on the right
			else if (balanceLeft != EQUAL) {
				return start;
			}

			// if balance is different on the left
			else if (balanceRight != EQUAL) {
				return end;
			}
		}

		return -1;

	}

	private int getFakePos4(int start, int end) {
		ScalePosition balanceLeft = bag.weigh(start, start, start+1, start+1);
		ScalePosition balanceRight = bag.weigh(end-1, end - 1, end, end);

		// if there is a change, we found a fake coin
		if (balanceRight != balanceLeft) {

			// if balance is different on the right
			if (balanceLeft != EQUAL) {
				return getFakePos3(start, end-1);
			}

			// if balance is different on the left
			else if (balanceRight != EQUAL) {
				return getFakePos3(start+1, end);
			}
		}

		return -1;
	
	}
}
