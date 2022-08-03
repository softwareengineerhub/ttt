package com.app;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Main {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.compareAndSet(1, 3);
        System.out.println(atomicInteger);
        atomicInteger.getAndUpdate(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                System.out.println("operand="+operand);
                if(operand>0){
                    return operand-1;
                }
                return 0;
            }
        });
        System.out.println(atomicInteger);
    }

}
