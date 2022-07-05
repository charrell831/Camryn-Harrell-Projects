package com.example.tuitionmanagergui;
/**
 * Profile class creates a profile for each student from their name and major
 *
 * @author Varsha Balaji, Camryn Harrell
 */

public class Profile {
    private String name;
    private Major major; //5 majors and 2-charater each: CS, IT, BA, EE, ME

    /**
     * Creates a profile for each student from their name and major
     *
     * @param name  students name
     * @param major students major
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     * Gets the students name and returns name
     *
     * @return returns the students name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the students major and returns major
     *
     * @return returns the students Major type Major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * method sets the students name
     *
     * @param name the students name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method sets the students major
     *
     * @param major the students major type Major
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Equals method override for album object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            if (profile.name.equals(this.name) && profile.major.equals(this.major))
                return true;
        }
        return false;
    }

    /**
     * toString() method override for album object.
     */
    @Override
    public String toString() {
        return getName() + ":" + getMajor();
    }
}

