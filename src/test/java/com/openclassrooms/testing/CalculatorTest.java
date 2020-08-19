package com.openclassrooms.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

class CalculatorTest {
    //variables de l'instance en cours du calculateur et une variable de temps du début des tests
    private Calculator calculatorUnderTest;
    private static Instant startedAt;

    // =================================================================================================================
    // objectif 1: initialiser une instance du calculateur avant chaque test, créons une méthode pour initialiser
    // le calculateur (et ajouter un message à la console)
    // =================================================================================================================
    @BeforeEach // executer cette méthode avant chaque test
    public void initCalculator() {
        System.out.println("Appel avant chaque test");
        calculatorUnderTest = new Calculator();
    }
    // Il faut à présent supprimer les initialisations de Calculator dans les méthodes de tests
    // pour utiliser l'instance calculatorUnderTest

    @Test
    public void testAddTwoPositiveNumbers() {
        // Arrange
        int a = 2;
        int b = 3;

        // Act
        int somme = calculatorUnderTest.add(a, b);

        // Assert
        assertEquals(5, somme);
    }

    @Test
    public void multiply_shouldReturnTheProduct_ofTwoIntegers() {
        // Arrange
        int a = 42;
        int b = 11;

        // Act
        int produit = calculatorUnderTest.multiply(a, b);

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
        calculatorUnderTest = null;
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

    // JUnit 5 vous simplifie la vie ! Grâce à l'annotation @ParameterizedTest, à la place de @Test.
    // Voyons cela de plus près.

    // Nous allons fournir les différents entrants possibles avec l'annotation @ValueSource. Cette annotation accepte
    // tous les types primitifs Java standard comme les valeurs ints, longs, strings, etc.

    // Ensuite, la méthode de test elle-même est dotée d'un argument. Cela donne le résultat ci-dessous.

    @ParameterizedTest(name = "{0} X 0 doit être égal à 0")
    @ValueSource(ints = {1, 2, 42, 1011, 5089})
    public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
        // Arrange -- tout est prêt!

        // Act -- multiplayer par zéro
        int actualResult = calculatorUnderTest.multiply(arg, 0);

        // Assert -- ça vaut toujours zéro !
        assertEquals(0, actualResult);
    }

    // Sympathique, non ? Et avez-vous remarqué que l'annotation @ParametrizedTest accepte un paramètre pour formater
    // le nom du test en fonction du paramètre ?

    // Mais, à quoi cela sert ?
    //Eh bien, cela améliore l'affichage du résultat dans JUnit. Sans ce paramètre, voici ce que cela donne :

    //           Image bien rangée Résultat d'un @ParameterizedTest avec paramètre de formatage

    // Bon, une liste d'entrants, c'est pas mal, mais vous aimeriez peut-être que votre test ait plusieurs paramètres ?
    // Par exemple, pour tester l'addition, fournir une liste d'éléments contenant chacun deux nombres entrants et la
    // somme attendue de ces deux nombres ?

    // JUnit 5 a une annotation pour cela. Vous pouvez utiliser @CsvSourse à la place de @ValueSource.
    // Voici un exemple d'utilisation :

    @ParameterizedTest(name = "{0} + {1} should be equal to {2}")
    @CsvSource({"1,1,2", "2,3,5", "42,57,99"})
    public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
        // Arrange -- Tout est prêt !

        // Act
        int actualResult = calculatorUnderTest.add(arg1, arg2);

        // Assert
        assertEquals(expectResult, actualResult);
    }

    // La liste d'entrants/sortants est formatée sous forme de chaînes de caractères, et chaque chaîne possède un jeu
    // de paramètres, séparé par des virgules. Dans l'exemple ci-dessus, les triplets de valeur :

    // * 1, 1 et 2
    // * 2, 3 et 5
    // * 42, 57 et 99

    // représentent chacun un jeu de paramètres. Et comme vous pouvez le voir, chacun de ces jeux de paramètres est
    // utilisable pour la méthode de test, mais aussi pour le formatage du nom du test affiché dans les résultats JUnit

    //                        Résultat d'un test paramétré avec jeux de paramètres

    //==================================================================================================================
    //                               Testez la vitesse de vos traitements
    //==================================================================================================================

    // Certaines fonctionnalités peuvent prendre du temps à être traitées. Si vous souhaitez vérifier que ce délai
    // ne soit pas trop long, vous pouvez décider de faire échouer le test à partir d'un délai que vous estimez
    // trop long.

    // L'annotation @Timeout est fait pour ça. Elle prend en argument le délai à partir duquel vous souhaitez faire
    // échouer le test (en secondes par défaut) :

    @Timeout(1) //je dis que si ca dépasse une seconde alors c'est pas bon
    @Test
    public void longCalcul_shouldComputeInLessThan1Second() {

        //Arrange

        // Act
        calculatorUnderTest.longCalculation();// par default cette méthode Thread.sleep(2000) va durer 2 secs (ça dépasse)

        // Assert
        // ...

    }

    // Dans ce cas, votre test échouera au bout d'une seconde. Si vous remplacez la valeur 2 000 par 500
    // (en ms, c'est-à-dire une demi-seconde) dans longCalculation, votre test passera en succès au bout d'une
    // demi-seconde !

    // N'abusez pas de l'annotation @Timeout. En effet, dans un travail d'équipe, tout le monde peut exécuter
    // les tests, et vous ne maîtrisez pas la puissance de la machine qui va exécuter les tests. Vous risqueriez
    // d'obtenir des faux positifs à cause d'un ordinateur lent ou d'un serveur surchargé.
}
