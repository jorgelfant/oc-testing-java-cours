package com.openclassrooms.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class CalculatorTest {
    //variables de l'instance en cours du calculateur et une variable de temps du début des tests
    private Calculator calculatorunderTest;
    private static Instant calculatorUnderTest;

    // objectif 1: initialiser une instance du calculateur avant chaque test, créons une méthode pour initialiser
    // le calculateur (et ajouter un message à la console)
    @BeforeEach // executer cette méthode avant chaque test
    public void initCalculator() {
        System.out.println("Appel avant chaque test");
        calculatorunderTest = new Calculator();
    }
    // Il faut à présent supprimer les initialisations de Calculator dans les méthodes de tests
    // pour utiliser l'instance calculatorUnderTest

    @Test
    public void testAddTwoPositiveNumbers() {
        // Arrange
        int a = 2;
        int b = 3;

        // Act
        int somme = calculatorunderTest.add(a, b);

        // Assert
        assertEquals(5, somme);
    }

    @Test
    public void multiply_shouldReturnTheProduct_ofTwoIntegers() {
        // Arrange
        int a = 42;
        int b = 11;

        // Act
        int produit = calculatorunderTest.multiply(a, b);

        // Assert
        assertEquals(462, produit);
    }

}
