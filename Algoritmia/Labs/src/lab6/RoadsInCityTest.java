package lab6;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * JUnit Test for RoadsInCity
 */
public class RoadsInCityTest {

    @Test
    public void testA() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case1.txt");
        assertEquals(2, result);
    }

    @Test
    public void testB() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case2.txt");
        assertEquals(252, result);
    }

    @Test
    public void testC() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case3.txt");
        assertEquals(0, result);
    }

    @Test
    public void testD() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case4.txt");
        assertEquals(6406484391866534976L, result);
    }

    @Test
    public void testE() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case5.txt");
        assertEquals(4, result);
    }


    @Test
    public void testF() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case6.txt");
        assertEquals(2, result);
    }

    @Test
    public void testG() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case7.txt");
        assertEquals(-1, result);
    }

    @Test
    public void testH() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case8.txt");
        assertEquals(-1, result);
    }

    @Test
    public void testI() {
        long result = executeFromFile("D:\\Documentos\\Git\\University\\Algoritmia\\Labs\\src\\lab6\\case9.txt");
        assertEquals(-1, result);
    }

    private long executeFromFile(String file) {
        long result = 0;
        //Input
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));

            //First line (SIZE OF THE CITY)
            String[] size = br.readLine().split(","); //width and height of the city
            RoadsInCity roads = new RoadsInCity(Integer.parseInt(size[0]), Integer.parseInt(size[1]));

            //Second line (BARRIERS)
            try {
                String[] barriers = br.readLine().split(";");
                if (barriers.length > 0)
                    for (int i = 0; i < barriers.length; i++) {
                        String[] barrierPosition = barriers[i].split(","); //barrierX and barrierY
                        roads.addBarrier(Integer.parseInt(barrierPosition[0]), Integer.parseInt(barrierPosition[1]));
                    }
            } catch (Exception ex) { //There are not barriers...
            }

            //Third line (STARTING AND END POINTS)
            String[] positions = br.readLine().split(";");
            String[] start = positions[0].split(","); //startX and startY
            String[] end = positions[1].split(","); //endX and endY
            result = roads.calculate(Integer.parseInt(start[0]), Integer.parseInt(start[1]),
                    Integer.parseInt(end[0]), Integer.parseInt(end[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
