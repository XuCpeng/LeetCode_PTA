package cn.medemede.leecode.demos;

public class ClassReflection {
    /**
     * 通过Class.newInstance()方式创建类对象的对象实例，
     * 本质是执行了类对象的默认的空参的构造函数，
     * 如果类对象含有非空的构造函数，并且没有显式的声明空参的构造函数，
     * 则会抛出java.lang.NoSuchMethodException异常。
     */
    private void newClassBynewInstance() throws InstantiationException, IllegalAccessException {
        Person person = Person.class.newInstance();
        person.setAge(24);
        person.setName("XCP");
        System.out.println(person);
    }

    public static void main(String[] args) {
        ClassReflection c = new ClassReflection();
        try {
            c.newClassBynewInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

class Person {
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}