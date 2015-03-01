package org.musca.kata09shoppingcart;

import java.util.Comparator;

public class RuleComparator implements Comparator<Rule> {

    @Override
    public int compare(Rule thisRule, Rule thatRule) {
        return Integer.compare(thisRule.getNumberProducts(), thatRule.getNumberProducts()) * -1;
    }
}