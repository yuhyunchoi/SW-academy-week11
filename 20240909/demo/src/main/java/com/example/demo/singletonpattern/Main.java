package com.example.demo.singletonpattern;

public class Main {

    public static void main(String[] args) {
        SingleObject object1 = SingleObject.getInstance();

        object1.showMessage();
        object1.showHashCode();

        SingleObject  object2 = SingleObject.getInstance();

        object2.showMessage();
        object2.showHashCode();
    }
}
