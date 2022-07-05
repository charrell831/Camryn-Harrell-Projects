import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResidentTest {

    @Test
    void tuitionDue() {
        Profile residentProfile = new Profile("John Doe", Major.CS);
        Student residentStudent = new Student(residentProfile, 3);
        Resident resident = new Resident(residentProfile, residentStudent.getCreditHours());
        resident.tuitionDue();

        assertEquals(3826.40, resident.getTuition());
    }
}