package org.musca.kata09shoppingcart;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SmokeTest {
    
    private static final List<Rule> RULES = getRules(); 

    private int price(String products) {
        final CheckOut checkOut = new CheckOut(RULES);

        products.chars().forEach((product) -> checkOut.scan((char) product));

        return checkOut.getTotal();
    }

    @Test
    public void testTesting() {
        assertThat("This String").isNotEmpty();
    }

    @Test
    public void testTotal() {
        assertThat(price("")).isEqualTo(0);
        assertThat(price("A")).isEqualTo(50);
        assertThat(price("AB")).isEqualTo(80);
        assertThat(price("CDBA")).isEqualTo(115);

        assertThat(price("AA")).isEqualTo(100);
        assertThat(price("AAA")).isEqualTo(130);
        assertThat(price("AAAA")).isEqualTo(180);
        assertThat(price("AAAAA")).isEqualTo(230);
        assertThat(price("AAAAAA")).isEqualTo(260);

        assertThat(price("AAAB")).isEqualTo(160);
        assertThat(price("AAABB")).isEqualTo(175);
        assertThat(price("AAABBD")).isEqualTo(190);
        assertThat(price("DABABA")).isEqualTo(190);
    }

    @Test
    public void testIncrementalTotal() {
        final CheckOut checkOut = new CheckOut(RULES);
        assertThat(checkOut.getTotal()).isEqualTo(0);
        checkOut.scan('A');
        assertThat(checkOut.getTotal()).isEqualTo(50);
        checkOut.scan('B');
        assertThat(checkOut.getTotal()).isEqualTo(80);
        checkOut.scan('A');
        assertThat(checkOut.getTotal()).isEqualTo(130);
        checkOut.scan('A');
        assertThat(checkOut.getTotal()).isEqualTo(160);
        checkOut.scan('B');
        assertThat(checkOut.getTotal()).isEqualTo(175);
    }

    public static List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();

        rules.add(new Rule(getList('A'), 50));
        rules.add(new Rule(getList('B'), 30));
        rules.add(new Rule(getList('C'), 20));
        rules.add(new Rule(getList('D'), 15));
        rules.add(new Rule(getList('A', 'A', 'A'), 130));
        rules.add(new Rule(getList('B', 'B'), 45));

        return rules;
    }

    private static List<Character> getList(Character... characters) {
        List<Character> characterList = new ArrayList<>();
        Collections.addAll(characterList, characters);

        return characterList;
    }
}