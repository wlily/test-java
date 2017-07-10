package com.wll.test.java.io;

import java.io.*;

/**
 * Created by wll on 17-7-10.
 */
public class TestSerialize {
    private String path = "/home/wll/1-code/study/test-java";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TestSerialize testSerialize = new TestSerialize();
        testSerialize.write();
        testSerialize.read();
    }
    static class Person implements Serializable{
        private String name;
        private int age;
        private transient int id;

        public Person(){}

        public Person (String name, int age, int id){
            this.name = name;
            this.age = age;
            this.id = id;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    public void write() throws IOException {
        File file = new File(path + File.separator + "person.txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(new Person("haha", 20, 1));
        oos.close();
    }

    public void read() throws IOException, ClassNotFoundException {
        File file = new File(path + File.separator + "person.txt");
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
        Object obj = input.readObject();
        input.close();
        System.out.println(obj);
    }
}
