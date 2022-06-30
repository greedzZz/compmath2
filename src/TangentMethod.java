public class TangentMethod implements EquationMethod {
    private final int number;
    private final double e;
    private final double a;
    private final double b;
    private Double result;

    public TangentMethod(Equation equation) {
        this.number = equation.getNumber();
        this.e = equation.getAccuracy();
        this.a = equation.getLeftBoundary();
        this.b = equation.getRightBoundary();
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public Double getResult() {
        return result;
    }

    @Override
    public void solve() {
        if (getValueOfFunction(a) * getValueOfFunction(b) > 0) {
            result = null;
            return;
        }
        double x = a;
        if (getValueOfFunction(b) * getDerivative(2, b) > 0) x = b;
        double previousX;
        do {
            previousX = x;
            x = x - getValueOfFunction(x) / getDerivative(1, x);
        } while (Math.abs(x - previousX) > e);
        result = x;
    }

    private double getDerivative(int level, double x) {
        if (level <= 0) return 0;
        if (level == 1) return (getValueOfFunction(x + 0.00001) - getValueOfFunction(x)) / 0.00001;
        return (getDerivative(level - 1, x + 0.00001) - getDerivative(level - 1, x)) / 0.00001;
    }
}
