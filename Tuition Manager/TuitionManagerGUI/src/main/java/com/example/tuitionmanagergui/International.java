package com.example.tuitionmanagergui;

/**
 * International Class extends the NonResident class and determines the International Student tuition
 * Adds the International fee and study abroad status for the student
 *
 * @author Varsha Balaji, Camryn Harrell
 */

public class International extends NonResident {
    //TODO: should not be set to 0 in constructors
    private double internationalTuition;
    private boolean isStudyingAbroad;
    private final int NON_RES_ADDITIONAL_FEE = 966;
    private final int INTERNATIONAL_ADDITIONAL_FEE = 2650;

    /**
     * Creates an International Object
     *
     * @param profile          is the students profile created in the Profile class
     * @param creditHours      number of credits the student is taking
     * @param isStudyingAbroad study abroad status
     */
    public International(Profile profile, int creditHours, boolean isStudyingAbroad) {
        super(profile, creditHours);
        this.isStudyingAbroad = isStudyingAbroad;
        this.internationalTuition = getDEFAULT_TUITION();
    }


    public void setStudyingAbroad(boolean isStudyingAbroad) {
        this.isStudyingAbroad = isStudyingAbroad;
    }
    /**
     * Gets the study abroad status of a student.
     *
     * @return true if the student is studying abroad and false otherwise.
     */
    public boolean getIsStudyingAbroad() {
        return this.isStudyingAbroad;
    }

    /**
     * Gets the tuition of an international student.
     *
     * @return the tuition of an international student.
     */
    public double getInternationalTuition() {
        return this.internationalTuition;
    }

    /**
     * Calculates a student's tuition using the international tuition fees
     * Overrides tuitionDue method in Student
     */
    @Override
    public void tuitionDue() {
        double tuition = getDEFAULT_TUITION();
        if (isFullTime(getCreditHours()) && !isStudyingAbroad) {
            tuition = fullTimeTuitionCalculation(getCreditHours(),
                    getFULL_TIME_NONRESIDENT(), getPART_TIME_NONRESIDENT_PER_CREDIT())  + INTERNATIONAL_ADDITIONAL_FEE;
        } else if (isStudyingAbroad) {
            tuition = getFULL_TIME_UNIVERSITY_FEE() + INTERNATIONAL_ADDITIONAL_FEE;
        }
        setTuition(tuition);
    }

    /**
     * Determines if an International student has exceeded the maximum
     * amount of credit hours or not.
     *
     * @return true if the International student has exceeded the maximum
     * credit hours and false otherwise.
     */
    public boolean exceedsInternationalCreditHours() {
        if (getCreditHours() > getMINIMUM_FULL_TIME_CREDIT_HOURS()) {
            return true;
        }
        return false;
    }

    /**
     * Determines if an International student has subceeded the minimum
     * amount of credit hours or not.
     *
     * @return true if the International student has subceeded the minimum
     * credit hours and false otherwise.
     */
    public boolean subceedsInternationalCreditHours() {
        if (getCreditHours() < getMINIMUM_FULL_TIME_CREDIT_HOURS()) {
            return true;
        }
        return false;
    }

}
