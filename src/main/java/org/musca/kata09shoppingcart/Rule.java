package org.musca.kata09shoppingcart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rule {

    private final Map<Character, Integer> productMapping;
    private final int price;
    private final int numberProducts;

    public Rule(List<Character> products, int price) {
        numberProducts = products.size();
        productMapping = new HashMap<>();

        for (Character product : products) {
            if (!productMapping.containsKey(product)) {
                productMapping.put(product, 1);
            } else {
                productMapping.put(product, productMapping.get(product) + 1);
            }
        }

        this.price = price;
    }

    public boolean matches(Map<Character, Integer> products) {
        boolean matches = true;

        for (Character product : productMapping.keySet()) {
            if (!products.containsKey(product) || (products.get(product) < productMapping.get(product))) {
                matches = false;
                break;
            }
        }

        return matches;
    }

    public int removeAndGetPrice(Map<Character, Integer> products) {
        for (Character product : productMapping.keySet()) {
            int updatedValue = products.get(product) - productMapping.get(product);

            if (updatedValue > 0) {
                products.put(product, updatedValue);
            } else {
                products.remove(product);
            }
        }

        return price;
    }

    public int getNumberProducts() {
        return numberProducts;
    }
}