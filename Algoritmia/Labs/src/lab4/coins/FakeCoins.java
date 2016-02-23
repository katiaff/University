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
        this.leftEnd = bag.getNumberOfCoins() / 2 - 1;
        this.rightStart = bag.getNumberOfCoins() / 2;
        this.rightEnd = bag.getNumberOfCoins() - 1;
    }

    public int findFake() {
        // check if the fake coin is heavier or
        // lighter than the standard weight coins
        //boolean heavier = assertHeavier();

        return findFakeByBalance();

        /*return heavier ? balanceHeavier(leftStart, leftEnd,
                rightStart, rightEnd) : balanceLighter(leftStart, leftEnd,
                rightStart, rightEnd);*/
    }

    private int balanceHeavier(int leftStart, int leftEnd, int rightStart, int rightEnd) {
        // do we have an interval of more than 2 elements?
        if (rightEnd - leftStart > 1) {
            ScalePosition balance = bag.weigh(leftStart, leftEnd,
                    rightStart, rightEnd);
            // the scale is heavier on the left, then
            // the HEAVIER element is on the left
            if (balance == ScalePosition.LEFT) {
                return balanceHeavier(leftStart, leftEnd / 2 - 1, leftEnd / 2, leftEnd);
            }
            // if it's the other way around,
            // the HEAVIER element is on the right
            else {
                return balanceHeavier(rightStart, rightEnd / 2 - 1, rightEnd / 2, rightEnd);
            }
        }
        // if we are down to the last 2 elements
        else {
            ScalePosition balance = bag.weigh(leftStart, leftEnd,
                    rightStart, rightEnd);
            // the scale is heavier on the left, then
            // the HEAVIER element is on the left and viceversa
            return balance == ScalePosition.LEFT ? leftStart : rightStart;
        }
    }

    private int balanceLighter(int leftStart, int leftEnd,
                               int rightStart, int rightEnd) {
        // do we have an interval of more than 2 elements?
        if (rightEnd - leftStart > 1) {
            ScalePosition balance = bag.weigh(leftStart, leftEnd,
                    rightStart, rightEnd);
            // the scale is heavier on the left, then
            // the LIGHTER element is on the right
            if (balance == ScalePosition.LEFT) {
                return balanceLighter(rightStart, rightEnd / 2 - 1, rightEnd / 2, rightEnd);
            }
            // if it's the other way around,
            // the LIGHTER element is on the left
            else {
                return balanceLighter(leftStart, leftEnd / 2 - 1, leftEnd / 2, leftEnd);
            }
        }
        // if we are down to the last 2 elements
        else {
            ScalePosition balance = bag.weigh(leftStart, leftEnd,
                    rightStart, rightEnd);
            // the scale is heavier on the left, then
            // the LIGHTER element is on the right and viceversa
            return balance == ScalePosition.LEFT ? rightStart : leftStart;
        }
    }

    private boolean assertHeavier() {
        boolean heavier = false;
        for (int i = 0; i < bag.getNumberOfCoins(); i++) {
            // check only two elements in each iteration
            ScalePosition balance = bag.weigh(i, i, i + 1, i + 1);
            // if the scale tips towards i+1, the newly
            // checked item, then it is a heavier item
            if (balance == ScalePosition.RIGHT) {
                heavier = true;
                break;
            }
            // if the scale tips towards i, then it
            // is a lighter item
            else if (balance == ScalePosition.LEFT) {
                break;
            }
        }
        return heavier;
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
                if ((balanceRight == ScalePosition.LEFT && balanceLeft == ScalePosition.RIGHT) |
                        (balanceRight == ScalePosition.RIGHT && balanceLeft == ScalePosition.LEFT)) {
                    result = i;
                    break;
                }

                // if balance is different on the right
                else if (balanceLeft != ScalePosition.EQUAL) {
                    result = i + 1;
                    break;
                }

                // if balance is different on the left
                else if (balanceRight != ScalePosition.EQUAL) {
                    result = i - 1;
                    break;
                }
            }
        }
        return result;
    }

                    /*if (balance == ScalePosition.RIGHT) {
                    result = i + 1;
                    break;
                } else if (balance == ScalePosition.LEFT) {
                    result = i;
                    break;
                }*/


}
