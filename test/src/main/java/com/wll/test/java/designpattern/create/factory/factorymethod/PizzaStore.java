package com.wll.test.java.designpattern.create.factory.factorymethod;

import com.wll.test.java.designpattern.create.factory.factorymethod.product.Pizza;

/**
 * The Factory Method Pattern defines an interface
 for creating an object, but lets subclasses decide which
 class to instantiate. Factory Method lets a class defer
 instantiation to subclasses.
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstract Pizza createPizza(String type);
}
