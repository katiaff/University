package lab4.coins;

import static lab4.coins.ScalePosition.EQUAL;

/**
 * Class to recursively solve the Fake Coin problem:
 * Finds a fake coin (weighs differently from
 * the rest of the coins) in a given bag of Coins.
 */
public class FakeCoins {
    private Coins bag;

    /**
     * Constructs the problem with a given bag of Coins
     *
     * @param bag Bag of Coins
     */
    public FakeCoins(Coins bag) {
        this.bag = bag;
    }

    /**
     * Recursively finds the fake coin in the bag
     *
     * @return Position of fake coin; -1 if not found
     */
    public int findFake() {
        return findFakeRecursive(0, bag.getNumberOfCoins() - 1);
    }

    /**
     * Private method to solve the problem
     *
     * @param start first coin
     * @param end   last coin
     * @return Position of fake coin; -1 if not found
     */
    private int findFakeRecursive(int start, int end) {
        // note that distance = numElements - 1
        // thus, an even distance indicates an odd number of elements
        // an odd distance indicates an even number of elements
        int distance = end - start;

        // base case: 3 coins
        if (distance == 2) {
            return getFakePos3(start, end);
        }

        // special case : 4 coins
        else if (distance == 3) {
            return getFakePos4(start, end);
        }

        // special case : 5 coins
        else if (distance == 4) {
            // assert if the fake is in the left 4 coins
            int fakePos = getFakePos4(start, end - 1);
            // if it is, return it, otherwise it's the last coin
            return fakePos != -1 ? fakePos : end;
        }

        // special case : 6 coins
        else if (distance == 5) {
            // balance each 3-coin side
            int balanceLeft = getFakePos3(start, start + 2);
            int balanceRight = getFakePos3(end - 2, end);
            // return the result of the balance where the coin was found
            return balanceLeft != -1 ? balanceLeft : balanceRight;
        }

        // general case
        else {
            int center = distance / 2;
            // odd distance --> do not repeat general central element
            if (distance % 2 != 0) {
                // odd distance, odd center --> do not repeat central element on either half
                if (center % 2 != 0) {
                    ScalePosition balanceLeft = bag.weigh(start, start + center / 2,
                            start + center / 2 + 1,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center + 1,
                            start + center + (center / 2), start + center + (center / 2) + 1,
                            end);
                    // fake coin can be on the center of the first half or the center of the second half
                    if (balanceLeft == EQUAL && balanceRight == EQUAL) {
                        return getFakePosCenter(start + center / 2,
                                start + center + (center / 2) + 1);
                    }
                    // if left unbalanced --> look left
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    }
                    // if right unbalanced --> look right
                    else {
                        return findFakeRecursive(start + center + 1, end);
                    }
                }
                // odd distance, even center --> repeat central element only on each half
                else {
                    ScalePosition balanceLeft = bag.weigh(start, start + center / 2,
                            start + center / 2,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center + 1,
                            start + center + (center / 2) + 1, start + center + (center / 2) + 1,
                            end);
                    // fake coin can be on the center of the first half or the center of the second half
                    if (balanceLeft == EQUAL && balanceRight == EQUAL) {
                        return getFakePosCenter(start + center / 2,
                                start + center + (center / 2) + 1);
                    }
                    // if left unbalanced --> look left
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    }
                    // if right unbalanced --> look right
                    else {
                        return findFakeRecursive(start + center + 1, end);
                    }
                }
            }
            // even distance --> repeat general central element
            else {
                // even distance, odd center --> do not repeat central element of each half
                if (center % 2 != 0) {
                    ScalePosition balanceLeft = bag.weigh(start, start + (center / 2),
                            start + (center / 2) + 1,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center,
                            start + center + (center / 2), start + center + (center / 2) + 1,
                            end);
                    // fake coin unbalances both sides, so it is the center element
                    if (balanceLeft != EQUAL && balanceRight != EQUAL) {
                        return center;
                    }
                    // if left unbalanced --> look left
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    }
                    // if right unbalanced --> look right
                    if (balanceRight != EQUAL) {
                        return findFakeRecursive(start + center, end);
                    }
                }
                // even distance, even center --> repeat the central element on each half
                else {
                    ScalePosition balanceLeft = bag.weigh(start, start + center / 2,
                            start + center / 2,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center,
                            start + center + (center / 2), start + center + (center / 2),
                            end);
                    // fake coin can be on the center of the first half or the center of the second half
                    if (balanceLeft == EQUAL && balanceRight == EQUAL) {
                        return getFakePosCenter(start + center / 2,
                                start + center + (center / 2));
                    }
                    // if left unbalanced --> look left
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    }
                    // if right unbalanced --> look right
                    else {
                        return findFakeRecursive(start + center, end);
                    }
                }
            }
        }

        return -1;
    }

    /**
     * Gets the fake among two possible positions
     *
     * @param low  - first possibility
     * @param high - second possibility
     * @return fake position; -1 if not found
     */
    private int getFakePosCenter(int low, int high) {
        return getFakePos3(low - 1, low + 1) != -1 ? low : high;
    }

    /**
     * Gets the fake coin among 3 possible coins
     *
     * @param start - first coin
     * @param end   - last coin
     * @return position of fake coin; -1 if not found
     */
    private int getFakePos3(int start, int end) {
        // balance first coin with second coin
        ScalePosition balanceLeft = bag.weigh(start, start, start + 1,
                start + 1);
        // balance second coin with third coin
        ScalePosition balanceRight = bag.weigh(start + 1, start + 1, end, end);

        // if there is a change, we found a fake coin
        if (balanceRight != balanceLeft) {

            // if balance is different on either side of the center coin
            if (balanceLeft != EQUAL && balanceRight != EQUAL) {
                return start + 1;
            }

            // if balance is different on the right
            else if (balanceLeft != EQUAL) {
                return start;
            }

            // if balance is different on the left
            else {
                return end;
            }
        }

        return -1;

    }

    /**
     * Gets the position of fake coin among 4 possible coins
     *
     * @param start - first coin
     * @param end   - last coin
     * @return position of fake coin; -1 if not found
     */
    private int getFakePos4(int start, int end) {
        // balance first coin with second coin
        ScalePosition balanceLeft = bag.weigh(start, start, start + 1, start + 1);
        // balance third and fourth coins
        ScalePosition balanceRight = bag.weigh(end - 1, end - 1, end, end);

        // if there is a change, we found a fake coin
        if (balanceRight != balanceLeft) {

            // if balance is different on the right
            if (balanceLeft != EQUAL) {
                return getFakePos3(start, end - 1);
            }

            // if balance is different on the left
            else {
                return getFakePos3(start + 1, end);
            }
        }

        return -1;

    }
}
