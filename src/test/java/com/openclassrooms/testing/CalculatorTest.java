package com.openclassrooms.testing;

import static org.assertj.core.api.Assertions.assertThat;// pour assertj librairie pour les asserts plus compréhensible ques
import static org.junit.jupiter.api.Assertions.*;        // les asserts de junit

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {

    private static Instant startedAt;
    private Calculator calculatorUnderTest;

    @BeforeEach
    public void initCalculator() {
        System.out.println("Appel avant chaque test");
        calculatorUnderTest = new Calculator();
    }

    @AfterEach
    public void undefCalculator() {
        System.out.println("Appel après chaque test");
        calculatorUnderTest = null;
    }

    @BeforeAll
    public static void initStartingTime() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showTestDuration() {
        System.out.println("Appel après tous les tests");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    //Avec nouvelle version assertJ ------------------------------------------------------------------------------------
    @Test
    public void testAddTwoPositiveNumbers() {
        // Arrange
        int a = 2;
        int b = 3;

        // Act
        int somme = calculatorUnderTest.add(a, b);

        // Assert de junit
        // assertEquals(5, somme);

        // Assert de assertj (pour remplacer l'assert de junit)
        assertThat(somme).isEqualTo(5);
    }

    //Avec nouvelle version assertJ ------------------------------------------------------------------------------------
    @Test
    public void multiply_shouldReturnTheProduct_ofTwoIntegers() {
        // Arrange
        int a = 42;
        int b = 11;

        // Act
        int produit = calculatorUnderTest.multiply(a, b);

        // Assert
        // assertEquals(462, produit);
        // Assert de assertj (pour remplacer l'assert de junit)
        assertThat(produit).isEqualTo(462);

    }

    //Avec nouvelle version assertJ ------------------------------------------------------------------------------------
    @ParameterizedTest(name = "{0} x 0 doit être égal à 0")
    @ValueSource(ints = {1, 2, 42, 1011, 5089})
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
        // Arrange -- Tout est prêt !

        // Act -- Multiplier par zéro
        int actualResult = calculatorUnderTest.multiply(arg, 0);

        // Assert -- ça vaut toujours zéro !
        //assertEquals(0, actualResult);
        assertThat(actualResult).isEqualTo(0);

    }

    //Avec nouvelle version assertJ ------------------------------------------------------------------------------------
    @ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
    @CsvSource({"1,1,2", "2,3,5", "42,57,99"})
    public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
        // Arrange -- Tout est prêt !

        // Act
        int actualResult = calculatorUnderTest.add(arg1, arg2);

        // Assert
        // assertEquals(expectResult, actualResult);
        assertThat(actualResult).isEqualTo(expectResult); // otro ejemplo MIO    assertThat(actualResult).isIn(1,2,3,4,5);
    }

    //Avec nouvelle version assertJ ------------------------------------------------------------------------------------
    @Timeout(1)
    @Test
    public void longCalcul_shouldComputeInLessThan1Second() {
        // Arrange

        // Act
        calculatorUnderTest.longCalculation();

        // Assert
        // ...
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test //pour nombre positif
    public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger() {

        //GIVEN
        int number = 95897;

        //WHEN
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

        // THEN
        // Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
        // assertEquals(expectedDigits, actualDigits);
        assertThat(actualDigits).containsExactlyInAnyOrder(9, 5, 8, 7);
        // Avec asserEquals on a besoin de construire un deuxième Set<> pour pouvoir comparer les 2 listes
        // alors qu'avec assertThat on peut comparer directement les chiffres de actualDigits avec 9 , 5 , 8 , 7 (contains)
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test //Pour nombre négatif
    public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger() {

        int number = -124432;
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigits).containsExactlyInAnyOrder(1, 2, 3, 4);
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test // Pour 0
    public void listDigits_shouldReturnsTheListOfZero_ofZero() {

        int number = 0;
        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
        assertThat(actualDigits).containsExactlyInAnyOrder(0);
    }
    /*
    AssertJ ne remplace pas JUnit. Cette bibliothèque apporte simplement une panoplie très complète d'assertions.
    JUnit est toujours utilisé pour ses annotations et l'orchestration des tests.

    Après, soyons francs. Le langage, même avec AssertJ, est toujours un peu robotisé, n’est-ce pas ? Vous
    ne pouvez pas l’éviter car Java est un langage de programmation. Ce qui est important, c’est que vous ayez un
    langage fluide, qui puisse être utilisé pour décrire votre intention un peu plus naturellement.

    Dans tous les cas, ce n’est que la partie émergée de l’iceberg. Parcourez la documentation de AssertJ pour
    découvrir toute l'API que vous pouvez utiliser. Et si vous aimez le Seigneur des Anneaux, les exemples utilisés
    dans cette documentation sont faits pour vous !
    */
}
