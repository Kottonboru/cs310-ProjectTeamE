package edu.jsu.mcis.cs310.tas_fa21;

import java.math.BigDecimal;

public class TAS {
    public static void main(String[] args) {
        BigDecimal d = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println(d);
    }
}