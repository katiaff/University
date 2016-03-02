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
            int fakePos = getFakePos4(start, end - 1);
            return fakePos != -1 ? fakePos : end;
        } else if (distance == 5) {
            int balanceLeft = getFakePos3(start, start + 2);
            int balanceRight = getFakePos3(end - 2, end);
            return balanceLeft != -1 ? balanceLeft : balanceRight;
        }
        // general case
        else {
            int center = distance / 2;
            if (distance % 2 != 0) {
                if (center % 2 != 0) {
                    ScalePosition balanceLeft = bag.weigh(start, start + center / 2, start + center / 2 + 1,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center, start + center + (center / 2) + 1, start + center + (center / 2) + 1,
                            end);
                    if (balanceLeft == EQUAL && balanceRight == EQUAL) {
                        return getFakePosCenter(start + center / 2, start + center + (center / 2) + 1);
                    }
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    } else {
                        return findFakeRecursive(start + center + 1, end);
                    }
                } else {
                    ScalePosition balanceLeft = bag.weigh(start, start + center / 2, start + center / 2,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center + 1, start + center + (center / 2) + 1, start + center + (center / 2) + 1,
                            end);
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    }
                    if (balanceRight != EQUAL) {
                        return findFakeRecursive(start + center + 1, end);
                    } else {
                        return getFakePosCenter(start + center / 2, start + center + (center / 2) + 1);
                    }
                }
            } else {
                if (center % 2 != 0) {
                    ScalePosition balanceLeft = bag.weigh(start, start + (center / 2), start + (center / 2) + 1,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center, start + center + (center / 2), start + center + (center / 2) + 1,
                            end);
                    if (balanceLeft != EQUAL && balanceRight != EQUAL) {
                        return center;
                    }
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    }
                    if (balanceRight != EQUAL) {
                        return findFakeRecursive(start + center, end);
                    }
                } else {
                    ScalePosition balanceLeft = bag.weigh(start, start + center / 2, start + center / 2,
                            start + center);
                    ScalePosition balanceRight = bag.weigh(start + center, start + center + (center / 2), start + center + (center / 2),
                            end);
                    if (balanceLeft != EQUAL) {
                        return findFakeRecursive(start, start + center);
                    }
                    if (balanceRight != EQUAL) {
                        return findFakeRecursive(start + center, end);
                    } else {
                        return getFakePosCenter(start + center / 2, start + center + (center / 2));
                    }
                }
            }
        }

        return -1;
    }

    private int getFakePosCenter(int low, int high) {
        return getFakePos3(low - 1, low + 1) != -1 ? low : high;
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

    private int getFakePos3V2(int start, int end) {
        for (int i = start + 1; i < start + 2; i++) {
            // get the balance on the left and on the right
            ScalePosition balanceRight = bag.weigh(i - 1, i - 1, i, i);
            ScalePosition balanceLeft = bag.weigh(i, i, i + 1, i + 1);

            // if there is a change, we found a fake coin
            if (balanceRight != balanceLeft) {

                // if balance is different on either side of i
                if ((balanceRight == LEFT && balanceLeft == RIGHT) ||
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

    private int getFakePos4(int start, int end) {
        ScalePosition balanceLeft = bag.weigh(start, start, start + 1, start + 1);
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
