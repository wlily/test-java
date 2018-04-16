package com.wll.test.java.designpattern.create.factory;

import com.wll.test.java.designpattern.create.factory.factorymethod.ChicagoStylePizzaStore;
import com.wll.test.java.designpattern.create.factory.factorymethod.NYPizzaStore;
import com.wll.test.java.designpattern.create.factory.factorymethod.product.Pizza;
import com.wll.test.java.designpattern.create.factory.simplefactory.PizzaStore;
import com.wll.test.java.designpattern.create.factory.simplefactory.SimplePizzaFactory;

public class TestFactory {

    public static void main(String[] args) {
        //简单工厂
        new PizzaStore(new SimplePizzaFactory()).orderPizza("cheese");

        // 工厂方法
        Pizza pizza = new NYPizzaStore().orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = new ChicagoStylePizzaStore().orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");

        //抽象工厂
        new NYPizzaStore().orderPizza("cheese");
    }
}
