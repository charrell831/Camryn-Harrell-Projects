/*************************************************************************
 *  Compilation:  javac HeartTransplant.java
 *  Execution:    java HeartTransplant < data.txt
 *
 *  @author: Camryn Harrell
 *
 *************************************************************************/
/*/*/
public class HeartTransplant {

    /* ------ Instance variables  -------- */

    // Person array, each Person is read from the data file
    private Person[] listOfPatients;

    // SurvivabilityByAge array, each rate is read from data file
    private SurvivabilityByAge[] survivabilityByAge;

    // SurvivabilityByCause array, each rate is read from data file
    private SurvivabilityByCause[] survivabilityByCause;

    /* ------ Constructor  -------- */

    /*
     * Initializes all instance variables to null.
     */
    public HeartTransplant()
    {
      this.listOfPatients = null;
      this.survivabilityByAge = null;
      this.survivabilityByCause = null;
        // WRITE YOUR CODE HERE
    }

    /* ------ Methods  -------- */

    /*
     * Inserts Person p into listOfPatients
     *
     * Returns:  0 if successfully inserts p into array,
     *          -1 if there is not enough space to insert p into array
     */
    public int addPerson(Person p, int arrayIndex)
    {
      if (arrayIndex < listOfPatients.length)
      {
        this.listOfPatients[arrayIndex] = p;
        return 0;
      }
      return -1;
    }

/*
     * 1) Creates the listOfPatients array with numberOfLines length.
     *
     * 2) Reads from the command line data file.
     *    File Format: ID, Ethinicity, Gender, Age, Cause, Urgency, State of health
     *    Each line refers to one Person.
     *
     * 3) Inserts each person from file into listOfPatients
     *    Hint: uses addPerson() method
     *
     * Returns the number of patients read from file
     */
    public int readPersonsFromFile(int numberOfLines)
    {
      this.listOfPatients = new Person[numberOfLines];
      int[] p = new int[7];
      int patientCount = 0;
      for (int i = 0; i < numberOfLines; i++)
      {
        for (int j = 0; j < 7; j++)
        {
          p[j] = StdIn.readInt();
        }
        Person temp = new Person(p[0], p[1], p[2], p[3], p[4], p[5], p[6]);
        if (addPerson(temp, patientCount) == 0)
        {
          patientCount++;
        }
      }
      return patientCount;
    }

    /*
     * 1) Creates the survivabilityByAge array with numberOfLines length.
     *
     * 2) Reads from the command line file.
     *    File Format: Age YearsPostTransplant Rate
     *    Each line refers to one survivability rate by age.
     *
     * 3) Inserts each rate from file into the survivabilityByAge array
     *
     * Returns the number of survivabilities rates read from file
     */
    //
     public int addRate(SurvivabilityByAge a, int arrayIndex)
     {
       if (arrayIndex < survivabilityByAge.length)
       {
         survivabilityByAge[arrayIndex] = a;
         return 0;
       }
       return -1;
     }


    public int readSurvivabilityRateByAgeFromFile (int numberOfLines)
    {
      this.survivabilityByAge = new SurvivabilityByAge[numberOfLines];
      double[] s = new double[3];
      int surviveCount = 0;
      for (int i = 0; i < numberOfLines; i++)
      {
        for (int j = 0; j < 3; j++)
        {
          s[j] = StdIn.readDouble();
        }
        SurvivabilityByAge temp = new SurvivabilityByAge((int)s[0], (int)s[1], s[2]);
        if (addRate(temp, surviveCount) == 0)
        {
          surviveCount++;
        }

      }
      return surviveCount;
    }
    /*
     * 1) Creates the survivabilityByCause array with numberOfLines length.
     *
     * 2) Reads from the command line file.
     *    File Format: Cause YearsPostTransplant Rate
     *    Each line refers to one survivability rate by cause.
     *
     * 3) Inserts each rate from file into the survivabilityByCause array
     *
     * Returns the number of survivabilities rates read from file
     */

     public int addCause(SurvivabilityByCause a, int arrayIndex)
     {
       if (arrayIndex < survivabilityByCause.length)
       {
         survivabilityByCause[arrayIndex] = a;
         return 0;
       }
       return -1;
     }

    public int readSurvivabilityRateByCauseFromFile (int numberOfLines)
    {
      this.survivabilityByCause = new SurvivabilityByCause[numberOfLines];
      double[] c = new double[3];
      int causeCount = 0;
      for (int i = 0; i < numberOfLines; i++)
      {
        for (int j = 0; j < 3; j++)
        {
          c[j] = StdIn.readDouble();
        }
        SurvivabilityByCause temp = new SurvivabilityByCause((int)c[0], (int)c[1], c[2]);
        if (addCause(temp, causeCount) == 0)
        {
          causeCount++;
        }
      }
      return causeCount;
    }
    /*
     * Returns listOfPatients
     */
    public Person[] getListOfPatients()
    {
        return listOfPatients;
    }

    /*
     * Returns survivabilityByAge
     */
    public SurvivabilityByAge[] getSurvivabilityByAge()
    {
        return survivabilityByAge;
    }

    /*
     * Returns survivabilityByCause
     */
    public SurvivabilityByCause[] getSurvivabilityByCause() {
        return survivabilityByCause;
    }

    /*
     * Returns a Person array in which with every Person that has
     * age above the parameter age from the listOfPatients array.
     *
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with age above the parameter age.
     *
     * Return null if there is no Person with age above the
     * parameter age.
     */
     public Person[] getPatientsWithAgeAbove(int age)
     {
       int count = 0;
       for (int i = 0; i < listOfPatients.length; i++)
       {
         if (listOfPatients[i].getAge() > age)
         {
           count++;
         }
       }
       Person[] personAboveAge = new Person[count];
       int temp = 0;
       for (int i = 0; i < listOfPatients.length; i++)
       {
         if (listOfPatients[i].getAge() > age)
         {
           personAboveAge[temp] = listOfPatients[i];
           temp++;
         }
       }
       if (personAboveAge.length == 0 || personAboveAge == null)
       {
         return null;
       }
       return personAboveAge;
     }

    /*
     * Returns a Person array with every Person that has the state of health
     * equal to the parameter state from the listOfPatients array.
     *
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with the state of health equal to the parameter state.
     *
     * Return null if there is no Person with the state of health
     * equal to the parameter state.
     */
    public Person[] getPatientsByStateOfHealth(int state)
    {
      int count = 0;
      for (int i = 0; i < listOfPatients.length; i++)
      {
        if (listOfPatients[i].getStateOfHealth() == state)
        {
          count++;
        }
      }
      Person[] sameState = new Person[count];
      int temp = 0;
      for (int i = 0; i < listOfPatients.length; i++)
      {
        if (listOfPatients[i].getStateOfHealth() == state)
        {
          sameState[temp] = listOfPatients[i];
          temp++;
        }
      }
      if (sameState.length == 0 || sameState == null)
      {
        return null;
      }
      return listOfPatients;
    }
    
    /*
     * Returns a Person array with every person that has the heart
     * condition cause equal to the parameter cause from the listOfPatients array.
     *
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of persons with the heart condition cause equal to the parameter cause.
     *
     * Return null if there is no Person with the heart condition cause
     * equal to the parameter cause.
     */
    public Person[] getPatientsByHeartConditionCause(int cause) {
      int count = 0;
      for (int i = 0; i < listOfPatients.length; i++)
      {
        if (listOfPatients[i].getCause() == cause)
        {
          count++;
        }
      }
      Person[] equalCause = new Person[count];
      int temp = 0;
      for (int i = 0; i < listOfPatients.length; i++)
      {
        if (listOfPatients[i].getCause() == cause)
        {
          equalCause[temp] = listOfPatients[i];
          temp++;
        }
      }

      if (equalCause.length == 0 || equalCause == null)
      {
        return null;
      }
      return equalCause;

     }
    /*
     * Assume there are numberOfHearts available for transplantation surgery.
     * Also assume that the hearts are of the same blood type as the
     * persons on the listOfPatients.
     * This method finds a set of persons to be the recepients of these
     * hearts.
     *
     * The method returns a Person array from the listOfPatients
     * array that have the highest potential for survivability after
     * the transplant. The array size is numberOfHearts.
     *
     * If numberOfHeartsAvailable is greater than listOfPatients
     * array size all Persons will receive a transplant.
     *
     * If numberOfHeartsAvailable is smaller than listOfPatients
     * array size find the set of people with the highest
     * potential for survivability.
     *
     * There is no correct solution, you may come up with any set of
     * persons from the listOfPatients array.
    //  */
    public Person[] match(int numberOfHearts)
    {
      int count = 0;
      if (numberOfHearts >= listOfPatients.length)
      {
        return listOfPatients;
      }
      else
      {
        for (int i = 0; i < survivabilityByCause.length; i++)
        {
          if (survivabilityByCause[i].getRate() > 75.0)
          {
            count++;
          }
        }
      Person[] highPotential = new Person[count];
      int temp = 0;
        for (int i = 0; i < survivabilityByCause.length; i++)
        {
          if (survivabilityByCause[i].getRate() > 75.0)
          {
            highPotential[temp] = listOfPatients[i];
            temp++;
          }
        }
        return highPotential;
      }

    }

    /*
     * Client to test the methods you write
     */
    public static void main (String[] args)
    {
        HeartTransplant ht = new HeartTransplant();
        //read persons from file
        int numberOfLines = StdIn.readInt();
        int numberOfReadings = ht.readPersonsFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " patients read from file.");


        // read survivability by age from file

        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByAgeFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by age lines read from file.");

        // read survivability by heart condition cause from file
        numberOfLines = StdIn.readInt();
        numberOfReadings = ht.readSurvivabilityRateByCauseFromFile(numberOfLines);
        StdOut.println(numberOfReadings + " survivability rates by cause lines read from file.");

      //  list all patients
        for (Person p : ht.getListOfPatients()) {
            StdOut.println(p);
        }

        // list survivability by age rates
        for (SurvivabilityByAge rate : ht.getSurvivabilityByAge()) {
            StdOut.println(rate);
        }

        // // list survivability by cause rates
        for (SurvivabilityByCause rate : ht.getSurvivabilityByCause()) {
            StdOut.println(rate);
        }

    }
}
