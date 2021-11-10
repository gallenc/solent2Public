package org.solent.com504.factoryandfacade.model;

public class Dog implements Animal {
    private String name = "";
    
    @Override
    public String getSound() {
        return "Woof";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
