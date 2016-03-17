package lab6;

/**
 * Model which solves the problem of the paths to go from one place to another
 * moving north or east
 */
public class RoadsInCity {
    private long[][] results;

    public RoadsInCity(int width, int height) {
        this.results = new long[width][height];
    }

    public void addBarrier(int x, int y) {
        this.results[x][y] = -2;
    }

    public long calculate(int startX, int startY, int endX, int endY) {
        if (endX >= startX || endY <= startY) {
            return -1;
        }
        if (startX == results.length || endX == results.length
                || startY == results[0].length || endY == results[0].length) {
            return -1;
        }

        fillArray(startX, startY, endX, endY);

        return results[endX][endY];
    }

    private void fillArray(int startX, int startY, int endX, int endY) {
        int j = startY;

        // if the barrier is above the start, all that column will be zeroes
        if (results[startX - 1][startY] == -2) {
            for (int i = 0; i < results.length; i++) {
                results[i][startY] = 0;
            }
            // start from the next column
            j = startY + 1;
        }

        for (int i = startX; i >= endX; i--) {
            for (int k = j; k <= endY; k++) {
                // if there is a barrier, possibilities are 0
                if (results[i][k] == -2) {
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
