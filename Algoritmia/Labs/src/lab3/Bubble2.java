package lab3;

public class Bubble2 {

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
                Bubble1.bubble(vector);
            }
            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            System.out.println("BUBBLE RANDOM -- size: " + vector.length
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
                Bubble1.bubble(vector);
            }

            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            System.out.println("BUBBLE INVERSE -- size: " + vector.length
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
                Bubble1.bubble(vector);
            }

            long t2 = System.currentTimeMillis();
            long time = t2 - t1;

            System.out.println("BUBBLE SORTED -- size: " + vector.length
                    + " -- time: " + time + " ms -- nTimes: " + nTimes);

            vector = new int[vector.length * 2];

            if (time > timeLimit) {
                nTimes /= 100;
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
    }

    /*private static void createFiles() {
        try {
            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("bubble.csv")));
            writer.write("Sorting,Size,Time(ms),NTimes");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
