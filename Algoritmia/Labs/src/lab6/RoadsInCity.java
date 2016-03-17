package lab6;

/**
 * Model which solves the problem of the paths to go from one place to another
 * moving north or east
 */
public class RoadsInCity {
    private final static boolean BEGIN = true;
    private final static boolean END = false;
    private long[][] results;

    public RoadsInCity(int width, int height) {
        this.results = new long[width][height];
    }

    public void addBarrier(int x, int y) {
        this.results[x][y] = -1;
    }

    public long calculate(int startX, int startY, int endX, int endY) {
        printTable(startX, startY, endX, endY, BEGIN);

        if (endX >= startX || endY <= startY) {
            return -1;
        }
        if (startX == results.length || endX == results.length
                || startY == results[0].length || endY == results[0].length) {
            return -1;
        }

        fillArray(startX, startY, endX, endY);

        printTable(startX, startY, endX, endY, END);
        return results[endX][endY];
    }


    private void printTable(int startX, int startY, int endX, int endY, boolean when) {
        if (when == BEGIN) {
            System.out.println("Starting position: (" + startX + ", " + startY + ")");
            System.out.println("Target position: (" + endX + ", " + endY + ")");
            System.out.println("Representation of the map at the beginning");
        } else {
            System.out.println("Representation of the map at the end");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < results[0].length; j++) {
                if (i == startX && j == startY) {
                    sb.append("START" + "\t");
                } else if (i == endX && j == endY) {
                    if (when == BEGIN) {
                        sb.append("END" + "\t");
                    } else {
                        sb.append("END = ").append(results[i][j]).append("\t");
                    }

                } else {
                    sb.append(results[i][j]).append("\t\t");
                }
                if (j == results[0].length - 1) {
                    sb.append("\n");
                }

            }
        }
        System.out.println(sb.toString());
        if (when == END)
            System.out.println("----------------------------------------------");
    }


    private void fillArray(int startX, int startY, int endX, int endY) {
        int j = startY;

        // if the barrier is above the start, all that column will be zeroes
        if (results[startX - 1][startY] == -1) {
            for (int i = 0; i < results.length; i++) {
                results[i][startY] = 0;
            }
            // start from the next column
            j = startY + 1;
        }

        for (int i = startX; i >= endX; i--) {
            for (int k = j; k <= endY; k++) {
                // if there is a barrier, possibilities are 0
                if (results[i][k] == -1) {
                    results[i][k] = 0;
                }
                // if it is the starting row or column, 1's
                else if (i == startX || k == startY) {
                    results[i][k] = 1;
                }
                // general case, sum number on the left and number below
                else {
                    results[i][k] = results[i + 1][k] + results[i][k - 1];
                }

            }
        }
    }
}
