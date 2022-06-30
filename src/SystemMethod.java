import static java.lang.Math.pow;
import static java.lang.Math.sin;

public interface SystemMethod {

    int getNumber();

    void solve();

    Double getResultX();

    Double getResultY();

    default double getValueOfFirstFunction(double x, double y) {
        return switch (getNumber()) {
            case (1) -> 0.1 * pow(x, 2) + x + 0.2 * y - 0.3;
            case (2) -> sin(x + 0.5) - y - 1;
            default -> 0;
        };
    }

    default double getValueOfSecondFunction(double x, double y) {
        return switch (getNumber()) {
            case (1) -> 0.2 * pow(x, 2) + y - 0.1 * x * y - 0.7;
            case (2) -> y + 5 * pow(x, 2) - 13;
            default -> 0;
        };
    }

}
