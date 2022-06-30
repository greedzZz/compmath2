public class EquationsSystem {
    private final int number;
    private final double accuracy;
    private final double x;
    private final double y;

    public EquationsSystem(int number, double accuracy, double[] approximation) {
        this.number = number;
        this.accuracy = accuracy;
        this.x = approximation[0];
        this.y = approximation[1];
    }

    public int getNumber() {
        return number;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
