package com.openclassrooms.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

class CalculatorTest {
    //variables de l'instance en cours du calculateur et une variable de temps du début des tests
    private Calculator calculatorunderTest;
    private static Instant startedAt;

    // =================================================================================================================
    // objectif 1: initialiser une instance du calculateur avant chaque test, créons une méthode pour initialiser
    // le calculateur (et ajouter un message à la console)
    // =================================================================================================================
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

    // =================================================================================================================
    // objectif 2 : après chaque test, mettre la valeur du calculateur à null), nous allons utiliser
    // l'annotation @AfterEach
    // Mettre la variable calculatorUnderTest à null n'a pas de réelle utilité ici. C'est juste pour montrer
    // l'utilisation de cette annotation.
    // =================================================================================================================
    @AfterEach
    public void undefCalculator() {
        System.out.println("Appel après chaque test");
        calculatorunderTest = null;
    }
    // =================================================================================================================
    // Enfin, pour l'objectif 3 (mesurer le temps de traitement de l'ensemble des tests de CalculatorTest),
    // nous allons créer des méthodes statiques annotées avec @BeforeAll et @AfterAll :
    // =================================================================================================================

    @BeforeAll
    static public void initStartingTime() {
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    static public void showTestDuration() {
        System.out.println("Appel après tous les tests");
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    // Grâce à ces quatre annotations, ce qui est intéressant, c'est que JUnit ne vous impose pas d'effectuer ces
    // actions dans des méthodes avec un nommage spécifique comme  setUp  ou  tearDown()  (c'était le cas avec les
    // anciennes versions de JUnit). Donc, profitez-en, soyez descriptif dans vos noms de méthodes !

    // Pourquoi ces méthodes et la variables startedAt sont-elles statiques ?

    // C'est un héritage des anciennes versions de JUnit. Les méthodes appelées avant ou après tous les tests sont
    // donc statiques car considérées comme liées à l'objet de la classe de test, et non à une instance particulière.

    // En fait, ces méthodes pourraient très bien être déclarées comme étant non statiques, mais vous devrez ajouter
    // l'annotation @TestInstance(Lifecycle.PER_CLASS) à la classe de tests.

    // Pour ce cours, je code avec JUnit 5. Si vous êtes amené à coder sur des projets existants avec JUnit 4,
    // les noms des annotations diffèrent légèrement.

    // =================================================================================================================
    //              Jouez avec les entrants et les sortants grâce aux tests paramétrés
    // =================================================================================================================

    // Vous avez déjà vu l’annotation @Test. Imaginez que vous souhaitiez effectuer le même traitement de tests
    // (l'étape Act), sur des entrants différents (de l'étape Arrange), afin de vérifier différents cas de figure.

    // Pour rappel :
    //  * étape Arrange : je paramètre les entrants des tests ;
    //  * étape Act : j'effectue l'action sur la classe à tester ;
    //  * étape Assert : je vérifie les résultats (sortants) de l'action.

}
