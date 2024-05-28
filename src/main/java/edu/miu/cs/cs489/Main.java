package edu.miu.cs.cs489;

import edu.miu.cs.cs489.model.Employee;
import edu.miu.cs.cs489.model.PensionPlan;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main {
    public static void main(String[] args) {

        Employee[] employees = new Employee[] {
                new Employee(123, "Daniel", "Agar", LocalDate.parse("2018-01-17"), 105945.50,
                        new PensionPlan("EX1089", LocalDate.parse("2023-01-17"), 100.00)),
                new Employee(456, "Benard", "Shaw", LocalDate.parse("2019-05-03"), 197750.00, new PensionPlan()),
                new Employee(789, "Carly", "Agar", LocalDate.parse("2014-05-16"), 842000.75,
                        new PensionPlan("SM2307", LocalDate.parse("2023-01-17"), 1555.50)),
                new Employee(901, "Wesley", "Schneider", LocalDate.parse("2019-05-02"), 74500.00, new PensionPlan()),
        };
        printEmployees(employees);
        printMonthlyUpcomingEnrollees(employees);
    }

    static void printEmployees(Employee[] employees) {
        System.out.println("\n--- Employees ---");
        String result = Arrays.stream(employees)
                .sorted(Comparator.comparing(Employee::lastName))
                .map(Employee::toJSON)
                .collect(Collectors.joining(",\n", "[\n", "\n]"));
        System.out.println(result);
    }

    static void printMonthlyUpcomingEnrollees(Employee[] employees) {
        System.out.println("\n--- Monthly Upcoming Enrollees ---");
        String result = Arrays.stream(employees)
                .filter(employee -> employee.pensionPlan().getPlanReferenceNumber() != null)
                .filter(employee -> {
                    LocalDate today = LocalDate.now();
                    int daysLeftInMonth = LocalDate.EPOCH.lengthOfMonth()-today.getDayOfMonth();
                    LocalDate deadline = today.plusDays(daysLeftInMonth+1);
                    long daysBetween = DAYS.between(employee.employmentDate(), deadline);
                    long inYears = daysBetween/365;
                    return inYears >= 5;
                })
                .sorted(Comparator.comparing(Employee::employmentDate))
                .map(Employee::toJSON)
                .collect(Collectors.joining(",\n", "[\n", "\n]"));
        System.out.println(result);
    }
}