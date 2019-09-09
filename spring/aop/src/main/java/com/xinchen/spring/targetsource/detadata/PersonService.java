package com.xinchen.spring.targetsource.detadata;

/**
 * @author xinchen
 * @version 1.0
 * @date 09/09/2019 17:18
 */
public class PersonService {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private void action(){
        System.out.println("[PersonService] -> action()");
    }
}
