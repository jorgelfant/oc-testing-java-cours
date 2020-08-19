package com.openclassrooms.testing;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    //méthode pour calculer le temps qui prend une fonctionnalité à être testée
    public void longCalculation() {//cette méthode peut ressemble à cela
        try {
            // Attendre 2 secondes
            Thread.sleep(500); //2000 ca ferait echouer le test
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
