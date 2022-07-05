import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RosterTest {

    @Test
    void add() {
        Roster roster = new Roster();
        Profile resProfile = new Profile("John Doe", Major.EE);
        Student resStudent = new Student(resProfile, 18);
        Resident resident = new Resident(resProfile, resStudent.getCreditHours());

        //TODO: specify AR, AI etc.
        assertEquals(false, roster.add(resident));
        assertEquals(true, roster.add(resident));

        Profile internationalProfile = new Profile("Gloria Salas", Major.CS);
        Student internationalStudent = new Student(internationalProfile, 12);
        International international = new International(internationalProfile, internationalStudent.getCreditHours(), false);

        assertEquals(false, roster.add(international));

        Profile internationalProfile2 = new Profile("Andrea Lopez", Major.CS);
        Student internationalStudent2 = new Student(internationalProfile, 12); //over max for international studying abroad
        International international2 = new International(internationalProfile2, internationalStudent2.getCreditHours(), true);

        assertEquals(false, roster.add(international2));

        Profile tristateProfile = new Profile("Xochitl M", Major.BA);
        Student tristateStudent = new Student(tristateProfile, 13);
        TriState triState = new TriState(tristateProfile, tristateStudent.getCreditHours(), State.CT);

        assertEquals(false, roster.add(triState));

        Profile tristateProfile2 = new Profile("Sammi S", Major.CS);
        Student tristateStudent2 = new Student(tristateProfile2, 20);
        TriState triState2 = new TriState(tristateProfile2, tristateStudent2.getCreditHours(), State.NY);

        assertEquals(false, roster.add(triState2));
    }

    @Test
    void remove() {
        Roster roster = new Roster();
        Profile resProfile = new Profile("John Doe", Major.EE);
        Student resStudent = new Student(resProfile, 18);
        Resident resident = new Resident(resProfile, resStudent.getCreditHours());

        roster.add(resident);
        assertEquals(true, roster.remove(resident));
        assertEquals(false, roster.remove(resident));


        Profile internationalProfile = new Profile("Gloria Salas", Major.CS);
        Student internationalStudent = new Student(internationalProfile, 12);
        International international = new International(internationalProfile, internationalStudent.getCreditHours(), false);
        roster.add(international);
        assertEquals(true, roster.remove(international));

        Profile internationalProfile2 = new Profile("Andrea Lopez", Major.CS);
        Student internationalStudent2 = new Student(internationalProfile, 12);
        International international2 = new International(internationalProfile2, internationalStudent2.getCreditHours(), true);
        roster.add(international2);
        assertEquals(true, roster.remove(international2));

        Profile tristateProfile = new Profile("Xochitl M", Major.BA);
        Student tristateStudent = new Student(tristateProfile, 12);
        TriState triState = new TriState(tristateProfile, tristateStudent.getCreditHours(), State.CT);
        roster.add(triState);
        assertEquals(true, roster.remove(triState));

        Profile tristateProfile2 = new Profile("Sammi S", Major.CS);
        Student tristateStudent2 = new Student(tristateProfile2, 12);
        TriState triState2 = new TriState(tristateProfile2, tristateStudent2.getCreditHours(), State.NY);
        roster.add(triState2);
        assertEquals(true, roster.remove(triState2));
    }

    public static void main(String[] args) {

    }
}