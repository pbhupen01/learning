package javaLang.eight.stream.operation.terminal;

import java.util.stream.IntStream;

/**
 * Reduce:
 * It combines all the elements in stream to a single result
 *
 * There are three flavors of Reduce:
 *  1. Accepts one parameters - BinaryOperator Accumulator function
 *  2. Accepts two parameters - Identity value and BinaryOperator Accumulator function
 *  3. Accepts three parameters - Identity value, BiFunction Accumulator function and BinaryOperator Combiner function
 */
public class StreamReduceTerminalOperation {

    public static void main(String args[])
    {
        IntStream.range(1, 4).reduce((x,y) -> x + y).ifPresent(System.out::println);
        System.out.println(IntStream.range(1, 4).reduce(10,(x,y) -> x + y));
        IntStream.range(1, 4).reduce((x,y) -> (x < y)?x:y).ifPresent(System.out::println);
    }
}
