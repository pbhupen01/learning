package java.eight.lambda;

/**
 * Adding default methods in interfaces makes lets developer modify interface without breaking existing code.
 * Otherwise all the existing classes would require to implement any new method added to interface.
 */
public class DefaultMethodInInterfaceExample {

    interface Formula {
        double calculate(int a);

        // Default method in interface.
        // Class implementing this interface may override this method or use this default implementation.
        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    public static void main(String args[])
    {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        formula.calculate(100);     // 100.0
        formula.sqrt(16);
    }

}

