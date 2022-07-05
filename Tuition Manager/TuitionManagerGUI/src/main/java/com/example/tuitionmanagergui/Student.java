package com.example.tuitionmanagergui;

/**
 * The Student Class initializes profile, credit hours, tuition, payment date and student type for a student
 * The class creates an instance of a Student object and the public methods of this class are called
 * in the TuitionManager.java and Roster.java
 *
 * @author Varsha Balaji, Camryn Harrell
 */




public class Student {
    private Profile profile;
    private int creditHours;
    private double tuition;
    private double tuitionPaid;
    private Date date;
    private StudentType studentType;
    private boolean isAwarded;

    private final int MINIMUM_CREDIT_HOURS = 3;
    private final int MAXIMUM_CREDIT_HOURS = 24;
    private final int CREDIT_HOUR_CAP = 16;
    private final int MINIMUM_FULL_TIME_CREDIT_HOURS = 12;
    private final int FULL_TIME_UNIVERSITY_FEE = 3268;
    private final double PART_TIME_UNIVERSITY_FEE = 3268 * 0.80;
    private final int DEFAULT_TUITION = 0;

    /**
     * Creates an student with specified profile from Profile class, and their credit hours.
     *
     * @param profile     the profile of the student.
     * @param creditHours the credit hours for the student.
     */
    public Student(Profile profile, int creditHours) {
        this.profile = profile;
        this.creditHours = creditHours;
        this.studentType = studentType;
    }


    /**
     * Creates a Student object with Profile as an argument
     *
     * @param profile students profile from Profile.java
     */
    public Student(Profile profile) { //to help with remove method
        this.profile = profile;
    }


    /**
     * sets the student type(resident, non resident, etc.)  for each student
     *
     * @param studentType the type of student
     */
    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    /**
     * gets the type of student for each student(ex : resident, nonresident)
     *
     * @return the type of student
     */
    public StudentType getStudentType() {
        return this.studentType;
    }

    /**
     * Sets the studentType of the student to a string and converts to applicable String.
     *
     * @return returns the student type as a string.
     */
    public String studentTypeToString(State state) {
        if (this.studentType == StudentType.RESIDENT) {
            return "resident";
        }
        if (this.studentType == StudentType.NONRESIDENT) {
            return "non-resident";
        }
        if (this.studentType == StudentType.TRISTATE) {
            return "non-resident(tri-state):" + state;
        }
        if (this.studentType == StudentType.INTERNATIONAL) {
            return "non-resident:international";
        }
        return null;
    }


    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * sets credit hours for a student
     *
     * @param creditHours a student's credit hours
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setPayment(double payment) {
        this.tuition -= payment;
    }

    /**
     * sets the tuition for a student
     *
     * @param tuition the tuition for a particular student
     */
    public void setTuition(double tuition) {
        this.tuition = tuition;
    }

    /**
     * Gets the default tuition of a given student.
     *
     * @return the default tuition.
     */
    public int getDEFAULT_TUITION() {
        return this.DEFAULT_TUITION;
    }

    /**
     * Gets the profile (name and major) of a given student.
     *
     * @return the profile of a given student.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Gets the number credit hours that a given student has.
     *
     * @return the number of credit hours that a student has.
     */
    public int getCreditHours() {
        return this.creditHours;
    }

    public double getMAXIMUM_CREDIT_HOURS() {
        return this.MAXIMUM_CREDIT_HOURS;
    }

    /**
     * Gets the tuition of a given student.
     *
     * @return the tuition of a given student.
     */
    public double getTuition() {
        return this.tuition;
    }

    /**
     * Gets the maximum amount of credit hours for a given student.
     *
     * @return maximum amount of credit hours for a given student
     */
    public int getCREDIT_HOUR_CAP() {
        return this.CREDIT_HOUR_CAP;
    }

    /**
     * Gets the minimum amount of credit ours for a given student.
     *
     * @return minimum amount of credit hours for a given student.
     */
    public int getMINIMUM_FULL_TIME_CREDIT_HOURS() {
        return this.MINIMUM_FULL_TIME_CREDIT_HOURS;
    }

    /**
     * Gets the University fee of a full-time student.
     *
     * @return the University fee of a full-time student.
     */
    public int getFULL_TIME_UNIVERSITY_FEE() {
        return this.FULL_TIME_UNIVERSITY_FEE;
    }

    /**
     * Gets the University fee of a part-time student.
     *
     * @return the University fee of a part-time student.
     */
    public double getPART_TIME_UNIVERSITY_FEE() {
        return this.PART_TIME_UNIVERSITY_FEE;
    }

    /**
     * Determines whether or not a student is over the maximum
     * amount of credits.
     *
     * @param creditHours
     * @return
     */
    public boolean isOverMaxCredits(int creditHours) {
        return creditHours > MAXIMUM_CREDIT_HOURS;
    }

    /**
     * Determines whether or not a student is under the minimum
     * amount of credits.
     *
     * @param creditHours
     * @return
     */
    public boolean isUnderMinCredits(int creditHours) {
        return creditHours < MINIMUM_CREDIT_HOURS;
    }

    /**
     * Calculates the exceeded amount of credit hours completed by a student.
     *
     * @param creditHours number of credit hours completed by a student.
     * @return the amount of credit hours that a student has exceeded.
     */
    public int exceededCreditHoursRemainder(int creditHours) {
        return creditHours - CREDIT_HOUR_CAP;
    }

    /**
     * Determines whether or not a student is full time
     * based on number of credit hours.
     *
     * @param creditHours
     * @return true if number of credit hours indicates that a student
     * is full time and false otherwise.
     */
    public boolean isFullTime(int creditHours) {
        if (creditHours >= MINIMUM_FULL_TIME_CREDIT_HOURS) {
            return true;
        }
        return false; //if false, then part-time
    }

    /**
     * Determines if a student has exceeded the maximum amount
     * of credit hours.
     *
     * @param creditHours the number of credit hours a student has completed.
     * @return true if a student has exceeded the maximum amount of credit hours
     * and false otherwise.
     */
    public boolean exceededCreditHours(int creditHours) {

        if (creditHours > CREDIT_HOUR_CAP) {
            return true;
        }
        return false;
    }

    /**
     * Calculates a student's tuition using {@link #fullTimeTuitionCalculation(int, int, int)}
     * and {@link #partTimeTuitionCalculation(int, int)}
     */
    public void tuitionDue() {
    }

    /**
     * Calculates the tuition of a full time student.
     *
     * @param creditHours            students credit hours
     * @param fullTimeTuition        full time tuition rate for a student
     * @param partTimeTuitionPerHour part time tuition rate for a student
     * @return the final tuition for a student
     */
    public double fullTimeTuitionCalculation(int creditHours, int fullTimeTuition, int partTimeTuitionPerHour) {
        double finalTuition = 0;
        //EXCEEDED CREDIT HOURS TUITION CALCULATION
        if (exceededCreditHours(creditHours)) {
            finalTuition = fullTimeTuition + FULL_TIME_UNIVERSITY_FEE
                    + (partTimeTuitionPerHour * (exceededCreditHoursRemainder(creditHours)));

        }
        //FULL-TIME CREDIT HOURS TUITION CALCULATION
        if (creditHours >= MINIMUM_FULL_TIME_CREDIT_HOURS && creditHours <= CREDIT_HOUR_CAP) {
            finalTuition = fullTimeTuition + FULL_TIME_UNIVERSITY_FEE;
            //System.out.print("\ntuition: " + finalTuition);
        }
        //System.out.print("\ntuition: " + finalTuition);
        return finalTuition;
    }

    public void setTuitionPaid(double tuitionPaid) {
        this.tuitionPaid += tuitionPaid;
    }

    public double getTuitionPaid() {
        return this.tuitionPaid;
    }

    /**
     * sets the payment date
     *
     * @param date payment date of type Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAwarded() {
        return this.isAwarded;
    }

    public void setAwarded(boolean isAwarded) {
        this.isAwarded = isAwarded;
    }
    /**
     * gets the payment date for a student
     *
     * @return the students payment date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Calculates the tuition of a part-time student.
     *
     * @param creditHours            students credit hours
     * @param partTimeTuitionPerHour part time tuition rate for a student
     * @return final tuition for student
     */
    public double partTimeTuitionCalculation(int creditHours, int partTimeTuitionPerHour) {
        double finalTuition = partTimeTuitionPerHour * creditHours + PART_TIME_UNIVERSITY_FEE;
        return finalTuition;
    }

    /**
     * converts tuition format to proper decimal format
     *
     * @return tuition
     */
    private String tuitionDueToString() {
        String tuitionDue = String.format("%,.2f", this.tuition);
        return tuitionDue;
    }

    /**
     * converts Date to a toString
     *
     * @param date payment date
     * @return date in toString format
     */
    private String dateToString(Date date) {
        if (date == null) {
            return "--/--/--";
        }
        return date.toString();
    }

    /**
     * Equals method override for student object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            if (student.profile.equals(this.profile))
                return true;
        }
        return false;
    }

    private String tuitionPaidToString() {
        String tuitionPaid = String.format("%,.2f", this.tuitionPaid);
        return tuitionPaid;
    }

    /**
     * toString() method override for student object.
     */
    @Override
    public String toString() {
        return profile.toString() + ":" + creditHours + " credit hours:tuition due: " + "" + tuitionDueToString() +
                ":total payment:" + tuitionPaidToString() + ":last payment date:" + dateToString(date);
    }


}
