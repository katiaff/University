package lab3;

/**
 * Created by carla on 17/02/2016.
 */
public class Runner {

    public static void main(String[] args) {
        System.out.println("BUBBLE ALGORITHM: \n");
        BubbleRunner.main(args);
        System.out.println("END OF BUBBLE \n");
        System.out.println("INSERTION ALGORITHM: \n");
        InsertionRunner.main(args);
        System.out.println("END OF INSERTION \n");
        System.out.println("QUICKSORT ALGORITHM: \n");
        QuicksortCentralElementRunner.main(args);
        System.out.println("END OF QUICKSORT \n");
        System.out.println("SELECTION ALGORITHM: \n");
        SelectionRunner.main(args);
        System.out.println("END OF SELECTION \n");

    }
}
