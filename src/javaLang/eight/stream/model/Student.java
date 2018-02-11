package javaLang.eight.stream.model;

import java.util.HashSet;
import java.util.Set;

public class Student {

    private String name;
    private Set<String> books = new HashSet<>();

    public Student(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getBooks() {
        return books;
    }

    public void setBooks(Set<String> books) {
        this.books = books;
    }

    public void addBook(String bookName)
    {
        books.add(bookName);
    }
}
