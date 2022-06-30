import java.util.Scanner;

public class InputManager {
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Решение нелинейных уравнений и систем нелинейных уравнений.");
        int type = readType();
        if (type == 1) {
            Equation equation = new Equation(readEquationNumber(), readAccuracy(), readBoundary());
            BisectionMethod bisectionMethod = new BisectionMethod(equation);
            TangentMethod tangentMethod = new TangentMethod(equation);
            bisectionMethod.solve();
            tangentMethod.solve();
            if (null == bisectionMethod.getResult() || null == tangentMethod.getResult())
                System.out.println("Указан неверный интервал.");
            else {
                System.out.println("Результат метода половинного деления:");
                System.out.println("x = " + bisectionMethod.getResult());
                System.out.println("\nРезультат метода касательных:");
                System.out.println("x = " + tangentMethod.getResult());
                System.out.println("\nРазница между ответами: " + Math.abs(bisectionMethod.getResult() - tangentMethod.getResult()));
            }
        } else {
            EquationsSystem system = new EquationsSystem(readSystemNumber(), readAccuracy(), readApproximation());
            NewtonMethod newtonMethod = new NewtonMethod(system);
            newtonMethod.solve();
            System.out.println("Результат метода Ньютона:");
            if (null == newtonMethod.getResultX() || null == newtonMethod.getResultY())
                System.out.println("Нет корней.");
            else {
                System.out.println("x = " + newtonMethod.getResultX());
                System.out.println("y = " + newtonMethod.getResultY());
            }
        }
    }

    private int readType() {
        int type = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Выберите, что необходимо решить:\n[1]: нелинейное уравнение.\n[2]: система нелинейных уравнений.");
            try {
                type = Integer.parseInt(scanner.nextLine());
                if (type >= 1 && type <= 2) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return type;
    }

    private int readEquationNumber() {
        int number = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Выберите уравнение, которое необходимо решить:");
            System.out.println("[1]: " + Data.EQUATIONS[0]);
            System.out.println("[2]: " + Data.EQUATIONS[1]);
            System.out.println("[3]: " + Data.EQUATIONS[2]);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= 1 && number <= 3) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return number;
    }

    private int readSystemNumber() {
        int number = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Выберите систему уравнений, которую необходимо решить:");
            System.out.println("[1]:");
            System.out.println(Data.SYSTEMS[0][0]);
            System.out.println(Data.SYSTEMS[0][1]);
            System.out.println("[2]:");
            System.out.println(Data.SYSTEMS[1][0]);
            System.out.println(Data.SYSTEMS[1][1]);
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= 1 && number <= 2) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return number;
    }

    private double readAccuracy() {
        double accuracy = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Введите точность от 0 до 1:");
            try {
                accuracy = Double.parseDouble(scanner.nextLine());
                if (accuracy > 0 && accuracy < 1) correct = true;
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return accuracy;
    }

    private double[] readBoundary() {
        double left = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Введите левую границу интервала: ");
            try {
                left = Double.parseDouble(scanner.nextLine());
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        double right = 0;
        correct = false;
        while (!correct) {
            System.out.println("Введите правую границу интервала: ");
            try {
                right = Double.parseDouble(scanner.nextLine());
                if (right > left) correct = true;
                else throw new IllegalArgumentException();
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение.");
            } catch (IllegalArgumentException e) {
                System.out.println("Правая граница должна быть больше левой.");
            }
        }
        return new double[]{left, right};
    }

    private double[] readApproximation() {
        double x = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Введите приближение для x: ");
            try {
                x = Double.parseDouble(scanner.nextLine());
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        double y = 0;
        correct = false;
        while (!correct) {
            System.out.println("Введите приближение для y: ");
            try {
                y = Double.parseDouble(scanner.nextLine());
                correct = true;
            } catch (NumberFormatException e) {
                System.out.println("Введено неверное значение.");
            }
        }
        return new double[]{x, y};
    }
}
