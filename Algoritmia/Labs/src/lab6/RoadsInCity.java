package lab6;

/**
 * Created by Carla on 15/03/2016.
 */
public class RoadsInCity {
    private long[][] results;

    public RoadsInCity(int width, int height) {
        this.results = new long[width][height];
    }

    public void addBarrier(int x, int y) {

    }

    public long calculate(int startX, int startY, int endX, int endY) {
        if (endX >= startX || endY <= startY) {
            return -1;
        }
        if (startX == results.length || endX == results.length
                || startY == results[0].length || endY == results[0].length) {
            return -1;
        }

        for (int i = startX; i >= endX; i--) {
            for (int j = startY; j <= endY; j++) {
                if (i == startX || j == startY) {
                    results[i][j] = 1;
                } else {
                    results[i][j] = results[i + 1][j] + results[i][j - 1];
                }
            }
        }
        return results[endX][endY];
    }
}
