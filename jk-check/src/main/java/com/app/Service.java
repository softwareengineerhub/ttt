package com.app;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Service {
    private int connectionLimit=100;
    private AtomicInteger currentActiveConnectionsCount = new AtomicInteger();


    public void handleSubscription(){
        if(currentActiveConnectionsCount.get() > connectionLimit){
            System.out.println("connection limit is reached!!!");
            return;
        }
        currentActiveConnectionsCount.incrementAndGet();
    }

    public void handleUnSubscription(){
        currentActiveConnectionsCount.getAndUpdate(new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return 0;
            }
        });
        if(currentActiveConnectionsCount.get()>0){
            currentActiveConnectionsCount.decrementAndGet();
        }
    }

}
