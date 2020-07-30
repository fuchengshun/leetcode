package com.example.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo {
    private AtomicInteger one =new AtomicInteger(0);
    private AtomicInteger two =new AtomicInteger(0);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        one.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (one.get()==0){}
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        two.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (two.get()==0){}
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
