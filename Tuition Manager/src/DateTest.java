/**
 * JUnit Test Class for Date and isValid method specifically.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void isValid() {
        //any years before 2021 are invalid
        Date date1 = new Date("05/21/2020");
        assertEquals(false, date1.isValid());

        Date date2 = new Date("03/23/2030");
        assertEquals(true, date2.isValid());

        Date date3 = new Date("02/29/2022");
        assertEquals(false, date3.isValid());

        Date date4 = new Date("05/29/2021");
        assertEquals(true, date4.isValid());

        Date date5 = new Date("06/31/2023");
        assertEquals(false, date5.isValid());

        Date date6 = new Date("03/12/2024");
        assertEquals(true, date6.isValid());

        Date date7 = new Date("05/32/2022");
        assertEquals(false, date7.isValid());

        Date date8 = new Date("13/21/2022");
        assertEquals(false, date8.isValid());

        Date date9 = new Date("01/23/1999");
        assertEquals(false, date9.isValid());

        Date date10 = new Date("00/01/2000");
        assertEquals(false, date10.isValid());

        Date date11 = new Date("02/31/2020");
        assertEquals(false, date11.isValid());

    }
}
