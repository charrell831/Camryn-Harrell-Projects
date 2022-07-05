/**
 * Tuition Manager includes the use of Scanner to read and take in inputs while breaking the components up after a comma
 * using String Tokenizer. Scanner class continuously scans until a user wishes to quit the program. This class tells the user
 * information about the students in the roster and reads in commands that a user inputs.
 *
 * @author Varsha Balaji, Camryn Harrell
 */

import java.util.StringTokenizer;
import java.util.Scanner;

public class TuitionManager {

    private final int MINIMUM_CREDIT_HOURS = 3;
    private final int INTERNATIONAL_MIN_CREDIT_HOURS = 12;
    private final int MAX_CREDIT_HOURS = 24;
    private final int ZERO_OR_NEGATIVE_CREDIT_HOURS = 0;
    private int creditHours;
    private State state;
    private boolean isStudyingAbroad;
    private double tuition;
    private double tuitionPayment;
    private boolean endOfInput; //flag to determine whether or not to continue running code with particular input
    private Student student;
    private StudentType studentType;
    private boolean alreadyAwarded = false;


    public void run() {

        System.out.println("Tuition Manager starts running.");
        Scanner scan = new Scanner(System.in); //picks up words until enter
        Roster roster = new Roster();
        while (scan.hasNext()) { //while scan has another token in inputString command = scan.nextLine();
            String command = scan.nextLine();
            StringTokenizer input = new StringTokenizer(command, ",");
            if (!input.hasMoreTokens()) {
                continue;
            }
            String firstCommand = input.nextToken();
            if (firstCommand.equals("Q")) {
                break;
            }
            inputHandler(firstCommand, input, roster);
        }
        scan.close();
        System.out.print("Tuition Manager terminated.");
    }

    private void inputHandler(String firstCommand, StringTokenizer input, Roster roster) {
        if (firstCommand.equals("AR") || firstCommand.equals("AN") || firstCommand.equals("AT") ||
                firstCommand.equals("AI")) {
            createProfile(input, input.countTokens(), firstCommand, roster); //after returns "credit hours missing" will move onto addCommand
            if (endOfInput == true) {
                addCommandManager(firstCommand, student, roster);
            } else {
                return;
            }
        } else if (firstCommand.equals("R")) {
            createProfile(input, input.countTokens(), firstCommand, roster);
            removeCommand(student, roster);
        } else if (firstCommand.equals("C")) {
            calculateCommand(roster);
        } else if (firstCommand.equals("T")) {
            createProfile(input, input.countTokens(), firstCommand, roster);
            if (endOfInput) {
                applyPayment(input);
            }
        } else if (firstCommand.equals("S")) {
            setStudyAbroad(input, roster);
        } else if (firstCommand.equals("F")) {
            setFinancialAid(input, roster);
        } else if (firstCommand.equals("P") || firstCommand.equals("PN") || firstCommand.equals("PT")) {
           // printCommandManager(firstCommand, roster);
        } else if (firstCommand.equals("Q")) {
            System.exit(0);
        } else {
            System.out.print("\nCommand '" + firstCommand + "' not supported!");
        }
    }
    //just use this to create profile object and then based on commands create diff
    //student objects based on qualities needed.

    //based on firstCommand, pass rest of inputs there
    private void createProfile(StringTokenizer input, int inputSize, String firstCommand, Roster roster) {
        //create object
        String name = "";
        Major major = null;
        endOfInput = true;

        if (input.hasMoreTokens()) {
            name = input.nextToken(); //null check
        } else {
            System.out.print("\nMissing data in command line.");
            endOfInput = false;
            return;
        }

        if (input.hasMoreTokens()) {
            String majorInput = input.nextToken();
            major = returnMajor(majorInput);
            if (major == null) {
                System.out.print("\n'" + majorInput + "' is not a valid major.");
                endOfInput = false;
                return;
            }
        } else {
            System.out.print("\nMissing data in command line.");
            endOfInput = false;
            return;
        }

        Profile profile = new Profile(name, major);
        student = new Student(profile);
        if (firstCommand.equals("AR") || firstCommand.equals("AN") || firstCommand.equals("AT") ||
                firstCommand.equals("AI")) {
            createStudentWithCredits(firstCommand, input, profile);
            student.setDate(null);
        }
        if (firstCommand.equals("T")) {
            createStudentWithDate(input, roster);
        }
    }

    public void createStudentWithDate(StringTokenizer input, Roster roster) {
        double tuitionPayment = 0;
        Student findStudent = new Student(student.getProfile());
        student = roster.findStudent(findStudent);
        if (input.hasMoreTokens()) {
            tuitionPayment = Double.parseDouble(input.nextToken());

            if (tuitionPayment <= 0) { //fix, ctrl f 0
                System.out.print("\nInvalid amount.");
                endOfInput = false;
                return;
            }

            if (tuitionPayment > student.getTuition()) {
                System.out.print("\nAmount is greater than amount due.");
                endOfInput = false;
                return;
            }
        } else {
            System.out.print("\nPayment amount missing.");
            endOfInput = false;
            return;
        }

        if (input.hasMoreTokens()) {
            Date date = new Date(input.nextToken());
            if (!date.isValid()) {
                System.out.print("\nPayment date invalid.");
                endOfInput = false;
                return;
            } else {
                student.setDate(date);
            }
        }
        student.setTuitionPaid(tuitionPayment);
        student.setPayment(tuitionPayment);
        System.out.print("\nPayment applied.");
        endOfInput = false;
        return;
    }

    public void createStudentWithCredits(String firstCommand, StringTokenizer input, Profile profile) {
        String creditHoursInput = "";

        if (input.hasMoreTokens()) {
            creditHoursInput = input.nextToken();
            try {
                creditHours = Integer.parseInt(creditHoursInput);
            } catch (NumberFormatException nfe) {
                System.err.print("\nInvalid credit hours."); //something wrong here
                endOfInput = false;
                return;
            }
        } else {
            System.out.print("\nCredit hours missing.");
            endOfInput = false;
            return;
        }

        if (creditHours < ZERO_OR_NEGATIVE_CREDIT_HOURS) {
            System.out.print("\nCredit hours cannot be negative.");
            endOfInput = false;
            return;
        }

        if (creditHours < MINIMUM_CREDIT_HOURS) {
            System.out.print("\nMinimum credit hours is 3.");
            endOfInput = false;
            return;
        }

        if (creditHours > MAX_CREDIT_HOURS) {
            System.out.print("\nCredit hours exceed the maximum 24.");
            endOfInput = false;
            return;
        }

        student = new Student(profile, creditHours);
        internationalOrTriStateStudent(input, firstCommand);
    }

    public void setStudyAbroad(StringTokenizer input, Roster roster) {
        final double FEES = 3268 + 2650;
        String name = input.nextToken();
        String majorInput = input.nextToken();
        Major major = returnMajor(majorInput);
        Profile profile = new Profile(name, major);
        Student student = new Student(profile);
        if (!roster.doesExist(student)) {
            System.out.println("Couldn't find the international student.");
            return;
        } else {
            International interStudent = new International(profile, student.getCreditHours(), true);
            if (interStudent.getCreditHours() > 12) {
                interStudent.setCreditHours(12);
                interStudent.setTuitionPaid(0);
                interStudent.setDate(null);
                interStudent.setTuition(FEES);
            }
        }
    }

    private void internationalOrTriStateStudent(StringTokenizer input, String firstCommand) {
        if (firstCommand.equals("AT")) {
            if (input.hasMoreTokens()) {
                state = returnState(input.nextToken());
                if (state == null) {
                    System.out.print("\nNot part of the tri-state area.");
                    endOfInput = false;
                    return;
                } else {
                    student = new TriState(student.getProfile(), student.getCreditHours(), state);
                }
            } else {
                System.out.print("\nMissing data in command line.");
                endOfInput = false;
                return;
            }

        } else if (firstCommand.equals("AI")) {
            if (student.getCreditHours() < INTERNATIONAL_MIN_CREDIT_HOURS) {
                System.out.print("\nXInternational students must enroll at least 12 credits.");
                endOfInput = false;
                return;
            }
            isStudyingAbroad = false;
            if (input.nextToken().equalsIgnoreCase("false")) {
                student = new International(student.getProfile(), student.getCreditHours(), isStudyingAbroad);
            } else {
                isStudyingAbroad = true;
                student = new International(student.getProfile(), student.getCreditHours(), isStudyingAbroad);
            }
        }
    }

    private void applyPayment(StringTokenizer input) {
        //add in tuition payment somewhere
        if (!student.getDate().isValid()) {
            System.out.print("\nPayment date invalid");
        }
        if (student.getTuition() < tuitionPayment) {
            System.out.print("\nAmount is greater than amount due.");
        } else {
            double tuitionPayment = Double.parseDouble(input.nextToken());
            tuition = student.getTuition();
            tuition -= tuitionPayment;
            System.out.print("\nPayment applied.");
        }
    }

    private void addCommandManager(String firstCommand, Student student, Roster roster) {
        //doesExist isnt working, P works so students are being entered, but it isn't
        //correctly checking for a student that is already there
        if (roster.doesExist(student)) {
            System.out.print("\nStudent is already in the roster.");
            endOfInput = false;
            return;
        }
        if (firstCommand.equals("AR") || firstCommand.equals("AN")) {
            addResidentAndNonResidentCommand(student, roster, firstCommand);
        }

        if (firstCommand.equals("AT")) {
            addTriStateCommand(student, roster, state);
        }

        if (firstCommand.equals("AI")) {
            addInternationalCommand(student, roster, isStudyingAbroad);
        }

    }

    private void calculateCommand(Roster roster) {
        roster.calculateTuitionDue();
        System.out.print("\nCalculation completed.");
        endOfInput = false;
        return;
    }

    private void removeCommand(Student student, Roster roster) {
        if (roster.remove(student)) {
            System.out.print("\nStudent removed from the roster.");
        } else {
            System.out.print("\nStudent is not in the roster.");
            endOfInput = false;
            return;
        }
    }

//    private void printCommandManager(String firstCommand, Roster roster) {
//        if (roster.isEmpty()) {
//            System.out.print("\nStudent roster is empty!");
//            endOfInput = false;
//            return;
//        }
//        if (firstCommand.equals("P")) {
//            roster.print();
//        }
//        if (firstCommand.equals("PN")) {
//            roster.printByName(studentType, state);
//        }
//        if (firstCommand.equals("PT")) {
//            roster.printByPaymentDate(studentType, state);
//        }
//    }

    private void addInternationalCommand(Student student, Roster roster, boolean isStudyingAbroad) {
        International internationalStudent = new International(student.getProfile(), student.getCreditHours(), isStudyingAbroad);
        internationalStudent.setStudentType(StudentType.INTERNATIONAL);
        if (roster.doesExist(student)) {
            System.out.println("\nStudent is already in the roster.");
            endOfInput = false;
            return;
        } else {
            roster.add(internationalStudent);
            System.out.print("\nStudent added.");
        }
    }


    private void addTriStateCommand(Student student, Roster roster, State state) {
        TriState triStateStudent = new TriState(student.getProfile(), student.getCreditHours(), state);
        triStateStudent.setStudentType(StudentType.TRISTATE);
        if (roster.doesExist(student)) {
            System.out.println("\nStudent is already in the roster.");
            endOfInput = false;
            return;
        } else {
            roster.add(triStateStudent);
            System.out.print("\nStudent added.");
        }
    }

    private void addResidentAndNonResidentCommand(Student student, Roster roster, String firstCommand) {
        //TODO: Figure out International and TriState commands
        if (firstCommand.equals("AR")) {
            Resident resident = new Resident(student.getProfile(), student.getCreditHours());
            resident.setStudentType(StudentType.RESIDENT);
            if (roster.doesExist(student)) { //NOTE: when a student is added, they are NOT being replaced from what i see
                System.out.println("\nStudent is already in the roster.");
                endOfInput = false;
                return;
            } else {
                roster.add(resident);
                System.out.print("\nStudent added.");
            }
        }

        if (firstCommand.equals("AN")) {
            NonResident nonResident = new NonResident(student.getProfile(), student.getCreditHours());
            nonResident.setStudentType(StudentType.NONRESIDENT);
            if (roster.doesExist(student)) {
                System.out.println("Student is already in the roster.");
            } else {
                roster.add(nonResident);
                System.out.print("\nStudent added.");
            }
        }
    }

    public void setFinancialAid(StringTokenizer input, Roster roster) {
        final double MAX_FINANCIAL_AID = 10000.0;
        final double MINIMUM = 0;
        String name = input.nextToken();
        String majorInput = input.nextToken();
        Major major = returnMajor(majorInput);
        Profile profile = new Profile(name, major);
        Student student = new Student(profile);

        if (!input.hasMoreTokens()) {
            System.out.println("Missing the amount.");
            return;
        } else {
            if (!roster.doesExist(student)) {
                System.out.println("Student not in the roster.");
                return;
            } else {
                Student newStudent = roster.findStudent(student);
                if (!newStudent.isFullTime(newStudent.getCreditHours())) {
                    System.out.println("Parttime student doesn't qualify for the award.");
                    return;
                } else {
                    if (!newStudent.getStudentType().equals(studentType.RESIDENT)) {
                        System.out.println("Not a resident student.");
                        return;
                    } else {
                        double financialAid = Double.parseDouble(input.nextToken());
                        if (financialAid < MINIMUM || financialAid > MAX_FINANCIAL_AID) {
                            System.out.println("Invalid amount.");
                            return;
                        } else {
                            Resident residentStudent = new Resident(profile, newStudent.getCreditHours());
                            if (alreadyAwarded == true) {
                                System.out.println("Awarded once already.");
                                return;
                            } else {
                                alreadyAwarded = true;
                                newStudent.setTuition(newStudent.getTuition() - financialAid);
                                residentStudent.setFinancialAidAmount(financialAid);
                                System.out.println("Tuition updated.");
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private State returnState(String state) {
        if (state.equalsIgnoreCase("NY")) {
            return State.NY;
        }
        if (state.equalsIgnoreCase("CT")) {
            return State.CT;
        }
        return null;
    }

    // CS, IT, BA, EE, ME;
    private Major returnMajor(String major) {
        if (major.equalsIgnoreCase("CS")) {
            return Major.CS;
        }
        if (major.equalsIgnoreCase("IT")) {
            return Major.IT;
        }
        if (major.equalsIgnoreCase("BA")) {
            return Major.BA;
        }
        if (major.equalsIgnoreCase("EE")) {
            return Major.EE;
        }
        if (major.equalsIgnoreCase("ME")) {
            return Major.ME;
        }
        return null;
    }

}


