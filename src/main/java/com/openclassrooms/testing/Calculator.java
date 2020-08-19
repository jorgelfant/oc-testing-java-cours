package com.openclassrooms.testing;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public void longCalculation() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> digitsSet(int number) {
        Set<Integer> integers = new HashSet<Integer>();
        String numberString = String.valueOf(number);

        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) != '-') {// ne prendrait pas le negatif du coup -150  prendrait 150
                integers.add(Integer.parseInt(numberString, i, i + 1, 10)); // este methodo es mas amplio en jdk 11 (no funciona con 1.8)
            }
        }
        return integers;
    }
}
