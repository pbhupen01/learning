package javaLang.eight.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 *
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
 * So to summarize:
 * Lambda, implementation of single function interface, is replacement of single function Anonymous class.
 * You can pass this implementation directly to some other method.
 * Or you can call it by calling the interface function.
 *
 *
 * Implementation Steps:
 * 1. Define Functional Interface
 * 2. Defining Lambda - implementation of Interface in following format:
 *    (params..) -> {Implementation body}
 *    Please note for single statement implementation we can skip curly brackets
 *    (params..) -> Implementation single statement
 *
 * Ways of using Lambda:
 * 1. Directly calling Functional Interface function on the implementation reference object
 * 2. Passing lambda to some method which would then call the Functional Interface function on the passed Lambda (implementation object)
 *
 * Point to be noted java provides :: operator for calling method
 * System.out::println
 *
 */
public class BasicLambdaExpressionExample {

    /**
     * Implementation Step 1: Defining Functional Interface
     *
     */
    // Interface must have single non abstract method for it to be mapped as lambda expression.
    @FunctionalInterface
    interface Converter<F, T> {

        /**
         * Abstract function
         */
        T convert(F from);

        // void failingNonAbstractMethod();

        default void allowedDefaultMethod(F from)
        {
            System.out.println("This default method is allowed in Functional Interface");
        }
    }

    public static void main(String args[])
    {
        basicLambdaExpressionImplementation();
        tipsOfUsingLambdaExpression();
    }


    private static void basicLambdaExpressionImplementation()
    {
        /**
         * Implementation Step 2: Defining Lambda
         */
        // Please note that for single argument we can skip parenthesis
        Converter<Integer, String> intToStringConverter =  i -> String.valueOf(i);
        // Another implementation of above call. In below implementation passed argument is implicit.
        // If there are more than one implementation of String.valueOf then based on the parameters passed in Use no 2 String.valueOf method will be called
        Converter<Integer, String> intToStringConverter2 =  String::valueOf;

        /**
         *  First way of using lambda: by directly calling method of the interface implementation object
         */
        System.out.println(intToStringConverter.convert(123));
        // Use No 2
        System.out.println(intToStringConverter2.convert(456));


        // Default method can not be called from lambda expression
        //Converter<Integer, Void> testConverter = i -> allowedDefaultMethod;

        /**
         * Second way of using lambda by passing it to another method.
         *
         * You use lambda expressions to create anonymous methods. Sometimes, however, a lambda expression does nothing but call an existing method.
         * In those cases, it's often clearer to refer to the existing method by name.
         * Method references enable you to do this; they are compact, easy-to-read lambda expressions for methods that already have a name.
         */
        Arrays.stream(new String[]{"111,222,444"}).forEach(String::valueOf);
        //Arrays.stream(new String[]{"111,222,444"}).forEach(intToStringConverter2);
    }

    private static void tipsOfUsingLambdaExpression()
    {
        // avoid specifying type in lambda
        // avoid paranthesis around single parameter
        // avoid return statement
        Predicate<String> predicate = a -> a.toLowerCase() == "true";

        // use method reference
        Consumer<Object> objectConsumer = System.out::println;
   }

   // TODO
    // open points use of lambda
    // Iimplementation of Predicate
    // Implementation of Function
    // Use of method reference
}
