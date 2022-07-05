/**
 * The Roster class includes an array of Students, the number of students currently in the list, and the initial size.
 * Roster class is responsible for adding and removing students while also including methods to search for
 * an student in the array. The class includes three print methods to sort and display
 * the contents of a roster either by payment date or by the students' name.
 *
 * @author Varsha Balaji, Camryn Harrell
 */
public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster
    private final int CAPACITY_SIZE = 4;
    private final int INITIAL_SIZE = 0;

    /**
     * Default constructor for roster that sets student list initial size to 4.
     * size is set to default value of 0.
     */
    public Roster() {
        roster = new Student[CAPACITY_SIZE];
        size = INITIAL_SIZE;
    }

    /**
     * Method to see if a student exists or not in the roster
     *
     * @param student Student to find in the roster
     * @return returns true if the student exists in the roster and false otherwise
     */
    public boolean doesExist(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                return true;
            }
        }
        return false;
    }

    public void replaceStudent(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                roster[i] = student;
                // System.out.println("student: " + roster[i].toString());
            }
        }
    }

    /**
     * method searches roster to find and return a student
     *
     * @param student student in roster
     * @return student
     */
    public Student findStudent(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                return roster[i];
            }
        }
        return null;
    }

    /**
     * Searches the roster array for the given student and returns -1 if the student is not found.
     *
     * @param student student that will be searched for in albums array
     * @return returns the index of the found student and -1 if the student is not found.
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Increases the list of student list by 4 if it is full.
     */
    private void grow() {
        Student[] rosterIncrease = new Student[size + CAPACITY_SIZE];

        for (int i = 0; i < size; i++) {
            rosterIncrease[i] = roster[i];
        }

        roster = rosterIncrease;
    }

    /**
     * Checks if the list of students is empty.
     *
     * @return true if the list of students is empty and false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Adds a student into the Roster.
     *
     * @param student student looking to be added to the roster
     * @return true if a student has been successfully added to the roster and
     * false otherwise.
     */
    public boolean add(Student student) {
        if (find(student) != -1) {
            return true;
        }
        int counter = 0;
        while (counter < roster.length) {
            if (roster[counter] == null) {
                break;
            }
            counter++;
        }

        if (counter < roster.length) {
            roster[counter] = student;
            size++;
        } else {
            grow();
            roster[counter] = student;
            size++;
        }
        return false;
    }

    /**
     * Removes a student from the roster.
     *
     * @param student student looking to be removed from roster
     * @return true if a student has been successfully removed from the roster and
     * false otherwise.
     */
    public boolean remove(Student student) {
        if (doesExist(student)) {
            int index = find(student);

            if (index != roster.length - 1) {
                roster[index] = null;
                size--;
                for (int i = index; i < roster.length - 1; i++) {
                    roster[i] = roster[i + 1];
                }
                roster[size] = null;
                return true;
            } else if (index == roster.length - 1) {
                roster[index] = null;
                size--;
                return true;
            }
        }
        return false;
    }


    /**
     * calls tuition due on each student in roster.
     */
    public void calculateTuitionDue() {
        for (int i = 0; i < size; i++) {
            if (roster[i].getClass().getName().equals("TriState")) {
                TriState triStudent = (TriState) roster[i];
                triStudent.tuitionDue();
            } else if (roster[i].getClass().getName().equals("International")) {
                International international = (International) roster[i];
                international.tuitionDue();
            } else if (roster[i].getClass().getName().equals("Resident")) {
                Resident resStudent = (Resident) roster[i];
                resStudent.tuitionDue();
            } else if (roster[i].getClass().getName().equals("NonResident")) {
                NonResident nonResStudent = (NonResident) roster[i];
                nonResStudent.tuitionDue();
            }
        }
    }

    /**
     * Sorts each student by name and then prints each student in the roster.
     *
     * @param studentType type of student
     * @param state       the state the student is from
     */
    public void printByName(StudentType studentType, State state) {
        Student temp;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (roster[i].getProfile().getName().compareTo(roster[j].getProfile().getName()) == 1) {
                    temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }
        System.out.println("* list of students ordered by name **\n");
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) {
                System.out.println(this.roster[i].toString());
            }
        }
        System.out.print("* end of roster **");
    }

    /**
     * Sorts each album by payment date and then prints each student in the roster.
     *
     * @param studentType type of student
     * @param state       the state the student is from
     */
    public void printByPaymentDate(StudentType studentType, State state) {
        Student temp;
        if (size == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        System.out.println("* list of students made payments ordered by payment date **\n");
        for (int i = 0; i < roster.length; i++) {
            for (int j = i + 1; j < roster.length; j++) {
                if (roster[i].getDate().compareTo(roster[j].getDate()) == 1) {
                    temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.println(roster[i].toString());
        }
        System.out.print("* end of roster **");
    }
}
