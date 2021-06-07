package cn.medemede.leecode.demos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassReflection {
    /**
     * 通过Class.newInstance()方式创建类对象的对象实例，
     * 本质是执行了类对象的默认的空参的构造函数，
     * 如果类对象含有非空的构造函数，并且没有显式的声明空参的构造函数，
     * 则会抛出java.lang.NoSuchMethodException异常。
     */
    private void newClassBynewInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {

        Class<?> clz = Class.forName("cn.medemede.leecode.demos.Person");
        Method setAge = clz.getMethod("setAge", int.class);
        Method serName = clz.getMethod("setName", String.class);

        Object person = clz
                //getConstructor()可以使用特定的构造方法，直接使用newInstance只能使用无参构造方法
                .getConstructor(String.class, int.class)
                .newInstance("Class.forName", 24);
        System.out.println(person);


        Person person1 = Person.class.newInstance();
        setAge.invoke(person1, 25);
        serName.invoke(person1, "Person.class");
        System.out.println(person1);

        Person person2 = person1.getClass().newInstance();
        setAge.invoke(person2, 26);
        serName.invoke(person2, "instance.getClass");
        System.out.println(person2);

        //获取类的public属性
        Field[] fields = clz.getFields();
        System.out.println(Arrays.toString(Arrays.stream(fields).map(Field::getName).toArray()));

        //获取类的所有声明的属性（包括私有属性）
        fields = clz.getDeclaredFields();
        System.out.println(Arrays.toString(Arrays.stream(fields).map(Field::getName).toArray()));

        //获取类的public方法，包括继承的public方法
        Method[] methods = clz.getMethods();
        System.out.println(Arrays.toString(Arrays.stream(methods).map(Method::getName).toArray()));


        //获取类所有声明的方法（包括私有方法），但不包括继承的方法
        methods = clz.getDeclaredMethods();
        System.out.println(Arrays.toString(Arrays.stream(methods).map(Method::getName).toArray()));
    }

    public static void main(String[] args) {
        ClassReflection c = new ClassReflection();
        try {
            c.newClassBynewInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

class People {
    private long id;

    private void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }
}

class Person extends People {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void increaseAge() {
        this.age++;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}