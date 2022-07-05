package com.example.tuitionmanagergui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.skin.TextAreaSkin;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * The Tuition Manager controller class is the backend software for the Tuition Management GUI.
 * This class contains includes actions such as adding students, removing students, changing the
 * abroad status of a student, calculating the tuition of one or more students at a time and
 * entering finanial aid amounts.
 *
 * @author Camryn Harrell, Varsha Balaji
 */
/*
* TODO:
*  Test Cases,
* and quit (basically x out right?), make sure all commands are filled, panes, residency
*
* */
public class TuitionManagerController {
    private final int ZERO = 0;
    private final int MIN_HOURS = 3;
    private final int MAX_HOURS = 24;
    @FXML
    private Button addStudent, removeStudent, tuitionDue, applyFinancialAidButton;

    @FXML
    private TextField studentNameInput, creditHoursInput, tuitionDueInput, inputDate, studentPFAName, financialAid;

    @FXML
    private TextArea textArea, textAreaPFA;

    @FXML
    private RadioButton csButton, eeButton, meButton, itButton, baButton, nyButton,
            ctButton, residentButton, nonResButton, triButton, internationalButton,
            csPFAButton, eePFAButton, mePFAButton, itPFAButton, baPFAButton;

    @FXML
    private CheckBox isAbroad;

    private boolean disableAll = false;

    private Roster roster = new Roster();

    private boolean endOfInput;

    /**
     * Disables non-applicable buttons as soon as the student profile tab is generated.
     */
    public void studentProfileSelected() {
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        isAbroad.setDisable(true);
    }

    /**
     * Disables any buttons that represent any majors besides CS.
     */
    public void csClicked() {
        //once clicked, remove any other selected buttons (shouldn't disable incase user makes mistake)
        eeButton.setSelected(false);
        itButton.setSelected(false);
        meButton.setSelected(false);
        baButton.setSelected(false);

        eePFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        mePFAButton.setSelected(false);
        baPFAButton.setSelected(false);
    }

    /**
     * Disables any buttons that represent any majors besides IT.
     */
    public void itClicked() {
        //once clicked, remove any other selected buttons (shouldn't disable incase user makes mistake)
        eeButton.setSelected(false);
        csButton.setSelected(false);
        meButton.setSelected(false);
        baButton.setSelected(false);

        eePFAButton.setSelected(false);
        csPFAButton.setSelected(false);
        mePFAButton.setSelected(false);
        baPFAButton.setSelected(false);
    }

    /**
     * Disables any buttons that represent any majors besides EE.
     */
    public void eeClicked() {
        //once clicked, remove any other selected buttons (shouldn't disable incase user makes mistake)
        csButton.setSelected(false);
        itButton.setSelected(false);
        meButton.setSelected(false);
        baButton.setSelected(false);

        csPFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        mePFAButton.setSelected(false);
        baPFAButton.setSelected(false);
    }

    /**
     * Disables any buttons that represent any majors besides ME.
     */
    public void meClicked() {
        //once clicked, remove any other selected buttons (shouldn't disable incase user makes mistake)
        eeButton.setSelected(false);
        itButton.setSelected(false);
        csButton.setSelected(false);
        baButton.setSelected(false);

        eePFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        csPFAButton.setSelected(false);
        baPFAButton.setSelected(false);

    }

    /**
     * Disables any buttons that represent any majors besides BA.
     */
    public void baClicked() {
        //once clicked, remove any other selected buttons (shouldn't disable incase user makes mistake)
        eeButton.setSelected(false);
        itButton.setSelected(false);
        meButton.setSelected(false);
        csButton.setSelected(false);

        eePFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        mePFAButton.setSelected(false);
        csPFAButton.setSelected(false);
    }

    /**
     * Adds a student to the roster once the "Add Student" button is clicked.
     */
    public void addStudentClicked() {
        endOfInput = false;
        Student student = createStudent();
        if (endOfInput) {
            return;
        }
         if (creditHoursInput.getText().isEmpty()) {
             textArea.appendText("\nStudent credit hours have not been added");
             deselectAll();
        } else {
            int creditHours = Integer.parseInt(creditHoursInput.getText());
            if (creditHours < ZERO) {
                textArea.appendText("\nCredit hours cannot be negative.");
                deselectAll();
            } else if (creditHours < MIN_HOURS) {
                textArea.appendText("\nMinimum credit hours is 3.");
                deselectAll();
            } else if (creditHours > MAX_HOURS) {
                textArea.appendText("\nCredit hours exceed the maximum 24.");
                deselectAll();
            } else {
                if (roster.doesExist(student)) {
                    textArea.appendText("\nStudent is already in the roster.");
                    deselectAll();
                } else {
                    if (residentButton.isSelected()) {
                        buildResStudent(student.getProfile(), creditHours);
                    } else if (nonResButton.isSelected()) {
                        buildnonResStudent(student.getProfile(), creditHours);
                    } else if (internationalButton.isSelected()) {
                        boolean isStudyAbroad = false;
                        if (isAbroad.isSelected()) {
                            isStudyAbroad = true;
                        }
                        buildinternationalStudent(student.getProfile(), creditHours, isStudyAbroad);
                    } else if (triButton.isSelected()) {
                        if (nyButton.isSelected()) {
                            buildtriStudent(student.getProfile(), creditHours, State.NY);
                        } else if (ctButton.isSelected()) {
                            buildtriStudent(student.getProfile(), creditHours, State.CT);
                        }
                    } else {
                        textArea.appendText("\nStudent residency hasn't been added.");
                        deselectAll();
                    }
                }
            }
        }
         deselectAll();
    }

    /**
     * Deselects all buttons and inputs.
     */
    private void deselectAll() {
        nonResButton.setSelected(false);
        residentButton.setSelected(false);
        csButton.setSelected(false);
        itButton.setSelected(false);
        eeButton.setSelected(false);
        meButton.setSelected(false);
        baButton.setSelected(false);
        nyButton.setSelected(false);
        ctButton.setSelected(false);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        isAbroad.setDisable(true);
        triButton.setSelected(false);
        internationalButton.setSelected(false);
        isAbroad.setSelected(false);
        studentNameInput.clear();
        creditHoursInput.clear();
    }

    /**
     * Creates a student using student name, major and number of credits.
     * @return Student object.
     */
    private Student createStudent() {
        String name = studentNameInput.getText();
        Profile profile = null;
        Major major = null;
        if (name.isEmpty()) {
            textArea.appendText("\nStudent name has not been added");
            endOfInput = true;
            deselectAll();
        } else {
            if (csButton.isSelected()) {
                major = Major.CS;
                profile = new Profile(name, major);
                csButton.setSelected(false);
            } else if (eeButton.isSelected()) {
                major = Major.EE;
                profile = new Profile(name, major);
                eeButton.setSelected(false);
            } else if (meButton.isSelected()) {
                major = Major.ME;
                profile = new Profile(name, major);
                meButton.setSelected(false);
            } else if (baButton.isSelected()) {
                major = Major.BA;
                profile = new Profile(name, major);
                baButton.setSelected(false);
            } else if (itButton.isSelected()) {
                major = Major.IT;
                profile = new Profile(name, major);
                itButton.setSelected(false);
            } else { //no invalid majors, just no major chosen at all
                textArea.appendText("\nStudent major has not been added");
                endOfInput = true;
            }
        }
        return new Student(profile);
    }

    /**
     * Sorts students in the roster by name in the Student Profile tab.
     */
    public void callPrintByName() {
        if (roster.isEmpty()) {
            textArea.appendText("\nThe collection is empty!");
        } else {
            int numStudents = roster.size();
            textArea.appendText("* list of students ordered by name **\n");
            roster.sortByName();
            for (int i = 0; i < numStudents; i++) {
                if (roster.get(i) != null && roster != null) {
                    textArea.appendText("\n" + roster.get(i).toString());
                }
            }
            textArea.appendText("* end of roster **\n");
        }
    }

    /**
     * Sorts students in the roster by name in the Payments/Financial Aid tab.
     */
    public void callPrintByNamePFA() {
        if (roster.isEmpty()) {
            textAreaPFA.appendText("\nThe collection is empty!");
        } else {
            int numStudents = roster.size();
            textAreaPFA.appendText("* list of students ordered by name **\n");
            roster.sortByName();
            for (int i = 0; i < numStudents; i++) {
                if (roster.get(i) != null && roster != null) {
                    textAreaPFA.appendText("\n" + roster.get(i).toString());
                }
            }
            textAreaPFA.appendText("* end of roster **\n");
        }
    }

    /**
     * Sorts students in the roster by payment date in the Payments/Financial Aid tab.
     */
    public void callPrintByDatePFA() {
        if (roster.isEmpty()) {
            textAreaPFA.appendText("\nThe collection is empty!");
        } else {
            textAreaPFA.appendText("\n* list of students made payments ordered by payment date **\n");
            roster.printByPaymentDate();
            for (int i = 0; i < roster.size(); i++) {
                if (roster.get(i) != null && roster != null) {
                    textAreaPFA.appendText(roster.get(i).toString());
                }
            }
            textAreaPFA.appendText("* end of roster **\n");
        }
    }


    /**
     * Updates study abroad status for a student.
     */
    public void isAbroadClicked() {
        endOfInput = false;
        Student student = createStudent();

        if (endOfInput) {
            textArea.appendText("\nMissing Information");
        } else {
            International international = null;

            if (roster.findStudent(student) != null && roster.findStudent(student).getClass().getName().equals("com.example.tuitionmanagergui.International")) {
                international = (International) roster.findStudent(student);
                international.setStudyingAbroad(true);
                textArea.appendText("\nAbroad status is updated");
            } else if (roster.findStudent(student) == null){
                textArea.appendText("\nStudent is not in the roster.");
            } else if (roster.findStudent(student) != null && !roster.findStudent(student).getClass().getName().equals("com.example.tuitionmanagergui.International")){
                textArea.appendText("\nStudent is not International");
            }
        }
        deselectAll();
    }

    /**
     * Deselects resident button if non-resident button is selected.
     */
    public void nonResClicked() {
        residentButton.setSelected(false);
    }

    /**
     * Deselects non-resident, new york, connecticut, and isAbroad button
     * if resident button is clicked.
     */
    public void resClicked() {
        nonResButton.setSelected(false);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        isAbroad.setDisable(true);

    }

    /**
     * Disables New York, Connecticut and isAbroad buttons once
     * resident student button is released.
     */
    public void resStudentReleased() {
        nyButton.setDisable(false);
        ctButton.setDisable(false);
        isAbroad.setDisable(false);
    }

    /**
     * Builds a Resident object based on input profile and credit hours.
     * @param profile Object that holds name and major of the student.
     * @param creditHours number of credit hours for the student.
     */
    private void buildResStudent(Profile profile, int creditHours) {
        Resident resStudent = new Resident(profile, creditHours);
        roster.add(resStudent);
        textArea.appendText("\nStudent added.");
    }

    /**
     * Builds a Non-Resident object based on input profile and credit hours.
     * @param profile Object that holds the name and major of the student.
     * @param creditHours number of credit hours for the student.
     */
    private void buildnonResStudent(Profile profile, int creditHours) {
        NonResident nonResStudent = new NonResident(profile, creditHours);
        roster.add(nonResStudent);
        textArea.appendText("\nStudent added.");
    }

    /**
     * Disables and deselects any non-applicable buttons once the International
     * radio button is clicked.
     */
    public void inClicked() {
        isAbroad.setDisable(false);
        internationalButton.setDisable(false);
        residentButton.setSelected(false);
        nonResButton.setSelected(false);
        triButton.setSelected(false);
        nyButton.setSelected(false);
        ctButton.setSelected(false);
        nyButton.setDisable(true);
        ctButton.setDisable(true);
        if (!internationalButton.isSelected()) {
            residentButton.setDisable(false);
            isAbroad.setDisable(true);
        }
    }

    /**
     * Builds a Non-Resident object based on input profile, credit hours and whether the
     * student is studying abroad.
     * @param profile Object that holds the name and major of the student.
     * @param creditHours number of credit hours for the student.
     * @param isAbroad specifies whether student is studying abroad.
     */
    private void buildinternationalStudent(Profile profile, int creditHours, boolean isAbroad) {
        International inStudent = new International(profile, creditHours, isAbroad);
        roster.add(inStudent);
        textArea.appendText("\nStudent added.");
    }

    /**
     * Disables and deselects any non-applicable buttons once the "Tri-State" button is clicked.
     */
    public void triClicked() {
        ctButton.setDisable(false);
        nyButton.setDisable(false);
        internationalButton.setSelected(false);
        isAbroad.setDisable(true);
        residentButton.setSelected(false);
        nonResButton.setSelected(false);
        if (!triButton.isSelected()) {
            nyButton.setDisable(true);
            ctButton.setDisable(true);
            ctButton.setSelected(false);
            nyButton.setSelected(false);
        }
    }

    /**
     * Builds a Tri-State object based on input profile, credit hours and whether the
     * student is studying abroad.
     * @param profile Object that holds the name and major of the student.
     * @param creditHours number of credit hours for the student.
     * @param state specifies the state of the tri-state student.
     */
    private void buildtriStudent(Profile profile, int creditHours, State state) {
        TriState triStudent = new TriState(profile, creditHours, state);
        triStudent.setState(state);
        roster.add(triStudent);
        textArea.appendText("\nStudent added.");
    }

    /**
     * Deselects all buttons and clears TextArea.
     */
    public void clearPane(){
        deselectAll();
      textArea.clear();
    }
    /**
     * Deselects the New York button once the Connecticut is clicked.
     */
    public void ctClicked() {
        nyButton.setSelected(false);
    }

    /**
     * Deselects the Connecticut button once the New York is clicked.
     */
    public void nyClicked() {
        ctButton.setSelected(false);
    }

    /**
     * Prints all students in the roster in the text area of the 'Student Profiles' tab.
     */
    public void printStudents() {
        if (roster.isEmpty()){
            textArea.appendText("\nStudent roster is empty!");
        } else {
            textArea.appendText("\n* list of students in the roster **");
            for (int i = 0; i < roster.size(); i++) {
                if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.TriState")) {
                    TriState triStudent = (TriState) roster.get(i);
                    textArea.appendText("\n" + roster.get(i).toString() + ":non-resident(tri-state):" + triStudent.getState());
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.International")) {
                    International inStudent = (International) roster.get(i);
                    if (inStudent.getIsStudyingAbroad()) {
                        textArea.appendText("\n" + roster.get(i).toString() + ":non-resident:international:study abroad.");
                    } else {
                        textArea.appendText("\n" + roster.get(i).toString() + ":non-resident:international");
                    }
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.Resident")) { //if disable buttons after adding student,
                    textArea.appendText("\n" + roster.get(i).toString() + ":resident");
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.NonResident")) {
                    textArea.appendText("\n" + roster.get(i).toString() + ":non-resident");
                }
            }
            textArea.appendText("\n** end of roster **");
        }
        deselectAll();
    }

    /**
     * Prints all students in the roster in the text area of the 'Payments/Financial Aid' tab.
     */
    public void printStudentsPFA() {
        if (roster.isEmpty()){
            textAreaPFA.appendText("\nStudent roster is empty!");
        } else {
            textAreaPFA.appendText("\n* list of students in the roster **");
            for (int i = 0; i < roster.size(); i++) {
                if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.TriState")) {
                    TriState triStudent = (TriState) roster.get(i);
                    textAreaPFA.appendText("\n" + roster.get(i).toString() + ":non-resident(tri-state):" + triStudent.getState());
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.International")) {
                    International inStudent = (International) roster.get(i);
                    if (inStudent.getIsStudyingAbroad()) {
                        textAreaPFA.appendText("\n" + roster.get(i).toString() + ":non-resident:international:study abroad.");
                    } else {
                        textAreaPFA.appendText("\n" + roster.get(i).toString() + ":non-resident:international");
                    }
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.Resident")) { //if disable buttons after adding student,
                    textAreaPFA.appendText("\n" + roster.get(i).toString() + ":resident");
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.NonResident")) {
                    textAreaPFA.appendText("\n" + roster.get(i).toString() + ":non-resident");
                }
            }
            textAreaPFA.appendText("\n** end of roster **");
        }
        deselectAllPFA();
    }

    /**
     * Removes student from the roster.
     */
    public void removeStudent() {
        endOfInput = false;
        if (endOfInput) {
            return;
        }
        Student student = createStudent();
        if (roster.doesExist(student)) {
           roster.remove(student);
           textArea.appendText("\nStudent removed from the roster.");
        } else {
            textArea.appendText("\nStudent is not in the roster.");
        }
        deselectAll();
    }

    /**
     * Calculates the tuition due for one student in the Payments/Financial Aid.
     */
    public void tuitionDueOncePFA() {
        if (roster.isEmpty()) {
            textAreaPFA.appendText("\nStudent roster is empty!");
        }  else {
            endOfInput = false;
            Student student = createPFAStudent();
            if (endOfInput) {
                textAreaPFA.appendText("\nMissing information.");
            } else {
                if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.TriState")) {
                    TriState triStudent = (TriState) roster.findStudent(student);
                    triStudent.tuitionDue();
                } else if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.International")) {
                    International international = (International) roster.findStudent(student);
                    international.tuitionDue();
                } else if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.Resident")) {
                    Resident resStudent = (Resident) roster.findStudent(student);
                    resStudent.tuitionDue();
                } else if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.NonResident")) {
                    NonResident nonResStudent = (NonResident) roster.findStudent(student);
                    nonResStudent.tuitionDue();
                }
                textAreaPFA.appendText("\nCalculating tuition due...");
                deselectAllPFA();
            }
        }
    }

    /**
     * Calculates the tuition due for one student in the Student Profiles.
     */
    public void tuitionDueOnce() {
        if (roster.isEmpty()) {
            textArea.appendText("\nStudent roster is empty!");
        }  else {
            endOfInput = false;
            Student student = createStudent(); //creates student with profile
            if (endOfInput) {
                textArea.appendText("\nMissing information.");
            } else {
                if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.TriState")) {
                    TriState triStudent = (TriState) roster.findStudent(student);
                    triStudent.tuitionDue();
                } else if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.International")) {
                    International international = (International) roster.findStudent(student);
                    international.tuitionDue();
                } else if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.Resident")) {
                    Resident resStudent = (Resident) roster.findStudent(student);
                    resStudent.tuitionDue();
                } else if (roster.findStudentByProfile(student.getProfile()).getClass().getName().equals("com.example.tuitionmanagergui.NonResident")) {
                    NonResident nonResStudent = (NonResident) roster.findStudent(student);
                    nonResStudent.tuitionDue();
                }
                textArea.appendText("\nCalculating tuition due...");
                deselectAll();
            }
        }
    }

    /**
     * Calculates the tuition of all students in the roster in the Student Profile tab.
     */
    public void calculateTuitionPFA() {
        if (roster.isEmpty()) {
            textAreaPFA.appendText("\nStudent roster is empty!");
        } else {
            for (int i = 0; i < roster.size(); i++) {
                if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.TriState")) {
                    TriState triStudent = (TriState) roster.get(i);
                    triStudent.tuitionDue();
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.International")) {
                    International international = (International) roster.get(i);
                    international.tuitionDue();
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.Resident")) {
                    Resident resStudent = (Resident) roster.get(i);
                    resStudent.tuitionDue();
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.NonResident")) {
                    NonResident nonResStudent = (NonResident) roster.get(i);
                    nonResStudent.tuitionDue();
                }
            }
            textAreaPFA.appendText("\nCalculating tuition due...");
            deselectAllPFA();
        }
    }

    /**
     * Calculates the tuition of all students in the roster in the Student Profile tab.
     */
    public void calculateTuition() {
        if (roster.isEmpty()) {
            textArea.appendText("\nStudent roster is empty!");
        } else {
            for (int i = 0; i < roster.size(); i++) {
                if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.TriState")) {
                    TriState triStudent = (TriState) roster.get(i);
                    triStudent.tuitionDue();
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.International")) {
                    International international = (International) roster.get(i);
                    international.tuitionDue();
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.Resident")) {
                    Resident resStudent = (Resident) roster.get(i);
                    resStudent.tuitionDue();
                } else if (roster.get(i).getClass().getName().equals("com.example.tuitionmanagergui.NonResident")) {
                    NonResident nonResStudent = (NonResident) roster.get(i);
                    nonResStudent.tuitionDue();
                }
            }
            textArea.appendText("\nCalculating tuition due...");
            deselectAll();
        }
    }

    /**
     * Applies financial aid to a student.
     */
    public void applyFinancialAid() {
        final double MAX_FINANCIAL_AID = 10000.0;
        final double MINIMUM = 0;
        Student student = createPFAStudent();
        if (student == null) {
            textAreaPFA.appendText("\nMissing major input.");
        } else {
            if (financialAid.getText().equals("")) {
                textAreaPFA.appendText("\nMissing the amount.");
            } else {
                double aid = Double.parseDouble(financialAid.getText());
                if (!roster.doesExist(student)) {
                    textAreaPFA.appendText("\nStudent not in the roster.");
                } else {
                    Student newStudent = roster.findStudent(student);
                    if (!newStudent.isFullTime(newStudent.getCreditHours())) {
                        textAreaPFA.appendText("\nParttime student doesn't qualify for the award.");
                    } else {
                        if (!newStudent.getClass().getName().equals("com.example.tuitionmanagergui.Resident")) {
                            textAreaPFA.appendText("\nNot a resident student.");
                        } else {
                            if (aid < MINIMUM || aid > MAX_FINANCIAL_AID) {
                                textAreaPFA.appendText("\nInvalid amount.");
                            } else {
                                Resident residentStudent = new Resident(newStudent.getProfile(), newStudent.getCreditHours());
                                if (residentStudent.isAwarded()) {
                                    textAreaPFA.appendText("\nAwarded once already.");
                                } else {
                                    residentStudent.setAwarded(true);
                                    newStudent.setTuition(newStudent.getTuition() - aid);
                                    residentStudent.setFinancialAidAmount(aid);
                                    textAreaPFA.appendText("\nTuition updated.");
                                }
                            }
                        }
                    }
                }
            }
        }
        deselectAllPFA();
    }

    /**
     * Deselects all buttons and inputs in the 'Payments/Financial Aid' tab
     */
    private void deselectAllPFA() {
        csPFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        eePFAButton.setSelected(false);
        mePFAButton.setSelected(false);
        baPFAButton.setSelected(false);
        tuitionDueInput.clear();
        financialAid.clear();
        studentPFAName.clear();
        inputDate.clear();
    }

    /**
     * Checks if a student exists in the roster and if they are full time.
     * @param student input Object.
     * @return true if the student is not full-time and false if they are.
     */
    private boolean studentCheck(Student student) {
        if (roster.doesExist(student)) {
            if (!student.isFullTime(student.getCreditHours())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deselects all other majors besides CS and disables the financialAid text box and
     * applyFinancialAid button if the student is not full-time.
     */
    public void csCFAClicked() {
        mePFAButton.setSelected(false);
        baPFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        eePFAButton.setSelected(false);

        String name = studentPFAName.getText();
        Major major = Major.CS;
        if (!name.isEmpty()) {
            Profile profile = new Profile(name, major);
            Student tmp = new Student(profile);
            Student student = roster.findStudent(tmp);

            if (studentCheck(student)) {
                financialAid.setDisable(true);
                applyFinancialAidButton.setDisable(true);
                textAreaPFA.appendText("\nParttime student doesn't qualify for the award.");
                deselectAllPFA();
            } else {
                financialAid.setDisable(false);
                applyFinancialAidButton.setDisable(false);
            }
        }
    }

    /**
     * Deselects all other majors besides BA and disables the financialAid text box and
     * applyFinancialAid button if the student is not full-time.
     */
    public void baCFAClicked() {
        csPFAButton.setSelected(false);
        mePFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        eePFAButton.setSelected(false);

        String name = studentPFAName.getText();
        Major major = Major.BA;
        if (!name.isEmpty()) {
            Profile profile = new Profile(name, major);
            Student tmp = new Student(profile);
            Student student = roster.findStudent(tmp);

            if (studentCheck(student)) {
                financialAid.setDisable(true);
                applyFinancialAidButton.setDisable(true);
                textAreaPFA.appendText("\nParttime student doesn't qualify for the award.");
                deselectAllPFA();
            } else {
                financialAid.setDisable(false);
                applyFinancialAidButton.setDisable(false);
            }
        }
    }

    /**
     * Deselects all other majors besides EE and disables the financialAid text box and
     * applyFinancialAid button if the student is not full-time.
     */
    public void eeCFAClicked() {
        csPFAButton.setSelected(false);
        baPFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        mePFAButton.setSelected(false);

        String name = studentPFAName.getText();
        Major major = Major.EE;
        if (!name.isEmpty()) {
            Profile profile = new Profile(name, major);
            Student tmp = new Student(profile);
            Student student = roster.findStudent(tmp);

            if (studentCheck(student)) {
                financialAid.setDisable(true);
                applyFinancialAidButton.setDisable(true);
                textAreaPFA.appendText("\nParttime student doesn't qualify for the award.");
                deselectAllPFA();
            } else {
                financialAid.setDisable(false);
                applyFinancialAidButton.setDisable(false);
            }
        }
    }

    /**
     * Deselects all other majors besides ME and disables the financialAid text box and
     * applyFinancialAid button if the student is not full-time.
     */
    public void meCFAClicked() {
        csPFAButton.setSelected(false);
        baPFAButton.setSelected(false);
        itPFAButton.setSelected(false);
        eePFAButton.setSelected(false);

        String name = studentPFAName.getText();
        Major major = Major.ME;
        if (!name.isEmpty()) {
            Profile profile = new Profile(name, major);
            Student tmp = new Student(profile);
            Student student = roster.findStudent(tmp);

            if (studentCheck(student)) {
                financialAid.setDisable(true);
                applyFinancialAidButton.setDisable(true);
                textAreaPFA.appendText("\nParttime student doesn't qualify for the award.");
                deselectAllPFA();
            } else {
                financialAid.setDisable(false);
                applyFinancialAidButton.setDisable(false);
            }
        }
    }

    /**
     * Deselects all other majors besides IT and disables the financialAid text box and
     * applyFinancialAid button if the student is not full-time.
     */
    public void itCFAClicked() {
        csPFAButton.setSelected(false);
        baPFAButton.setSelected(false);
        mePFAButton.setSelected(false);
        eePFAButton.setSelected(false);

        String name = studentPFAName.getText();
        Major major = Major.IT;
        if (!name.isEmpty()) {
            Profile profile = new Profile(name, major);
            Student tmp = new Student(profile);
            Student student = roster.findStudent(tmp);

            if (studentCheck(student)) {
                financialAid.setDisable(true);
                applyFinancialAidButton.setDisable(true);
                textAreaPFA.appendText("\nParttime student doesn't qualify for the award.");
                deselectAllPFA();
            } else {
                financialAid.setDisable(false);
                applyFinancialAidButton.setDisable(false);
            }
        }
    }

    /**
     * Creates a Student object using Profile in the 'Payments/Financial Aid' tab.
     * @return Student object.
     */
    public Student createPFAStudent() {
        String name = studentPFAName.getText();
        Profile profile = null;
        Major major = null;

        if (name.isEmpty()) {
            textAreaPFA.appendText("\nStudent name has not been added");
        } else {
            if (csPFAButton.isSelected()) {
                major = Major.CS;
                profile = new Profile(name, major);
            } else if (eePFAButton.isSelected()) {
                major = Major.EE;
                profile = new Profile(name, major);
            } else if (mePFAButton.isSelected()) {
                major = Major.ME;
                profile = new Profile(name, major);
            } else if (baPFAButton.isSelected()) {
                major = Major.BA;
                profile = new Profile(name, major);
            } else if (itPFAButton.isSelected()) {
                major = Major.IT;
                profile = new Profile(name, major);
            } else {
                return null;
            }
        }
        return new Student(profile);
    }

    /**
     * Deselects all buttons and clears Text Area in Payments/Financial Tab.
     */
    public void clearPanePFA() {
        deselectAllPFA();
        textAreaPFA.clear();
    }
    /**
     * Applies the input payment to a particular student.
     */
    public void applyPayment() {
        Student student = createPFAStudent();
        if (student == null) {
            textAreaPFA.appendText("\nMissing major input.");
        } else {
            double payment = ZERO;
            if (tuitionDueInput.getText().equals("")) {
                textAreaPFA.appendText("\nPayment has not been added.");
            } else {
                payment = Double.parseDouble(tuitionDueInput.getText());
                if (inputDate.getText().equals("")) {
                    System.out.println("hits");
                    textAreaPFA.appendText("\nPayment date is missing.");
                } else {
                    Date date = new Date(inputDate.getText());
                    Student tmpStudent = new Student(student.getProfile());
                    if (roster.doesExist(tmpStudent)) {
                        student = roster.findStudent(tmpStudent);
                        if (!date.isValid()) {
                            textAreaPFA.appendText("\nPayment date invalid.");
                        } else {
                            student.setDate(date);
                            if (student.getTuition() < payment) {
                                textAreaPFA.appendText("\nAmount is greater than amount due.");
                            } else {
                                student.setPayment(payment);
                                student.setTuitionPaid(payment);
                                textAreaPFA.appendText("\nPayment applied.");
                            }
                        }
                    }
                }
            }
        }
         deselectAllPFA();
    }


}
