package com.musikouyi.jzframe;

import org.junit.Test;

import java.math.BigDecimal;

public class ReviewTest {

    private Integer total = 0;

    @Test
    public void test1() {
        paramCheck(total);
        System.out.println(total);
    }

    private void paramCheck(Integer total) {
        total += 1;
    }

    @Test
    public void test2() {
        BigDecimal c = new BigDecimal("7000.5");
        BigDecimal d = new BigDecimal("5000.4");
        System.out.println(c.subtract(d));
        Float a = 7000.5f;
        Float b = 5000.4f;
        System.out.println(a - b);
    }

}
