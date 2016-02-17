package lab3;

/**
 * Created by carla on 17/02/2016.
 */
public class Selection2 {
    private final static int MIN_SIZE = 512;
    private final static int MAX_SIZE = 128000;
    private final static int N_TIMES = 1000000;
    private final static int TIME_LIMIT = 3000;

    private static int nTimes = N_TIMES;

    public static void main(String[] args) {
        timeSorted();
        timeInverse();
        timeRandom();
    }

    private static void timeRandom() {
        int[] vector = new int[MIN_SIZE];

        while (vector.length <= MAX_SIZE) {
            Vector.random(vector, 100);

            long t1 = System.currentTimeMillis();

            for (int i = 0; i < nTimes; i++) {
                Selection1.selection(vector);
            }
            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            System.out.println("SELECTION RANDOM -- size: " + vector.length
                    + " -- time: " + time + " ms -- nTimes: " + nTimes);

            vector = new int[vector.length * 2];

            if (time > TIME_LIMIT) {
                nTimes /= 100;
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
        nTimes = N_TIMES;
    }

    private static void timeInverse() {
        int[] vector = new int[MIN_SIZE];

        while (vector.length <= MAX_SIZE) {
            Vector.inverselySorted(vector);

            long t1 = System.currentTimeMillis();

            for (int i = 0; i < nTimes; i++) {
                Selection1.selection(vector);
            }

            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            System.out.println("SELECTION INVERSE -- size: " + vector.length
                    + " -- time: " + time + " ms -- nTimes: " + nTimes);

            vector = new int[vector.length * 2];

            if (time > TIME_LIMIT) {
                nTimes /= 100;
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
        nTimes = N_TIMES;
    }

    private static void timeSorted() {
        int[] vector = new int[MIN_SIZE];

        while (vector.length <= MAX_SIZE) {
            Vector.sorted(vector);
            long t1 = System.currentTimeMillis();

            for (int i = 0; i < nTimes; i++) {
                Selection1.selection(vector);
            }

            long t2 = System.currentTimeMillis();
            long time = t2 - t1;

            System.out.println("SELECTION SORTED -- size: " + vector.length
                    + " -- time: " + time + " ms -- nTimes: " + nTimes);

            vector = new int[vector.length * 2];

            if (time > TIME_LIMIT) {
                nTimes /= 100;
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
        nTimes = N_TIMES;
    }
}
