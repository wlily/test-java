package com.wll.test.java.basic;

public class TestString {

    public static void main(String[] args) {
        String hello = "Hello", lo = "lo";
        System.out.print((hello == "Hello") + " ");
        System.out.print((Test.hello == hello) + " ");
        System.out.print((hello == ("Hel"+"lo")) + " ");
        System.out.print((hello == ("Hel"+lo)) + " ");
        System.out.println(hello == ("Hel"+lo).intern());

        System.out.println(("Hell" + lo).intern() == ("Hell" + lo).intern());

        String a = "abc";
        String b = "abc";
        String c = new String("abc");
        System.out.println(a);
        System.out.println(b);
        System.out.println(a == c);
    }
}
class Test { static String hello = "Hello"; }