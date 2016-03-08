package lab4.coins;

import java.util.Random;

/**
 * JUnit Test for FakeCoins
 */
public class FakeCoinsTimes {
    public static void main(String[] args) {
        int size = 1000;
        int nTimes = 1000000;
        do {
            Coins bag = new Coins(size, new Random().nextInt(size + 1));
            FakeCoins fakeCoins = new FakeCoins(bag);
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < nTimes; i++) {
                fakeCoins.findFake();
            }
            long t2 = System.currentTimeMillis();
            long time = t2 - t1;
            System.out.println("Size: " + size + ", time: " + time + ", nTimes: " + nTimes);
            size *= 2;
            nTimes = time > 2500 ? nTimes / 10 : nTimes;
        } while (size < 100000000);

    }
}
