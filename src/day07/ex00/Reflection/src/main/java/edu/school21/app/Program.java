package edu.school21.app;

import edu.school21.classes.Pony;
import edu.school21.classes.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Program {
    final static String TIRE = "-------------------";

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Set<Class> classes = findAllClassesUsingClassLoader("edu.school21.classes");
        Scanner sc = new Scanner(System.in);
        System.out.println("Classes:");
        for (Class c : classes) {
            System.out.println(c.getSimpleName());
        }
        System.out.println(TIRE);
        System.out.println("Enter class name:\n");
        String classname = sc.nextLine();
        for (Class c : classes) {
            if (classname.equals(c.getSimpleName())) {
                Object o = printInfo(c);
                System.out.println("\nLet's create an object!");
                Field[] fields = o.getClass().getDeclaredFields();
                Method[] methods = o.getClass().getDeclaredMethods();
                for (Field f : fields) {
                    System.out.println(f.getName() + ":");
                    f.setAccessible(true);
                    setField(o, f, sc);
                }
                System.out.println("Object created: " + o.toString());
                System.out.println(TIRE);
                System.out.println("Enter name of the field for changing:");
                String changedField = sc.nextLine();
                for (Field f : fields) {
                    if (f.getName().equals(changedField)) {
                        System.out.println("Enter " + f.getType() + " value");
                        setField(o, f, sc);
                    }
                }
                System.out.println("Object updated: " + o.toString());
                System.out.println(TIRE);
                System.out.println("Enter name of the method for call:");
                String callingMethod = sc.nextLine();
                for (Method m : methods) {
                    if(callingMethod.equals(getSignature(m))) {
                        Parameter[] param = m.getParameters();
                        Object [] arguments = new Object[param.length];
                        int i = 0;
                        for (Parameter p : param) {
                            System.out.println("Enter " + p.getType().getSimpleName() + " value");
                            arguments[i] = getObj(p, sc);
                            i++;
                        }
                        Object ret = m.invoke(o, arguments);
                        if (ret != null) {
                            System.out.println("method returned: " + ret);
                        }
                        System.out.println("Good bye!");
                        System.exit(0);
                    }

                }
            }
        }
        System.out.println("No.");
        System.exit(-1);
    }

    private static String getSignature(Method m) {
        String result = "";
        result = m.getName() + "(";
        Parameter[] param = m.getParameters();
        for (Parameter p : param) {
            result += p.getType().getSimpleName();
            if (!p.equals(param[param.length - 1]))
                result += ", ";
        }
        result += ")";
        return result;
    }

    private static Object getObj(Parameter p, Scanner sc) throws IllegalAccessException {
        if (p.getType().getSimpleName().equals("String")) {
            return sc.nextLine();
        } else if (p.getType().getSimpleName().equals("Integer")) {
            return sc.nextInt();
        } else if (p.getType().getSimpleName().equals("Double")) {
            return sc.nextDouble();
        } else if (p.getType().getSimpleName().equals("Long")) {
            return sc.nextLong();
        } else if (p.getType().getSimpleName().equals("Boolean")) {
            return sc.nextBoolean();
        }
        return null;
    }

    private static void setField(Object o, Field f, Scanner sc) throws IllegalAccessException {
        if (f.getType().getSimpleName().equals("String")) {
            f.set(o, sc.nextLine());
        } else if (f.getType().getSimpleName().equals("Integer")) {
            f.set(o, sc.nextInt());
        } else if (f.getType().getSimpleName().equals("Double")) {
            f.set(o, sc.nextDouble());
        } else if (f.getType().getSimpleName().equals("Long")) {
            f.set(o, sc.nextLong());
        } else if (f.getType().getSimpleName().equals("Boolean")) {
            f.set(o, sc.nextBoolean());
        }
    }

    private static Object printInfo(Class c) throws InstantiationException, IllegalAccessException {
        Object o = c.newInstance();
        Field[] fields = o.getClass().getDeclaredFields();
        Method[] methods = o.getClass().getDeclaredMethods();
        System.out.println(TIRE + "\nfields :");
        for (Field f : fields) {
            System.out.println("     " + f.getType().getSimpleName() + " " + f.getName());
        }
        System.out.println("methods:");
        for (Method m : methods) {
            if (!m.getName().equals("toString")) {
                System.out.print("     " + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
                Parameter[] param = m.getParameters();
                for (Parameter p : param) {
                    System.out.print(p.getType().getSimpleName());
                    if (!p.equals(param[param.length - 1]))
                        System.out.print(", ");
                }
                System.out.print(")\n");
            }
        }
        System.out.print(TIRE);
        return o;
    }


    private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            System.err.println("class not found");
        }
        return null;
    }
}
