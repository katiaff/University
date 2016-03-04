package lab4.parallel;

import lab3.Vector;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by carla on 17/02/2016.
 */
public class QuicksortTimes {
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
    }

    private static String timeRandom() {
        ForkJoinPool pool = new ForkJoinPool();
        StringBuilder ret = new StringBuilder();
        int[] vector = new int[minSize];
        ParallelQuicksort quicksort;

        while (vector.length <= maxSize) {

            vector = new int[vector.length * 2];
            Vector.random(vector, 100);
            quicksort = new ParallelQuicksort(vector, 0, vector.length - 1);

            long t1 = System.currentTimeMillis();
            for (int i = 0; i < nTimes; i++) {
                pool.invoke(quicksort);
            }
            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            ret.append("random;" + vector.length + ";" +
                    +time + ";" + nTimes + "\n");

            System.out.println("length: " + vector.length + " - time: " + time);
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
        ForkJoinPool pool = new ForkJoinPool();
        StringBuilder ret = new StringBuilder();
        int[] vector = new int[minSize];
        ParallelQuicksort quicksort;

        while (vector.length <= maxSize) {

            vector = new int[vector.length * 2];
            Vector.inverselySorted(vector);
            quicksort = new ParallelQuicksort(vector, 0, vector.length - 1);

            long t1 = System.currentTimeMillis();
            for (int i = 0; i < nTimes; i++) {
                pool.invoke(quicksort);
            }
            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            ret.append("random;" + vector.length + ";" +
                    +time + ";" + nTimes + "\n");

            System.out.println("length: " + vector.length + " - time: " + time);

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
        ForkJoinPool pool = new ForkJoinPool();
        StringBuilder ret = new StringBuilder();
        int[] vector = new int[minSize];
        ParallelQuicksort quicksort;

        while (vector.length <= maxSize) {

            vector = new int[vector.length * 2];
            Vector.sorted(vector);
            quicksort = new ParallelQuicksort(vector, 0, vector.length - 1);

            long t1 = System.currentTimeMillis();
            for (int i = 0; i < nTimes; i++) {
                pool.invoke(quicksort);
            }
            long t2 = System.currentTimeMillis();

            long time = t2 - t1;

            ret.append("random;" + vector.length + ";" +
                    +time + ";" + nTimes + "\n");

            System.out.println("length: " + vector.length + " - time: " + time);

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
            FileWriter file = new FileWriter("quicksort.csv");
            file.append("Sorting;Size;Total time(ms);NTimes\n");

            System.out.println("\n----------------------QUICKSORT ALGORITHM---------------------------\n");
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
