package com.demo.javademo.io.objectSerializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectReadWriteTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[4];

        staff[0] = new Employee("employee1", 10000, 1987, 1, 1);
        staff[1] = new Employee("employee2", 20000, 1989, 2, 2);
        staff[2] = new Employee("employee3", 30000, 1990, 3, 3);
        staff[3] = new Employee("employee4", 40000, 1990, 4, 4);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.dat"));
            oos.writeObject(staff);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.dat"));
            Employee[] employees = (Employee[]) ois.readObject();
            for (Employee employee : employees) {
                System.out.println(employee);
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
