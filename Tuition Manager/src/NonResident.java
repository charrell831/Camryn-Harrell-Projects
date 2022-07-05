/**
 * Non Resident class extends the Student class and determines the tuition for non resident students(part time and full time)
 *
 * @author Varsha Balaji, Camryn Harrell
 */
public class NonResident extends Student {
    //child of student class
    private double nonResidentTuition;
    private final int FULL_TIME_NONRESIDENT = 29737;
    private final int PART_TIME_NONRESIDENT_PER_CREDIT = 966;
    private State state;

    /**
     * Creates a Non Resident object from a students profile and their credit hours
     *
     * @param profile     students profile of type Profile created in Profile.java
     * @param creditHours student's credit hours
     */
    public NonResident(Profile profile, int creditHours) {
        super(profile, creditHours);
        this.nonResidentTuition = getDEFAULT_TUITION();
    }

    /**
     * Gets the tuition of a full time non-resident student.
     *
     * @return full time non-resident student tuition.
     */
    public int getFULL_TIME_NONRESIDENT() {
        return FULL_TIME_NONRESIDENT;
    }


    public State getState() {
        return this.state;
    }

    /**
     * Gets the tuition of a part-time non-resident student per credit.
     *
     * @return part-time non-resident student amount per credit.
     */
    public int getPART_TIME_NONRESIDENT_PER_CREDIT() {
        return PART_TIME_NONRESIDENT_PER_CREDIT;
    }

    public void setNonResidentTuition(double nonResidentTuition) {
        this.nonResidentTuition = nonResidentTuition;
    }

    /**
     * Gets the tuition of a non-resident student
     *
     * @return the tuition of a non-resident student.
     */
    public double getNonResidentTuition() {
        return this.nonResidentTuition;
    }


    //TODO: remove all calculateTuitions once tuitionManager is fixed

    /**
     * Calculates a student's tuition using the non resident tuition fees
     * Overrides tuitionDue method in Student
     */
    @Override
    public void tuitionDue() {
        double tuition = getDEFAULT_TUITION();
        if (isFullTime(getCreditHours())) {
            tuition = fullTimeTuitionCalculation(getCreditHours(), FULL_TIME_NONRESIDENT, PART_TIME_NONRESIDENT_PER_CREDIT);
        } else {
            tuition = partTimeTuitionCalculation(getCreditHours(), PART_TIME_NONRESIDENT_PER_CREDIT);
        }
        setTuition(tuition);
    }
}

