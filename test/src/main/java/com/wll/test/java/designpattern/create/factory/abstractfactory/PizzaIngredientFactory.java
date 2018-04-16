package com.wll.test.java.designpattern.create.factory.abstractfactory;

import com.wll.test.java.designpattern.create.factory.abstractfactory.ingredients.*;

public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClam();
}