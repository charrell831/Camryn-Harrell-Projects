import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriStateTest {
    @Test
    void tuitionDue() {

        Profile triProfile = new Profile("Mary Johnson", Major.BA);
        Student triStudent = new Student(triProfile, 15);
        TriState tri = new TriState(triProfile, triStudent.getCreditHours(), State.CT);
        tri.tuitionDue();

        assertEquals(28005.00, tri.getTuition());
    }
}
