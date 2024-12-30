package org.example.sqlspeedtest;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestClass {

    @Test
    public void test()
    {
        BigDecimal a = BigDecimal.valueOf(10);
        BigDecimal b = BigDecimal.valueOf(10.00);

        System.out.println(a.compareTo(b));
        System.out.println(a.equals(b));

    }

    @Test
    public void test2()
    {
        try {
            int a = 1 / 0;

        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }
    }
    }
