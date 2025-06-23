package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Represents an Employee with name, age, salary, and occupation level.
 * Implements Comparable to enable sorting.
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private double salary;
    private String occupationLevel;

    public Employee(String name, int age, double salary, String occupationLevel) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.occupationLevel = occupationLevel;
    }

    @Override
    public int compareTo(Employee other) {
        if (this.salary != other.salary) {
            return Double.compare(other.salary, this.salary); // Descending salary
        } else if (this.age != other.age) {
            return Integer.compare(this.age, other.age); // Ascending age
        } else {
            return this.name.compareTo(other.name); // Alphabetical
        }
    }

    public String toCSV() {
        return String.format("%s,%d,%.2f,%s", name, age, salary, occupationLevel);
    }

    public static void main(String[] args) throws IOException {
        List<Employee> employees = List.of(
            new Employee("Henry Ford", 30, 73500.00, "Engineer Mid-Level"),
            new Employee("James Lee", 24, 58700.40, "Engineer Entry"),
            new Employee("Nicola Tesla", 24, 62370.50, "Engineer Entry"),
            new Employee("Tiberius Kirk", 49, 169270.20, "Manager Senior"),
            new Employee("Samir Khan", 37, 141120.30, "Engineer Senior"),
            new Employee("Martha Nguyen", 34, 138207.70, "Manager Mid-Level")
        );

        List<Employee> sorted = new ArrayList<>(employees);
        Collections.sort(sorted);

        FileWriter writer = new FileWriter("data/employees.csv");
        writer.write("Name,Age,Salary,Occupation Level\n");
        for (Employee e : sorted) {
            writer.write(e.toCSV() + "\n");
        }
        writer.close();

        System.out.println("✅ Sorted employee data written to data/employees.csv");
    }
}