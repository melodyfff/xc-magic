package com.xinchen.spring.loadtime;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/08/2019 15:36
 */
public class StubEntitlementCalculationService {
    public void calculateEntitlement(){
        try {
            System.out.println("start ...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
