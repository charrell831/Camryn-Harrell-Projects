package com.example.tuitionmanagergui;
import java.util.Calendar;
import java.util.StringTokenizer;
/**
 * The Date class defines a Date object and initialize the day, month and year.
 * The dates in this class are checked to make sure they are valid by using the isValid method.
 *
 * @author Varsha Balaji, Camryn Harrell
 */



public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;


    /**
     * Creates a date object based on input String date (MM/DD/YYYY).
     *
     * @param date date given as a string that will be used to create a date object
     */
    public Date(String date) {
        StringTokenizer input = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(input.nextToken());
        this.day = Integer.parseInt(input.nextToken());
        this.year = Integer.parseInt(input.nextToken());
    }

    /**
     * Creates a Date object with today's date.
     */
    public Date() {
        Calendar current = Calendar.getInstance();
        this.day = current.get(Calendar.DAY_OF_MONTH);
        this.month = current.get(Calendar.MONTH);
        this.year = current.get(Calendar.YEAR);
    }

    /**
     * Gets year
     *
     * @return returns year.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets month.
     *
     * @return returns month.
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Gets day.
     *
     * @return returns day.
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Converts Date object to a string.
     *
     * @return returns a string representation (MM/DD/YYYY) of Date object.
     */
    @Override
    public String toString() {
        return getMonth() + "/" + getDay() + "/" + getYear();
    }

    /**
     * Checks to see if the given date is valid. Returns true if the date is valid and false
     * if it is not.
     *
     * @return returns true or false based on whether or not a date is valid.
     */
    public boolean isValid() {
        Date today = new Date();
        if (day <= 0 || year < 2021 || month <= 0 || month > 12 ||
                (month > today.month && year == today.year)) { //TODO: change magic numbers, figure out way to get todays date
            return false;
        }
        //january, mar, may, july, aug, oct, dec
        //remember to check for date beyond current date

        else if (!(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            if (day == 31) {
                return false;
            }
            //april, june, sept, nov 30 :feb 28, (29 leap years) :
            else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    return false;
                }
            }

            //leap year check
            else if (year % 4 == 0) {
                if (year % 100 == 0 && year % 400 == 0) { //and statement or nested if?
                    //is leap year
                    if (month == 2 && day != 29) {
                        return false;
                    }
                }
            } else if (month == 2) {
                if (day > 28) {
                    return false;
                }
            }
        } else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {
            if (day > 31) {
                return false;
            }
        }
        return true;
    }


    /**
     * Uses the Java interface Comparable to compare the release dates of each album.
     */
    @Override
    public int compareTo(Date date) {
        final int greaterThan = 1;
        final int equalTo = 0;
        final int lessThan = -1;

        if (this.year == date.year && this.month == date.month && this.day == date.day) {
            return equalTo;
        } else if ((this.year > date.year) || (this.year == date.year && this.month > date.month) ||
                (this.year == date.year && this.month == date.month && this.day > date.day)) {
            return greaterThan;
        } else {
            return lessThan;
        }
    }

    public static void main(String[] args) {
        System.out.print(Calendar.getInstance().getTime());
    }
}