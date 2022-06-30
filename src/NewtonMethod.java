import static java.lang.Math.abs;

public class NewtonMethod implements SystemMethod {
    private final int number;
    private final double e;
    private double x;
    private double y;
    private Double resultX;
    private Double resultY;

    public NewtonMethod(EquationsSystem system) {
        this.number = system.getNumber();
        this.e = system.getAccuracy();
        this.x = system.getX();
        this.y = system.getY();
    }

    public void solve() {
        double previousX, previousY;
        do {
            previousX = x;
            previousY = y;
            double[] coefficients = getCoefficients(previousX, previousY);
            x = previousX + coefficients[0];
            y = previousY + coefficients[1];
        } while (abs(x - previousX) > e || abs(y - previousY) > e);
        if (Double.isNaN(x) || Double.isNaN(y)) {
            resultX = null;
            resultY = null;
        } else {
            resultX = x;
            resultY = y;
        }
    }

    private double[] getCoefficients(double x, double y) {
        double[] derivativesByX = getDerivativesByX(x, y);
        double[] derivativesByY = getDerivativesByY(x, y);
        double firstValue = getValueOfFirstFunction(x, y);
        double secondValue = getValueOfSecondFunction(x, y);
        double first = (secondValue * derivativesByY[0] - firstValue * derivativesByY[1]) /
                (derivativesByY[1] * derivativesByX[0] - derivativesByX[1] * derivativesByY[0]);
        double second = (firstValue * derivativesByX[1] - secondValue * derivativesByX[0]) /
                (derivativesByY[1] * derivativesByX[0] - derivativesByX[1] * derivativesByY[0]);
        return new double[]{first, second};
    }

    private double[] getDerivativesByX(double x, double y) {
        double first = (getValueOfFirstFunction(x + 0.00001, y) - getValueOfFirstFunction(x, y)) / 0.00001;
        double second = (getValueOfSecondFunction(x + 0.00001, y) - getValueOfSecondFunction(x, y)) / 0.00001;
        return new double[]{first, second};
    }

    private double[] getDerivativesByY(double x, double y) {
        double first = (getValueOfFirstFunction(x, y + 0.00001) - getValueOfFirstFunction(x, y)) / 0.00001;
        double second = (getValueOfSecondFunction(x, y) - getValueOfSecondFunction(x, y + 0.00001)) / 0.00001;
        return new double[]{first, second};
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public Double getResultX() {
        return resultX;
    }

    @Override
    public Double getResultY() {
        return resultY;
    }
}
