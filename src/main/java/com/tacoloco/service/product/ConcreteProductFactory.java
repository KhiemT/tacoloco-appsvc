package com.tacoloco.service.product;

import com.tacoloco.exception.unchecked.ProductInstanceNotFoundException;

import java.util.UUID;

/**
 * Product Factory Pattern where allows to create a new product instance.
 */
public class ConcreteProductFactory extends ProductFactory {

    @Override
    public Product createProduct(ProductInstance instance) {
        if (ProductInstance.VEGGIE.equals(instance)) {

            return new Veggie(UUID.randomUUID().toString());

        } else if (ProductInstance.CHICKEN.equals(instance)) {

            return new Chicken(UUID.randomUUID().toString());

        } else if (ProductInstance.CHORIZO.equals(instance)) {

            return new Chorizo(UUID.randomUUID().toString());

        } else if (ProductInstance.BEEF.equals(instance)) {

            return new Beef(UUID.randomUUID().toString());

        } else {
            throw new ProductInstanceNotFoundException();
        }

    }
}
