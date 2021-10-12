package com.demo.javademo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter class name (e.g. java.util.Date)");
        String name = in.next();
        System.out.println("------------------------declare this class is begin......!------------------------\n");

        try {
            Class clz = Class.forName(name);
            Class superclz = clz.getClass();

            String modifier = Modifier.toString(clz.getModifiers());
            if (modifier.length() > 0)
                System.out.print(modifier + " ");
            System.out.print("class " + name);
            if (superclz != null && superclz != Object.class) {
                System.out.println(" extends " + superclz.getName() + " {");

                printConstructors(clz);
                System.out.println();

                printMethods(clz);
                System.out.println();

                printFields(clz);
                System.out.println("}");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    /**
     * prints all constructors of a class
     *
     * @param clz a class
     */
    public static void printConstructors(Class clz) {
        Constructor[] constructors = clz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(" ");
            String modifier = Modifier.toString(constructor.getModifiers());
            String constructorName = constructor.getName();
            if (modifier.length() > 0)
                System.out.print(modifier + " ");
            System.out.print(constructorName + "(");

            //print parameter types
            Class[] constructorParamTypes = constructor.getParameterTypes();
            for (int j = 0; j < constructorParamTypes.length; j++) {
                if (j > 0) System.out.print(" ,");
                System.out.print(constructorParamTypes[j].getName());
            }
            System.out.print(");");
        }
    }

    /**
     * print all methods of a class
     * param clz is a class
     */
    public static void printMethods(Class clz) {
        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(" ");
            //print modifiers ,return type,and method name
            String modifier = Modifier.toString(method.getModifiers());
            Class retType = method.getReturnType();
            String methodName = method.getName();
            if (modifier.length() > 0) {
                System.out.print(modifier + " " + retType.getName() + " " + methodName + "(");
                //print parameter types
                Class[] paramTypes = method.getParameterTypes();
                for (int j = 0; j < paramTypes.length; j++) {
                    if (j > 0) {
                        System.out.print(",");
                    }
                    System.out.print(paramTypes[j].getName());
                }
                System.out.println(");");
            }
        }
    }

    /**
     * print all fields of a class
     *
     * @param clz
     */
    public static void printFields(Class clz) {
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}
