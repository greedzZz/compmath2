import static java.lang.Math.*;

public interface EquationMethod {

    int getNumber();

    Double getResult();

    void solve();

    default double getValueOfFunction(double x) {
        return switch (getNumber()) {
            case (1) -> 1.73d * pow(x, 3) - 3.21d * pow(x, 2) + x - 0.025d;
            case (2) -> 2.24 * x + pow(x, 2) - 1 - pow(E, -1 * x);
            case (3) -> 4.31 * cos(x) + 0.5 * log(x) - 5;
            default -> 0;
        };
    }
}
