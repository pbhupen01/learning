package javaLang.eight.stream.operation.intermediate;

public class StreamPeekIntermediateOperation {

    /**
     * Stream.of("one", "two", "three", "four")
     .filter(e -> e.length() > 3)
     .peek(e -> System.out.println("Filtered value: " + e))
     .map(String::toUpperCase)
     .peek(e -> System.out.println("Mapped value: " + e))
     .forEach(System.out::println);
     */
}
