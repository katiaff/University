package lab3;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class BubbleRunner {

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
        createFiles(times);
        /*
        timeSorted();
        nTimes = times;
        timeInverse();
        nTimes = times;
        timeRandom();*/
    }

    private static String timeRandom() {
        int[] vector = new int[minSize];
        StringBuilder ret = new StringBuilder();

        while (vector.length <= maxSize) {
            Vector.random(vector, 100);

            long t1 = System.currentTimeMillis();

            for (int i = 0; i < nTimes; i++) {
                Bubble1.bubble(vector);
            }
            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            ret.append("random;" + vector.length + ";" +
                    +time + ";" + nTimes + ";" + "\n");

            vector = new int[vector.length * 2];

            if (time > timeLimit) {
                if (nTimes != 1) {
                    nTimes /= 100;
                } else {
                    break;
                }
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
        return ret.toString();
    }

    private static String timeInverse() {
        int[] vector = new int[minSize];
        StringBuilder ret = new StringBuilder();

        while (vector.length <= maxSize) {
            Vector.inverselySorted(vector);

            long t1 = System.currentTimeMillis();

            for (int i = 0; i < nTimes; i++) {
                Bubble1.bubble(vector);
            }

            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            ret.append("inverse;" + vector.length + ";" +
                    +time + ";" + nTimes + ";" + "\n");

            vector = new int[vector.length * 2];

            if (time > timeLimit) {
                if (nTimes != 1) {
                    nTimes /= 100;
                } else {
                    break;
                }
            }
        }
        System.out.println("\n------------------------------------------------------------------\n");
        return ret.toString();
    }

    private static String timeSorted() {
        int[] vector = new int[minSize];
        StringBuilder ret = new StringBuilder();

        while (vector.length <= maxSize) {
            Vector.sorted(vector);
            long t1 = System.currentTimeMillis();

            for (int i = 0; i < nTimes; i++) {
                Bubble1.bubble(vector);
            }

            long t2 = System.currentTimeMillis();
            long time = t2 - t1;

            ret.append("sorted;" + vector.length + ";" +
                    +time + ";" + nTimes + ";" + "\n");

            vector = new int[vector.length * 2];

            if (time > timeLimit) {
                if (nTimes != 1) {
                    nTimes /= 100;
                } else {
                    break;
                }
            }

        }
        System.out.println("\n------------------------------------------------------------------\n");
        return ret.toString();
    }

    private static void createFiles(int times) {
        try {
            FileWriter file = new FileWriter("bubble.csv");
            file.append("Sorting;Size;Total time(ms);NTimes;\n");

            System.out.println("\n----------------------BUBBLE ALGORITHM---------------------------\n");
            System.out.println("Logging SORTED time");
            file.append(timeSorted());

            System.out.println("Logging INVERSE time");
            nTimes = times;
            file.append(timeInverse());

            System.out.println("Logging RANDOM time");
            nTimes = times;
            file.append(timeRandom());

            file.flush();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
