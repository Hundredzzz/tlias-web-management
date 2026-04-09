package com.hundredz;

public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Main Message");

        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

        threadLocal.remove();

        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
    }
}
