package com.example.tuitionmanagergui;
/**
 * Resident class extends the Student class and calculates tuition for Resident students
 *
 * @author Varsha Balaji, Camryn Harrell
 */
public class Resident extends Student {
    //child of student class
    //operations for just resident students
    private double residentTuition;
    private double financialAidAmount;

    private final int FULL_TIME_RESIDENT = 12536;
    private final int PART_TIME_RESIDENT_PER_HOUR = 404;
    private final int MAX_FINANCIAL_AID = 10000;

    /**
     * Creates a Resident Object for resident students
     *
     * @param profile     students profile created from Profile class
     * @param creditHours students credit hourd
     */
    public Resident(Profile profile, int creditHours) {
        //first line of constructor must invoke parent constructor
        super(profile, creditHours);
        this.financialAidAmount = financialAidAmount;
        this.residentTuition = getDEFAULT_TUITION();
    }

    /**
     * sets financial aid amount for resident student
     *
     * @param financialAidAmount the financial aid amount for student
     */
    public void setFinancialAidAmount(double financialAidAmount) {
        this.financialAidAmount = financialAidAmount;
    }

    /**
     * gets the resident students' financial aid amount if applicable
     *
     * @return returns the financial aid amount for the student
     */
    public double getFinancialAidAmount() {
        return financialAidAmount;
    }

    //TODO: remove all calculateTuitions once tuitionManager is fixed

    /**
     * Gets the tuition of a resident student.
     * Overrides from the Student class
     */
    @Override
    public void tuitionDue() {
        double tuition = getDEFAULT_TUITION();
        if (isFullTime(getCreditHours())) {
            tuition = fullTimeTuitionCalculation(getCreditHours(), FULL_TIME_RESIDENT, PART_TIME_RESIDENT_PER_HOUR) - getFinancialAidAmount();
        } else {
            tuition = partTimeTuitionCalculation(getCreditHours(), PART_TIME_RESIDENT_PER_HOUR);
        }
        setTuition(tuition);
    }

}

