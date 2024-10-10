package com.example.demo.singletonpattern;

public class SingleObject {
    private static final SingleObject instance = new SingleObject();

    private SingleObject() {

    }

    public static SingleObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello world!");
    }

    public void showHashCode(){
        System.out.println(this.hashCode());
    }
}
