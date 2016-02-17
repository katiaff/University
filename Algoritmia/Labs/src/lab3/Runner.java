package lab3;

/**
 * Created by carla on 17/02/2016.
 */
public class Runner {

    public static void main(String[] args) {
        System.out.println("BUBBLE ALGORITHM: \n");
        Bubble2.main(args);
        System.out.println("END OF BUBBLE \n");
        System.out.println("INSERTION ALGORITHM: \n");
        Insertion2.main(args);
        System.out.println("END OF INSERTION \n");
        System.out.println("QUICKSORT ALGORITHM: \n");
        QuicksortCentralElement2.main(args);
        System.out.println("END OF QUICKSORT \n");
        System.out.println("SELECTION ALGORITHM: \n");
        Selection2.main(args);
        System.out.println("END OF SELECTION \n");

    }
}
