public class BisectionMethod implements EquationMethod {
    private final int number;
    private final double e;
    private double a;
    private double b;
    private Double result;

    public BisectionMethod(Equation equation) {
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
        double x = 0;
        while (b - a > e) {
            x = (a + b) / 2;
            if (getValueOfFunction(a) * getValueOfFunction(x) < 0) b = x;
            else a = x;
        }
        result = x;
    }
}
