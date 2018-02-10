package java.eight.lambda;

/**
 * Anonymous class let us write classes without specifying name. These classes are basically implementation which are treated as object.
 * Anonymous class with single method is moreover a functionality and seems bit excessive and cumbersome. As for single functionality we need to implement a class with only one method.
 *
 * Lambda expression let us write this single functionality in a concise way. It enable us to treat functionality as method argument, code or data.
 * It is first step to functional programming by java. In which we can treat any function as a variable, argument, can pass it some other method.
 * It provides a clear and concise way to represent one method interface using an expression.
 *
 * Each lambda corresponds to a given type, which is specified by a Functional Interface (interface with single abstract method, but may have multiple default methods)
 * All lambda expression of that type will be matched to this abstract method.
 * We can add @FunctionalInterface annotation to ensure that interface has only one abstract method. Compiler verifies it if this annotation is provided for interface.
 * In functional programming language (e.g. Scala) we do not need to map interface to any function lambda expression.
 *
 *
 */
public class BasicLambdaExpressionExample {

    // Interface must have single non abstract method for it to be mapped as lambda expression.
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);

        // void failingNonAbstractMethod();

        default void allowedDefaultMethod()
        {
            System.out.println("This default method is allowed in Functional Interface");
        }
    }

    public void main(String args[])
    {
    }
}
