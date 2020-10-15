class Tutor {
  private String name;
  private String subject;
  private double rating;
  public Tutor (String name, String subject, double rating)
  {
    this.name = name;
    this.subject = subject;
    this.rating = rating;
  }
  public String getName()
  {
    return name;
  }
  public String getSubject()
  {
    return subject;
  }
  public double getRating()
  {
    return rating;
  }
  public String toString()
  {
    return name + ", " + subject + ", " + rating;
  }
}
class Match {
  private Tutor[] matches;
  public Match (int numTutors)
  {
    matches = new Tutor[numTutors];
  }
  public void addTutor(String name, String subject, double rating, int index)
  {
    if (index < matches.length)
    {
      matches[index] = new Tutor (name, subject, rating);
    }
  }
  public void addMoreTutors(String name, String subject, double rating)
  {
    boolean flag = false;
    int len = matches.length;
    for (int i = 0; i < matches.length; i++)
    {
      if (matches[i].getName().equals(name) && matches[i].getSubject().equals(subject))
      {
        flag = true;
      }
    }

    if (flag == false)
    {
      Tutor[] addOns = new Tutor[len];
      for (int i = 0; i < addOns.length; i++)
      {
        addOns[i] = matches[i];
      }
      matches = new Tutor[len+1];
      for (int i = 0; i < addOns.length; i++)
      {
        matches[i] = addOns[i];
      }
      matches[len] = new Tutor(name, subject, rating);
    }
  }
  public Tutor[] subjectMatch (String subject)
  {
    int count = 0;
    for (int i = 0; i < matches.length; i++)
    {
      if (matches[i].getSubject().equals(subject))
      {
        count++;
      }
    }
    Tutor[] subMatch = new Tutor[count];
    int temp = 0;
    for (int i = 0; i < matches.length; i++)
    {
      if (matches[i].getSubject().equals(subject))
      {
        subMatch[temp] = matches[i];
        temp++;
      }
    }
    return subMatch;
  }
  public Tutor highPer()
  {
    double large = 0;
    //Tutor[] per = new Tutor[matches.length];
    //Tutor per = new Tutor ("Som", "Som", 0);
    for (int i = 0; i < matches.length; i++)
    {
      if (matches[i].getRating() > large)
      {
        large = matches[i].getRating();
      }
    }
    for (int i = 0; i < matches.length; i++)
    {
      if (matches[i].getRating() == large)
      {
        return matches[i];
      }
    }
    return null;
  }
  public Tutor perfect(String subject)
  {
    int count = 0;
    for (int i = 0; i < matches.length; i++)
    {
      if (matches[i].getSubject().equals(subject))
      {
        count++;
      }
    }
    Tutor[] subMatch = new Tutor[count];
    int temp = 0;
    for (int i = 0; i < matches.length; i++)
    {
      if (matches[i].getSubject().equals(subject))
      {
        subMatch[temp] = matches[i];
        temp++;
      }
    }
    double large = 0;
    for (int i = 0; i < subMatch.length; i++)
    {
      if (subMatch[i].getRating() > large)
      {
        large = subMatch[i].getRating();
      }
    }
    for (int i = 0; i < subMatch.length; i++)
    {
      if (subMatch[i].getRating() == large)
      {
        return subMatch[i];
      }
    }
    return null;
  }
  public void printAll()
  {
    for (int i = 0; i < matches.length; i++)
    {
      StdOut.println(matches[i].toString());
    }
  }
}

  class Test {
    public static void main(String[] args)
    {
      Google g1 = new Google(8);
      g1.addArticles("Coffee hacks", 0);
      g1.addArticles("Colorblindness in dogs", 1);
      g1.addArticles("The color of trees", 2);
      g1.addArticles("How Disney grew into an empire", 3);
      g1.addArticles("Who is the biggest Disney star", 4);
      g1.addArticles("How Disney bought the Avengers", 5);
      g1.addArticles("Nickelodeon vs Disney", 6);
      g1.addArticles("The best President", 7);
      for (Articles i : g1.searchBar("Disney"))
      {
        StdOut.println(i);
      }
      Match m1 = new Match(3);
      m1.addTutor("Mark", "Math", 3.2, 0);
      m1.addTutor("Yolanda", "Art", 4.2, 1);
      m1.addTutor("Sadie", "Math", 2.5, 2);
      m1.addMoreTutors("Lauren", "English", 5.0);
      // for (Tutor i : m1.subjectMatch("Math"))
      // {
      //   StdOut.println(i);
      // }
      StdOut.println();
      m1.printAll();
      // StdOut.println(m1.highPer());
      // StdOut.println(m1.perfect("Math"));
}
}
