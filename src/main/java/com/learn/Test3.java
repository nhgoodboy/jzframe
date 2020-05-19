package com.learn;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2020/3/8 17:34
 **/
public class Test3 extends Test4 implements Test1, Test2 {


    @Override
    public void test11() {
        System.out.println("hello world");
    }

    @Override
    public void test22() {
        System.out.println("good bye");
    }

    public static void main(String[] args) {
        Test3 test34 = new Test3();
        test34.test11();
        test34.test22();
        test34.test4m(5, 6);
        test34.test4d();
        System.out.println(test34);

        int a[] = {1,2,3,4,5};
        try {
            System.out.println(a[6]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }

        System.out.println("bye bye");
    }

}

class Test4 {
    private int a = 3;
    private String b = "";
    private char c = 4;

    final void test4m(int a, int b) {
        System.out.println(a + b);
    }

    void test4d() {
        System.out.println(a + c);
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }


}

