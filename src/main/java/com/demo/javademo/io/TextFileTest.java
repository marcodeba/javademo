package com.demo.javademo.io;

import com.demo.javademo.io.objectSerializable.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[4];
        staff[0] = new Employee("employee1", 10000, 1987, 1, 1);
        staff[1] = new Employee("employee2", 20000, 1989, 2, 2);
        staff[2] = new Employee("employee3", 30000, 1990, 3, 3);
        staff[3] = new Employee("employee4", 40000, 1990, 4, 4);

        try {
            // save all employee records to the file employee.dat
            PrintWriter out = new PrintWriter("employee.txt");
            writeData(staff, out);
            out.close();

            // retrieve all records into a new array
            Scanner in = new Scanner(new FileReader("employee.txt"));
            Employee[] newStaff = readData(in);
            in.close();

            // print the newly read employee records
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Writes all employees in an array to a print writer
     *
     * @param employees an array of employees
     * @param out       a print writer
     */
    private static void writeData(Employee[] employees, PrintWriter out) {
        // write number of employees
        out.println(employees.length);
        for (Employee e : employees) {
            out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay().getYear() + "|"
                    + e.getHireDay().getMonthValue() + "|" + e.getHireDay().getDayOfMonth());
        }
    }

    /**
     * Reads an array of employees from a scanner
     *
     * @param in the scanner
     * @return the array of employees
     */
    private static Employee[] readData(Scanner in) {
        // retrieve the array size
        int n = in.nextInt();
        in.nextLine(); // consume newline

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = new Employee();
            String line = in.nextLine();
            String[] tokens = line.split("\\|");
            employees[i].setName(tokens[0]);
            employees[i].setSalary(Double.parseDouble(tokens[1]));
            employees[i].setHireDay(LocalDate.of(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
        }
        return employees;
    }
}
