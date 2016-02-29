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
        if (distance <= 4) {
            return getFakePos(start, end);
        } else {
            int center = distance / 2;
            if (distance % 2 == 0) {
                ScalePosition balanceGeneral = bag.weigh(start, center, center, end);
                if (balanceGeneral == LEFT) {
                    return findFakeRecursive(start, center);
                }
                if (balanceGeneral == RIGHT) {
                    return findFakeRecursive(center, end);
                }
            } else {
                ScalePosition balanceGeneral = bag.weigh(start, center, center + 1, end);
                if (balanceGeneral == LEFT) {
                    return findFakeRecursive(start, center);
                }
                if (balanceGeneral == RIGHT) {
                    return findFakeRecursive(center, end);
                }
            }
        }

        return -1;
    }

    private int getFakePos(int start, int end) {
        for (int i = 1; i < end - start; i++) {
            ScalePosition balanceRight = bag.weigh(i - 1, i - 1, i, i);
            ScalePosition balanceLeft = bag.weigh(i, i, i + 1, i + 1);

            // if there is a change, we found a fake coin
            if (balanceRight != balanceLeft) {

                // if balance is different on either side of i
                if ((balanceRight == LEFT && balanceLeft == RIGHT) |
                        (balanceRight == RIGHT && balanceLeft == LEFT)) {
                    return i;
                }

                // if balance is different on the right
                else if (balanceLeft != EQUAL) {
                    return i + 1;

                }

                // if balance is different on the left
                else if (balanceRight != EQUAL) {
                    return i - 1;
                }
            }
        }
        return -1;
    }

}
