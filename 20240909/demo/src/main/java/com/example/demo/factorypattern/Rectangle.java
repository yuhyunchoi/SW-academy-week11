package com.example.demo.factorypattern;

public class Rectangle implements Shape{
    @Override
    public void draw(){
        System.out.println("Inside Rectangle::draw() method");
    }
}
