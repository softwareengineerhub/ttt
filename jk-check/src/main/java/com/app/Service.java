package com.app;

import java.util.concurrent.atomic.AtomicInteger;

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
        if(currentActiveConnectionsCount.get()>0){
            currentActiveConnectionsCount.decrementAndGet();
        }
    }

}
