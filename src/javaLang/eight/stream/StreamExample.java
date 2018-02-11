package javaLang.eight.stream;

import java.util.stream.Stream;

/**
 * Stream:
 * Stream is a sequence of elements on which one or multiple operations can be performed.
 *
 * Types of stream:
 *  Sequential
 *  Parallel
 *
 * Types of stream operations:
 * There are two types of stream operations:
 *  Intermediate
 *  Terminal
 *
 * Intermediate operations: returns a stream
 * Terminal operations: either returns void or non-stream result
 *
 * Intermediate operations type:
 *  Stateless operations e.g. filter, map
 *  Stateful operations e.g. distinct, sorted
 *
 * Stream pipeline:
 * Multiple intermediate operations and one terminal operations are chained to create Stream Pipeline
 * Pipeline consists of:
 *  Source
 *  Intermediate operations
 *  Terminal operations
 *
 * NOTE:
 * Intermediate operations are not executed until terminal operation is executed.
 * Streams can't be reused.
 */
public class StreamExample {

    public static void main(String arge[])
    {

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .forEach(System.out::println);

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }

    private static void streamSimpleExample()
    {

    }

    private static void waysOfCreatingStream()
    {

    }

    private static void streamProcessingOrderVertical()
    {

    }


    private static void streamSupplierExample()
    {

    }

    private static void streamForeachExample()
    {

    }

    private static void streamForeachOrderedExample(){

    }

}
