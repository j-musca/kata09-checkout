package org.musca.kata09shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckOut {

    private final List<Rule> rules;
    private final Map<Character, Integer> products;

    public CheckOut(List<Rule> rules) {
        this.rules = new ArrayList<>(rules);
        this.rules.sort(new RuleComparator());
        this.products = new HashMap<>();
    }

    public void scan(char product) {
        if (!products.containsKey(product)) {
            products.put(product, 1);
        } else {
            products.put(product, products.get(product) + 1);
        }
    }

    public int getTotal() {
        int total = 0;

        final Map<Character, Integer> tmpProducts = new HashMap<>(products);

        for (Rule rule : rules) {
            while(!tmpProducts.isEmpty() && rule.matches(tmpProducts)) {
                total += rule.removeAndGetPrice(tmpProducts);
            }
        }

        return total;
    }
}