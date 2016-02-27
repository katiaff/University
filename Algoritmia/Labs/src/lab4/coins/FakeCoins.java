package lab4.coins;

public class FakeCoins {
    private Coins bag;

    public FakeCoins(Coins bag) {
        this.bag = bag;
    }

/*    private int findFakeRecursive(int start, int end) {
        if (end - start <= 4) {
            int i = getFakePos(start, end);
            return i;
        } else {
            int center = (end - start) / 2;
            ScalePosition balanceGeneral = bag.weigh(start, end / 2, (end / 2) + 1, end);
            ScalePosition balanceLeft = bag.weigh(start, center / 2, (center / 2) + 1, center);
            ScalePosition balanceRight = bag.weigh(center + 1, end / 2, (end / 2) + 1, end);

            if (balanceGeneral == ScalePosition.LEFT) {
                if (balanceRight == ScalePosition.RIGHT) {
                    findFakeRecursive(center + 1, end);
                }
                if (balanceLeft == ScalePosition.RIGHT) {
                    findFakeRecursive(start, center);
                }
            }
            if (balanceGeneral == ScalePosition.RIGHT) {
                if (balanceRight == ScalePosition.LEFT) {
                    findFakeRecursive(start, center);
                }
                if (balanceLeft == ScalePosition.LEFT) {
                    findFakeRecursive(center + 1, end);
                }
            } else {
                throw new IllegalStateException("Bag does not include a fake coin");

            }
        }
        return -1;
    }*/

    private int findFakeRecursive(int start, int end) {
        if (end - start <= 4) {
            return getFakePos(start, end);
        } else {
            int center = (end - start) / 2;
            int rightCenter = center + ((end - center) / 2);
//            ScalePosition balanceLeft = bag.weigh(start, center / 2, (center / 2) + 1, center);
            ScalePosition balanceLeft = bag.weigh(start, center / 2, (center / 2) + 1, center);
            ScalePosition balanceRight = bag.weigh(center, rightCenter, rightCenter + 1, end);

            if (balanceRight != balanceLeft) {

                // if balance is different on the right
                if (balanceLeft != ScalePosition.EQUAL) {
                    findFakeRecursive(start, center);

                }

                // if balance is different on the left
                if (balanceRight != ScalePosition.EQUAL) {
                    findFakeRecursive(center + 1, end);
                }
            } else {
                throw new IllegalStateException("Bag does not include a fake coin");

            }
        }
        return -1;
    }

    public int findFake() {
        return findFakeRecursive(0, bag.getNumberOfCoins() - 1);
    }

    private int getFakePos(int start, int end) {
        for (int i = 1; i < end - start; i++) {
            ScalePosition balanceRight = bag.weigh(i - 1, i - 1, i, i);
            ScalePosition balanceLeft = bag.weigh(i, i, i + 1, i + 1);

            // if there is a change, we found a fake coin
            if (balanceRight != balanceLeft) {

                // if balance is different on either side of i
                if ((balanceRight == ScalePosition.LEFT && balanceLeft == ScalePosition.RIGHT) |
                        (balanceRight == ScalePosition.RIGHT && balanceLeft == ScalePosition.LEFT)) {
                    return i;
                }

                // if balance is different on the right
                else if (balanceLeft != ScalePosition.EQUAL) {
                    return i + 1;

                }

                // if balance is different on the left
                else if (balanceRight != ScalePosition.EQUAL) {
                    return i - 1;
                }
            }
        }
        return -1;
    }


    /*private int balanceHeavier(int leftStart, int leftEnd, int rightStart, int rightEnd) {
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
    }*/

}
