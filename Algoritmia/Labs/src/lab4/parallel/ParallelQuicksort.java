package lab4.parallel;

import lab3.Util;

import java.util.concurrent.RecursiveAction;

/* This program can be used to sort n elements with
 * the best algorithm. It is the QUICKSORT */
public class ParallelQuicksort extends RecursiveAction {

    private int[] elements;
    private int left;
    private int right;

    public ParallelQuicksort(int[] elements, int left, int right) {
        this.elements = elements;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        int i = this.left;
        int j = this.right - 1;
        int pivot;

        // if there is one element it is not necessary
        if (left < right) {
            int center = (left + right) / 2;
            pivot = elements[center]; // choose the pivot
            Util.interchange(elements, center, right); // hide the pivot

            do {
                while (elements[i] <= pivot && i < right)
                    i++; // first element > pivot
                while (elements[j] >= pivot && j > left)
                    j--; // first element < pivot
                if (i < j)
                    Util.interchange(elements, i, j);
            } while (i < j); // end while

            // we set the position of the pivot
            Util.interchange(elements, i, right);

            ParallelQuicksort qLeft = new ParallelQuicksort(elements, left, i - 1);
            ParallelQuicksort qRight = new ParallelQuicksort(elements, i + 1, right);
            invokeAll(qLeft, qRight);
        }
    }
}
