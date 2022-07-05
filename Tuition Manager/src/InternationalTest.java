/**
 * JUnit Test Class for International.
 * Checks accuracy of tuition rates
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternationalTest {


    @Test
    void tuitionDue() {
        Profile internationalProfile1 = new Profile("Joshua Patel", Major.CS);
        Student internationalStudent1 = new Student(internationalProfile1, 12);
        International international1 = new International(internationalProfile1, internationalStudent1.getCreditHours(), true);
        international1.tuitionDue();

        assertEquals(5918, international1.getTuition());

        Profile internationalProfile2 = new Profile("Sunny Lin", Major.CS);
        Student internationalStudent2 = new Student(internationalProfile2, 12);
        International international2 = new International(internationalProfile2, internationalStudent2.getCreditHours(), false);
        international2.tuitionDue();

        assertEquals(35655, international2.getTuition());

        Profile internationalProfile3 = new Profile("Sunny Lin", Major.BA);
        Student internationalStudent3 = new Student(internationalProfile3, 16);
        International international3 = new International(internationalProfile3, internationalStudent3.getCreditHours(), false);
        international3.tuitionDue();

        assertEquals(35655, international3.getTuition());


    }
}
