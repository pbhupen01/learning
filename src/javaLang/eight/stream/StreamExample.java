package javaLang.eight.stream;

import javaLang.eight.stream.model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;
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

    public static void main(String arge[]) throws Exception
    {
        List<Employee> employees = createEmployeeList();
        updateListUsingStream(employees);
        streamSimpleExample();
        waysOfCreatingStream();
        streamProcessingOrderVertical();
        streamSupplierExample();
        streamForeachOrderedExample();
    }

    private static List<Employee> createEmployeeList()
    {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("Ravi", 32, 200000));
        employees.add(new Employee("Mandar", 42, 150000));
        employees.add(new Employee("Agam", 35, 100000));
        employees.add(new Employee("Ram", 37, 220000));

        return employees;
    }

    private static void updateListUsingStream(List<Employee> employees)
    {
        AtomicInteger id = new AtomicInteger(10);
        employees.stream().peek(e-> e.setId(id.incrementAndGet())).forEach(System.out::println);
    }

    private static void streamSimpleExample()
    {
        System.out.println("\nSimple stream with filter");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("b"))
                .forEach(s -> System.out.print(s));
    }

    private static void waysOfCreatingStream() throws IOException {
        /**
         * Creating from array
         */
        System.out.println("\nCreated using Arrays.stream(array)");
        int array[] = new int[]{1,2,3,6};
        Arrays.stream(array).forEach(System.out::print);

        /**
         * Using Stream.of
         */
        System.out.println("\nCreated using Stream.of(varargs)");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("b"))
                .forEach(s -> System.out.print(s));

        /**
         * Using IntStream.range
         */

        System.out.println("\nCreated using IntStream.range()");
        IntStream.range(0,10).forEach(System.out::print);


        /**
         * From collection
         */
        System.out.println("\nCreated using Collection.stream(collection)");
        List<Employee> employees = createEmployeeList();
        employees.stream().forEach(e -> System.out.println(e.getName()));

        /**
         * From Files.lines
         */
        System.out.println("\nCreated using Files.lines");
        String fileName = "resources/stream_input.txt";
        Files.lines(Paths.get(fileName)).forEach(System.out::println);

        /**
         * From BufferredReader.lines
         */
        System.out.println("\nCreated using BufferredReader.lines");
        Files.newBufferedReader(Paths.get(fileName)).lines().forEach(System.out::println);

    }

    private static void streamProcessingOrderVertical()
    {
        System.out.println("\nOutput to demonstrate stream vertical processing");
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
                    //System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }


    /**
     * Create a new stream chain for every terminal operation we want to execute,
     * e.g. we could create a stream supplier to construct a new stream with all intermediate operations already set up
     */
    private static void streamSupplierExample()
    {
        System.out.println("\nStream supplier example");
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        System.out.println(streamSupplier.get().anyMatch(s -> true));   // ok
        System.out.println(streamSupplier.get().noneMatch(s -> true));  // ok

    }

    /**
     * The behavior of foreach operation is explicitly nondeterministic.
     * For parallel stream pipelines, this operation does not guarantee to respect the encounter order of the stream, as doing so would sacrifice the benefit of parallelism.
     *
     * But foreachOrdered will always guarantee to produce output in the same order as in the stream.
     */
    private static void streamForeachOrderedExample(){
        System.out.println("\nStream foreachOrdered example");
        /**
         * May not always produce result in the order given in the stream
         */
        Stream.of("AAA","BBB","CCC").parallel().forEach(s->System.out.println("Output:"+s));

        /**
         * Will always produce result in the order given in the stream
         */
        Stream.of("AAA","BBB","CCC").parallel().forEachOrdered(s->System.out.println("Output:"+s));

    }
}
