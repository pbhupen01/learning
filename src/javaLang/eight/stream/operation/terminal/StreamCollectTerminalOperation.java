package javaLang.eight.stream.operation.terminal;

import javaLang.eight.stream.model.Employee;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Collect method:
 * It is stream terminal operation to
 *      transform any stream into a different kind of result- List, Set, Map
 *      aggregate the elements of streams
 *
 * Collector:
 * Collect operations accepts a collector which is consists of four different operations:
 *  Supplier
 *  Accumulator
 *  Combiner
 *  Finsher
 *
 */
public class StreamCollectTerminalOperation {

    public static void main(String args[])
    {
        List<Employee> employees = createEmployees();
        collectToList(employees);
        collectToMap(employees);
        collectToSet(employees);
        groupBySimpleExample(employees);
        groupByAveragingExample(employees);
        groupBySummingExample(employees);
        groupByMaxExample(employees);
    }

    private static List<Employee> createEmployees()
    {
        Random r = new Random();
        return IntStream.range(0,20)
                .mapToObj(i -> "Employee"+i)
                .map(n-> new Employee(n, r.nextInt(27) + 24, r.nextInt(100001) + 100000))
                .collect(Collectors.toList());
    }

    private static void collectToList(List<Employee> employees){
        List<Employee> filteredEmployees = employees.stream()
                .filter(e->e.getAge()>40)
                .collect(Collectors.toList());
        filteredEmployees.stream().forEach(System.out::println);
    }

    private static void collectToMap(List<Employee> employees)
    {
        Map<Integer, String> map = employees.stream()
                .collect(Collectors
                        .toMap(e -> e.getAge(), e -> e.getName(), (n1,n2) -> n1+";"+n2));
        System.out.println(map);

    }

    private static void collectToSet(List<Employee> employees)
    {
        Set<String> filteredEmployees = employees.stream()
                .filter(e->e.getAge()>40)
                .map(e-> e.getName())
                .collect(Collectors.toSet());
        filteredEmployees.stream().forEach(System.out::println);

    }

    private static void groupBySimpleExample(List<Employee> employees) {
        Map<Integer, List<Employee>> map =employees.stream()
                .collect(Collectors.groupingBy(e-> e.getAge()));
        System.out.println(map);
    }

    private static void groupByAveragingExample(List<Employee> employees) {
       Map<Integer,Double> map = employees.stream()
                .collect(Collectors.groupingBy(e-> e.getAge(), Collectors.averagingDouble(e->e.getSalary())));
        System.out.println(map);
    }

    private static void groupBySummingExample(List<Employee> employees) {
        Map<Integer,Double> map = employees.stream()
                .collect(Collectors.groupingBy(e-> e.getAge(), Collectors.summingDouble(e->e.getSalary())));
        System.out.println(map);
    }

    private static void groupByMaxExample(List<Employee> employees) {
        Map<Integer, Optional<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(e-> e.getAge(), Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
        System.out.println(map);

    }

    private static void aggregationUsingCollector()
    {

    }

    private static void joiningCollector()
    {

    }

    private static void customCollector()
    {

    }
}
