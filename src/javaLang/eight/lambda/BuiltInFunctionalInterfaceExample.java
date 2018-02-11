package javaLang.eight.lambda;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BuiltInFunctionalInterfaceExample {

    public static void main(String args[])
    {
        builtInFunctionalInterfacePredicate();
        builtInFunctionalInterfaceFunction();
        builtInFunctionalInterfaceSupplier();
        builtInFunctionalInterfaceConsumer();
        builtInFunctionalInterfaceComparator();
    }


    /**
     * Accepts one parameter and returns boolean
     */
    private static void builtInFunctionalInterfacePredicate()
    {
        Predicate<String> predicate = (s) -> s.length() > 0;

        Predicate<String> predicateAnonymousClass = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 0;
            }
        };

        // Note that the Method reference used in below statement accepts the same parameter as test method of Predicate
        // In this method String.isEmpty accepts String and returns boolean
        Predicate<String> isEmpty = String::isEmpty;

        Predicate<String> isEmptyAnonymousClass = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };
    }


    /**
     * Accepts one parameter and produce one result
     */
    private static void builtInFunctionalInterfaceFunction()
    {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
    }


    /** Do not take any parameter and produces result
     *
     */
    private static void builtInFunctionalInterfaceSupplier()
    {
        Supplier<Object> personSupplier = Object::new;
        personSupplier.get();
    }


    /** Accepts one parameter and produces no result. Just operates on the parameter
     *
     */
    private static void builtInFunctionalInterfaceConsumer()
    {
        Consumer<Object> objectConsumer = System.out::println;
        objectConsumer.accept("This is a test");
    }


    /** Java comparator
     *
     */
    private static void builtInFunctionalInterfaceComparator()
    {
        Comparator<Integer> comparator = (i1, i2) -> i1.compareTo(i2);
    }

    /** Optionals are not functional interfaces, instead it's a nifty utility to prevent NullPointerException
     *
     */
    private static void builtInInterfaceOptional()
    {
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }
}
