package lab5;

/**
 * Created by Carla on 08/03/2016.
 */
public class Payment {

    private final int value;
    private final String source;
    private final String target;

    public Payment(String source, String target, int value) {
        this.source = source;
        this.target = target;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
