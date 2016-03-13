package lab5;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * JUnit Test for MinimizeCashFlow
 */
public class MinimizeCashFlowTest {
    @Test
    public void testA() {
        MinimizeCashFlow cashFlow = loadDataFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab5\\case1.txt");

        //Calculation
        cashFlow.calculate();

        //Expected results VS Actual results
        assertEquals(0, cashFlow.getFinalDebt("David", "Juan"));
        assertEquals(4000, cashFlow.getFinalDebt("Juan", "Ana"));
        assertEquals(3000, cashFlow.getFinalDebt("David", "Ana"));
    }

    @Test
    public void testB() {
        MinimizeCashFlow cashFlow = loadDataFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab5\\case2.txt");

        //Calculation
        cashFlow.calculate();

        //Expected results VS Actual results
        assertEquals(45, cashFlow.getFinalDebt("Victor", "Tamara"));
        assertEquals(15, cashFlow.getFinalDebt("Victor", "Elena"));
        assertEquals(5, cashFlow.getFinalDebt("Alberto", "Celia"));
        assertEquals(5, cashFlow.getFinalDebt("Alberto", "Pablo"));
    }

    @Test
    public void testC() {
        MinimizeCashFlow cashFlow = loadDataFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab5\\case3.txt");

        //Calculation
        cashFlow.calculate();

        //Expected results VS Actual results
        assertEquals(45, cashFlow.getFinalDebt("Victor", "Tamara"));
        assertEquals(15, cashFlow.getFinalDebt("Victor", "Celia"));
        assertEquals(16, cashFlow.getFinalDebt("Alberto", "Elena"));
    }

    private MinimizeCashFlow loadDataFromFile(String file) {
        MinimizeCashFlow cashFlow = new MinimizeCashFlow(); //Create an object to work
        //Input
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file)); //Read the specified file
            String line;
            line = br.readLine();

            while (line != null) { //Compute all the payments in the text file
                String[] values = line.split(" ");
                String source = values[0]; //Person that pays some money
                String target = values[1]; //Person that receives some money
                int value = Integer.valueOf(values[2]); //Amount of money

                Payment payment = new Payment(source, target, value); //Create a payment with the initial information
                cashFlow.addPayment(payment); //Add the payment to the program

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cashFlow;
    }


}
