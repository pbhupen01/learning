package javaLang.eight.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MigrationToStream {

    static public class Employee {

        private String name;
        private int age;
        private int salary;

        public Employee(String name, int age, int salary)
        {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public void printEmployee()
        {
            System.out.println(String.format("Employee name: %s Age: %d Salary: %d",name, age, salary));
        }

    }

    static interface CheckEmployee
    {
        boolean check(Employee employee);
    }

    static class CheckEmployeeWithNameStartingWithR implements CheckEmployee
    {

        @Override
        public boolean check(Employee employee) {
            return employee.name.startsWith("R");
        }
    }


    public static void main(String args[])
    {
        List<Employee> employees = createEmployeeList();

        System.out.println("\nempFilterWithSingleSearchParameter");
        empFilterWithSingleSearchParameter(employees, 35);

        System.out.println("\nempFilterWithTwoSearchParameters");
        empFilterWithTwoSearchParameters(employees, 31, 34);

        System.out.println("\nempFilterWithCondition using Search Condition class implementation");
        empFilterWithCondition(employees, new CheckEmployeeWithNameStartingWithR());

        System.out.println("\nempFilterWithCondition using search condition anonymous class");
        /**
         *  Instead of creating a class for CheckEmployee. Let us use anonymous class.
         */
        empFilterWithCondition(employees, new CheckEmployee() {
            @Override
            public boolean check(Employee employee) {
                return employee.salary > 200000;
            }
        });

        System.out.println("\nempFilterWithCondition using lambda");
        /**
         * To much code to write with anonymous class.
         * Let us use lambda
         *
         * Note that parameter and return type match with checkEmployee interface
         * parameter is employee. Which is the parameter of check method in CheckEmployee interface
         * return time is boolean which is return type of check method in CheckEmployee interface
         */
        empFilterWithCondition(employees, e -> e.salary > 150000 && e.age < 34);

        System.out.println("\nempFilterWithPredicate using lambda");
        empFilterWithPredicate(employees, e -> e.age < 33);

        System.out.println("\nempFilterWithPredicateAndDefinedAction using lambda with defined action");
        empFilterWithPredicateAndDefinedAction(employees, e -> e.age > 32, e -> System.out.println(e.getName()));

        System.out.println("\nempFilterWithPredicateMapperAndDefinedAction using lambda with mapper and defined action");
        empFilterWithPredicateMapperAndDefinedAction(employees, e-> e.age > 34, e -> e.name, System.out::println);

        System.out.println("\nprocessElements using lambda with mapper and defined action");
        processElements(employees, e-> e.name.startsWith("R"), e-> e.age, a -> System.out.println("Age: " + a));

        /**
         * procesElements method performs following actions:
         *
         * 1. Obtain source of objects from collection source
         * 2. Apply the specified filter by Predicate function to the source
         * 3. Map the filtered objects to other object type as defined in mapper Function
         * 4. Perform defined function, in Consumer, on each mapped object
         *
         * Java provides aggregate function for that. Stream aggregate functions
         */
        System.out.println("\nUsing Stream aggregate functions");
        employees.stream()
                .filter(e -> e.salary > 110000)
                .map(e -> e.getName())
                .forEach(n -> System.out.println("Name: " + n));
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


    /**
     * Simple implementation of iterating over a collection and filtering on some condition.
     *
     * @param employees
     * @param age
     */
    private static void empFilterWithSingleSearchParameter(List<Employee> employees, int age)
    {
        for(Employee employee: employees)
        {
            if(employee.age > age)
            {
                employee.printEmployee();
            }
        }
    }

    /**
     * Change search condition.
     * Add two parameters for searching.
     *
     * @param employees
     * @param low
     * @param high
     */
    private static void empFilterWithTwoSearchParameters(List<Employee> employees, int low, int high)
    {
        for(Employee employee: employees)
        {
            if(employee.age > low && employee.age < high)
            {
                employee.printEmployee();
            }
        }
    }

    /**
     * As search condition is changing.
     * Let's move it to a different class.
     *
     * @param employees
     * @param condition
     */
    private static void empFilterWithCondition(List<Employee> employees, CheckEmployee condition)
    {
        System.out.println("\nsimpleMethodWithCondition");
        for(Employee employee: employees)
        {
            if(condition.check(employee))
            {
                employee.printEmployee();
            }
        }
    }


    /**
     * Using Predicate. Which takes Object paramter and returns boolean
     *
     * @param employees
     * @param condition
     */
    private static void empFilterWithPredicate(List<Employee> employees, Predicate<Employee> condition)
    {
        for(Employee employee: employees)
        {
            if(condition.test(employee))
            {
                employee.printEmployee();
            }
        }
    }

    /**
     * In previous filter method action was hardcoded.
     * Let us make it flexible by allowing user to pass the action as well.
     *
     * @param employees
     * @param condition
     * @param action
     */
    private static void empFilterWithPredicateAndDefinedAction(List<Employee> employees, Predicate<Employee> condition, Consumer<Employee> action)
    {
        for(Employee employee: employees)
        {
            if(condition.test(employee))
            {
                action.accept(employee);
            }
        }
    }

    /**
     * Let's add a mapper which transforms input object to some other object
     *
     * @param employees
     * @param condition
     * @param mapper
     * @param action
     */
    private static void empFilterWithPredicateMapperAndDefinedAction(List<Employee> employees, Predicate<Employee> condition,
                                                                        Function<Employee, String> mapper, Consumer<String> action)
    {
        for(Employee employee: employees)
        {
            if(condition.test(employee))
            {
                String mappedString = mapper.apply(employee);
                action.accept(mappedString);
            }
        }
    }

    /**
     * Using Generics to make this method applicable for all types of objects
     *
     * @param source
     * @param condition
     * @param action
     */
    private static <X, Y> void  processElements(Iterable<X> source, Predicate<X> condition,  Function<X, Y> mapper, Consumer<Y> action)
    {
        for(X o: source)
        {
            if(condition.test(o))
            {
                Y mappedObject = mapper.apply(o);
                action.accept(mappedObject);
            }
        }
    }

}
