package lab4.recursive;

/* Class that models T(n)=??
 * Params: a=3;b=2;k=3^(n/2)
 * The time complexity is quadratic O(3^(n/2))
 * and the waste of stack is O(?)
 * In this case => the stack does not overflow because 
 * long before the execution time is untreatable 
 */
public class Subtraction4 {
    public static long rec4(int n) {
        long cont = 0;
        if (n <= 0) cont++;
        else {
            cont++;  //O(1)
            rec4(n - 2);
            rec4(n - 2);
            rec4(n - 2);
        }
        return cont;
    }

    public static void main(String arg[]) {
        long t1, t2, cont = 0;
        for (int n = 1; n <= 100; n++) {
            t1 = System.currentTimeMillis();

            cont = rec4(n);

            t2 = System.currentTimeMillis();

            System.out.println("n=" + n + "**TIME=" + (t2 - t1) + "**cont=" + cont);
        }  // for
    } // main
} //class