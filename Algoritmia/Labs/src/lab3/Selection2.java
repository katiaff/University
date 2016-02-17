package lab3;

/**
 * Created by carla on 17/02/2016.
 */
public class Selection2 {
    private static int nTimes;
    private static int minSize;
    private static int maxSize;
    private static int timeLimit;

    public static void main(String[] args) {
        int times = Integer.parseInt(args[0]);
        minSize = Integer.parseInt(args[1]);
        maxSize = Integer.parseInt(args[2]);
        timeLimit = Integer.parseInt(args[3]);
        nTimes = times;
        timeSorted();
        nTimes = times;
        timeInverse();
        nTimes = times;
        timeRandom();
    }

    private static void timeRandom() {
        int[] vector = new int[minSize];

        while (vector.length <= maxSize) {
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

            if (time > timeLimit) {
                nTimes /= 100;
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
    }

    private static void timeInverse() {
        int[] vector = new int[minSize];

        while (vector.length <= maxSize) {
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

            if (time > timeLimit) {
                nTimes /= 100;
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
    }

    private static void timeSorted() {
        int[] vector = new int[minSize];

        while (vector.length <= maxSize) {
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

            if (time > timeLimit) {
                nTimes /= 100;
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
    }
}
