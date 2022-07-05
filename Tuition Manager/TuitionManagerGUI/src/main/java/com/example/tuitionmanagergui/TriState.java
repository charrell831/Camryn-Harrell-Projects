package com.example.tuitionmanagergui;

/**
 * TriState Class extends the NonResident class and determines the TriState Student tuition
 * Adds the TriState fee and discount for the student where applicable
 *
 * @author Varsha Balaji, Camryn Harrell
 */

public class TriState extends NonResident {
    //includes specific data and operations to students who live in connecticut or ny
    private double tristateTuition;
    private State state;
    private double discount;
    private final int NY_DISCOUNT = 4000;
    private final int CT_DISCOUNT = 5000;
    private final int DEFAULT_TUITION = 0;

    /**
     * creates a TriState student object
     *
     * @param profile     students profile from the Profile class
     * @param creditHours studentfs credit hours
     * @param state       state where student is from
     */
    public TriState(Profile profile, int creditHours, State state) {
        super(profile, creditHours);
        this.state = state;
        this.tristateTuition = DEFAULT_TUITION;
    }

    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return this.state;
    }

    public double getTristateTuition() {
        return this.tristateTuition;
    }

    /**
     * Calculates a student's tuition using the tri state tuition fees
     * Overrides tuitionDue method in Student
     */
    @Override
    public void tuitionDue() {
        double tuition = 0;
        if (isFullTime(getCreditHours())) {
            tuition = fullTimeTuitionCalculation(getCreditHours(),
                    getFULL_TIME_NONRESIDENT(), getPART_TIME_NONRESIDENT_PER_CREDIT());
            if (state.equals(State.NY)) {
                tuition -= NY_DISCOUNT;
            }

            if (state.equals(State.CT)) {
                tuition -= CT_DISCOUNT;
            }
        } else {
            tuition = partTimeTuitionCalculation(getCreditHours(),
                    getPART_TIME_NONRESIDENT_PER_CREDIT());
        }

        setTuition(tuition);
    }

}