package lab4.coins;

import static lab4.coins.ScalePosition.*;

public class FakeCoinsIterative {
    private Coins bag;

    public FakeCoinsIterative(Coins bag) {
        this.bag = bag;
    }

    public int findFake() {
        return findFakeByBalance();
    }

    private int findFakeByBalance() {
        int result = -1;
        for (int i = 1; i < bag.getNumberOfCoins() - 1; i++) {
            // get the balance on the left and on the right
            ScalePosition balanceRight = bag.weigh(i - 1, i - 1, i, i);
            ScalePosition balanceLeft = bag.weigh(i, i, i + 1, i + 1);

            // if there is a change, we found a fake coin
            if (balanceRight != balanceLeft) {

                // if balance is different on either side of i
                if ((balanceRight == LEFT && balanceLeft == RIGHT) ||
                        (balanceRight == RIGHT && balanceLeft == LEFT)) {
                    result = i;
                    break;
                }

                // if balance is different on the right
                else if (balanceLeft != EQUAL) {
                    result = i + 1;
                    break;
                }

                // if balance is different on the left
                else if (balanceRight != EQUAL) {
                    result = i - 1;
                    break;
                }
            }
        }
        return result;
    }


}
