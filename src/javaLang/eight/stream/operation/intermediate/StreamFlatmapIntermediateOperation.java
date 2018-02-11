package javaLang.eight.stream.operation.intermediate;

import javaLang.eight.stream.model.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Returns a new stream from stream
 */
public class StreamFlatmapIntermediateOperation {

    public static void main(String args[])
    {
        List<Student> students = createStudentListSimple();
        flatMapExample(students);

        List<Student> students1 = createStudentListUsingStream();
        flatMapDistinctExample(students1);
    }

    private static List<Student> createStudentListSimple()
    {
        Student student1 = new Student("Sachin");
        student1.addBook("Java");
        student1.addBook("Python");
        student1.addBook("Scala");
        Student student2 = new Student("Saket");
        student2.addBook("AI");
        student2.addBook("ML");
        return Stream.of(student1,student2).collect(Collectors.toList());
    }

    private static List<Student> createStudentListUsingStream()
    {
        return IntStream.range(1,4)
                .mapToObj(i -> "Student"+i )
                .map(Student::new)
                .peek(s -> IntStream.range(1,3)
                        .mapToObj(i -> "Book"+i)
                        .map(String::new)
                        .forEach(s::addBook)
                )
                .collect(Collectors.toList());
    }

    /**
     * Internally creates stream of books from students.
     *
     * @param students
     */
    private static void flatMapExample(List<Student> students)
    {
        students.stream()
                .flatMap(s -> s.getBooks().stream())
                .forEach(System.out::println);

        System.out.println("Implementation with Map");
        students.stream()
                .map(s -> s.getBooks())
                .flatMap(b -> b.stream())
                .forEach(System.out::println);
    }

    private static void flatMapDistinctExample(List<Student> students)
    {
        students.stream()
                .map(s -> s.getBooks())
                .flatMap(b -> b.stream())
                .distinct()
                .forEach(System.out::println);
    }

}
